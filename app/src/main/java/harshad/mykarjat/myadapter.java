package harshad.mykarjat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by family on 16/6/17.
 */

public class myadapter extends ArrayAdapter {
    Context context;
    int layoutResourceId;
    String[] data = null;
    //List<string_item> data=null;

    public myadapter(Context context, int resource, String[] objects) {
    //public myadapter(Context context, int resource, List<string_item> objects) {
        super(context, resource, objects);
        this.context = context;
        //this.data = objects;
        this.data=objects;
        this.layoutResourceId = resource;
    }

    static class myHolder {
        TextView txtName;
        TextView txtAdd;
        Button btnMobile;
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

            convertView.setTag(holder);


        } else {
            holder = (myHolder) convertView.getTag();
        }

      //  string_item stringItem=data.get(position);

        holder.txtName.setText(data[position]);
        holder.btnMobile.setText(data[position]);
        holder.txtAdd.setText(data[position]);

        holder.btnMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+data[position]));
                getContext().startActivity(i);
            }
        });
/*
        holder.txtName.setText(stringItem.name);
        holder.btnMobile.setText(stringItem.mobile);
        holder.txtAdd.setText(stringItem.address);*/


        return convertView;
    }
}
