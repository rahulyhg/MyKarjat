package harshad.mykarjat;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving an Intent broadcast.


//     String br=intent.getStringExtra(ConnectivityManager.CONNECTIVITY_ACTION);
//        if(br.equals(ConnectivityManager.EXTRA_NO_CONNECTIVITY))
//            Toast.makeText(context, "Time Tick", Toast.LENGTH_SHORT).show();
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
     //   Toast.makeText(context, ""+intent.getAction(), Toast.LENGTH_SHORT).show();
        if(ni!=null && ni.isConnected()){
            //Toast.makeText(context, "Internet available", Toast.LENGTH_SHORT).show();
            supermain.ifnet();
            supermain.skInternetAvailable.show();

        }
        else{
            //Toast.makeText(context, "Internet not available", Toast.LENGTH_SHORT).show();
            supermain.skInternetNotAvailable.show();
        }



        /*NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder nBuilder=new NotificationCompat.Builder(context);
        nBuilder.setSmallIcon(R.mipmap.ic_launcher);
        nBuilder.setContentTitle("Title of Notification");
        nBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        nBuilder.setContentText("This is the text in notification");
        nBuilder.setOngoing(false);
        nm.notify(1,nBuilder.build());*/
      //  Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
