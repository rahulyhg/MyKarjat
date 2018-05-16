package harshad.mykarjat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by family on 16/6/17.
 */

public class emergAdapter extends ArrayAdapter {
    Context context;
    int layoutResourceId;
    String[][] data = null;



    public emergAdapter(Context context, int resource, String[][] objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
        this.layoutResourceId = resource;
    }

    static class myHolder {
        TextView txtName;
        TextView txtAdd;
        Button btnMobile;
        Button btnShare;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        myHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new myHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.mylistname);
            holder.txtAdd = (TextView) convertView.findViewById(R.id.mylistadd);
            holder.btnMobile = (Button) convertView.findViewById(R.id.mylistmobile);
            holder.btnShare=(Button)convertView.findViewById(R.id.btnShare);

            convertView.setTag(holder);


        } else {
            holder = (myHolder) convertView.getTag();
        }

        holder.txtName.setText(data[position][0]);
        holder.btnMobile.setText(data[position][1]);
        //holder.txtAdd.setText(data[position][2]);

        holder.btnMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+data[position][1]));
                getContext().startActivity(i);

            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,data[position][1]);
                getContext().startActivity(Intent.createChooser(i,"Share via..."));
            }
        });


        return convertView;
    }
}

