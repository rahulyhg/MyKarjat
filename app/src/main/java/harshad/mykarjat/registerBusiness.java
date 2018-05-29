package harshad.mykarjat;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
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

    ArrayAdapter<String> adpType, adpSubtype;
    String[] mainType={"Select main category","Health","Education","Classes","Wedding","Services","Suppliers","Entertainment","Rented Services","Food","Holidays",
            "Shops","Daily Needs","Professionals","Emergency","Construction","Beauty","Agents"};
    String[] subHealth={"Select sub-category","Doctors","Medical/Chemist","Pathology","Blood Bank","Ambulance","X-ray & Sonography","Vet.Doctors","MRI/CT Scan","Govt.Hospitals"};
    String[] subEducation={"Select sub-category","Preschool","School","ITI","Junior College","Senior College","Diploma","Engineering","Pharmacy","School Bus"};
    String[] subClasses={"Select sub-category","Music","Acting","Karate","Typing","Yoga","Computer","Singing","Dance","Primary","Secondary","SSC","HSC","Diploma","Engineering","Commerce"};
    String[] subWedding={"Select sub-category","Brahman","Pooja Sahitya","Hall","Mandap","Caterers","Feta","Horse","Music Band"};
    String[] subServices={"Select sub-category","Carpenter","Plumber","Painter","Water Filter","Electrician","Welder","Gavandi","Inverters","Refrigeration","LPG/Stove","Mobile","Computers","Software","Key Maker","Laundry","Dry Cleaning","Tank Cleaning","Internet","Cable TV","Drivers","Borewell","Cyber","Maid Servants","Mess/Tiffin","Name Plate","Rubber Stamp","Waterproofing","Solar Products","Towing"};
    String[] subSuppliers={"Select sub-category","Water","Building Material","News Paper","Vegetables","Fruits","Meat","Fish","Egg","Soil","Ice","Flowers","Labour"};
    String[] subEntertainment={"Select sub-category","Theatre","Multiplex","Event Mgmt","Orchestra","Dancers","Cable N/W","Artists","Clubs"};
    String[] subRentedServices={"Select sub-category","Drivers","Cars","Tempo/Truck","Bus","Dumper","JCB","Costumes","Parking","Paying Guest","Tractor","Crane"};
    String[] subFood={"Select sub-category","Snack Corner","Juice Center","Veg Restaurants","Non-Veg Restarurants","Bakery","Cakes","Ice-Creams","Cafe","Sweetmarts","Khanaval","Caterers","Dairy Products",
            "Chinese","Dhaba","Tea Corner"};
    String[] subHolidays={"Select sub-category","Resorts","Farm Houses","Bunglows","Lodges","Hotels"};
    String[] subShops={"Select sub-category","Mobile","Xerox","Men Cloths","Women Cloths","Photo Studio","Cyber Cafe","Footware","Hardware","Steel Utensils","Bag House","Furniture","Spare Parts",
            "Sports Material","Stationary","Books","Opticals","Electronics","Plywood","Gift Shop","Music Instruments","Pet Shop"};
    String[] subDailyNeeds={"Select sub-category",  "Supermarkets","Kirana","Vegetables","Fruits","Dairy","Fish","Meat","Bakery","Sweetmart","Juice Center","Ice-Cream"};
    String[] subProfessionals={"Select sub-category","Android Developer","Software Developers","Website Designer","Chartered Accountants","Advocates","Photographers","Security","Gym Intructor","Journalist","Interior Designer","PUC","Courier","Pest Control","Architect","Vastu Shastra","Dietician","Astrologer","Printing"};
    String[] subEmergency={"Select sub-category","Police","Fire Brigade","Ambulance","Snake Friends","Towing","Gas Agency","Petrol Pump","Rescuers","Parking"};
    String[] subGovtOffices={"Select sub-category","MSEB","Bus Depo","Railway Station","BSNL","Municipal Council","Tehsil Office","Prant Office","Panchayat Samiti","PWD Office","Post Office","Govt. Hospital","Survey Office","Civil Court","Agro Services"};
    String[] subConstruction={"Select sub-category","Building Material","Flooring","Steel","Marble","Polish","Wallpaper","Tiles/Ceramics"};
    String[] subBeauty={"Select sub-category","Beauty Parlor","Saloon","Ladies Tailor","Gents Tailor","Mehendi","Tattoo","Hair Dresser"};
    String[] subAgents={"Select sub-category","Real Estate","Driving License","Pan Card","Aadhar Card","Travel Agent","Stamp Vendor","Life Insurance","Tax Consultant","Vehicle Insurance","Medical Insurance","Passport","Loans"};

    ScrollView svRegBusiness;
    Spinner spinnerType,spinnerSubtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business);

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();

        Log.d("rgbus",""+submitEdit+" "+page);


        spinnerType=findViewById(R.id.spinnerType);
        spinnerSubtype=findViewById(R.id.spinnerSubtype);

        adpType=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,mainType);
        spinnerType.setAdapter(adpType);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subHealth);
                        break;
                    case 2:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subEducation);
                        break;
                    case 3:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subClasses);
                        break;
                    case 4:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subWedding);
                        break;
                    case 5:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subServices);
                        break;
                    case 6:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subSuppliers);
                        break;
                    case 7:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subEntertainment);
                        break;
                    case 8:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subRentedServices);
                        break;
                    case 9:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subFood);
                        break;
                    case 10:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subHolidays);
                        break;
                    case 11:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subShops);
                        break;
                    case 12:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subDailyNeeds);
                        break;
                    case 13:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subProfessionals);
                        break;
                    case 14:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subEmergency);
                        break;
                    case 15:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subConstruction);
                        break;
                    case 16:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subBeauty);
                        break;
                    case 17:
                        adpSubtype=new ArrayAdapter<String>(registerBusiness.this,android.R.layout.simple_spinner_dropdown_item,subAgents);
                        break;
                }
                spinnerSubtype.setAdapter(adpSubtype);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        wvregBusiness=(WebView)findViewById(R.id.wvregisterbusiness);
        wvregBusiness.setWebViewClient(new WebViewClient());
        wvregBusiness.getSettings().setJavaScriptEnabled(true);

        wvregBusiness.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvregBusiness.getSettings().setDomStorageEnabled(true);
        // wvregBusiness.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSfZ08XET147LMHunxWMkR4CYCfjaXMxLq8XIM4gSZGGssRRyA/viewform");

        svRegBusiness=findViewById(R.id.svRegBusiness);

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

        etRBKeywords.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
             //   svRegBusiness.fullScroll(View.FOCUS_DOWN);
                new CountDownTimer(500,100){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
//                        Toast.makeText(ChatActivity.this, "test", Toast.LENGTH_SHORT).show();
                        svRegBusiness.fullScroll(View.FOCUS_DOWN);
                    }
                }.start();
                return false;
            }

        });

        ConnectivityManager conn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=conn.getActiveNetworkInfo();

        if(nf!=null && nf.isConnected()){
            Query adRegBusStatus=dbRef.child("Advertisements").child("adRegBus");
            adRegBusStatus.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    fbase fb=dataSnapshot.getValue(fbase.class);
                    if(fb.getStatus().equals("yes")) {
                        wvregBusiness.loadUrl(fb.getUrl());
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
//            wvregBusiness.loadUrl("https://wwwkarjatonlinecom.000webhostapp.com/adregisterbusiness.html");
        }
        else {
            Snackbar sb=Snackbar.make(this.findViewById(R.id.activity_register_business),"Internet not available !",Snackbar.LENGTH_SHORT);
            sb.show();
            btnRBLogin.setEnabled(false);
            btnRBSignup.setEnabled(false);
            wvregBusiness.loadUrl("file:///android_asset/ad.html");
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
                                else if(spinnerType.getSelectedItemPosition()==0)
                                    Toast.makeText(registerBusiness.this, "Select main category", Toast.LENGTH_SHORT).show();
                                else if(spinnerSubtype.getSelectedItemPosition()==0)
                                    Toast.makeText(registerBusiness.this, "Select sub-category", Toast.LENGTH_SHORT).show();
                                else {
                                    fb.setUsername(etRBUsername.getText().toString());
                                    fb.setPassword(etRBPassword.getText().toString());
                                    fb.setShopname(etBName.getText().toString());
                                    fb.setName(etOName.getText().toString());
                                    fb.setAddress(etAddress.getText().toString());
                                    fb.setPhone(etContact.getText().toString());
                                    fb.setKeywords(etRBKeywords.getText().toString());
                                    fb.setType(spinnerType.getSelectedItem().toString());
                                    fb.setSubtype(spinnerSubtype.getSelectedItem().toString());
                                    fb.setTypeposition(String.valueOf(spinnerType.getSelectedItemPosition()));
                                    fb.setSubtypeposition(String.valueOf(spinnerSubtype.getSelectedItemPosition()));

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
                                else if(spinnerType.getSelectedItemPosition()==0)
                                     Toast.makeText(registerBusiness.this, "Select main category", Toast.LENGTH_SHORT).show();
                                else if(spinnerSubtype.getSelectedItemPosition()==0)
                                     Toast.makeText(registerBusiness.this, "Select sub-category", Toast.LENGTH_SHORT).show();
                                else {
                                    Map<String, Object> taskMap = new HashMap<String, Object>();
                                    taskMap.put("username", etRBUsername.getText().toString());
                                    taskMap.put("password", etRBPassword.getText().toString());
                                    taskMap.put("shopname", etBName.getText().toString());
                                    taskMap.put("name", etOName.getText().toString());
                                    taskMap.put("address", etAddress.getText().toString());
                                    taskMap.put("phone", etContact.getText().toString());
                                    taskMap.put("keywords", etRBKeywords.getText().toString());
                                    taskMap.put("type",spinnerType.getSelectedItem().toString());
                                    taskMap.put("subtype",spinnerSubtype.getSelectedItem().toString());
                                    taskMap.put("typeposition",String.valueOf(spinnerType.getSelectedItemPosition()));
                                    taskMap.put("subtypeposition",String.valueOf(spinnerSubtype.getSelectedItemPosition()));

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
                                   try {
                                       spinnerType.setSelection(Integer.parseInt(fb.getTypeposition()));
                                       spinnerSubtype.setSelection(Integer.parseInt(fb.getSubtypeposition()));
                                   }
                                   catch (Exception e){
                                   }
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
