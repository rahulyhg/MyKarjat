package harshad.mykarjat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by family on 16/6/17.
 */

public class myadapter2 extends ArrayAdapter {
    Context context;
    int layoutResourceId;
    String[][] data = null;

    //List<string_item> data=null;

    public myadapter2(Context context, int resource, String[][] objects) {
        //public myadapter(Context context, int resource, List<string_item> objects) {
        super(context, resource, objects);
        this.context = context;
        //this.data = objects;
        this.data=objects;
        this.layoutResourceId = resource;
    }

    static class myHolder {
        TextView txtShop;
        TextView txtName;
        TextView txtAdd;
        Button btnMobile;
        Button btnShare;
        //int noContact=0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        myHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new myHolder();
            holder.txtShop=(TextView)convertView.findViewById(R.id.mylist2shop);
            holder.txtName = (TextView) convertView.findViewById(R.id.mylist2name);
            holder.txtAdd = (TextView) convertView.findViewById(R.id.mylist2add);
            holder.btnMobile = (Button) convertView.findViewById(R.id.mylist2mobile);
            holder.btnShare=(Button)convertView.findViewById(R.id.btnShare);

            convertView.setTag(holder);


        } else {
            holder = (myHolder) convertView.getTag();
        }

        //  string_item stringItem=data.get(position);

        if(data[position][0].isEmpty() || data[position][0].equals(null)) //checking if first value is empty..if yes make corresponding layout invisible
            //holder.txtShop.setVisibility(View.GONE);
            holder.txtShop.setText("...");
        else
        holder.txtShop.setText(""+data[position][0]);


        if(data[position].length>2)
        {
            if(data[position][1].isEmpty() || data[position][1].equals(null))
                //holder.txtName.setVisibility(View.GONE);
                holder.txtName.setText("...");
            else
                holder.txtName.setText(""+data[position][1]);

            if (data[position][2].isEmpty() || data[position][2].equals(null))
//                holder.txtAdd.setVisibility(View.GONE);
                  holder.txtAdd.setText("...");
            else
                holder.txtAdd.setText(""+data[position][2]);

            if (data[position][3].isEmpty() || data[position][3].equals(null)){
//                holder.btnMobile.setGravity(Gravity.CENTER);
                holder.btnMobile.setText("No Contact");

            }
            else
                holder.btnMobile.setText("  "+data[position][3]);
        }
        else{
            //holder.txtName.setVisibility(View.GONE);
            holder.txtName.setText("...");
//            holder.txtAdd.setVisibility(View.GONE);
            holder.txtAdd.setText("...");
            if (data[position][1].isEmpty() || data[position][1].equals(null)){
//                holder.btnMobile.setGravity(Gravity.CENTER);
                holder.btnMobile.setText("No Contact");

            }
            else
                holder.btnMobile.setText("  "+data[position][1]);
        }

        //click to call button


        holder.btnMobile.setOnClickListener(new View.OnClickListener() {
            String str,temp;
            String[] samp=new String[2];
            @Override
            public void onClick(View v) {
//                str=data[position][3];
                if(data[position].length>2 && data[position][3].isEmpty() || data[position].length<3 && data[position][1].isEmpty()){
                    Toast.makeText(getContext(), "Sorry! No Contact to Dial.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (data[position].length > 2)
                        str = data[position][3];
                    else
                        str = data[position][1];

                    if (str.contains("/")) {//for contact having two numbers
                        //  Toast.makeText(getContext(), "" + (str.indexOf('/') - 2), Toast.LENGTH_SHORT).show();
                        samp[0] = str.substring(0, str.indexOf('/'));  //first number
                        samp[1] = str.substring(str.indexOf('/') + 2, str.length());  //second number
                        new AlertDialog.Builder(getContext()).setSingleChoiceItems(samp, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getContext(), ""+which, Toast.LENGTH_SHORT).show();
                                if (which == 0) {  //if first number selected
                                    temp = str.substring(0, str.indexOf('/'));
                                } else {  //if second number selected
                                    temp = str.substring(str.indexOf('/') + 2, str.length());
                                }
                            }
                        }).setTitle("Select Number to dial:").setPositiveButton("Select", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(Intent.ACTION_DIAL);
                                i.setData(Uri.parse("tel:" + temp));
                                getContext().startActivity(i);
                                // Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
//                    Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                    }


                    //Toast.makeText(getContext(), str.substring(0,10)+"\n"+str.substring(13,23), Toast.LENGTH_SHORT).show();
                    else {
                        Intent i = new Intent(Intent.ACTION_DIAL);
                        i.setData(Uri.parse("tel:" + str));
                        getContext().startActivity(i);
                    }
                }
            }
        });


        //share contact details button click
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                if(data[position].length>2)
                    i.putExtra(Intent.EXTRA_TEXT,data[position][0]+"\n"+data[position][1]+"\n"+data[position][2]+"\n"+data[position][3]);
                else
                    i.putExtra(Intent.EXTRA_TEXT,data[position][0]+"\n"+data[position][1]);
                getContext().startActivity(Intent.createChooser(i,"Share via..."));
            }
        });
/*
        holder.txtName.setText(stringItem.name);
        holder.btnMobile.setText(stringItem.mobile);
        holder.txtAdd.setText(stringItem.address);*/


        return convertView;
    }
}