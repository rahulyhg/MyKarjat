package harshad.mykarjat;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class advertise extends AppCompatActivity {
    WebView advWv;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);

  /*      advWv=(WebView)findViewById(R.id.advWv);
        advWv.setWebViewClient(new WebViewClient());
        advWv.getSettings().setJavaScriptEnabled(true);
        advWv.loadUrl("http://www.karjatonline.com");*/
        TextView tv=(TextView)findViewById(R.id.advTv);

        CountDownTimer ct=new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        ct.start();



    }
}
