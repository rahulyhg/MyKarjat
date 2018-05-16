package harshad.mykarjat;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ChatActivity extends AppCompatActivity {

    TextView tvChat;
    String dburl="https://fbasenotification-e419f.firebaseio.com/";
    Firebase firebase;
    DatabaseReference dbRef;
    Button btnSendChat;
    EditText etChat;
    Calendar calendar;
    String date="",chatstr="",time="",month="";

    ScrollView svChat;
    SharedPreferences share;
    SharedPreferences.Editor ed;

    //auth code till oncreate
    private static final String TAG = "PhoneAuthActivity";

    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_CODE_SENT = 2;
    private static final int STATE_VERIFY_FAILED = 3;
    private static final int STATE_VERIFY_SUCCESS = 4;
    private static final int STATE_SIGNIN_FAILED = 5;
    private static final int STATE_SIGNIN_SUCCESS = 6;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);

        // [START_EXCLUDE]
        if (mVerificationInProgress && validatePhoneNumber()) {
            startPhoneNumberVerification(mPhoneNumberField.getText().toString());
        }
        // [END_EXCLUDE]
    }
    // [END on_start_check_user]

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
    }


    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

        mVerificationInProgress = true;
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }

    // [START resend_verification]
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }
    // [END resend_verification]

    // [START sign_in_with_phone]
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // [START_EXCLUDE]
                            updateUI(STATE_SIGNIN_SUCCESS, user);
                            // [END_EXCLUDE]
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                // [START_EXCLUDE silent]
                                mVerificationField.setError("Invalid code.");
                                // [END_EXCLUDE]
                            }
                            // [START_EXCLUDE silent]
                            // Update UI
                            updateUI(STATE_SIGNIN_FAILED);
                            // [END_EXCLUDE]
                        }
                    }
                });
    }
    // [END sign_in_with_phone]

    private void signOut() {
        mAuth.signOut();
        updateUI(STATE_INITIALIZED);
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = mPhoneNumberField.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberField.setError("Invalid phone number.");
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //auth code

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("mr");
        // [END initialize_auth]

        // Initialize phone auth callbacks
        // [START phone_auth_callbacks]
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted: " + credential);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                // [START_EXCLUDE silent]
                // Update the UI and attempt sign in with the phone credential
               // updateUI(STATE_VERIFY_SUCCESS, credential);
                // [END_EXCLUDE]
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                    mPhoneNumberField.setError("Invalid phone number.");
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }

                // Show a message and update the UI
                // [START_EXCLUDE]
                updateUI(STATE_VERIFY_FAILED);
                // [END_EXCLUDE]
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // [START_EXCLUDE]
                // Update UI
                updateUI(STATE_CODE_SENT);
                // [END_EXCLUDE]
            }
        };
        // [END phone_auth_callbacks]

        //auth code end


        share=getSharedPreferences(Prefs.StoreKey,Context.MODE_PRIVATE);
        Toast.makeText(this, share.getString(Prefs.name,"no"), Toast.LENGTH_SHORT).show();
        ed=share.edit();
        ed.putString(Prefs.name,"Harshad").apply();

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);
        dbRef= FirebaseDatabase.getInstance().getReference();

        tvChat=(TextView)findViewById(R.id.tvChat);
        btnSendChat=(Button)findViewById(R.id.btnSendChat);
        etChat=(EditText)findViewById(R.id.etChat);

        svChat=(ScrollView)findViewById(R.id.svChat);

        calendar=Calendar.getInstance();


        ConnectivityManager conn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=conn.getActiveNetworkInfo();
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        //check internet connection here
        if(nf!=null && nf.isConnected()){
        }
        else {
            Snackbar sb=Snackbar.make(this.findViewById(R.id.activity_chat),"Internet not available !",Snackbar.LENGTH_SHORT);
            sb.show();
            btnSendChat.setEnabled(false);
            etChat.setEnabled(false);
        }

        switch(calendar.get(Calendar.MONTH)){
            case 0:
                month="January";
                break;
            case 1:
                month="February";
                break;
            case 2:
                month="March";
                break;
            case 3:
                month="April";
                break;
            case 4:
                month="May";
                break;
            case 5:
                month="June";
                break;
            case 6:
                month="July";
                break;
            case 7:
                month="August";
                break;
            case 8:
                month="September";
                break;
            case 9:
                month="October";
                break;
            case 10:
                 month="November";
                break;
            case 11:
                month="December";
                break;
        }

        date=calendar.get(Calendar.DATE)+"-"+month+"-"+calendar.get(Calendar.YEAR);


        etChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svChat.fullScroll(View.FOCUS_DOWN);
                new CountDownTimer(500,100){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
//                        Toast.makeText(ChatActivity.this, "test", Toast.LENGTH_SHORT).show();
                        svChat.fullScroll(View.FOCUS_DOWN);
                    }
                }.start();
            }
        });

        Query qload=dbRef.child("chat").child(date);
        qload.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    fbase fb = data.getValue(fbase.class);
                    chatstr = chatstr  + fb.getChat()+ "\n\n";
                    tvChat.setText(chatstr);
                    Log.d("chatwindow",chatstr);
                }
                svChat.fullScroll(View.FOCUS_DOWN);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query qreal=dbRef.child("chat").child(date).limitToLast(1);
        qreal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    fbase fb = data.getValue(fbase.class);
                    chatstr = chatstr + fb.getChat()+"\n\n";
                    tvChat.setText(chatstr);
                    Log.d("chatwindow",chatstr);
                }
                svChat.fullScroll(View.FOCUS_DOWN);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnSendChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etChat.getText().toString().isEmpty()){
                    calendar=Calendar.getInstance();
                    switch(calendar.get(Calendar.MONTH)){
                        case 0:
                            month="January";
                            break;
                        case 1:
                            month="February";
                            break;
                        case 2:
                            month="March";
                            break;
                        case 3:
                            month="April";
                            break;
                        case 4:
                            month="May";
                            break;
                        case 5:
                            month="June";
                            break;
                        case 6:
                            month="July";
                            break;
                        case 7:
                            month="August";
                            break;
                        case 8:
                            month="September";
                            break;
                        case 9:
                            month="October";
                            break;
                        case 10:
                            month="November";
                            break;
                        case 11:
                            month="December";
                            break;
                    }
                    date=calendar.get(Calendar.DATE)+"-"+month+"-"+calendar.get(Calendar.YEAR);
                    time=calendar.getTime().getHours()+":"+calendar.getTime().getMinutes()+":"+calendar.getTime().getSeconds();
                    fbase f=new fbase();
                    f.setChat(etChat.getText().toString()+"\n"+time);
                    firebase.child("chat").child(date).push().setValue(f);
                    etChat.setText("");
                }
            }
        });

    }
}
