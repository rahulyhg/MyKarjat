package harshad.mykarjat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class ExitAdvertise extends AppCompatActivity {

    WebView wv;

    private AdView mAdView,mAdView1;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit_advertise);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        //my admob app id=        ca-app-pub-4170269433133188~7483411963
        MobileAds.initialize(this, String.valueOf(R.string.admobapptestid));
        mAdView= (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        wv=(WebView)findViewById(R.id.wvExitAd);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
        //wv.loadUrl("http://www.google.com");
        wv.loadUrl("file:///android_asset/ad.html");
    }
}
