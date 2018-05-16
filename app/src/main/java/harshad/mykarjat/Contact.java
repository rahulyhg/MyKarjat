package harshad.mykarjat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Contact extends AppCompatActivity {
    TextView tvAdvertise;
    Button btnCall1,btnCall2;
    private AdView mAdView,mAdView1;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        //my admob app id=        ca-app-pub-4170269433133188~7483411963
        MobileAds.initialize(this, String.valueOf(R.string.admobapptestid));
        mAdView= (AdView) findViewById(R.id.adView);
        mAdView1= (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView1.loadAd(adRequest);

        tvAdvertise=(TextView)findViewById(R.id.tvAdvertise);
        btnCall1=(Button)findViewById(R.id.btnCall1);
        btnCall2=(Button)findViewById(R.id.btnCall2);

        btnCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:9595031739"));
                startActivity(i);
            }
        });

        btnCall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:8956107979"));
                startActivity(i);
            }
        });
    }
}
