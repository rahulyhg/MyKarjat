package harshad.mykarjat;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


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
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public class supermain extends AppCompatActivity{

    //Admobs
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    ListView lvmain;

    BottomNavigationView bnvSupermain;

    public  static Context mcontext;

    //Snackbars
    public static Snackbar skInternetAvailable;
    public static Snackbar skInternetNotAvailable;

    //Autocomplete
    AutoCompleteTextView actv;
    ArrayAdapter<String> aadp;
    LinearLayout ll;
    WebView mainWv;
    public static GridView gv;
    public static int i=0,data=0;
    gridviewAdapter gvadp;
     TypedArray img;
    myadapter2 myadp2;
    TextView tvResultCount;

   // String[] str={"Health","Agents","Local/Bus","Food","Shops","Construction","Daily Needs","Beauty","Banks","Emergency","Important","Classes","Places to Visit","Contact"};
    String[] str={"Health","Education","Classes","Wedding","Services","Suppliers","Entertainment","Rented Services","Food","Holidays","Shops","Daily Needs","Professionals","Emergency",
           "Govt. Offices","Local/Bus","Construction","Beauty","Agents","Banks","Places to Visit","Contact"};
    String[] strdataMain={"Guitar Academy","Mr. Harshad Dagade","Karjat Police Station",
            "Neral Police Station",
            "Matheran Police Station",
            "Karjat Tehsil Office",
            "Karjat Panchayat Samiti",
            "Karjat Nagarpalika",
            "Neral Grampanchayat",
            "Karjat Railway Station",
            "Karjat ST Depo",
            "Karjat Post Office",
            "Govt. Ladies Hostel",
            "Karjat Govt Hospital",
            "Neral Govt Hospital",
            "Kashele Govt Hospital",
            "Kalamb Govt Hospital",
            "Fire Brigade Office",
            "Telephone Exchange Karjat",
            "MSEB Karjat",
            "MSEB Dahivali",
            "Sub-Distict Hospital",
            "Sarvajanik Ganeshotsav Mandal",
            "Thakkar Bharat Gas",
            "Pinto HP Gas Service",
            "Abhinav Dnyan Mandir",
            "Sharda Mandir",
            "English Medium School",
            "Good Shepherd Convent School",
            "Mahila Mandal Vidya Vikas Mandir",
            "Dombe Vidyaniketan",
            "Bhausaheb Raut Vidyalay",
            "Kokan Gyanpeeth Engineering College",
            "Tasgaonkar Engineering College",
            "Royal Garden",
            "Shelke Mangal Karyalay",
            "Dhapaya Mangal Karyalay",
            "Jai Ambe Mata Mandir, Bhisegaon",
            "Matoshree Hall",
            "Yashada Mangal Karyalay",
            "Rohit Mangal Karyalay, Diksal",
            "Vrushab Garden",
            "State Bank of India (SBI)","Bank of India (BOI)","Union Bank Of India","IDBI Bank","Bank of Badora","UCO Bank","State Bank of Patiala","HDFC Bank","Vijaya Bank","Dombivali Nagari Sahakari Bank","Bank of Maharashtra","Raigad Bank","Apna Sahakari Bank LTD",
            "Dagade Vadewale","Mr. Chandrakant V. Dagade","Ganesh Tea House","Mr.Krishna Bhoir","Ravi Tea House","Mr.Ravindra Gholap",
            "Sattu vadewale","Mr.Santosh Dabhane","Om Sai Ram Tea & Snack","Mr.Veer","Ulhas Uphargruha","Mr.Ramshet Hagavane","Sai Idli Center","Mr.Sudhakar Bangesh",
            "Ramesh Lunch Home","Hanuman Uphar Gruha","Mr.Vinayak Mavkar",
            "Hotel Devki","Mr.Rakesh Gupta","Samadhan Hotel","Mr. Surendra Deshmukh","Hotel Jay Ganesh","Mr. Arjun Kadu","Hotel Shivam","Mr.Deshmukh","Hotel New Santosh","Mr. Pankaj Vansant Bedekar","Hotel Dataray","Mr.Ganesh D.Yadav","Suleman Restaurant & Caterers","Mr. Mohammad Bhai","Hotel Satkar","Mr.Shankar Thombre","The CBC Residency ","A-one Food Center","Mr. Kisan Dhisana","Hotel shree Samarth Krupa","Mr. Rajendra Gaikar","Patil Hotel","Mr. Anant Patil","Mayura Hotel","Mr. Mayur Joshi",
            "Hotel Tanmay","Mr. Rajesh Bhoir",
            "New Monali Bakery","Mr.VikasTupe/Mr.PrakashTupe","Shree Kirsha Ayangar Bakery","Meher Bakery","Mr.Meher",
            "Monginis Cakes & Bakery",
            "New Santosh Ice-Cream Center","Mr. Mahesh shiram Thakur","Vaidya ICE- Cream","Mr. Yogesh Vaidya","Agrawal ICE-Cream Center","Mr. Sonu Agrawal",
            "Sai Shraddha Cool Point Coffe","Mr. Milind Gholap","Siddhivinayak ICE-Cream Parlor","Mr. Pankaj Badekar",
            "Bikaner Sweets & farsan","Mr. Sameer Patil","Maharaja Sweet","Mr. Prakash Prajapati","Puna Sweet","Mr. Nivruti Sawant","Laxumi Sweets","Mr. Suhas Gawade",
            "Pooja Sweets","Mr. Amit Gupta","Bombay Sweets Mart","Mr. Satish Gupta","MabhaDeva Sweets","Mr. RamSingh Pardeshi","Balaji Sweets","Mr. Amrutlala Joshi",
            "Trupti Sweetmart","Mr. Ravindra Kashelikar","Karjat Sweet Mart","Mr. Prajapati","Vaidya sweet Mart","Mr. Yogesh Vaidya","R.K.Sweets","Mr. Ritesh Gupta",
            "Aai Ekvira Khanaval","Mr. Sanjivani Mhase",
            "AYAN Caterers","Mr. Shafiq Khan / Qaum Khan","BISMILLAH Caterers","Mr. Mohd. Israil / Mohd. Ashique Ali",
            "Shree Smaran"," Mr. Rushikesh Arun Gaikwad","Mateshavari Dairy","Mr. K.P.Patel / Mr. P. Patel","Deepak Agency","Mrs. Chaha Manohar Rajmane","Kajal Dairy"," Mr.Hanuman Badekar","Om Sai Dairy","Mr. Ashok More","MahaLaxmi Dairy","Mr. Akshay Sonawale","Sawariya Dairy","Mr. Tagir","Om SaiRam","Mr. Suresh Dalvi","Pooja Milk Dairy","Mr. Manohar Patil","Gyatri Milk Dairy","Mr. Mukesh Purohit","Diksha Dairy","Mr. Ravindra Karad",
            "Nakashtra Telecom","Mr. Shrikant Karpe","Shri Sai Ganesh Mobile","Mr. Sanjay","MK Mobile & Electronics","Mr. Mukeshbhai Purohit","Shri Ashapura Mobile",
            "Shri.Chamunda Mobile & Electronics","Shyam Enterprises (Micromax Center)","Mr. PavanKumar Ramshevak Chavan","S.K. Mobile","Mr. Siddhesh P. Karle",
            "Nachiket Communication","Mr. Nachiket R. Bali","Venkatesh Enterprises","Mr. Ganpat / Mr. Umesh","Suyog Communication","Mr. Suyog S.Hingmire",
            "Bharat Mobile","Mr. Husen Gupta","Raj Mobiles","Mr. Rajendra Bodke","Nakoda Mobiles","Mr. Gaurav Jain","Khetheshvar Mobile","M.S. Communication","Mr. Dinesh M. Parmar","Durgavati Mobile & Electronic","Mr. Nitish Gupta","Shree RamDev Mobile","Mr. Sandesh Bhutoda","A to Z Mobile Shop","Samsung Phone Café","Mr. Piyush Oswal",
            "Karteswar Mobile(Repairing Center)","Mr. Anil",
            "Vaidya Xerox","Mr. Yogesh Vaidya","Raigad Xerox","Mr. Kale","Sachin Xerox","Mr. Sachin","Brahmachitayna Xerox","Mr. Chandrakant Ghosalkar",
            "Shreya Xerox","Mr. Rahul ","Prasad Xerox","Mr. Salunkhe","Vishal Xerox","Mr. Ashok Salgavkar",
            "Rajguru Collection","Mr. Bharat Rajguru / Lokesh Rajguru","Parampara Collection"," Mr. Pravin","D Fashion Den"," Mr. Kaushik Wanjale",
            "HD Planet","Amardeep Collection"," Mr. L.S. Rajpurohit","Rocki's NX","Mr. M.D.Joshi / Mr. S.D.joshi","Shree Smarth Collection","Mr. Ravindra k. Bhalivde",
            "Raymomd Satnam Collection","Mr. Haresh Manjalani / Mr. Ravindra Kadav","Shree Collection","Mr. Rahul Dhamnase","Man-Mindir","Mr. M.G.Oswal",
            "Om Collection","Mr. Ankit","Om Sai Collection","Mr. Jalram Jadhav / Mr. Banti Palkar","Shri Smaran Redymade Store","Mr. Sammer L. Hulawale",
            "Bhargav Collection"," OM Collection","Mr. Ketan Virlani","Giyan Readymade Shop","Mr. Giyan Wadhwar","Popular Cloth  Store",
            "Parivar Cloth Store","Mr. Gandhi","Shiv Mudra Collection","Mr. Devendra Zanze","Lucky's Mens Wear","Jockey ","Mr. Mahesh","Mangalam Mens Wear","Mr. Harish",
            "Shree Samarth Krupa","Mr. Nitin Deshmukh",
            "Sai Collection","Mr. Jitu","Mayuri Collection","Poonam Cloth Store","Priya Saree Center","Mr. Chintan / Mrs. Kirti","Tanush Saree Center","Mr. Avinash Shirke / Mr. Deepak Pawar","New Fashion Collection","Mr. Vrushali Shinde","Paras Cloth Store"," Mr. Prakash / Mr Manoj / Mr. Amit","Pankaj Cloth Stores","Mr. Pankaj Jain / Mr. Vikram jain","Memsaab Collection"," Mr. Dhanji","DeepSangam Collection","Mr. Jayesh Mate / Mr. Lalit","Panchratana Saree Center"," Mr. Sajan / Mr. Yogesh",
            "Classic Kids & ladies Wear","Mr. Mahendra Kumavat","DeepLuxumi Saree Center","Mr. Ganesh Baban Patil","Maitree Butic","Mr. Ashok Oswal / Mr. Ankit Oswal",
            "Vaidya Cloth Store","Mahavir Fabrics / Nilesh Textiles","Mr. Nilesh / Mr. Shailesh","Anjali Family Shop","Mr. Girdhari Purohit","Bafana Cloth Store",
            "MahaLaxmi Kids & ladies wear","Mr. Prajapati ","Shweta Ladies Corner","Mr. Sumesh Shetye","Kapaleshver Saree Center","Mr. Mandar. N. More / Mr. Nandakumar More",
            "Datta Redymade Cloth Store","Mr. Luxuman D. Hulawale","Jalaram Matching & Ladies Wear","Pinto Dressess","Mr. Amrut Parmar","Pooja Collection","Mr. Dilip Oswal",
            "Ganpati Saree Center","Mr. Kalpesh Jain","Darshan Collection"," Mr. Girdhari Singh J. Purohit","Roopam Dresses"," Mr. Rajesh Oswal","RajDhan Collectiom / Aashish Saree","Mr. Suresh Oswal","Parth Collection","Mr. Mayur Jain","Lavanya Redymade ","Mr. Pandhrinath Deshmukh","Sai Raj Ladies Collection","Mr. Bhushan Khandagale",
            "Vasudha Maching Center"," Mrs. Pushpa Hulawale","PreetSangam Saree Center","Mr. Vikram Kothari","Poonam Saree Center"," Mr. Mahendra Solanki","Shree Rushab Saree Center","Mr. Dinesh","Pushpak Saree Centre","Mr. S.B.Oswal","Keseriya Saree Center","Life Style","Cotton Fasion","Fashion Planet",
            "Domain Computers","Mr.Ulkesh Pednekar","RC Intex Distributors","Mr.Suraj More","Gateway Computer Solution","Shyam Enterprises","Mr. PavanKumar Ramshevak Chavan",
            "Sky Line Computer Solution","Mr. Abhinav Khangate","LINK Computer & cyber Café","Comptech Computer Education","Mr. Mali","Bunti Cyber Café","Mr. Bunti",
            "Baba Computer","Mr. Ajay",
            "Tip-Top Shoe Mart","Mr. Mayur Joshi","Raj Foothwear","Amol Lader Work","Mr. Amol Zarekar","Eman Ali Fatemanni Sons","Kumar Footware","Mr. Kumar Kukreja",
            "Parmar Footware","Mr.Naresh Parmar",
            "Jai Shree Ram","Shalimar Hardware","Mr. Irfan J. Shaikh / Mr. Sadik S. Shaikh","Pragati Hardware","Mr. Paresh Shah",
            "Asgarali Agencies","Mr. Aliasgar I. Dhariwala","Shree Bhairav Fancy Hardware","Mr. Mahesh","Shree Nakoda Plywood & Aluminium","Mr. Kishan",
            "Shri Sai Pumps ","Mr. A.B. Deshmukh / Mr. Dhananjay","Venkatesh Fiber Works","Mr. Raju Walunj","Maharashtra Hardware","Mr. Bharat Rajguru",
            "New Bharat Hardware","Shree Samarth Krupa Hardware","Mr. Bhagwan Khade",
            "Ankita Steel Corner","Mr. Arvind Jain","Shree Ashapura Steel","Palkar Steel","Mr. Chandakant R. Palkar","MahaLaxumi Steel","Mr. LalaRam Patel",
            "Pravin Steel Bahandar"," Mr. Pravin Oswal","Sha. Perajman Phulaji","Mr. Kalpesh Oswal","Deshmukh Steel","Mr. Aravinda Deshmukh",
            "Vaman All Tyre Service","Mr. Vilas Dangre",
            "Prasad Motors","Mr. Sandeep Mavkar","Shree Ganesh Motors","Mr. Girish Kumbhar","Ashok Motors","Parshv Motors","Mr. Amol Oswal",
            "Sanket Motors","Mr. Sanket Thakekar","Karjat Motors",
            "Suraj Auto Consultant","Mr. Dinesh M. Rawal",
            "New Look, Parlor","Mrs. Rekha Gholap","Ramaj Ladies Parlor","Choice Point, Parlor","Mrs. Shilpa Raval",
            "Jai Deep Tailor","Mr. Joyti D. Dhakval","Friends Tailor","Mr. Nitin Deshmukh","New Style Tailor","Mr. Sarafraj Patel","Rehan Tailor","Mr. Mahamad Aasif",
            "Sangita Tailor","Mrs. Sangita m. Jadhav","Ruchita Tailor","Pranjal Tailor"," Mr. Vilas Musale","Raigad Tailor","Mr. Manohar Lobhi",
            "Aatif Tailor"," Mr. Anjum Patel","Sai Raj Ladies Tailor"," Mr. Bhushan Khandagale","Prasad Ladies Tailor & Matching Center","Mr. Smita Balu Navghane",
            "Sangita Ladies Tailor","Mrs. Sangita Jadhav","Umesh ladies Tailor","Mr. Umesh Khangare","Shirke Ladies Tailor","Mr. Deepak Shirke",
            "Raigad Tailor","Mr. Manohar Lobhi","Shree Samarth Tailor","Mr. Ankush Dalvi","Sarathk Tailor","Mr. Prasad Lad","Tushar Tailor","Mr. Tushar Dighe",
            "Umesh Mens Tailor","Mr. Umesh Khangare","New Bombay Tailor","Mr. Rajendra Deshmukh","R.D. Tailor","Mr. Rajendra Dalvi",
            "Padmavati chemist","Dinesh T. Solanki",
            "Parmar medical","vijay s. parmar",
            "vijay medical","deepak d. kulkarni",
            "Rajendra medical","rakesh s. jain",
            "Gurudutt medical","vitthal n. joshi",
            "Trimurti medical","lilabai p. hargade",
            "Om medical","mamta a. oswal",
            "Mahavir medical","kamlesh a. jain",
            "Aniket medical","ramrao y. patil",
            "arihant medical","pravin j. oswal",
            "prem medical","jabbirsingh purohit",
            "sneha medical","s.s patil",
            "Ambikamedical","krishna g. patel",
            "janseva medical","mayur s. phadtare",
            "dhanwantari medical","sugandha g. patil",
            "gajanan medical","sirla s. patil",
            "milan medical","madansingh rajpurohit",
            "om sai medical","shekar borade",
            "priyanka medical","priyanka.t chavan",
            "shri.siddhivinayak medical","hema v. joshi",
            "Varad Medical"," Amit J. Pawali",

            "Meher Clinical Laboratory","Mr. Abid Khan",
            "Nidan Clinical Laboratory","Mr. kalpesh Patel",
            "Perfect Clinical Laboratory","Mr. Ravindra Mane",

            "Adarsh Laboratory",
            "Breach Candy Hospitaland Research Center",
            "Dr. Khandwalkar's Lab",
            "Harkisandas Nurottamads Hospital Blood Bank",
            "Sion Hospital Blood Bank",
            "Ambika Blood Bank",
            "B.Y.L Nair Hospital",
            "Dr. Mahesh Mehta",
            "Hinduja National Hospital & Medical Research",
            "Sir.J.J Groups Of Hospital",
            "Ambaji Blood Bank",
            "Cama & Albless Hospital Blood Bank",
            "Bhatia General Hospital",
            "Indian Red Cross Soc. Blood Bank",
            "Anviksha Pathological",
            "Central Hospital Blood Bank",
            "Bhiwandi Blood Bank",
            "INHS Ashwini Blood Bank",
            "St. George's Hospital Blood Bank",
            "Ashirwad Blood Bank",
            "Chhartapati Shivaji Maharaj Rugnalaya",
            "D.S Kothari Hospital Blood Bank",
            "Jain Clinic Blood Bank",
            "Sushilaben R Mehta & Sir Kikabai",
            "BARC Hospotial Blood Bank",
            "Civil Hospital Blood Bank",
            "E.S.I.S. Hospital",
            "Jaslok Hospital & Research Centre",
            "Tata Blood Bank",
            "Dr. Bharat Kansaria",
            "Cottage Hospital",
            "Chinmayanand Charitable Trust Blood Bank",
            "G.T.Hospital Blood Bank",
            "JVP Blood Bank Arenja",
            "Bombay Hospital Trust Blood Bank",
            "Dr. B A Memorial Hospital Blood Bank",
            "Haematoloey Laboratory",
            "K.B. Bhabha Hospital",
            "Bombay Hospital Trust Blood Bank",
            "K.E.M. Hospital Blood Bank",
            "K.J. Somaiya Blood Bank",
            "Lokamanya Tilak Municipal Hospital",
            "Mahatama Gandhi Mission Hospital Blood Bank",
            "Padhe's Lab",
            "Paras Pathological Laboratory & Blood Bank",
            "Parsi Gen. Hospital Blood Bank",
            "Parang Shah Cottage Hospital",
            "Petit parsee General Hospital & Blood Bank",
            "Plasma Dignostic Laboratory & Blood Bank",
            "Pooja Blood Bank",
            "Prince Ali Khan Hospital Blood Bank",
            "Sarvoday Lab",
            "JVP Blood Bank Arenja",
            "K.B. Bhabha Hospital",
            "Rajawadi Municipal Hospital",
            "Rashmi Blood Bank",
            "Samarpan Blood Bank",
            "Sankalp Blood Bank",
            "Sarvodaya Hospital Blood Bank",
            "Sewree T.B Blood Bank",
            "Sri Satya Sai Blood Bank",
            "Regional Blood Bank",
            "Sushilaben R Mehta & Sir Kikabai",
            "Tata Blood Bank",

            "Devghare Constructions","Mr. Sudesh",
            "Bhoir Constructions","Mr. Vasant Bhoir",
            "ShivSaiConstruction","Mr. BhagvanShet Bhoir",

            "Jain Travels","Mr. Anuj Oswal / Mr. Madan Oswal / Alex Oswal",
            "Veena World (Ashok Travels)","Mr. Swapnil kadam",
            "Raj Travels","Mrs. Kalpana R. Bodke",
            "Shri Varadvinayak Tours & Travels","Mr. Sanket Arun Yakar",
            "OM Travels","Mr. R. Jaiswal",
            "Bhavesh Toures","Bhavesh",

            "Mohite Consultancy","Mr. Sanjay Mohite",
            "Shruti Concultancy LIC",
            "Mehul Agency",

            "M/S. S.S.Sawant & Co.","Mr. Suresh S. Sawant",
            "Bajaj Finance","Mr. Milind Sawant / Shiraji Sawant",

            "Amrapali Hospital","Dr. Gaikwad Ramesh N.,M.B.B.S.,D.G.O.",
            "Shree Narayan Hospital","Dr. Nazirkar Alka G.,M.D.(Obst.,Gynac.)",
            "Shree Swaami Samarth Nursing Home","Dr. Parmar Satish, M.B.B.S.,MS(OBGY)",
            "Phadke Hospital","Dr. Phadke Neelkanth S.,OBGY consultation, Surgery,Maternity Ultrsound",

            "Ganesh Dental Clinic","Dr. Gaikwad Vaibhav G.,B.D.S.",
            "","Dr. Gaikwad Varsha G.,B.D.S.",
            "","Dr. Jain Ritesh,B.D.S.",
            "Pankaj dental Clinic","Dr. Jain Shripal S.,B.D.S.",
            "Parmar Dental Clinic","Dr. Parmar Manish,B.D.S.",
            "Shree multispeciality Dental Clinic","Dr Jangam Aditya G.,B.D.S.,DGDEMS",

            "Vignahartha Hospital","Dr.Adakmol Smita D.,M.B.B.S.",
            "Shree Sai Hospital","Dr.Sable Rambhau B.,M.B.B.S.,MS. Orthopedic Surgeon",
            "Sidhivinayak Hospital","Dr Vinay Sangle N.,M.B.B.S.,D-Ortho,PGDMILS",

            "Shree gajanan clinic","Dr Dalvi sangita H., B.H.M.S.",
            "Spandan Panchakarma Ayurved chikitsalay","Dr.Padte Dhanashri S.,B.H.M.S.",
            "Spandan Panchakarma Ayurved chikitsalay","Dr.Swapnil Padte C.,B.A.M.S.,(M.D.)",

            "Ashram Clinic","Dr. Kale geeta P.,B.A.M.S.",
            "Ashram Clinic","Dr. Kale Prabhakar M.,B.S.A.M.",
            "Spandan Panchakarma Ayurved chikitsalay","Dr. Padte swapnil C.,B.A.M.S.,(M.D.)",
            "Shushrut Hospital","Dr.Dhavale Sunil T.,B.A.M.S.,M.S.",

            "S.P. Video Cable Network", "Mr. Paul",
            "Bhairavanath Raswanti, Juice Center","Mr. Ramdas Phanse"
    };

    String[][] strSearch={{}};
      /*  {{"Karjat Police Station","02148-222100"},
            {"Neral Police Station","02148-238444"},
            {"Matheran Police Station","02148-230300"},
            {"Karjat Tehsil Office","02148-222037"},
            {"Karjat Panchayat Samiti","02148-222034"},
            {"Karjat Nagarpalika","02148-222033"},
            {"Neral Grampanchayat","02148-238445"},
            {"Karjat Railway Station","02148-222064"},
            {"Karjat ST Depo","02148-222085"},
            {"Karjat Post Office","02148-222023"},
            {"Govt. Ladies Hostel","02148-220605"},
            {"Karjat Govt Hospital","02148-222070"},
            {"Neral Govt Hospital","02148-238632"},
            {"Kashele Govt Hospital","02148-244049"},
            {"Kalamb Govt Hospital","02148-232244"},
            {"Fire Brigade Office","02148-202101"},
            {"Telephone Exchange Karjat","02148-220500"},
            {"MSEB Karjat","02148-223635"},
            {"MSEB Dahivali","02148-223630"},

            {"Sub-Distict Hospital","02148-222069"},
            {"Sarvajanik Ganeshotsav Mandal","02148-221101"},

            {"Thakkar Bharat Gas","02148-221641"},
            {"Pinto HP Gas Service","02148-222192"},

            {"Abhinav Dnyan Mandir","02148-222063"},
            {"Sharda Mandir","02148-222415"},
            {"English Medium School","02148-220798"},
            {"Good Shepherd Convent School","02148-651718"},
            {"Mahila Mandal Vidya Vikas Mandir","02148-223210"},
            {"Dombe Vidyaniketan","02148-221722"},
            {"Bhausaheb Raut Vidyalay","02148-225603"},
            {"Kokan Gyanpeeth Engineering College","02148-223289"},
            {"Tasgaonkar Engineering College","9594974906"},

            {"Royal Garden","02148-222704"},
            {"Shelke Mangal Karyalay","02148-223317"},
            {"Dhapaya Mangal Karyalay","9373510410"},
            {"Jai Ambe Mata Mandir, Bhisegaon","9771653141"},
            {"Matoshree Hall","9819174018"},
            {"Yashada Mangal Karyalay","02148-222151"},
            {"Rohit Mangal Karyalay, Diksal","9270058112"},
            {"Vrushab Garden","9921943394"},

            {"New Santosh Ice-Cream Center","Mr. Mahesh shiram Thakur","Shiv Sai Darshan, Shop No. 4, Near Abhinav Gyan Mandir School, Karjat-Raigad 410201","9527282627 / 9850653675"},
            {"Vaidya ICE- Cream","Mr. Yogesh Vaidya","Near Govt. Hospital, Ambedkar Chawk, Bhide wada, Karjat-Raigad 410201","9822558264 / 9370631502"},
            {"Agrawal ICE-Cream Center","Mr. Sonu Agrawal","Shivaji Putal, Deecan Ghymkhana, Kacheri Road, Karjat-Raigad 410201","9021244445"},
            {"Sai Shraddha Cool Point Coffe","Mr. Milind Gholap","Near Kapaleshver Mandir, Karjat-Raigad 410201","9028555128 / 9595958582"},
            {"Siddhivinayak ICE-Cream Parlor","Mr. Pankaj Badekar","Sidhakala Apt., Near Ambedkar Chowk,Karjat-Raigad 410201","705935999"},

            {"Guitar Academy","Mr. Harshad Dagade","Flat 101, First Floor, Near Govt. Hospital, Karjat","9595031739"},

            {"State Bank of India (SBI)","IFSC: SBIN0012869","Laxminarayan Niketan Apartment, Karjat-Raigad 410201",""},
            {"Bank of India (BOI)","IFSC: BKID0001205 ","Karjat-Raigad 410201","02148-222056"},
            {"Union Bank Of India","IFSC: UBIN0561827","Chaitanya Apt., Near LS Library, Opp. Police Station, Karjat-Raigad 410201",""},
            {"IDBI Bank","IFSC: IBKL0000899","Vaidya Sankul,Karjat-Raigad 410201",""},
            {"Bank of Badora","IFSC: BARB0KARJAT","Chhatrapati shivaji Mandal Trust, Shivaji Chowk, Near Bazarpeth, Karjat-Raigad 410201","02148-223962"},
            {"UCO Bank","IFSC: UCBA0000203","Kacheri Road, Karjat-Raigad 410201",""},
            {"State Bank of Patiala","IFSC: STBP0001331","Shop No 6,7,8,9,10, Manas Complex, Amrai Road, Karjat-Raigad 410201",""},
            {"HDFC Bank","IFSC: HDFC0002973","","02148-222310 / 9890603333"},
            {"Vijaya Bank","IFSC: VIJB0005126","House No.63, Ground Floor, Mahavir peth, Karjat-Raigad 410201",""},
            {"Dombivali Nagari Sahakari Bank","","Mathura Nivas,Karjat-Raigad 410201","18002331700"},
            {"Bank of Maharashtra","IFSC: MAHB0001645","Mahila Mandal, Near Government Hospital, Karjat-Raigad 410201",""},
            {"Raigad Bank","","Tilak Chawk, Above Kapaleshwar Mandir, Karjat-Raigad 410201",""},
            {"Apna Sahakari Bank LTD","IFSC: ASBL0000074","Gr.Floor & First Floor, Karjat Muncipal Council Administration Bldg,Karjat-Raigad 410201",""},

            {"Dagade Vadewale","Mr. Chandrakant V. Dagade","Gujarati Wada, Kacheri Road, Karjat-Raigad 410201","9011778442"},
            {"Ganesh Tea House","Mr.Krishna Bhoir","Kacheri Road, Karjat-Raigad 410201","9527282543"},
            {"Ravi Tea House","Mr.Ravindra Gholap","Mahavir Peth, Karjat-Raigad 410201  ","9225685887"},
            {"Sattu vadewale","Mr.Santosh Dabhane","Near Dr.Ambedkar Putala, Karjat-Raigad 410201","9371899979 / 9049098999"},
            {"Om Sai Ram Tea & Snack","Mr.Veer","Aamrai Road, Near Alankar Talkies, Karjat-Raigad 410201","8237133488"},
            {"Ulhas Uphargruha","Mr.Ramshet Hagavane","Amrai Road, Karjat-Raigad 410201","7507861303"},
            {"Sai Idli Center","Mr.Sudhakar Bangesh","Kacheri Road, Karjat-Raigad 410201","9673150173  "},
            {"Ramesh Lunch Home","","Kacheri Road, Karjat-Raigad 410201",""},
            {"Hanuman Uphar Gruha","Mr.Vinayak Mavkar","Karjat-Raigad 410201","9822426788"},

            {"Bhairavanath Raswanti, Juice Center","Mr. Ramdas Phanse","Bazapeth, Karjat-Raigad 410201","9372749546"},

            {"Hotel Devki","Mr.Rakesh Gupta","Near Dr. Ambedkar Putala, Karjat-Raigad 410201","9764848938"},
            {"Samadhan Hotel","Mr. Surendra Deshmukh","Kotwal Nagar, Karjat-Raigad 410201","9021899944"},
            {"Hotel Jay Ganesh","Mr. Arjun Kadu","Amira States, Infront of Nagarpalika,Karjat-Raigad 410201","8149676802"},
            {"Hotel Shivam","Mr.Deshmukh","Tilak Chowk, infron of Post Office, Karjat, Raigad ","02148-221515 / 9850056060"},
            {"Hotel New Santosh","Mr. Pankaj Vansant Bedekar","Near Railway station, Near UCO Bank, Kacheri Road, Karjat-Raigad-410201","9960999469 / 9970932929"},
            {"Hotel Dataray","Mr.Ganesh D.Yadav","Deecan Gymkhana, kacheri Road, Karjat-Raigad-410201","7588461608 / 9823217711"},
            {"Suleman Restaurant & Caterers","Mr. Mohammad Bhai","Shivaji Chowk, Kacheri road, Karjat-Raigad-410201","9595081093 / 9823310200 / 9028717478"},
            {"Hotel Satakar","Mr.Shankar Thombre","Shivaji Chowk, Kacheri road, Karjat-Raigad-410201","9422083631 / 7304261899"},
            {"The CBC Residency ","","Karjat-Murbad Highway, Mudre(BK)., Karjat-Raigad-410201","9326106060"},
            {"A-one Food Center","Mr. Kisan Dhisana","Patil Ali, Near Hanuman Mandir,  Karjat-Raigad 410201","8408991616"},
            {"Hotel shree Samarth Krupa","Mr. Rajendra Gaikar","Kacheri Road, Karjat-Raigad 410201","8793722274"},
            {"Patil Hotel","Mr. Anant Patil","Bazapeth, Karjat-Raigad 410201","8983330467"},
            {"Mayura Hotel","Mr. Mayur Joshi","Bazapeth, Karjat-Raigad 410201","02148-222926"},
            {"Hotel Tanmay","Mr. Rajesh Bhoir","Near Kapaleshvar Mandir, Karjat-Raigad 410201","8087578330"},

            {"New Monali Bakery","Mr.VikasTupe/Mr.PrakashTupe","Near Shree Ram Bridge,Amrai,Karjat-Raigad 410201","9225733179"},
            {"Shree Kirsha Ayangar Bakery","","",""},{"Meher Bakery","Mr.Meher","Near Hanuman Mandir,Karjat-Raigad 410201","9371207527"},

            {"Monginis Cakes & Bakery","","Mahavir Peth, Karjat-Raigad 410201","9225733179 / 9270917884"},

            {"New Santosh Ice-Cream Center","Mr. Mahesh shiram Thakur","Shiv Sai Darshan, Shop No. 4, Near Abhinav Gyan Mandir School, Karjat-Raigad 410201","9527282627 / 9850653675"},
            {"Vaidya ICE- Cream","Mr. Yogesh Vaidya","Near Govt. Hospital, Ambedkar Chawk, Bhide wada, Karjat-Raigad 410201","9822558264 / 9370631502"},
            {"Agrawal ICE-Cream Center","Mr. Sonu Agrawal","Shivaji Putal, Deecan Ghymkhana, Kacheri Road, Karjat-Raigad 410201","9021244445"},
            {"Sai Shraddha Cool Point Coffe","Mr. Milind Gholap","Near Kapaleshver Mandir, Karjat-Raigad 410201","9028555128 / 9595958582"},
            {"Siddhivinayak ICE-Cream Parlor","Mr. Pankaj Badekar","Sidhakala Apt., Near Ambedkar Chowk,Karjat-Raigad 410201","705935999"},

            {"Bikaner Sweets & farsan","Mr. Sameer Patil","Shop No. 8/9, Hirapanna shopping Palza, Near Alankar Theatre, Amarai Road, Karjat-Raigad 410201","8149947944"},
            {"Maharaja Sweet","Mr. Prakash Prajapati","Infront of Marathi School,  Karjat-Raigad 410201","9372489062"},
            {"Puna Sweet","Mr. Nivruti Sawant","Shop No.10, Police Ground, Amarai Road, Mithila nagari,  Karjat-Raigad 410201","9260093603"},
            {"Laxumi Sweets","Mr. Suhas Gawade ","Kacheri Road, Bhaji Market,Karjat-Raigad 410201","9881023366"},
            {"Pooja Sweets","Mr. Amit Gupta","Bazarpeth, Karjat-Raigad 410201","9028626056"},
            {"Bombay Sweets Mart","Mr. Satish Gupta","Bazarpeth, Karjat-Raigad 410201","9373697335"},
            {"MabhaDeva Sweets","Mr. RamSingh Pardeshi","Near Arihant Towar, Mahavir Peth, Karjat-Raigad 410201","7798811515"},
            {"Balaji Sweets","Mr. Amrutlala Joshi","Bazarpeth, Station Road, Karjat-Raigad 410201","7219289994"},
            {"Trupti Sweetmart","Mr. Ravindra Kashelikar","Bazarpeth, Karjat-Raigad 410201","9226366472"},
            {"Karjat Sweet Mart","Mr. Prajapati","Bazarpeth, Karjat-Raigad 410201","9822665733"},
            {"Vaidya sweet Mart","Mr. Yogesh Vaidya"," Near Govt. Hospital, Ambedkar Chawk, Bhide wada, Karjat-Raigad 410201","9822558264 / 9370631502"},
            {"R.K.Sweets","Mr. Ritesh Gupta","Kacheri Road, Karjat-Raigad 410201","777697665"},

            {"Aai Ekvira Khanaval","Mr. Sanjivani Mhase","Near Maruti Mandir, Karjat-Raigad 410201","9225492524 / 7387202033"},

            {"AYAN Caterers","Mr. Shafiq Khan / Qaum Khan","Mayur Apt., Shop No. 8 Sunil Chicki Patil Ali, Karjat-Raigad 410201","7507267609 / 7040287324"},
            {"BISMILLAH Caterers","Mr. Mohd. Israil / Mohd. Ashique Ali","Sunder Kalpana Building, Dahivali, Kondewade Road, Karjat-Raigad.","7506799873 / 8087548006"},

            {"Shree Smaran"," Mr. Rushikesh Arun Gaikwad","Kitwal Nagar, Mahada Colony, Karjat-Raigad 410201","9657728228"},
            {"Mateshavari Dairy","Mr. K.P.Patel / Mr. P. Patel","Shop No.7, Nemenath Residency Mudre Road, Karjat-Raigad 410201","7507827607 / 8446872522"},
            {" Deepak Agency","Mrs. Chaha Manohar Rajmane","Infornt of Shivaji Putala, Kacheri Road, Karjat-Raigad 410201","8237679050"},
            {"Kajal Dairy"," Mr.Hanuman Badekar"," Kacheri Road, Karjat-Raigad 410201","9130262122"},
            {"Om Sai Dairy","Mr. Ashok More","Kacheri Road, Karjat-Raigad 410201","9029025290"},
            {"MahaLaxmi Dairy","Mr. Akshay Sonawale","Kacheri Road, Karjat-Raigad 410201","9689538890"},
            {"Sawariya Dairy","Mr. Tagir","Pratibha Apt.,Ambedkar Chawk, Karjat-Raigad 410201","9769828702"},
            {"Om SaiRam","Mr. Suresh Dalvi","Dhyapaya Mandir Road, Karjat-Raigad 410201","9158941516"},
            {"Pooja Milk Dairy","Mr. Manohar Patil","Hira Panna Shoping Plaza, Amarai Road, Karjat-Raigad 410201","7875222555"},
            {"Gyatri Milk Dairy","Mr. Mukesh Purohit","Near Alankar Talkies, Amarai Road, Karjat-Raigad 410201","9028564080"},
            {"Diksha Dairy","Mr. Ravindra Karad","Kotwal Nagar, Karjat-Raigad 410201","8287777491"},

            {"Nakashtra Telecom","Mr. Shrikant Karpe","Shop No.7, Siddhivinayak Vishal Vihar Apt, Opp.Govt.Hospital,Karjat-Raigad.","9881444999"},
            {"Shri Sai Ganesh Mobile","Mr. Sanjay","Near Mayura Hotel, Station Road,Karjat-Raigad","9970169444 / 9372686006"},
            {"MK Mobile & Electronics","Mr. Mukeshbhai Purohit","Samarth Apt., shop No.1, nar Govt. Hospital, Dr.Ambedkar Chowk, Karjat-Raigad","9967256188"},
            {"Shri Ashapura Mobile","","Bazarpeeth, Infront of Marathi School, Karjat-Raigad","8655939449"},
            {"Shri.Chamunda Mobile & Electronics","","Bazarpeeth,Station Road, Karjat-Raigad","9967256188"},
            {"Shyam Enterprises (Micromax Center)","Mr. PavanKumar Ramshevak Chavan","Ground floor, Ganesh Kripa Buld., kacheri road, Opp.Eco Bank,Karjat-Raigad","7045242425"},
            {"S.K. Mobile","Mr. Siddhesh P. Karle","Hirapanna Shoping Palza, Near Alankar talkies, Amrai,Karjat-Raigad","9552444333"},
            {"Nachiket Communication","Mr. Nachiket R. Bali","Mahavir Peth,Karjat-Raigad","9028242376"},
            {"Venkatesh Enterprises","Mr. Ganpat / Mr. Umesh","Shop No.5, Shiv Om Complex Fire Brigade Raod, Mudre(Kh), Karjat-Raigad.","9823588589 / 7066551197"},
            {"Suyog Communication","Mr. Suyog S.Hingmire","Kacheri Road, Opp.Union Bank, Karjat-Raigad 410201","901182724"},
            {"Bharat Mobile","Mr. Husen Gupta","Kacheri Road,  Karjat-Raigad 410201","9503414871"},
            {"Raj Mobiles","Mr. Rajendra Bodke","Kacheri Road,  Karjat-Raigad 410201","9850582490"},
            {"Nakoda Mobiles","Mr. Gaurav Jain","Mahavir Peth, Karjat-Raigad 410201","9145097170"},
            {"Khetheshvar Mobile","","Near Kapaleshevar Mandir, Karjat-Raigad 410201","9767637676"},
            {"M.S. Communication","Mr. Dinesh M. Parmar","Daga Bldg., Station Rd, Karjat-Raigad 410201","9226424652"},
            {"Durgavati Mobile & Electronic","Mr. Nitish Gupta","Bazarpeth,Karjat-Raigad 410201","921654275"},
            {"Shree RamDev Mobile","Mr. Sandesh Bhutoda","Bazarpeth,Police Line Karjat-Raigad 410201","9370384070"},
            {"A to Z Mobile Shop","","At Bhoir Building, Shop No.8, Near Police Ground Karjat-Raigad 410201","9767370888"},
            {"Samsung Phone Café","Mr. Piyush Oswal","Punyoduya Appt.,Opp. Police Ground,Karjat-Raigad 410201","9923884400"},
            {"Karteswar Mobile(Repairing Center)","Mr. Anil","Amrai Road, Karjat-Raigad 410201","9930838588"},

            {"Vaidya Xerox","Mr. Yogesh Vaidya","Near Govt. Hospital, Ambedkar Chowk, Bhide wada, Karjat-Raigad 410201","9822558264 / 9370631502"},
            {"Raigad Xerox","Mr. Kale","Bazarpeth, Karjat-Raigad 410201","02148-222211"},
            {"Sachin Xerox","Mr. Sachin","Near Kapaleshvar Mandir, Karjat-Raigad 410201","8600098444"},
            {"Brahmachitayna Xerox","Mr. Chandrakant Ghosalkar","Near Abhinav Gyanmandir,Vitthal Nagar, Karjat-Raigad 410201",""},
            {"Shreya Xerox","Mr. Rahul ","Near Abhinav Gyanmandir,Vitthal Nagar, Karjat-Raigad 410201","9822999214"},
            {"","Mr. Salunkhe"," Near Abhinav Gyanmandir,Vitthal Nagar, Karjat-Raigad 410201","9270697173"},
            {"Vishal Xerox","Mr. Ashok Salgavkar","Near Ambedkar Chawk, Karjat-Raigad 410201","9271105142"},

            {"Rajguru Collection","Mr. Bharat Rajguru / Lokesh Rajguru","Shop No. 21, Heera Panna Complex, Alankar Talkies, Near Police Ground, Amrai Road, Karjat-Raigad 410201","9096573051 / 9096573646"},
            {"Parampara Collection"," Mr. Pravin","Shop No. 19, Heera Panna Complex, Alankar Talkies, Near Police Ground, Amrai Road, Karjat-Raigad 410201","7719898985"},
            {"D Fashion Den"," Mr. Kaushik wanjale","Madhumalti Apt., Deccan gym khana, Kaheri Road,  Karjat-Raigad 410201","9987636333 / 9272732979"},
            {"HD Planet","","Shop No/1., Heera Panna Shopping Plaza, Near Alankar Talkies, Amarai Road,Karjat-Raigad 410201","9028412100 / 9028792100"},
            {"Amardeep Collection"," Mr. L.S. Rajpurohit","Vaidya Sankul, shop No.5, Near IDBI Bank & Post Office,Karjat-Raigad 410201","9892454175 / 9881113575"},
            {"Rocki's NX","Mr. M.D.Joshi / Mr. S.D.joshi","Talkies Road, Amrai Road, Near Ambe Mata Mandir","7350197776 / 9552266111"},
            {"Shree Smarth Collection","Mr. Ravindra k. Bhalivde","Shop No.3.Near Govt. Hospital, Karjat-Raigad 410201","8805316408"},
            {"Raymomd Satnam Collection","Mr. Haresh Manjalani / Mr. Ravindra Kadav","14, Mithila Complex, Near Police Ground, Opp.Mhada Colony, Karjat-Raigad 410201","9821443780 / 9004981395"},
            {"Shree Collection","Mr. Rahul Dhamnase","Bidri Mension, In front of Amira Palace, Karjat-Raigad 410201","9665883162 / 9209612010"},
            {"Man-Mindir","Mr. M.G.Oswal","Station Road, Karjat-Raigad 410201","8087412074 "},
            {"Om Collection","Mr. Ankit","Bazar Peth, Near Mayura Hotel, Karjat-Raigad 410201","9028283055 "},
            {"Om Sai Collection","Mr. Jalram Jadhav / Mr. Banti Palkar","Bidri Mansion, In front of Amira Palace, Karjat-Raigad 410201","9271752406 / 7875245421 "},
            {"Shri Smaran Redymade Store","Mr. Sammer L. Hulawale","Vinayaki Apt.,Near Raigad Xerox, Karjat-Raigad 410201","9822248281"},
            {"Bhargav Collection","","Bazarpeth, Karjat-Raigad 410201","9112118575"},
            {" OM Collection","Mr. Ketan Virlani","Mahavir Peth, Karjat-Raigad 410201","9028542269"},
            {"Giyan Readymade Shop","Mr. Giyan Wadhwar","Bazarpeth, Machi Gali, Karjat-Raigad 410201","9881262315 "},
            {"Popular Cloth  Store","","Bazarpeth, Karjat-Raigad 410201","9890777657"},
            {"Parivar Cloth Store","Mr. Gandhi","Bazarpeth,  Karjat-Raigad 410201","02148-223321"},
            {"Shiv Mudra Collection","Mr. Devendra Zanze","Near Dr. Ambedkar Putala,  Karjat-Raigad 410201","9552898665"},
            {"Lucky's Mens Wear","","Bhusari Buldg.,Karjat-Raigad 410201","9766629245"},
            {"Jockey ","Mr. Mahesh","Near Alankar Talkies, Karjat-Raigad 410201","9623732345"},
            {"Mangalam Mens Wear","Mr. Harish","Manas Complex, Near Alankar Talkies, Karjat-Raigad 410201","8425096979"},
            {"Shree Samarth Krupa ","Mr. Nitin Deshmukh","Kotwal Nagar, Karjat-Raigad 410201","8605801616"},

            {"Sai Collection","Mr. Jitu","Bazar Peth, Station Road, Karjat-Raigad 410201","9028561884"},
            {"Mayuri Collection","","Mahavir complex, Opp. Madhura Kanchan, station Road, Karjat-Raigad 410201","9373766888"},
            {"Poonam Cloth Store","","Mahair Peth, Karjat-Raigad 410201","9422690094"},
            {"Priya Saree Center","Mr. Chintan / Mrs. Kirti","Mahair Peth, Karjat-Raigad 410201","9881185395 / 9420199301"},
            {"Tanush Saree Center","Mr. Avinash Shirke / Mr. Deepak Pawar","Vaidya Sankul, Shop No.3, Mahair Peth, Karjat-Raigad 410201","971890171"},
            {"New Fashion Collection","Mr. Vrushali Shinde","Mahavir Peth Near Paras Cloth,Karjat-Raigad 410201 ","9921603903"},
            {"Paras Cloth Store"," Mr. Prakash / Mr Manoj / Mr. Amit","Mahavir Peth, Karjat-Raigad 410201","9422339800 / 9921888111 / 9921711711"},
            {"Pankaj Cloth Stores","Mr. Pankaj Jain / Mr. Vikram jain","Mahair Peth, Karjat-Raigad 410202","9423375948 /  9423235247"},
            {"Memsaab Collection"," Mr. Dhanji","Mahair Peth, Station Road, Opp. State Bank, Karjat-Raigad 410201","8898727751"},
            {"DeepSangam Collection","Mr. Jayesh Mate / Mr. Lalit","Mahair Peth, Karjat-Raigad 410201","7875821530 / 9225711355"},
            {"Panchratana Saree Center"," Mr. Sajan / Mr. Yogesh","Mahavir Peth, Karjat-Raigad 410201","9421981666 / 9423377999"},
            {"Classic Kids & ladies Wear","Mr. Mahendra Kumavat","Shop No. 27-A, Mahair Peth, Karjat-Raigad 410201","9967241149 / 8796579404"},
            {"DeepLuxumi Saree Center","Mr. Ganesh Baban Patil","Vaidya Sankul, Near IDBI Bank, Near Post Office , Karjat-Raigad 410201","9822824466 / 7738244466"},
            {"Maitree Butic","Mr. Ashok Oswal / Mr. Ankit Oswal","Mahavir Peth, Karjat-Raigad 410201","9822430295 / 9011071117"},
            {"Vaidya Cloth Store","","Bazar Peth, Karjat-Raigad 410201","02148-221205 / 222204"},
            {"Mahavir Fabrics / Nilesh Textiles","Mr. Nilesh / Mr. Shailesh","Mahair Peth, Karjat-Raigad 410201","9422664735  / 9373773627"},
            {"Anjali Family Shop","Mr. Girdhari Purohit","Near Railway Station, Bazar Peth, Karjat-Raigad 410201","8087437357"},
            {"Bafana Cloth Store","","Mahavir Complex, Station Road,Karjat-Raigad 410201 ","02148-223011"},
            {"MahaLaxmi Kids & ladies wear","Mr. Prajapati ","Mhada Colony, Amarai Road, Karjat-Raigad 410201","9028469962"},
            {"Shweta Ladies Corner","Mr. Sumesh Shetye","Pratiksh Complex, Shop No. 12, Station Road, Bazar Peth,Karjat-Raigad 410201","9371481679 / 9028559907"},
            {"Kapaleshver Saree Center","Mr. Mandar. N. More / Mr. Nandakumar More","Vaidya Sankul, Mahavir Peth, Karjat-Raigad 410201","8975629197 / 9822078459"},
            {"Datta Redymade Cloth Store","Mr. Luxuman D. Hulawale","Near Dhapaya Mandir,  Karjat-Raigad 410201 ","9225489485"},
            {"Jalaram Matching & Ladies Wear","","Opp. Police Ground,  Karjat-Raigad 410201 ","8862009653 / 9503646966"},
            {"Pinto Dressess","Mr. Amrut Parmar","Shop No.5, Laxmi Narayan, Mahair Peth, Karjat-Raigad 410201","9975291877"},
            {"Pooja Collection","Mr. Dilip Oswal","Mahair Peth, Karjat-Raigad 410201","9028551079"},
            {"Ganpati Saree Center","Mr. Kalpesh Jain","Mahavir Peth, Karjat-Raigad 410201Mahair Peth, Karjat-Raigad 410201","9822664450"},
            {"Darshan Collection"," Mr. Girdhari Singh J. Purohit","Aamarai Road, Near Post Office,Karjat-Raigad 410201","8087437357 "},
            {"Roopam Dresses"," Mr. Rajesh Oswal","Station Road, Mahair Peth, Karjat-Raigad 410201","02148-220330 / 9604060771"},
            {"RajDhan Collectiom / Aashish Saree","Mr. Suresh Oswal","Mahair Peth, Karjat-Raigad 410201","9422693137 / 9421161492"},
            {"Parth Collection","Mr. Mayur Jain","Station Road, Bazar Peth, Mahair Peth, Karjat-Raigad 410201","7385028538 / 7350945794"},
            {"Lavanya Redymade ","Mr. Pandhrinath Deshmukh","Station Road, Patil Ali, Karjat-Raigad 410201","9850670488"},
            {"Sai Raj Ladies Collection","Mr. Bhushan Khandagale","Patil Ali, Near Hanuman Mandir,  Karjat-Raigad 410201","8108593831"},
            {"Vasudha Maching Center"," Mrs. Pushpa Hulawale","Bazapeth, Station Road, Karjat-Raigad 410201","9561716011"},
            {"PreetSangam Saree Center","Mr. Vikram Kothari","Mahavir Peth, Karjat-Raigad 410201","9881212025"},
            {"Poonam Saree Center"," Mr. Mahendra Solanki","Mahavir Peth,  Karjat-Raigad 410201","9270536731"},
            {"Shree Rushab Saree Center","Mr. Dinesh","Mahavir Peth, Karjat-Raigad 410201","8087784835"},
            {"Pushpak Saree Centre","Mr. S.B.Oswal","Mahavir Peth, Karjat-Raigad 410201","02148-223995"},
            {"Keseriya Saree Center","","Mahavir Peth, Karjat-Raigad 410201","9028551080"},
            {"Life Style","","Bazarpeth,  Karjat-Raigad 410201","9860991188"},
            {"Cotton Fasion","","Mithila Complex,Aamari Road, Near Amba Mata Mandir, Karjat-Raigad 410201","7363816244"},
            {"Fashion Planet","","Near Police Groudnd,Karjat-Raigad 410201","8551093949"},

            {"Domain Computers","Mr.Ulkesh Pednekar","Shop No.5, Sunrise Apt., Station Road ,Karjat, Dist-Raigad","9130437900 / 7387500555"},
            {"RC Intex Distributors","Mr.Suraj More","Ratneshwar Apt., Near UCO Bank, Kacheri Road,Karjat, Dist-Raigad","9325754050"},
            {"Gateway Computer Solution","","Shop No.6, Court View Apt., Opp karjat Court, Vitthal Nagar, Near Abhinav School, Karjat, Dist-Raigad","9209431888 / 9404810555"},
            {"Shyam Enterprises","Mr. PavanKumar Ramshevak Chavan","Ground floor, Ganesh Kripa Buld., kacheri road, Opp.Eco Bank,Karjat-Raigad","8291564737 / 9769400484"},
            {"Sky Line Computer Solution","Mr. Abhinav Khangate","Patil Ali,Station Road, Karjat-Raigad 410201","9595223621"},
            {"LINK Computer & cyber Café","","Shop No.11, Pratiksha Complex, Station Road, Karjat-Raigad 410201","9028342003"},
            {"Comptech Computer Education","Mr. Mali","Ratanraj Apt.,Dr. Ambedkar Chawk, Karjat-Raigad 410201","02148-221309 / 9373811316"},
            {"Bunti Cyber Café","Mr. Bunti","Bazarpeth,Dr. Ambedkar Chawk, Karjat-Raigad 410201","9373672110"},
            {"Baba Computer","Mr. Ajay","Amira States, Infront of Nagarpalika,Karjat-Raigad 410201","9226331224"},

            {"Tip-Top Shoe Mart","Mr. Mayur Joshi","Bazar peth, Karjat-Raigad 410201","02148-222592"},
            {"Raj Foothwear","","Nimkar Chawl, Station Road, Karjat-Raigad 410201","9028847118"},
            {"Amol Lader Work","Mr. Amol Zarekar","Patil Ali, Near Sunil Chikki, Karjat-Raigad 410201","8888069691"},
            {"Eman Ali Fatemanni Sons","","Bazarpeth, Karjat-Raigad 410201","9823877671"},
            {"Kumar Footware","Mr. Kumar Kukreja","Machii Gali Karjat-Raigad 410201","7757928704"},
            {"Parmar Footware","Mr.Naresh Parmar","Bazarpeth, Karjat-Raigad 410201","9969474188"},

            {"Jai Shree Ram","","Hira Panna Shopping palza, Alankar Theatre, Amarai Road, Karjat-Raigad 410201","9220779990 / 9221055319"},
            {"Shalimar Hardware "," Mr. Irfan J. Shaikh / Mr. Sadik S. Shaikh","Near Ice Factory, Khatik Ali, Sai Nagar, Karjat-Raigad 410201","8149837786 / 9850693599"},
            {"Pragati Hardware","Mr. Paresh Shah","Shop No.6, Edroos vila Apt.,Opp. Chatri-Kendra,Karjat-Raigad 410201","9422672122"},
            {"Asgarali Agencies","Mr. Aliasgar I. Dhariwala","Amatulla Manzil, Opp. Railway Station, Near S.B.I. Bank, Karjat-Raigad 410201","9822322015 / 02148-223091"},
            {"Shree Bhairav Fancy Hardware","Mr. Mahesh","Shop No. 14, Heera Panna Shopping Plaza, Amrai,Karjat-Raigad 410201","8976033507 / 9820477048 / 882894449"},
            {"Shree Nakoda Plywood & Aluminium","Mr. Kishan","Mahavir Peth, Ugale Heights Buld., Karjat-Raigad 410201","8007111421 / 8793171614"},
            {"Shri Sai Pumps ","Mr. A.B. Deshmukh / Mr. Dhananjay","kacheri Road, Karjat-Raigad 410201","9822401477 / 9272459412"},
            {"Venkatesh Fiber Works","Mr. Raju Walunj","Near Royal Garden, Murbad Road, Nana Mater Nagar, Mudre(Kh), Karjat-Raigad 410201","9850612224 / 9637454777"},
            {"Maharashtra Hardware","Mr. Bharat Rajguru","Shop No.1, Bhide House, Near Govt. Hospital, Baba Sasheb Ambedkar Chowk,Karjat-Raigad 410201","9028991982"},
            {"New Bharat Hardware","","Deepanjiali, Samarth nagar, Dahivali, Karjat-Raigad 410201","8419917888 / 7039828798 "},
            {"Shree Samarth Krupa Hardware","Mr. Bhagwan Khade","Shop No.4, Gaikar Apt., Kondivade Road, Dahivali,Karjat-Raigad 410201","8087153643"},

            {"Ankita Steel Corner","Mr. Arvind Jain","Near Govt. Hospital, Karjat-Raigad 410201","9209969002 / 02148-222709"},
            {"Shree Ashapura Steel","","Dr. Ambedkar Chawk, Bazar Peth,  Karjat-Raigad 410201","9673331783"},
            {"Palkar Steel","Mr. Chandakant R. Palkar","Near Dhapaya Mandir,  Karjat-Raigad 410201","9011864658"},
            {"MahaLaxumi Steel","Mr. LalaRam Patel","Mahavir Peth, Karjat-Raigad 410201","8446133500"},
            {"Pravin Steel Bahandar"," Mr. Pravin Oswal","Mahavir Peth, Karjat-Raigad 410201","9545268311"},
            {"Sha. Perajman Phulaji","Mr. Kalpesh Oswal","Bazararpeth, Karjat-Raigad 410201","9921022884"},
            {"Deshmukh Steel","Mr. Aravinda Deshmukh","Bazararpeth, Karjat-Raigad 410201","9823576762"},

            {"Vaman All Tyre Service","Mr. Vilas Dangre","At Dahivali, Karjat-Raigad 410201","9881036221 / 9271814442"},

            {"Prasad Motors","Mr. Sandeep Mavkar","Shop No.3, Swapandeep Buld., Deecan Gymkhana, Kacheri Road, Karjat-Raigad 410201","8446798584"},
            {"Shree Ganesh Motors","Mr. Girish Kumbhar","Shop No.3, Gokul Apt., Near shivaji Putal, Karjat-Raigad 410201","9029593578 / 9819459587"},
            {"Ashok Motors","","At.Post Ladivali, Karjat-Raigad 410201",""},
            {"Parshv Motors","Mr. Amol Oswal","Near Alibag Bank, Mahavir Peth, Karjat-Raigad 410201","9881888999"},
            {"Sanket Motors","Mr. Sanket Thakekar","Near Alankar Talkies,Karjat-Raigad 410201","9960825829"},
            {"Karjat Motors","","Near Abhinav Gyanmandir, Kotwal Nagar, Karjat-Raigad 410201","9637327733"},

            {"Suraj Auto Consultant","Mr. Dinesh M. Rawal","Arihant Tower, Amrai Road, Karjat-Raigad 410201","9270060660 / 9226319727"},

            {"New Look, Parlor","Mrs. Rekha Gholap","","9881408230"},
            {"Ramaj Ladies Parlor","","Amira States, Infront of Nagarpalika,Karjat-Raigad 410201","9421158386"},
            {"Choice Point, Parlor","Mrs. Shilpa Raval","Near Ambedkar Chawk, Karjat-Raigad 410201","8087742728"},

            {"Jai Deep Tailor","Mr. Joyti D. Dhakval","Near Hanuman Mandir, Patil Ali, Karjat-Raigad 410201","9220806021 / 9325862600"},
            {"Friends Tailor","Mr. Nitin Deshmukh","Infront of hanuman Mandir, Karjat-Raigad 410201","9881185123"},
            {"New Style Tailor","Mr. Sarafraj Patel","Near Dr.Gaikwad Hospital, Kotwal Nagar, Karjat-Raigad 410201","8983090092"},
            {"Rehan Tailor","Mr. Mahamad Aasif","Near Sunil Chikki, Patil Ali,Karjat-Raigad 410201","9923843033"},
            {"Sangita Tailor","Mrs. Sangita m. Jadhav","Prabhat Apt., Near Bank of Badodra, Patil Ali, Karjat-Raigad 410201","9225386778"},
            {"Ruchita Tailor","","Nimkar Apt., Infront of Karjat Railway Station, Karjat-Raigad 410201","9421161917 / 9403318581"},
            {"Pranjal Tailor"," Mr. Vilas Musale","Near KanyaSchool, Vitthal Nagar Karjat-Raigad 410201","9764036137"},
            {"Raigad Tailor","Mr. Manohar Lobhi","Near Dhapaya Mandir, Karjat-Raigad 410201","9270908043"},
            {"Aatif Tailor"," Mr. Anjum Patel","Patil Ali, Station Road, Karjat-Raigad 410201","9028571586"},
            {"Sai Raj LadiesTailor"," Mr. Bhushan Khandagale","Patil Ali, Near Hanuman Mandir,Karjat-Raigad 410201","8108593831"},
            {"Prasad Ladies Tailor & Matching Center","Mr. Smita Balu Navghane","Patil Ali, Near Hanuman Mandir,Karjat-Raigad 410201","9860181596"},
            {"Sangita Ladies Tailor","Mrs. Sangita Jadhav","Patil Ali, Near Hanuman Mandir,Karjat-Raigad 410201","9225386778"},
            {"Umesh ladies Tailor","Mr. Umesh Khangare","Patil Ali, Near Hanuman Mandir,Karjat-Raigad 410201","8975851317"},
            {"Shirke Ladies Tailor","Mr. Deepak Shirke","Mahavir Peth, Amrai Road, Karjat-Raigad 410201","8390891147"},

            {"Raigad Tailor","Mr. Manohar Lobhi","Near Dhapaya Mandir, Karjat-Raigad 410201","9270908043"},
            {"Shree Samarth Tailor","Mr. Ankush Dalvi","Sai Kiran Apt.,Near Amrapali, Kotwal nagar, Karjat-Raigad 410201","9028748207 / 9273746307"},
            {"Sarathk Tailor","Mr. Prasad Lad","Near Maruti Mandir, Patil Ali, Karjat-Raigad 410201","9260127703 / 9881847008"},
            {"Tushar Tailor","Mr. Tushar Dighe","Shree Ganesh Apt., Shop No.2, In front of RiceMeeal, Dahiwali-Vengaon Road, Dahiwali,  Karjat-Raigad 410201","9028569659"},
            {"Umesh Mens Tailor","Mr. Umesh Khangare","Patil Ali, Near Hanuman Mandir,  Karjat-Raigad 410201","8975851317"},
            {"New Bombay Tailor","Mr. Rajendra Deshmukh","Infornt of Maruti Mandir, Karjat-Raigad 410201","9604186481"},
            {"R.D. Tailor","Mr. Rajendra Dalvi ","Alankar Talkies, Amarai Road, Karjat-Raigad 410201","9970613651"},

            {"Padmavati chemist","Dinesh T. Solanki","solanakidinesh3569@gmail.com","9370820247"},
            {"Parmar medical","vijay s. parmar","vijaymohini13@gmail.com","9822656505"},
            {"vijay medical","deepak d. kulkarni","waceupsid@gmail.com","8390394829"},
            {"Rajendra medical","rakesh s. jain","rakeshj1212gmail.com","9822836350"},
            {"Gurudutt medical","vitthal n. joshi","abhishekjoshi.19@gmail.com","9822774170"},
            {"Trimurti medical","lilabai p. hargade","ndr.trimurti@gmail.com","9822089694"},
            {"Om medical","mamta a. oswal","adityaishaan2012@gmail.com","9764869084"},
            {"Mahavir medical","kamlesh a. jain","yatindra.jain11@gmail.com","9272304858"},
            {"Aniket medical","ramrao y. patil","patilaniket@yahoo.com","9420649699"},
            {"arihant medical","pravin j. oswal","pravinoswal1@gmail.com","9028548834"},
            {"prem medical","jabbirsingh purohit","","9920604854"},
            {"sneha medical","s.s patil","sandypatil09@yahoo.co.in","9970197769"},
            {"Ambikamedical","krishna g. patel","parthpatel1251196","9850756990"},
            {"janseva medical","mayur s. phadtare","mayurphadtare@yahoo.co.in","9822303356"},
            {"dhanwantari medical","sugandha g. patil","patilaniket@yahoo.co.in","9765048660"},
            {"gajanan medical","sirla s. patil","sandypatil09@yahoo.com","9970197769"},
            {"milan medical","madansingh rajpurohit","mdprajpurohit@gmail.com","9890143047"},
            {"om sai medical","shekar borade","borade.shekar4@gmail.com","9699666899"},
            {"priyanka medical","priyanka.t chavan","priyals.pc@gmail.com","9503674197"},
            {"shri.siddhivinayak medical","hema v. joshi","vivekjoshikjt@gmail.com","9372569992"},
            {"Varad Medical"," Amit J. Pawali","varadchemist@gmail.com","9970283640"},

            {"Meher Clinical Laboratory","Mr. Abid Khan","Shop No.8 Near Government Hospital, Samarath Buliding, Karjat-Raigad 410201","9422666178 / 02148-222224"},
            {"Nidan Clinical Laboratory","Mr. kalpesh Patel","Near Dr. Jain(Ratnaraj Hospital), Vitthal Nagar, Karjat-Raigad 410201","9850670407"},
            {"Perfect Clinical Laboratory","Mr. Ravindra Mane","Near Government Hospital,Karjat-Raigad 410201",""},

            {"Adarsh Laboratory","","67/541 Motilaal Nagar-1 Goregaon(W)",""},
            {"Breach Candy Hospitaland Research Center","","Bulabhai Desai Road,Mumbai 400 026","23633651 / 23685406"},
            {"Dr. Khandwalkar's Lab","","Hiren Shopping Centre Jnc.Off.S.v Road And M.G. Road,Goregaon (w)",""},
            {"Harkisandas Nurottamads Hospital Blood Bank","","Prathana Samaj Mumbai 400 004","23884015 / 2385555"},
            {"Sion Hospital Blood Bank","","Sion","24076381 / 24076389"},
            {"Ambika Blood Bank","","K.K. Smruti Apt.New Maneklal Estate,Ghatkopar(w),Mumbai 400086","25124322"},
            {"B.Y.L Nair Hospital","","Dr. A.L. NariRoad,Mumbai Central,Mumbai 400008","23098150"},
            {"Dr. Mahesh Mehta","","Kesar Kunj, Vasanji Lalji,Raod, Kandivli(w)",""},
            {"Hinduja National Hospital & Medical Research","","Veer Savarkar RoadMumbai 400 016","24452222 / 24451515"},
            {"Sir.J.J Groups Of Hospital","","Blood Bank,Byculla, Mumbai","23739400"},
            {"Ambaji Blood Bank","","Arjun Center,Govandi Station Rd.Mumbai 400088","25507553 / 25507555"},
            {"Cama & Albless Hospital Blood Bank","","Mahapalika Marg, Fort,Mumbai 400001","22611648 / 22620388"},
            {"Bhatia General Hospital","","Blood Bank, Chikhalwadi,J. Dadaji Road, Tardeo,Mumbai 400 034","56660000 / 56660666"},
            {"Indian Red Cross Soc. Blood Bank","","Shahid Bhagat Singh Marg,Mumbai 400 001","22663195 / 22663560"},
            {"Anviksha Pathological","","Laboratory Blood Bank,M.G. Road Ghatkopar,Mumba 400077","25136290"},
            {"Central Hospital Blood Bank","","Ulhasnagar,Thane 421003",""},
            {"Bhiwandi Blood Bank","","'A' Hera Shopping Center Bhiwandi,Thane 421 302",""},
            {"INHS Ashwini Blood Bank","","Colaba, Mumbai 400 005","22151666"},
            {"St. George's Hospital Blood Bank","","Regional Blood Bank,Near CST,Mumbai,400001","22620344"},
            {"Ashirwad Blood Bank","","Dadar T.T.Mumbai 4000014","24154826"},
            {"Chhartapati Shivaji Maharaj Rugnalaya","","Near kalwa Bus Dept.,Kalwa, Thane","25372774 / 25372775"},
            {"D.S Kothari Hospital Blood Bank","","C.P. Tank Road,Mumbai 400 004","22420957"},
            {"Jain Clinic Blood Bank","","Khadilkar Road,Kanadawadi, Girgaon,Mumbai 400004","23829308 / 23885866"},
            {"Sushilaben R Mehta & Sir Kikabai","","P Cardiac Blood Bank,Near Gandhi Mkt.,Mumbai","24035455-59"},
            {"BARC Hospotial Blood Bank","","Anushktinagar,Mumbai 400094","25563137-40 / 02522258065"},
            {"Civil Hospital Blood Bank","","Tembhi Naka Thane(w)","25471541-245"},
            {"E.S.I.S. Hospital","","E.S.I.S. Hospital","25645521"},
            {"Jaslok Hospital & Research Centre","","Dr. G. Deshmukh,Marg, Mumbai 400026","24939595"},
            {"Tata Blood Bank","",".J. Hospital Byculla(W),Mumbai","23756153"},
            {"Dr. Bharat Kansaria","","Samarth Bldg., Opp. Vyas Classes,Bajaj Road,Kandivli(W)",""},
            {"Cottage Hospital","","Jawahar 401 603,Thane","02520-222407"},
            {"Chinmayanand Charitable Trust Blood Bank","","Shastrinagar,Dombivali(w) 421202",""},
            {"G.T.Hospital Blood Bank","","L.T. Hospital, L.T Tilak,Marg. Crawford Market,Mumbai 400 001","2621464 / 22630560"},
            {"JVP Blood Bank Arenja","","Arcade, Sector 17,Vashi, Navi Mumbai","27894492 / 27890185"},
            {"Bombay Hospital Trust Blood Bank","","12 New Marine Lines,Mumbai 400 020","22067676-215"},
            {"Dr. B A Memorial Hospital Blood Bank","","Byculla, Mumbai 400 027","23791964"},
            {"Haematoloey Laboratory","","Parekh House, 14 Mama,Parmanand Marg,Mumbai 400004","23691297"},
            {"K.B. Bhabha Hospital","","Belgram Rod, Kurla(W),Mumbai 400070","26500241-245"},
            {"Bombay Hospital Trust Blood Bank","","12 New Marine Lines,Mumbai 400 020","22067676-215"},
            {"K.E.M. Hospital Blood Bank","","Parel, Mumbai","24136051 / 24131419"},
            {"K.J. Somaiya Blood Bank","","Sion, Mumbai 400022","24090253"},
            {"Lokamanya Tilak Municipal Hospital","","Sion, Mumbai 400 022","24076381 / 24095099"},
            {"Mahatama Gandhi Mission HospitalBlood Bank","","sector-18, Kamothe,Navi Mumbai 410209","27422459 / 27423002"},
            {"Padhe's Lab","","1/56 Smriti Bkdg. 4th Road, Tilak nagar,Goregaon(w)",""},
            {"Paras Pathological Laboratory & Blood Bank","","Malbar Hill, Mumbai 400006","23631017"},
            {"Parsi Gen. Hospital Blood Bank","","B. Desai Road,Mumbai","23633641"},
            {"Parang Shah Cottage Hospital","","Jawahar, Thane",""},
            {"Petit parsee General Hospital & Blood Bank","","B Petit Marg Cumballa Road, Mumbai","23685350"},
            {"Plasma Dignostic Laboratory & Blood Bank","","Kalyan Road, Dombivali,Thane 421201",""},
            {"Pooja Blood Bank","","Aroto House, P K Road, Nr. Mulund,Telephone Exchange,Mulund (w)","25693688"},
            {"Prince Ali Khan Hospital Blood Bank","","Aga Hall, Nesbit Road, Mazgaon,Mumbai 400010",""},
            {"Sarvoday Lab","","6/41 Unnath Nagar-3,M.G. Road, Goragaon(w)",""},
            {"JVP Blood Bank Arenja","","Arcade, Sector 17,Vashi, Navi Mumbai","27894492 / 27890185"},
            {"K.B. Bhabha Hospital","","Belgram Rod, Kurla(w),Mumbai 400070","26500241-245"},
            {"Rajawadi Municipal Hospital","","Blood Bank,Rajawadi, Ghatkopar","25094149"},
            {"Rashmi Blood Bank","","Ghatkopar 400 077","25135643 / 25135267"},
            {"Samarpan Blood Bank","","Swashraya Bidg.,Hingwala Lane, Ghatkopar,Mumbai 400 077","25111313 / 25100100"},
            {"Sankalp Blood Bank","","Riddhi Siddhi Apt.,Murbad Road.,Kalyan(w),Thane 421304",""},
            {"Sarvodaya Hospital Blood Bank","","Riffle range, L.B.S Marg,Ghatkopar(w) Mumbai","25152332"},
            {"Sewree T.B Blood Bank","","Jerbaiwadia Road., Sewree Mumbai,400015","24146993 / 24127015"},
            {"Sri Satya Sai Blood Bank","","Patkar Bulding Patkar Road,Dombivli 421201",""},
            {"Regional Blood Bank","","Near C S T, Mumbai,400 001","22620344"},
            {"Sushilaben R Mehta & Sir Kikabai","","P Cardiac Blood Bank,Near Gandhi Mkt.,Mumbai","24035455-59"},
            {"Tata Blood Bank","","",""},

            {"Devghare Constructions","Mr. Sudesh","At. Dahivali, Karjat-Raigad 410201","9823373857 / 9987436536"},
            {"Bhoir Constructions","Mr. Vasant Bhoir","Plot No.2, Sai Dhara, Bungalow, Samarth Nagar Society, At. Po. Dahiwali, Karjat-Raigad 410201","9272555777 / 9271573459"},{"ShivSaiConstruction","Mr. BhagvanShet Bhoir","Mittal Apt., Near Abhinav Mandir,Karjat-Raigad 410201","9881414888"},

            {"Jain Travels","Mr. Anuj Oswal / Mr. Madan Oswal / Alex Oswal","Shop No. 11, Arihant Towers, Mahavir Peth Road, Karjat, Dist-Raigad,jaintravels1987@gmail.com","9922327327 / 9822614399"},
            {"Veena World (Ashok Travels)","Mr. Swapnil kadam","Ashok Travels, Vitthak Nagar, Near Jain Hospital, karjat-410201,psp.ashoktavel@veenaworld.com","02148-220390 / 9004014166"},
            {"Raj Travels","Mrs. Kalpana R. Bodke","Siddhivinayak Plaza, Shop No.7, Opp Municipal Off.,Karjat-Raigad,kesarikarjat@gmail.com","7040042226 / 02148-222533"},
            {"Shri Varadvinayak Tours & Travels","Mr. Sanket Arun Yakar","Mudre -Khudra,Karjat-Raigad",""},
            {"OM Travels","Mr. R. Jaiswal","Deecan Gymkhana, Kacheri Road,Karjat-Raigad","9822509747 / 9527004176"},
            {"Bhavesh Toures","Bhavesh","Near Abhinav Gyanmandir,Vitthal Nagar, Karjat-Raigad 410201","703036777"},

            {"Mohite Consultancy","Mr. Sanjay Mohite","Gaikaar Building, gala No.1, near Dahivali Bridge, Dahivali, Karjat-Raigad 410201","9823465557 / 8149666222"},
            {"Shruti Concultancy LIC","","Near Govt.Hospital, Near Dr. Ambedkar Chawk,Karjat-Raigad 410201","02148-223535 / 9371133811"},
            {"Mehul Agency","","Mahavir Peth, Karjat-Raigad 410201","7875779779"},

            {"M/S. S.S.Sawant & Co.","Mr. Suresh S. Sawant","Shiv om Complex, B wing, Block No.2, Near Shetaki farm, Mudre(Budruk), Karjat-Raigad 410201","9822662684 / 02148-220667"},
            {"Bajaj Finance","Mr. Milind Sawant / Shiraji Sawant","Shivani Residency, Sr. No.19, Plot No.1, Mudre(brd),  Karjat-Raigad 410201","9987037396 / 9145145151"},

            {"Amrapali Hospital","Dr. Gaikwad Ramesh N.,M.B.B.S.,D.G.O.","Kotwal nagar, Karjat Raigad\ndrrameshgaikawad7@gmail.com","02148221576 / 9822665966"},
            {"Shree Narayan Hospital","Dr. Nazirkar Alka G.,M.D.(Obst.,Gynac.)","Near ice factory, Kapaleshwar mandir, Karjat\nndr.alka@gmail.com","02148222367 / 9822836185"},{"Shree Swaami Samarth Nursing Home","Dr. Parmar Satish, M.B.B.S.,MS(OBGY)","Ganesh Prasad,opp Z.P.Kanya School, Court Road, Karjat\ndrparmarsatish61@gmail.com","9923387712"},
            {"Phadke Hospital","Dr. Phadke Neelkanth S.,OBGY consultation, Surgery,Maternity Ultrsound","Kotwal nagar,Karjat, Raigad-410201\nnsphadke2gmail.com","02148223958 / 9822533646"},

            {"Ganesh Dental Clinic","Dr. Gaikwad Vaibhav G.,B.D.S.","Near saptapadi Hall,Swapnadip Apt,1st floor,C wing Shivaji chowk Karjat\nvaibhavtushar9@gmail.com","8446233797"},
            {"","Dr. Gaikwad Varsha G.,B.D.S.","Mohsim Arcade, 1st Floor, A-wing, Above Jain Medical Neral-410201,karjat raigad\ndrvarshagirishgaikwad@gmail.com","8446867648"},
            {"","Dr. Jain Ritesh,B.D.S.","Mahavir Complex,Above Reajendra Medical Store, Karjat station road, karjat Raigad\nJainritesh675@gmail.com","9271681166 / 9423200790"},
            {"Pankaj dental Clinic","Dr. Jain Shripal S.,B.D.S.","Baazar peth, station Rd, 1st floor,karjat\ndrshripal1980@yahoo.com","02148220882 / 9850545154"},
            {"Parmar Dental Clinic","Dr. Parmar Manish,B.D.S.","Arihant Tower,Mahavir peth, KARJAT\ndrmanish-parmar@yahoo.co.in","02148-693054 / 9822341431"},
            {"Shree multispeciality Dental Clinic","Dr Jangam Aditya G.,B.D.S.,DGDEMS","Ambavane bldg,1st Floor,Kacheri Road, Karjat,Raigad\ndr.adityajangam@gmail.com","02148-223658 / 9730486048"},

            {"Vignahartha Hospital","Dr.Adakmol Smita D.,M.B.B.S.","Dahiwali,KARJAT Front of shree ram bridge\ndrdeepaksadakamal@gmail.com","9594956880 / 928279482"},
            {"Shree Sai Hospital","Dr.Sable Rambhau B.,M.B.B.S.,MS. Orthopedic Surgeon","Om sai chandradoy Soc, Kotval Nagar, KARJAT","02148-222570 / 9822297433"},
            {"Sidhivinayak Hospital","Dr Vinay Sangle N.,M.B.B.S.,D-Ortho,PGDMILS","136,A2,Mhada Colony,KARJAT,Raigad\ndrvinaysangle@gmail.com","9881340402 / 9270768988"},

            {"Shree gajanan clinic","Dr Dalvi sangita H., B.H.M.S.","Ayodhya Apt.Dahivali,karjat Raigad\ndrsangitadalvi@gmail.com","02148-223070 / 9822559131"},
            {"Spandan Panchakarma Ayurved chikitsalay","Dr.Padte Dhanashri S.,B.H.M.S.",",Near nagar palika,karjat,sai Spurti Clinic,Hira Panna complex,Amrai Rd,KARJAT, Raigad\npadteswapnil@yahoo.co.in","9209203730"},
            {"Spandan Panchakarma Ayurved chikitsalay","Dr.Swapnil Padte C.,B.A.M.S.,(M.D.)","Near nagar palika,karjat,sai Spurti Clinic,Hira Panna complex,Amrai Rd,KARJAT, Raigad\npadteswapnil@yahoo.co.in","02151-2605615 / 9960357385"},

            {"Ashram Clinic","Dr. Kale geeta P.,B.A.M.S.","near Sunil chikki, At 2 post Karjat, Raigad","02148-222148 / 9096402470"},
            {"Ashram Clinic","Dr. Kale Prabhakar M.,B.S.A.M.","sai nagar near sunil chikki KARJAT Raigad\nPrabhakarkale@gmail.com","9975494747"},
            {"Spandan Panchakarma Ayurved chikitsalay","Dr. Padte swapnil C.,B.A.M.S.,(M.D.)","Near nagar palika,karjat, sai Spurti Clinic,Hira Panna complex,Amrai Rd,KARJAT, Raigad\npadteswapnil@yahoo.co.in","9209203730"},
            {"Shushrut Hospital","Dr.Dhavale Sunil T.,B.A.M.S.,M.S.","Above Dr.Sonawale clinic,Near Bhagwan Talkies\ndhavale.sunil21@gmail.com","02148-220144 / 9221528009"},

            {"S.P. Video Cable Network", "Mr. Paul", "Near Dr. Ambedkar Putala,Karjat-Raigad 410201", "9260100280 / 7040356399"}
    };

*/
    String dburl="https://fbasenotification-e419f.firebaseio.com/";
    Firebase firebase;
    DatabaseReference dbRef;
    public static Activity act;
    int page=0;  //to switch between direct search and gridview layout

    WebView adWv;
    dbHelper db;
    SQLiteDatabase dbase;

    SharedPreferences share;
    SharedPreferences.Editor ed;

    String url="";

    TextView tvChat,tvHome,tvArticles,tvRegister,tvNews;


    public static void ifnet(){
        act.finish();
        mcontext.startActivity(new Intent(mcontext,supermain.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        act=this;


        //sharedpreferences access
        share=getSharedPreferences(Prefs.StoreKey,Context.MODE_PRIVATE);
       // Toast.makeText(supermain.this, Prefs.StoreKey+" "+share.getString(Prefs.reg,"no"), Toast.LENGTH_SHORT).show();
        ed=share.edit();
        ed.putString(Prefs.reg,"yes").apply();




       /* if (getIntent().getExtras() != null) {

            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);

                if (key.equals("AnotherActivity") && value.equals("True")) {
                    Intent intent = new Intent(this, Contact.class);
                    intent.putExtra("value", value);
                    Log.d("MyFirebas","in getintent "+value);
                    startActivity(intent);
                    finish();
                }
            }
        }*/


        FirebaseMessaging.getInstance().subscribeToTopic("news");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermain);
        mcontext=this;

        tvChat=findViewById(R.id.tvChat);
        tvChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(supermain.this,ChatActivity.class));
            }
        });

        tvRegister=findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(supermain.this,registerBusiness.class));
            }
        });

        tvHome=findViewById(R.id.tvHome);
        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     startActivity(new Intent(supermain.this,registerBusiness.class));
                gridviewAdapter.gvi=0;
                gvadp=new gridviewAdapter(supermain.this,img,R.layout.gridview,str);
                gv.setAdapter(gvadp);
            }
        });

        tvNews=findViewById(R.id.tvNews);
        tvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(supermain.this,News.class));
            }
        });

        tvArticles=findViewById(R.id.tvArticles);
        tvArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(supermain.this,Articles.class));
            }
        });



        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);

        db=new dbHelper(this);
        dbase=db.getWritableDatabase();

        tvResultCount=findViewById(R.id.tvResultCount);

        bnvSupermain=(BottomNavigationView)findViewById(R.id.bnvSupermain);
        bnvSupermain.setItemIconTintList(null);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        //my admob app id=        ca-app-pub-4170269433133188~7483411963
       /* MobileAds.initialize(this, String.valueOf(R.string.admobapptestid));
        mAdView= (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
*/
        /*mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        Log.d("Tag",""+mInterstitialAd.isLoaded());*/
        adWv = (WebView) findViewById(R.id.adWv);

        ll = (LinearLayout) findViewById(R.id.activity_supermain);

        lvmain=(ListView)findViewById(R.id.lvmain);

        skInternetAvailable=Snackbar.make(this.findViewById(R.id.activity_supermain),"Internet Available !",Snackbar.LENGTH_INDEFINITE)
        .setAction("Refresh", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(supermain.this,supermain.class));
            }
        });
        skInternetNotAvailable=Snackbar.make(this.findViewById(R.id.activity_supermain),"Internet Not Available !",Snackbar.LENGTH_SHORT);

        mainWv = (WebView) findViewById(R.id.mainWv);
        mainWv.setWebViewClient(new WebViewClient());
        mainWv.getSettings().setJavaScriptEnabled(true);
        mainWv.loadUrl("file:///android_asset/test.html");
        img = getResources().obtainTypedArray(R.array.imgmain);

        gv = (GridView) findViewById(R.id.gvmain);
        gvadp = new gridviewAdapter(this, img, R.layout.gridview, str);
        gv.setAdapter(gvadp);
        actv = (AutoCompleteTextView) findViewById(R.id.actv);
        actv.setFocusable(false);
        actv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("logd", "ll");
                actv.setFocusable(true);
                actv.setShowSoftInputOnFocus(true);
                actv.setFocusableInTouchMode(true);
                actv.setText("");
                page++;
                //startActivity(new Intent(getApplicationContext(),sample.class));

                return false;
            }


        });

        aadp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        aadp.setNotifyOnChange(true);
        actv.setAdapter(aadp);
        dbRef = FirebaseDatabase.getInstance().getReference();

        ConnectivityManager conn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=conn.getActiveNetworkInfo();
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        //check internet connection here
        if(nf!=null && nf.isConnected()){
            //dbHelper db=new dbHelper(supermain.this);

            Query adHomeStatus=dbRef.child("Advertisements").child("adHome");
            adHomeStatus.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    fbase fb=dataSnapshot.getValue(fbase.class);
                    if(fb.getStatus().equals("yes")) {
                        adWv.loadUrl(fb.getUrl());
//                        adWv.reload();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //adWv.loadUrl("https://wwwkarjatonlinecom.000webhostapp.com/adhome.html");

            Log.d("logcon",nf.getExtraInfo()+" extra\n"+nf.getReason()+" reason\n" +
                    nf.getSubtypeName()+" subtype\n"+nf.getTypeName()+" type\n"+nf.getState()+" state\n"+nf.isConnected()+" connected");

            Snackbar sb=Snackbar.make(this.findViewById(R.id.activity_supermain),"Internet available. Online Mode !",Snackbar.LENGTH_SHORT);
            sb.show();




            //add all strsearch to firebase database
            //comment before running firebase download database query
  /*
                   fbase fb=new fbase();
                   for(int x=0;x<strSearch.length;x++) {
                if(strSearch[x].length==2){
                    fb.setShopname(strSearch[x][0]);
                    fb.setName("Not available");
                    fb.setAddress("Not available");
                    fb.setKeywords("");
                    fb.setUsername("");
                    fb.setPassword("");
                    fb.setPhone(strSearch[x][1]);
                }
                else{
                    fb.setKeywords("");
                    fb.setUsername("");
                    fb.setPassword("");
                    fb.setShopname(strSearch[x][0]);
                    fb.setName(strSearch[x][1]);
                    fb.setAddress(strSearch[x][2]);
                    fb.setPhone(strSearch[x][3]);

                }
                Log.d("fortest",""+strSearch.length+" "+x);
                firebase.child("business").push().setValue(fb);
               firebase.child("business1").push().setValue(fb);
            }

*/


        Query update=dbRef.child("aUpdate");
        update.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    fbase fb=dataSnapshot.getValue(fbase.class);
                    Log.d("update",fb.getUpdate());
                    if(fb.getUpdate().equals("yes")){
                        new AlertDialog.Builder(supermain.this).setTitle("Update your app !")
                                .setMessage("Update is availbale. Update the app ?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                        i.setData(Uri.parse("https://play.google.com/store/apps/details?id=harshad.mykarjat"));
                                        startActivity(i);
                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //firebase upload should be commented before running following business query
            //comment following entire query before uploading strsearch to firebase database
            Query query = dbRef.child("business");
            query.addValueEventListener(new com.google.firebase.database.ValueEventListener() {

                int i=0,j=0;

                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    //dbase.delete("user",null,null);
                    i=0;j=0;
                    aadp.clear();
                    strSearch=new String[(int)dataSnapshot.getChildrenCount()][5] ;

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        fbase pson = data.getValue(fbase.class);
                        aadp.add(pson.getName());
                        aadp.add(pson.getShopname());
                        aadp.add(pson.getAddress());
                        aadp.add(pson.getPhone());


                        strSearch[i][j]=pson.getShopname();
                        j++;
                        strSearch[i][j]=pson.getName();
                        j++;
                        strSearch[i][j]=pson.getAddress();
                        j++;
                        strSearch[i][j]=pson.getPhone();
                        j++;
                        strSearch[i][j]=pson.getKeywords();

                       // db.enterdata(pson.getName(),pson.getShopname(),pson.getAddress(),pson.getPhone());

                        Log.d("logg","inside i "+data.getKey()+" "+dataSnapshot.getChildrenCount());
                        j=0;
                        i++;

//                    a=pson.getadd();
//                    if(i==1){ b=pson.getadd();Log.d("logg","inside i eqil 1"+b);}
//                    if(i==2) c=pson.getadd();

                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //Toast.makeText(busGrid.this, "Error", Toast.LENGTH_SHORT).show();

                }
            });


          //  toast(""+strSearch.length);

        }
        else {

            adWv.loadUrl("file:///android_asset/ad.html");

            Snackbar sb=Snackbar.make(this.findViewById(R.id.activity_supermain),"Internet not available. Offline Mode !",Snackbar.LENGTH_SHORT);
            sb.show();

//            toast("inside else");
            //mainWv.addJavascriptInterface(new WebAppInterface(this),"Android");*/

            Context c = this;

            dbase=db.getWritableDatabase();
            String cq="select * from user";
            Cursor cursor=dbase.rawQuery(cq,null);
            strSearch=new String[cursor.getCount()][5];
//            Log.d("cursor","logged"+cq);
            for(int i=0;i<cursor.getCount();i++) {
                cursor.moveToNext();
                strSearch[i][0] = cursor.getString(0);
                strSearch[i][1] = cursor.getString(1);
                strSearch[i][2] = cursor.getString(2);
                strSearch[i][3] = cursor.getString(3);
                strSearch[i][4] = cursor.getString(4);
//                    Log.d("cursor", test[i]);

            }

            aadp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strdataMain);
            actv.setAdapter(aadp);

        }



        actv.addTextChangedListener(new TextWatcher() {
            String[][] temp = {{}};
            int counter=0,flag=0;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i0, int i1, int i2) {
                //  toast(""+charSequence+" "+i+" "+i1+" "+i2);
                counter=0;
                flag=0;


                for (int i = 0; i < strSearch.length; i++) {
                    //for (int j = 0; j < strSearch[i].length; j++) {

                      try {
                          if (strSearch[i][0].toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                                  strSearch[i][1].toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                                  strSearch[i][2].toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                                  strSearch[i][3].toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                                  strSearch[i][4].toLowerCase().contains(charSequence.toString().toLowerCase())) {
                              counter++;

                          }
                      }
                      catch(Exception e){
                          Log.d("catched",""+e.getMessage());
                    }

                    //}
                }

                Log.d("ontext",""+counter);
                temp=new String[counter][5];
                int a=0;
                for (int i = 0; i < strSearch.length; i++) {
                    //for (int j = 0; j < strSearch[i].length; j++) {
                      try {
                          if (strSearch[i][0].toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                                  strSearch[i][1].toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                                  strSearch[i][2].toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                                  strSearch[i][3].toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                                  strSearch[i][4].toLowerCase().contains(charSequence.toString().toLowerCase())) {
                              temp[a] = strSearch[i];
//                            Toast.makeText(getApplicationContext(), ""+temp[a]+"\n"+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                              a++;

                          }
                      }
                      catch (Exception e){
                          Log.d("catched",""+e.getMessage());
                      }

                   // }
                }

                adWv.setVisibility(View.GONE);
                gv.setVisibility(View.GONE);
                tvResultCount.setVisibility(View.VISIBLE);
                tvResultCount.setText("Number of Results : "+counter); //+" "+a);

                myadp2 = new myadapter2(supermain.this, R.layout.mylist2, temp);
                lvmain.setVisibility(View.VISIBLE);
                lvmain.setAdapter(myadp2);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String[][] temp = {{}};
            // String [][] temp=new String[1][1];


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                if(!parent.getItemAtPosition(position).toString().isEmpty()) {
                int a = 0;

                                  /*for (int i = 0; i < strEmergency.length; i++)
                            for (int j = 0; j < strEmergency[i].length; j++) {
                                if (strEmergency[i][j].contains(parent.getItemAtPosition(position).toString())) {
                                    temp[a] = strEmergency[i];
                                    a++;
                                }
                            }*/

                for (int i = 0; i < strSearch.length; i++) {
                    for (int j = 0; j < strSearch[i].length; j++) {
                        if (strSearch[i][j].contains(parent.getItemAtPosition(position).toString())) {
                            temp[a] = strSearch[i];
//                            Toast.makeText(getApplicationContext(), ""+temp[a]+"\n"+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                            a++;
                        }
                        if (a > 0) break;
                    }
                    if (a > 0) break;
                }

                if (a > 0) {
                    //myadp2 = new myadapter2(getBaseContext(), R.layout.mylist2, temp);
                    startActivity(new Intent(getApplicationContext(), resultSingleItem.class));
                    resultSingleItem.getArray(temp);
                    resultSingleItem.i = -1;
                }
            }
        });

        bnvSupermain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch ((item.getItemId())) {

                    case R.id.menuHome: {
                        gv.setAdapter(gvadp);
                    }
                    break;

                    case R.id.menuRegisterbusiness:{
                        startActivity(new Intent(supermain.this,registerBusiness.class));
                    }
                    break;

                    case R.id.menuChat: {
                        startActivity(new Intent(supermain.this, ChatActivity.class));
//                        startActivity(new Intent(supermain.this, GoogleSignInActivity.class));
                    }
                    break;
                }
                return true;
            }
        });
    }

    public void toast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }





    @Override
    public void onBackPressed() {
    /*    if(mainWv.canGoBack())
            mainWv.goBack();

        else
            super.onBackPressed();*/

        gv.setNumColumns(4);

        if(gridviewAdapter.gvi>0){
            gridviewAdapter.gvi--;
            gvadp=new gridviewAdapter(this,img,R.layout.gridview,str);
            gv.setAdapter(gvadp);
        }

        else if(page>0){
            page=0;
            adWv.setVisibility(View.VISIBLE);
            gv.setVisibility(View.VISIBLE);
            lvmain.setVisibility(View.GONE);
            tvResultCount.setVisibility(View.GONE);
        }
        else{
            startActivity(new Intent(this, ExitAdvertise.class));
            super.onBackPressed();
        }
        Log.d("supermain",""+page);

        /*if(gridviewAdapter.gvi==0)
            gv.setAdapter(gvadp);
        Toast.makeText(this, ""+gridviewAdapter.gvi, Toast.LENGTH_SHORT).show();*/

    }

    /*public class WebAppInterface {
        Context mContext;

        // Instantiate the interface and set the context
        WebAppInterface(Context c) {
            mContext = c;
        }

        // Show a toast from the web page
        @JavascriptInterface
        public void showToast(String toast) {
            //Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
            //if(toast.equals("12"))
              //  startActivity(new Intent(getApplicationContext(),Contact.class));
            if(toast.equals("sc")){
                result.i=1;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("jc")){
                result.i=2;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("rstrnt")){
                result.i=3;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("bk")){
                result.i=4;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("cake")){
                result.i=5;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("ic")){
                result.i=6;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("cafe")){
                result.i=7;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("swtmrt")){
                result.i=8;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("khnvl")){
                result.i=9;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("catr")){
                result.i=10;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("dairy")){
                result.i=11;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("mobile")){
                result.i=12;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("xerox")){
                result.i=13;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("mcloth")){
                result.i=14;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("fcloth")){
                result.i=15;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("pstudio")){
                result.i=16;
                Toast.makeText(mContext, "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),sample.class));
            }
            if(toast.equals("ccafe")){
                result.i=17;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("footware")){
                result.i=18;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("hardware")){
                result.i=19;
                startActivity(new Intent(getApplicationContext(),result.class));
            }
            if(toast.equals("steel")){
                result.i=20;
                startActivity(new Intent(getApplicationContext(),result.class));
            }


            //    startActivity(new Intent(getApplicationContext(),result.class));
        }
    }*/

}
