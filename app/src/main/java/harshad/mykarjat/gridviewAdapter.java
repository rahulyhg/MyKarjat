package harshad.mykarjat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by family on 16/6/17.
 */

public class gridviewAdapter extends ArrayAdapter<String> {


        private Context context;
        private int layoutResourceId;
        public  static int gvi=0;
        TypedArray img;


        String[] data;
        String[] localbus={"Karjat-CST","CST-Karjat","Karjat-Khopoli","Khopoli-Karjat","Karjat-Panvel Bus","Panvel-Karjat Bus"};
        String[] food={"Snack Corner","Juice Center","Restaurants","Bakery","Cakes","Ice-Creams","Cafe","Sweetmarts","Khanaval","Caterers","Dairy Products"};
        String[] shops={"Mobile","Xerox","Men Cloths","Women Cloths","Photo Studio","Cyber Cafe","Footware","Hardware","Steel Utensils"};
        String[] auto={"Tyres","2W Showroom","4W Showroom","2W Garage","4W Garage"};
        String[] dneeds={"Supermarkets","Kirana","Vegetables/Fruits","Cable Operator/DTH"};
        String[] beauty={"Parlor","Saloon","Ladies Tailor","Gents Tailor"};
        String[] health={"Doctors","Medical/Chemist","Pathology","Blood Bank"};
        String[] hospitals={"Gynaecologist","Dentist","Orthopedic","Child Specialist","Cardiologist","Homeopathy","Ayurvedic","ENT","Dermatologist"};
        String[] construction={"Building Material","Flooring","Steel","Marble","Polish","Wallpaper"};
        String[] emergency={};
        String[] agents={"Real Estate","Driving License","Pan Card","Aadhar Card","Travel Agent","Stamp Vendor","LIC","Tax Consultant"};


        public gridviewAdapter(Context context,TypedArray img, int layoutResourceId, String[] data) {
            super(context,layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
            this.img=img;
        // Toast.makeText(context, "const"+img.toString(), Toast.LENGTH_SHORT).show();
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;
            final View vrow=convertView;

            if (row == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);
                holder = new ViewHolder();
                holder.gvtv = (Button) row.findViewById(R.id.gvtv);

                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }


            //ImageItem item = data.get(position);
            holder.gvtv.setText(data[position]);

            holder.gvtv.setCompoundDrawablesWithIntrinsicBounds(0,img.getResourceId(position,-1),0,0);
            final String str=holder.gvtv.getText().toString();



            
            holder.gvtv.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    if(str.equals("Health")) {
                        gvi=1;

                      //  img=vrow.getResources().obtainTypedArray(R.array.health);
                        img=getContext().getResources().obtainTypedArray(R.array.health);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, health));
                    }
                    if(str.equals("Doctors")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.doctor);
                        //Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), img,R.layout.gridview, hospitals));
                    }
                    if(str.equals("Agents")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.agents);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, agents));
                    }
