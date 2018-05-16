package harshad.mykarjat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public static int choice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*CountDownTimer ct=new CountDownTimer(300000,60000) {
            @Override
            public void onTick(long millisUntilFinished) {
                startActivity(new Intent(getApplicationContext(),advertise.class));
            }

            @Override
            public void onFinish() {

            }
        };

        ct.start();*/


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        String[] str={"one","two","three","twoone","onetwo"};
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //tabLayout.setSelectedTabIndicatorColor(Color.GREEN);
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
       }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */


        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        String[] str1 = {"one", "oneaddress", "1234567890"};
        String[] str2 = {"two", "twoaddress", "1234567890"};
        String[] str3 = {"three", "threeaddress", "1234567890"};
        String[] str4 = {"two", "fouraddress", "1234567890"};
        String[] strdata={"Karjat Police Station","02148-222100",
                "Neral Police Station","02148-238444",
                "Matheran Police Station","02148-230300",
                "Karjat Tehsil Office","02148-222037",
                "Karjat Panchayat Samiti","02148-222034",
                "Karjat Nagarpalika","02148-222033",
                "Neral Grampanchayat","02148-238445",
                "Karjat Railway Station","02148-222064",
                "Karjat ST Depo","02148-222085",
                "Karjat Post Office","02148-222023",
                "Govt. Ladies Hostel","02148-220605",
                "Karjat Govt Hospital","02148-222070",
                "Neral Govt Hospital","02148-238632",
                "Kashele Govt Hospital","02148-244049",
                "Kalamb Govt Hospital","02148-232244",
                "Fire Brigade Office","02148-202101",
                "Telephone Exchange Karjat","02148-220500",
                "MSEB Karjat","02148-223635",
                "MSEB Dahivali","02148-223630",
                "Sub-Distict Hospital","02148-222069",
                "Sarvajanik Ganeshotsav Mandal","02148-221101",
                "Thakkar Bharat Gas","02148-221641",
                "Pinto HP Gas Service","02148-222192",
                "Abhinav Dnyan Mandir","02148-222063",
                "Sharda Mandir","02148-222415",
                "English Medium School","02148-220798",
                "Good Shepherd Convent School","02148-651718",
                "Mahila Mandal Vidya Vikas Mandir","02148-223210",
                "Dombe Vidyaniketan","02148-221722",
                "Bhausaheb Raut Vidyalay","02148-225603",
                "Kokan Gyanpeeth Engineering College","02148-223289",
                "Tasgaonkar Engineering College","9594974906",
                "Royal Garden","02148-222704",
                "Shelke Mangal Karyalay","02148-223317",
                "Dhapaya Mangal Karyalay","9373510410",
                "Jai Ambe Mata Mandir, Bhisegaon","9771653141",
                "Matoshree Hall","9819174018",
                "Yashada Mangal Karyalay","02148-222151",
                "Rohit Mangal Karyalay, Diksal","9270058112",
                "Vrushab Garden","9921943394"};
        String[][] strEmergency = {{"Karjat Police Station","02148-222100"},
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
                {"MSEB Dahivali","02148-223630"}};
        String[][] strAmbulance={{"Sub-Distict Hospital","02148-222069"},
                {"Sarvajanik Ganeshotsav Mandal","02148-221101"}};
        String[][] strLPG={{"Thakkar Bharat Gas","02148-221641"},
                {"Pinto HP Gas Service","02148-222192"}};
        String[][] strSchColg={{"Abhinav Dnyan Mandir","02148-222063"},
                {"Sharda Mandir","02148-222415"},
                {"English Medium School","02148-220798"},
                {"Good Shepherd Convent School","02148-651718"},
                {"Mahila Mandal Vidya Vikas Mandir","02148-223210"},
                {"Dombe Vidyaniketan","02148-221722"},
                {"Bhausaheb Raut Vidyalay","02148-225603"},
                {"Kokan Gyanpeeth Engineering College","02148-223289"},
                {"Tasgaonkar Engineering College","9594974906"}};
        String[][] strWedHall={{"Royal Garden","02148-222704"},
                {"Shelke Mangal Karyalay","02148-223317"},
                {"Dhapaya Mangal Karyalay","9373510410"},
                {"Jai Ambe Mata Mandir, Bhisegaon","9771653141"},
                {"Matoshree Hall","9819174018"},
                {"Yashada Mangal Karyalay","02148-222151"},
                {"Rohit Mangal Karyalay, Diksal","9270058112"},
                {"Vrushab Garden","9921943394"}};
        String[][] strSearch={{"Karjat Police Station","02148-222100"},
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
                {"Vrushab Garden","9921943394"}
        };
        private AdView mAdView;
        private InterstitialAd mInterstitialAd;

        myadapter adp;
        emergAdapter eadp;
        myadapter2 adp2;
        ArrayAdapter<String> aadp;

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            /*final WebView wv=(WebView)rootView.findViewById(R.id.wv);
            //wv.setWebViewClient(new WebViewClient());
            wv.getSettings().setJavaScriptEnabled(true);
            wv.loadUrl("file:///android_asset/memergency.html");



            wv.setOnKeyListener(new View.OnKeyListener() {
                int i=0;
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if(keyCode==KeyEvent.KEYCODE_BACK && wv.canGoBack()){
                        i=1;
                        //Toast.makeText(getContext(), ""+wv.canGoBack(), Toast.LENGTH_SHORT).show();
                        wv.goBack();

                    }

                    Toast.makeText(getContext(), ""+i, Toast.LENGTH_SHORT).show();
                    if(i==0) getActivity().finish();


                    return false;
                }
            });*/

            // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
            //my admob app id=        ca-app-pub-4170269433133188~7483411963
            MobileAds.initialize(getContext(), String.valueOf(R.string.admobapptestid));
            mAdView= (AdView)rootView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

            final ListView lv = (ListView) rootView.findViewById(R.id.lv);
            adp = new myadapter(getContext(), R.layout.mylist, str1);
            lv.setAdapter(adp);


            if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                //              adp.clear();
                adp2 = new myadapter2(getActivity(), R.layout.mylist2, strLPG);
                //aadp=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,str2);
                lv.setAdapter(adp2);
            }

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                //            adp.clear();
                if(choice==1)
                    adp2 = new myadapter2(getActivity(), R.layout.mylist2, strAmbulance);
                else
                    adp2 = new myadapter2(getActivity(), R.layout.mylist2, strWedHall);
                lv.setAdapter(adp2);
            }

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                //            adp.clear();
//                eadp = new emergAdapter(getContext(), R.layout.mylist, strEmergency);
                if(choice==1)
                    adp2 = new myadapter2(getContext(), R.layout.mylist2, strEmergency);
                else
                    adp2 = new myadapter2(getActivity(), R.layout.mylist2, strSchColg);
                lv.setAdapter(adp2);
