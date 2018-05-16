package harshad.mykarjat;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class registerBusiness extends AppCompatActivity {
    WebView wvregBusiness;
    private AdView mAdView,mAdView1;
    private InterstitialAd mInterstitialAd;

    EditText etBName,etOName,etContact,etAddress,etRBLUsername,etRBLPassword,etRBUsername,etRBPassword,etRBKeywords;
    Button btnSubmit,btnRBLLogin;
    Button btnRBLogin,btnRBSignup;
    String dburl="https://fbasenotification-e419f.firebaseio.com/";
    Firebase firebase;
    DatabaseReference dbRef;
    int page=0;
    int submitEdit=0;
    String key="";
    LinearLayout llRBLoginSignup,llRBLogin;
    RelativeLayout rlRBRegister;
    int i=0;
    int flag=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business);

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();

        Log.d("rgbus",""+submitEdit+" "+page);

        btnRBLogin=findViewById(R.id.btnRBLogin);
        btnRBSignup=findViewById(R.id.btnRBSignup);

        llRBLoginSignup=findViewById(R.id.llRBLoginSignup);
        llRBLogin=findViewById(R.id.llRBLogin);
        rlRBRegister=findViewById(R.id.rlRBRegister);

        etRBLUsername=findViewById(R.id.etRBLUsername);
        etRBLPassword=findViewById(R.id.etRBLPassword);
        etRBUsername=findViewById(R.id.etRBUsername);
        etRBPassword=findViewById(R.id.etRBPassword);
        etRBKeywords=findViewById(R.id.etRBKeywords);
        etBName=(EditText)findViewById(R.id.etBName);
        etOName=(EditText)findViewById(R.id.etOName);
        etAddress=(EditText)findViewById(R.id.etAddress);
        etContact=(EditText)findViewById(R.id.etContact);

        btnRBLLogin=findViewById(R.id.btnRBLLogin);
        btnSubmit=(Button) findViewById(R.id.btnSubmit);

        ConnectivityManager conn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=conn.getActiveNetworkInfo();

        if(nf!=null && nf.isConnected()){
        }
        else {
            Snackbar sb=Snackbar.make(this.findViewById(R.id.activity_register_business),"Internet not available !",Snackbar.LENGTH_SHORT);
            sb.show();
            btnRBLogin.setEnabled(false);
            btnRBSignup.setEnabled(false);
        }


        btnRBLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llRBLogin.setVisibility(View.VISIBLE);
                llRBLoginSignup.setVisibility(View.GONE);
                rlRBRegister.setVisibility(View.GONE);

                etRBLUsername.setText("");
                etRBLPassword.setText("");

                page++;
                submitEdit=1;
                Log.d("rgbus",""+submitEdit+" "+page);
            }
        });
        btnRBSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitEdit=0;
                llRBLogin.setVisibility(View.GONE);
                llRBLoginSignup.setVisibility(View.GONE);
                rlRBRegister.setVisibility(View.VISIBLE);

                etRBUsername.setText("");
                etRBPassword.setText("");
                etRBKeywords.setText("");
                etBName.setText("");
                etOName.setText("");
                etAddress.setText("");
                etContact.setText("");

                page++;
                Log.d("rgbus",""+submitEdit+" "+page);
            }
        });



        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        //my admob app id=        ca-app-pub-4170269433133188~7483411963
        MobileAds.initialize(this, String.valueOf(R.string.admobapptestid));
        mAdView= (AdView) findViewById(R.id.adView);
        mAdView1= (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView1.loadAd(adRequest);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=0;
                Query q=dbRef.child("business");

                if (etRBUsername.getText().toString().isEmpty())
                    etRBUsername.setError("Username");
                else
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data:dataSnapshot.getChildren()){
                            fbase fbb=data.getValue(fbase.class);
                            if(submitEdit==0) {
                                if (etRBUsername.getText().toString().equals(fbb.getUsername())) {
                                    i = 1;
                                    Log.d("rgbus", "i=" + i);
                                }
                            }
                            else {
                                if(!data.getKey().equals(key)) {
                                    if (etRBUsername.getText().toString().equals(fbb.getUsername())) {
                                        i = 1;
                                        Log.d("rgbus", "i=" + i);
                                    }
                                }
                            }
                        }

                        fbase fb=new fbase();
                        if(i==0) {
                            if (submitEdit == 0) {
//                                if (etRBUsername.getText().toString().isEmpty())
//                                    etRBUsername.setError("Username");
                                if (etRBPassword.getText().toString().isEmpty())
                                    etRBPassword.setError("Password");
                                else if (etBName.getText().toString().isEmpty())
                                    etBName.setError("Business/Shop Name");
                                else if (etOName.getText().toString().isEmpty())
                                    etOName.setError("Name");
                                else if (etAddress.getText().toString().isEmpty())
                                    etAddress.setError("Address");
                                else if (etContact.getText().toString().isEmpty())
                                    etContact.setError("Phone");
                                else {
                                    fb.setUsername(etRBUsername.getText().toString());
                                    fb.setPassword(etRBPassword.getText().toString());
                                    fb.setShopname(etBName.getText().toString());
                                    fb.setName(etOName.getText().toString());
                                    fb.setAddress(etAddress.getText().toString());
                                    fb.setPhone(etContact.getText().toString());
                                    fb.setKeywords(etRBKeywords.getText().toString());

                                    firebase.child("business").push().setValue(fb);
                                    Snackbar.make(getCurrentFocus(), "Registered Successfully !", Snackbar.LENGTH_SHORT).show();
                                    onBackPressed();
                                }
                            } else {
                                //if (etRBUsername.getText().toString().isEmpty())
                                  //  etRBUsername.setError("Username");
                                 if (etRBPassword.getText().toString().isEmpty())
                                    etRBPassword.setError("Password");
                                else if (etBName.getText().toString().isEmpty())
                                    etBName.setError("Business/Shop Name");
                                else if (etOName.getText().toString().isEmpty())
                                    etOName.setError("Name");
                                else if (etAddress.getText().toString().isEmpty())
                                    etAddress.setError("Address");
                                else if (etContact.getText().toString().isEmpty())
                                    etContact.setError("Phone");
                                else {
                                    Map<String, Object> taskMap = new HashMap<String, Object>();
                                    taskMap.put("username", etRBUsername.getText().toString());
                                    taskMap.put("password", etRBPassword.getText().toString());
                                    taskMap.put("shopname", etBName.getText().toString());
                                    taskMap.put("name", etOName.getText().toString());
                                    taskMap.put("address", etAddress.getText().toString());
                                    taskMap.put("phone", etContact.getText().toString());
                                    taskMap.put("keywords", etRBKeywords.getText().toString());
                                    firebase.child("business").child(key).updateChildren(taskMap);
                                    Snackbar.make(getCurrentFocus(), "Updated Successfully !", Snackbar.LENGTH_SHORT).show();
                                    onBackPressed();
                                }
                            }
                        }
                        else{
                            Snackbar.make(getCurrentFocus(), "Username not available !", Snackbar.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //fbase fb=new fbase();




               // page--;
                Log.d("rgbus",""+submitEdit+" "+page);

            }
        });

        btnRBLLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                Log.d("rgbus",""+submitEdit+" "+page);
                Query q=dbRef.child("business");
                if(!etRBLUsername.getText().toString().isEmpty() && !etRBLPassword.getText().toString().isEmpty()) {
                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                fbase fb = data.getValue(fbase.class);
                                if (fb.getPassword().equals(etRBLPassword.getText().toString())
                                        && fb.getUsername().equals(etRBLUsername.getText().toString())) {
                                    flag = 1;
                                    key=data.getKey();
                                    llRBLogin.setVisibility(View.GONE);
                                    llRBLoginSignup.setVisibility(View.GONE);
                                    rlRBRegister.setVisibility(View.VISIBLE);

                                    etRBUsername.setText(fb.getUsername());
                                    etRBPassword.setText(fb.getPassword());
                                    etBName.setText(fb.getShopname());
                                    etOName.setText(fb.getName());
                                    etAddress.setText(fb.getAddress());
                                    etContact.setText(fb.getPhone());
                                    etRBKeywords.setText(fb.getKeywords());
                                }
                            }

