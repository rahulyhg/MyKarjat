package harshad.mykarjat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

public class sample extends AppCompatActivity {
    ListView lvsample;
    SearchView svsample;
    myadapter2 adp2;
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

    public void setadp(String[][] str){
        adp2=new myadapter2(this,R.layout.mylist2,str);
       lvsample.setAdapter(adp2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        lvsample=(ListView)findViewById(R.id.lvsample);
        svsample=(SearchView)findViewById(R.id.svsample);



        svsample.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String[][] temp=new String[1000][1000];

                int k=0;
            //    Log.d("hgd",""+newText);
                for(int i=0;i<strSearch.length;i++)
                    for(int j=0;j<strSearch[i].length;j++){
                        if(strSearch[i][j].contains(newText)) {
                            temp[k] = strSearch[i];
                            k++;
                        }
                    }
                Log.d("search","found "+newText+" "+k);
                if(k>0) setadp(temp);
               // result.getArray(temp);
//                result.i=-1;
//                startActivity(new Intent(getApplicationContext(),result.class));
                return false;
            }


        });


        Toast.makeText(this, "sample called", Toast.LENGTH_SHORT).show();
        /*NotificationManager nm=(NotificationManager).
                NotificationCompat.Builder nBuilder=new NotificationCompat.Builder((getApplicationContext()));
        nBuilder.setSmallIcon(R.drawable.ic_stat_name);
        nBuilder.setContentTitle("Title of Notification");
        nBuilder.setContentText("This is the text in notification");
        nBuilder.setOngoing(false);

        nBuilder.setContentIntent(home);*/


    }
}