//                lv.setAdapter(eadp);
            }

           /* SearchView sv=(SearchView)rootView.findViewById(R.id.sv);
            sv.setIconified(false);
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                String [][] temp={{}};
                int a = 0;
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    //wv.setVisibility(View.GONE);
                    //lv.setVisibility(View.VISIBLE);
                    //Toast.makeText(getContext(), ""+newText, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < strEmergency.length; i++)
                        for (int j = 0; j < strEmergency[i].length; j++) {
                            if (strEmergency[i][j].contains(newText)) {
                                temp[a] = strEmergency[i];
                                a++;
                            }
                        }

                    eadp = new emergAdapter(getContext(), R.layout.mylist, temp);
                    lv.setAdapter(eadp);
                    return false;
                }
            });*/


            final AutoCompleteTextView atv=(AutoCompleteTextView)rootView.findViewById(R.id.atv);
            ArrayAdapter<String> adp=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,strdata);
            atv.setAdapter(adp);
            atv.setFocusable(false);
            atv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    atv.setFocusableInTouchMode(true);
                    return false;
                }
            });


            atv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                String [][] temp={{}};


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if(!parent.getItemAtPosition(position).toString().isEmpty()) {
                        int a = 0;
//                        wv.setVisibility(View.GONE);
                        lv.setVisibility(View.VISIBLE);

                        /*for (int i = 0; i < strEmergency.length; i++)
                            for (int j = 0; j < strEmergency[i].length; j++) {
                                if (strEmergency[i][j].contains(parent.getItemAtPosition(position).toString())) {
                                    temp[a] = strEmergency[i];
                                    a++;
                                }
                            }*/
                        for (int i = 0; i < strSearch.length; i++)
                            for (int j = 0; j < strSearch[i].length; j++) {
                                if (strSearch[i][j].contains(parent.getItemAtPosition(position).toString())) {
                                    temp[a] = strSearch[i];
                                    a++;
                                }
                            }

//                        eadp = new emergAdapter(getContext(), R.layout.mylist, temp);
                        if(a>0){
                            adp2 = new myadapter2(getActivity(), R.layout.mylist2, temp);
                            lv.setAdapter(adp2);
                        }
//                        lv.setAdapter(eadp);

                    }
                    else {
//                        eadp = new emergAdapter(getContext(), R.layout.mylist, strEmergency);
                        adp2 = new myadapter2(getActivity(), R.layout.mylist2, strEmergency);
                    }


                }
            });

            /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(), "touched"+position, Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse(parent.getItemAtPosition(position).toString())));
                    //str=(parent.getItemAtPosition(position).toString());
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:"+parent.getItemAtPosition(position).toString()));
                    //startActivity(i);


                }
            });*/



            return rootView;
        }



    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 7 total pages.
            if(choice==1)
                return 3;
            else
                return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    if(choice==1)
                        return "Administrative";
                    else
                        return "School/Colleges";
                case 1:
                    if(choice==1)
                        return "Ambulance/Hospital";
                    else
                        return "Mangal Karyalay";
                case 2:
                    return "LPG/Gas Agency";
            }
            return null;
        }
    }
}