//                                    Toast.makeText(registerBusiness.this, "Login", Toast.LENGTH_SHORT).show();
                                if(flag==1){
                                    try {
                                        Snackbar.make(getCurrentFocus(), "Login Successful !", Snackbar.LENGTH_SHORT).show();
                                    }
                                    catch (Exception e){

                                    }
                                }
                                else {
                                    try {
                                        Snackbar.make(getCurrentFocus(), "Invalid Username/Password !", Snackbar.LENGTH_SHORT).show();
                                    }
                                    catch (Exception e){

                                    }
                                }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    etRBLUsername.setError("Enter Username");
                    etRBLPassword.setError("Enter Password");
                }

            }
        });

        wvregBusiness=(WebView)findViewById(R.id.wvregisterbusiness);
        wvregBusiness.setWebViewClient(new WebViewClient());
        wvregBusiness.getSettings().setJavaScriptEnabled(true);

        wvregBusiness.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvregBusiness.getSettings().setDomStorageEnabled(true);
        wvregBusiness.loadUrl("file:///android_asset/addurbusiness.html");
       // wvregBusiness.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSfZ08XET147LMHunxWMkR4CYCfjaXMxLq8XIM4gSZGGssRRyA/viewform");
    }

    @Override
    public void onBackPressed() {

        if(page>0){
            page--;
            llRBLogin.setVisibility(View.GONE);
            llRBLoginSignup.setVisibility(View.VISIBLE);
            rlRBRegister.setVisibility(View.GONE);
        }
        else
            super.onBackPressed();
        Log.d("rgbus",""+submitEdit+" "+page);
    }
}
