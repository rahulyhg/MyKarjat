package harshad.mykarjat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
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

    //auth start
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private Button btnMailId,btnSignOut,btnDisconnect;
    SignInButton btnSignIn;
    //auth end



    //original code
    String username="";
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
    LinearLayout llInsideScrollview;

    WebView wvChat;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);
        dbRef= FirebaseDatabase.getInstance().getReference();

        wvChat=(WebView)findViewById(R.id.wvChat);
        wvChat.setWebViewClient(new WebViewClient());
        wvChat.getSettings().setJavaScriptEnabled(true);

        wvChat.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvChat.getSettings().setDomStorageEnabled(true);

        ConnectivityManager conn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=conn.getActiveNetworkInfo();
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        //check internet connection here
        if(nf!=null && nf.isConnected()){
            Query adChatStatus=dbRef.child("Advertisements").child("adChat");
            adChatStatus.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    fbase fb=dataSnapshot.getValue(fbase.class);
                    if(fb.getStatus().equals("yes")) {
                        wvChat.loadUrl(fb.getUrl());
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //wvChat.loadUrl("https://wwwkarjatonlinecom.000webhostapp.com/adchat.html");
        }
        else {
            Snackbar sb=Snackbar.make(this.findViewById(R.id.activity_chat),"Internet not available !",Snackbar.LENGTH_SHORT);
            sb.show();
            btnSendChat.setEnabled(false);
            etChat.setEnabled(false);
            wvChat.loadUrl("file:///android_asset/ad.html");
        }

        //auth code start
        // Views
        mStatusTextView = findViewById(R.id.status);
        mDetailTextView = findViewById(R.id.detail);
        btnMailId=findViewById(R.id.btnMailId);
        btnSignIn=findViewById(R.id.sign_in_button);
        btnSignOut=findViewById(R.id.sign_out_button);
        btnDisconnect=findViewById(R.id.disconnect_button);

        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revokeAccess();
            }
        });

        //auth code end


        share=getSharedPreferences(Prefs.StoreKey,Context.MODE_PRIVATE);
       // Toast.makeText(this, share.getString(Prefs.name,"no"), Toast.LENGTH_SHORT).show();
       // ed=share.edit();
       // ed.putString(Prefs.name,"Harshad").apply();



//        tvChat=(TextView)findViewById(R.id.tvChat);

        llInsideScrollview=findViewById(R.id.llInsideScrollview);
        btnSendChat= (Button)findViewById(R.id.btnSendChat);
        etChat=(EditText)findViewById(R.id.etChat);

        svChat=(ScrollView)findViewById(R.id.svChat);

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
                    chatstr = chatstr  + fb.getChatmsg()+ "\n\n";
                //    tvChat.setText(chatstr);
                    Log.d("chatwindow",chatstr);
                    ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    p.setMargins(0,20,0,0);

                    LinearLayout ll=new LinearLayout(ChatActivity.this);
                    ll.setOrientation(LinearLayout.VERTICAL);
                    ll.setLayoutParams(p);
                    ll.setPadding(5,5,5,5);
                    ll.setBackgroundResource(R.drawable.cardborder);

                    TextView tvUsername=new TextView(ChatActivity.this);
                    tvUsername.setText(fb.getChatusername());

                    tvUsername.setTextSize(13);
                    tvUsername.setTextColor(Color.rgb(0,150,0));
                    tvUsername.setLayoutParams(params);
                    Log.d("chatact",fb.getChatusername());

                    TextView tvChatMsg=new TextView(ChatActivity.this);
                    tvChatMsg.setText(fb.getChatmsg());
                    tvChatMsg.setTextSize(15);
                    tvChatMsg.setTextColor(Color.BLACK);
                    tvChatMsg.setLayoutParams(params);
                    Log.d("chatact",fb.getChatmsg());

                    TextView tvChatTime=new TextView(ChatActivity.this);
                    tvChatTime.setText(fb.getChattime());
                    tvChatTime.setTextSize(11);
                    tvChatTime.setLayoutParams(params);
                    tvChatTime.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
//                    Log.d("chatact",fb.getChattime());

                    ll.addView(tvUsername);
                    ll.addView(tvChatMsg);
                    ll.addView(tvChatTime);

                    llInsideScrollview.addView(ll);

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
                    chatstr = chatstr + fb.getChatmsg()+"\n\n";
              //      tvChat.setText(chatstr);
                    Log.d("chatwindow",chatstr);

                    //adding views 17may18
                    ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    p.setMargins(0,20,0,0);

                    LinearLayout ll=new LinearLayout(ChatActivity.this);
                    ll.setOrientation(LinearLayout.VERTICAL);
                    ll.setLayoutParams(p);
                    ll.setPadding(5,5,5,5);
                    ll.setBackgroundResource(R.drawable.cardborder);

                    TextView tvUsername=new TextView(ChatActivity.this);
                    tvUsername.setText(fb.getChatusername());

                    tvUsername.setTextSize(13);
                    tvUsername.setTextColor(Color.rgb(0,150,0));
                    tvUsername.setLayoutParams(params);
                    Log.d("chatact",fb.getChatusername());

                    TextView tvChatMsg=new TextView(ChatActivity.this);
                    tvChatMsg.setText(fb.getChatmsg());
                    tvChatMsg.setTextSize(15);
                    tvChatMsg.setTextColor(Color.BLACK);
                    tvChatMsg.setLayoutParams(params);
                    Log.d("chatact",fb.getChatmsg());

                    TextView tvChatTime=new TextView(ChatActivity.this);
                    tvChatTime.setText(fb.getChattime());
                    tvChatTime.setTextSize(11);
                    tvChatTime.setLayoutParams(params);
                    tvChatTime.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
//                    Log.d("chatact",fb.getChattime());

                    ll.addView(tvUsername);
                    ll.addView(tvChatMsg);
                    ll.addView(tvChatTime);

                    llInsideScrollview.addView(ll);

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
                    f.setChatusername(username);
                    f.setChatmsg(etChat.getText().toString());
                    f.setChattime(time);
                    firebase.child("chat").child(date).push().setValue(f);
                    etChat.setText("");
                }
            }
        });

    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [END on_start_check_user]

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        //showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
      //                  hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signin]

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
    //    hideProgressDialog();
        if (user != null) {
            mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
//            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()+"\n"+user.getDisplayName()+"\n"+
//            user.getPhoneNumber()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getDisplayName()));
            getSupportActionBar().setTitle("Signed in as : "+user.getDisplayName());
            btnMailId.setText(user.getEmail());
            username=user.getDisplayName();

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
            findViewById(R.id.llChat).setVisibility(View.VISIBLE);
            wvChat.setVisibility(View.GONE);
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);
            getSupportActionBar().setTitle("Chat Login");

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
            findViewById(R.id.llChat).setVisibility(View.GONE);
            wvChat.setVisibility(View.VISIBLE);
        }
    }


}
