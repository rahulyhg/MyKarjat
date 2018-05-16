package harshad.mykarjat;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ListView;

import java.util.List;

public class result_train extends AppCompatActivity {
    ListView lvtrain;
    WebView wvtrain;
    public static int train_i=0;
    trainListAdapter tadp;

    @Override
    public void onBackPressed() {
        gridviewAdapter.gvi--;
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_train);

        wvtrain=(WebView)findViewById(R.id.wvtrain);
        wvtrain.loadUrl("file:///android_asset/KarjatKhopoli.html");

        lvtrain=(ListView)findViewById(R.id.lvtrain);
//        tadp=new trainListAdapter(this,R.layout.trainlist,toKhopoli);

        switch (train_i){
            case 1:
                break;
            case 2:
                break;
            case 3:
                String[][] KarjatKhopoli={
                        {"05.30 AM     KHOPOLI     S","Karjat-Khopoli"},
                        {"06.42 AM     KHOPOLI     S","CST-Khopoli"},
                        {"08.15 AM     KHOPOLI     S","Karjat-Khopoli"},
                        {"09.44 AM     KHOPOLI     S-Diva","CST-Khopoli"},
                        {"10.55 AM     KHOPOLI     S","Karjat-Khopoli"},
                        {"12.05 PM     KHOPOLI     S","Karjat-Khopoli"},
                        {"01.15 PM     KHOPOLI     S","Karjat-Khopoli"},
                        {"02.16 PM     KHOPOLI     S","CST-Khopoli"},
                        {"03.27 PM     KHOPOLI     S","Karjat-Khopoli"},
                        {"05.19 PM     KHOPOLI     S-Mulund","CST-Khopoli"},
                        {"07.27 PM     KHOPOLI     S","CST-Khopoli"},
                        {"08.34 PM     KHOPOLI     S","Karjat-Khopoli"},
                        {"09.40 PM     KHOPOLI     S","Karjat-Khopoli"},
                        {"10.41 PM     KHOPOLI     S-Diva","CST-Khopoli"},
                        {"11.56 PM     KHOPOLI     S-Bhandup","CST-Khopoli"}};
                tadp=new trainListAdapter(this,R.layout.trainlist,KarjatKhopoli);
                break;
            case 4:
                String[][] KhopoliKarjat={{"12.30 AM     KARJAT     S","Khopoli-Karjat"},
                        {"06.15 AM     CST        F-Mulund","Khopoli-CST"},
                        {"07.20 AM     KARJAT     S","Khopoli-Karjat"},
                        {"09.19 AM     CST        F-Diva","Khopoli-CST"},
                        {"10.20 AM     KARJAT     S","Khopoli-Karjat"},
                        {"11.30 AM     KARJAT     S","Khopoli-Karjat"},
                        {"12.40 PM     KARJAT     S","Khopoli-Karjat"},
                        {"01.50 PM     CST        F","Khopoli-CST"},
                        {"02.50 PM     Karjat     S","Khopoli-Karjat"},
                        {"04.28 PM     CST        S","Khopoli-CST"},
                        {"06.02 PM     CST        F-Bhandup","Khopoli-CST"},
                        {"08.01 PM     KARJAT     S","Khopoli-Karjat"},
                        {"09.07 PM     KARJAT     S","Khopoli-Karjat"},
                        {"10.15 PM     CST        S","Khopoli-CST"},
                        {"11.20 PM     KARJAT     S","Khopoli-Karjat"}};
                tadp=new trainListAdapter(this,R.layout.trainlist,KhopoliKarjat);
                break;
        }
        lvtrain.setAdapter(tadp);

    }



}