//                    if(str.equals("Automobiles")) {
                    if(str.equals("Local/Bus")) {
                        gvi=1;
                        supermain.gv.setNumColumns(2);
//                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), R.layout.gridview, auto));
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, localbus));
                    }
                    if(str.equals("Food")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.food);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), img,R.layout.gridview, food));
                    }
                    if(str.equals("Shop")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.shop);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), img,R.layout.gridview, shops));
                    }
                    if(str.equals("Construction")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.construction);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), img,R.layout.gridview, construction));
                    }
                    if(str.equals("Daily Needs")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.dneeds);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), img,R.layout.gridview, dneeds));
                    }
                    if(str.equals("Beauty")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.beauty);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), img,R.layout.gridview, beauty));
                    }
                    if(str.equals("Banks")) {
                        gvi=1;
                        result.i=0;
                        getContext().startActivity(new Intent(getContext(),result.class));
                        //supermain.gv.setAdapter(new gridviewAdapter(getContext(), R.layout.gridview, food));
                    }
                    if(str.equals("Emergency")) {
                        gvi=1;
                        MainActivity.choice=1;
                        getContext().startActivity(new Intent(getContext(),MainActivity.class));
//                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), R.layout.gridview, emergency));
                    }
                    if(str.equals("Important")) {
                        gvi=1;
                        MainActivity.choice=2;
                        getContext().startActivity(new Intent(getContext(),MainActivity.class));
//                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), R.layout.gridview, food));
                    }
                    if(str.equals("Classes")) {
                        gvi=1;
                        result.i=-2;
                        getContext().startActivity(new Intent(getContext(),result.class));
//                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), R.layout.gridview, food));
                    }


                    if(str.equals("Contact")) {
                        gvi=1;
                        getContext().startActivity(new Intent(getContext(),Contact.class));
                        //supermain.gv.setAdapter(new gridviewAdapter(getContext(), R.layout.gridview, food));
                    }

                    if(str.equals("Snack Corner")){
                        gvi=2;
                        result.i=1;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Juice Center")){
                        gvi=2;
                        result.i=2;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Restaurants")){
                        gvi=2;
                        result.i=3;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Bakery")){
                        gvi=2;
                        result.i=4;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Cakes")){
                        gvi=2;
                        result.i=5;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Ice-Creams")){
                        gvi=2;
                        result.i=6;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Cafe")){
                        gvi=2;
                        result.i=7;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Sweetmarts")){
                        gvi=2;
                        result.i=8;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Khanaval")){
                        gvi=2;
                        result.i=9;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Caterers")){
                        gvi=2;
                        result.i=10;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Dairy Products")){
                        gvi=2;
                        result.i=11;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }

                    if(str.equals("Mobile")){
                        gvi=2;
                        result.i=12;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Xerox")){
                        gvi=2;
                        result.i=13;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Men Cloths")){
                        gvi=2;
                        result.i=14;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Women Cloths")){
                        gvi=2;
                        result.i=15;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Photo Studio")){
                        gvi=2;
                        result.i=16;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
                        //getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Cyber Cafe")){
                        gvi=2;
                        result.i=17;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Footware")){
                        gvi=2;
                        result.i=18;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Hardware")){
                        gvi=2;
                        result.i=19;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Steel Utensils")){
                        gvi=2;
                        result.i=20;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Tyres")){
                        gvi=2;
                        result.i=21;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("2W Showroom")){
                        gvi=2;
                        result.i=22;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("4W Showroom")){
                        gvi=2;
                        result.i=23;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }

                    if(str.equals("2W Garage")){
                        gvi=2;
                        result.i=24;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
                        //getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("4W Garage")){
                        gvi=2;
                        result.i=25;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
                        //getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Parlor")){
                        gvi=2;
                        result.i=26;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Saloon")){
                        gvi=2;
                        result.i=27;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Ladies Tailor")){
                        gvi=2;
                        result.i=28;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Gents Tailor")){
                        gvi=2;
                        result.i=29;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Medical/Chemist")){
                        gvi=2;
                        result.i=30;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Pathology")){
                        gvi=2;
                        result.i=31;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Blood Bank")){
                        gvi=2;
                        result.i=32;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Building Material")){
                        gvi=2;
                        result.i=33;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Flooring")){
                        gvi=2;
                        result.i=34;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Steel")){
                        gvi=2;
                        result.i=35;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Marble")){
                        gvi=2;
                        result.i=36;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Polish")){
                        gvi=2;
                        result.i=37;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Wallpaper")){
                        gvi=2;
                        result.i=38;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Real Estate")){
                        gvi=2;
                        result.i=39;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Driving License")){
                        gvi=2;
                        result.i=40;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Pan Card")){
                        gvi=2;
                        result.i=41;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Aadhar Card")){
                        gvi=2;
                        result.i=42;
                        Toast.makeText(getContext(), "Sorry, no contacts yet !", Toast.LENGTH_SHORT).show();
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Travel Agent")){
                        gvi=2;
                        result.i=43;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Stamp Vendor")){
                        gvi=2;
                        result.i=44;
//                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("LIC")){
                        gvi=2;
                        result.i=45;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Tax Consultant")){
                        gvi=2;
                        result.i=46;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Gynaecologist")){
                        gvi=2;
                        result.i=47;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Dentist")){
                        gvi=2;
                        result.i=48;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Orthopedic")){
                        gvi=2;
                        result.i=49;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Child Specialist")){
                        gvi=2;
                        result.i=50;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Cardiologist")){
                        gvi=2;
                        result.i=51;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Homeopathy")){
                        gvi=2;
                        result.i=52;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Ayurvedic")){
                        gvi=2;
                        result.i=53;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("ENT")){
                        gvi=2;
                        result.i=54;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Dermatologist")){
                        gvi=2;
                        result.i=55;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }
                    if(str.equals("Cable Operator/DTH")){
                        gvi=2;
                        result.i=56;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }

                    /*if(str.equals("")){
                        gvi=2;
                        result.i=;
                        getContext().startActivity(new Intent(getContext(),result.class));
                    }*/

                    if(str.equals("Karjat-CST")){
                        gvi=2;
                        result_train.train_i=1;
//                        getContext().startActivity(new Intent(getContext(),result_train.class));
                    }
                    if(str.equals("CST-Karjat")){
                        gvi=2;
                        result_train.train_i=2;
//                        getContext().startActivity(new Intent(getContext(),result_train.class));
                    }
                    if(str.equals("Karjat-Khopoli")){
                        gvi=2;
                        result_train.train_i=3;
                        getContext().startActivity(new Intent(getContext(),result_train.class));
                    }
                    if(str.equals("Khopoli-Karjat")){
                        gvi=2;
                        result_train.train_i=4;
                        getContext().startActivity(new Intent(getContext(),result_train.class));
                    }

                }
            });

            return row;
        }
    
        

        static class ViewHolder {
            Button gvtv;
        }
    }