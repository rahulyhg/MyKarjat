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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        share=getSharedPreferences(Prefs.StoreKey,Context.MODE_PRIVATE);
       // Toast.makeText(this, share.getString(Prefs.name,"no"), Toast.LENGTH_SHORT).show();
       // ed=share.edit();
       // ed.putString(Prefs.name,"Harshad").apply();

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
