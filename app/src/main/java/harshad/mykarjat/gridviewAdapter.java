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
       // String[] food={"Snack Corner","Juice Center","Restaurants","Bakery","Cakes","Ice-Creams","Cafe","Sweetmarts","Khanaval","Caterers","Dairy Products"};
//        String[] shops={"Mobile","Xerox","Men Cloths","Women Cloths","Photo Studio","Cyber Cafe","Footware","Hardware","Steel Utensils"};
        String[] auto={"Tyres","2W Showroom","4W Showroom","2W Garage","4W Garage"};
//        String[] dneeds={"Supermarkets","Kirana","Vegetables/Fruits","Cable Operator/DTH"};
     //   String[] beauty={"Parlor","Saloon","Ladies Tailor","Gents Tailor"};
      //  String[] health={"Doctors","Medical/Chemist","Pathology","Blood Bank"};
        String[] hospitals={"Gynaecologist","Dentist","Orthopedic","Child Specialist","Cardiologist","Homeopathy","Ayurvedic","ENT","Dermatologist"};
//        String[] construction={"Building Material","Flooring","Steel","Marble","Polish","Wallpaper"};
     //   String[] emergency={};
//        String[] agents={"Real Estate","Driving License","Pan Card","Aadhar Card","Travel Agent","Stamp Vendor","LIC","Tax Consultant"};

        //21st May Start
        String[] health={"Doctors","Medical/Chemist","Pathology","Blood Bank","Ambulance","X-ray & Sonography","Vet.Doctors","MRI/CT Scan","Govt.Hospitals"};
        String[] education={"Preschool","School","ITI","Junior College","Senior College","Diploma","Engineering","Pharmacy","School Bus"};
        String[] classes={"Music","Acting","Karate","Typing","Yoga","Computer","Singing","Dance","Primary","Secondary","SSC","HSC","Diploma","Engineering","Commerce"};
        String[] wedding={"Brahman","Pooja Sahitya","Hall","Mandap","Caterers","Feta","Horse","Music Band"};
        String[] services={"Carpenter","Plumber","Painter","Water Filter","Electrician","Welder","Gavandi","Inverters","Refrigeration","LPG/Stove","Mobile","Computers","Software","Key Maker","Laundry",
                "Dry Cleaning","Tank Cleaning","Internet","Cable TV","Drivers","Borewell","Cyber","Maid Servants","Mess/Tiffin","Name Plate","Rubber Stamp","Waterproofing","Solar Products","Towing"};
        String[] suppliers={"Water","Building Material","News Paper","Vegetables","Fruits","Meat","Fish","Egg","Soil","Ice","Flowers","Labour"};
        String[] entertainment={"Theatre","Multiplex","Event Mgmt","Orchestra","Dancers","Cable N/W","Artists","Clubs"};
        String[] rentedservices={"Drivers","Cars","Tempo/Truck","Bus","Dumper","JCB","Costumes","Parking","Paying Guest","Tractor","Crane"};
        String[] food={"Snack Corner","Juice Center","Veg Restaurants","Non-Veg Restarurants","Bakery","Cakes","Ice-Creams","Cafe","Sweetmarts","Khanaval","Caterers","Dairy Products",
                "Chinese","Dhaba","Tea Corner"};
        String[] holidays={"Resorts","Farm Houses","Bunglows","Lodges","Hotels"};
        String[] shops={"Mobile","Xerox","Men Cloths","Women Cloths","Photo Studio","Cyber Cafe","Footware","Hardware","Steel Utensils","Bag House","Furniture","Spare Parts",
                "Sports Material","Stationary","Books","Opticals","Electronics","Plywood","Gift Shop","Music Instruments","Pet Shop"};
        String[] dneeds={"Supermarkets","Kirana","Vegetables","Fruits","Dairy","Fish","Meat","Bakery","Sweetmart","Juice Center","Ice-Cream"};
        String[] professionals={"Android Developer","Software Developers","Website Designer","Chartered Accountants","Advocates","Photographers","Security","Gym Intructor","Journalist","Interior Designer","PUC","Courier","Pest Control","Architect","Vastu Shastra","Dietician","Astrologer","Printing"};
        String[] emergency={"Police","Fire Brigade","Ambulance","Snake Friends","Towing","Gas Agency","Petrol Pump","Rescuers","Parking"};
        String[] govtoffices={"MSEB","Bus Depo","Railway Station","BSNL","Municipal Council","Tehsil Office","Prant Office","Panchayat Samiti","PWD Office","Post Office","Govt. Hospital","Survey Office","Civil Court","Agro Services"};
        String[] construction={"Building Material","Flooring","Steel","Marble","Polish","Wallpaper","Tiles/Ceramics"};
        String[] beauty={"Beauty Parlor","Saloon","Ladies Tailor","Gents Tailor","Mehendi","Tattoo","Hair Dresser"};
        String[] agents={"Real Estate","Driving License","Pan Card","Aadhar Card","Travel Agent","Stamp Vendor","Life Insurance","Tax Consultant","Vehicle Insurance","Medical Insurance","Passport","Loans"};

    //21st May end

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
                    //21st may

                    if(str.equals("Emergency")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.emergency);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, emergency));
                    }

                    if(str.equals("Govt. Offices")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.govtoffices);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, govtoffices));
                    }

                    if(str.equals("Education")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.education);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, education));
                    }

                    if(str.equals("Classes")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.classes);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, classes));
                    }

                    if(str.equals("Wedding")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.wedding);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, wedding));
                    }

                    if(str.equals("Services")) {
                        gvi=1;
                        Toast.makeText(getContext(), "Scroll down to see more services !", Toast.LENGTH_SHORT).show();
                        img=getContext().getResources().obtainTypedArray(R.array.services);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, services));
                    }

                    if(str.equals("Suppliers")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.suppliers);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, suppliers));
                    }

                    if(str.equals("Entertainment")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.entertainment);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, entertainment));
                    }

                    if(str.equals("Rented Services")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.rentedservices);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, rentedservices));
                    }

                    if(str.equals("Holidays")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.holidays);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, holidays));
                    }

                    if(str.equals("Professionals")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.professionals);
                        supermain.gv.setAdapter(new gridviewAdapter(getContext(),img, R.layout.gridview, professionals));
                    }


                    //end 21st may

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
                    if(str.equals("Shops")) {
                        gvi=1;
                        img=getContext().getResources().obtainTypedArray(R.array.shops);
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

                    if(str.equals("Important")) {
                        gvi=1;
                        MainActivity.choice=2;
                        getContext().startActivity(new Intent(getContext(),MainActivity.class));
//                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), R.layout.gridview, food));
                    }
                  /*  if(str.equals("Classes")) {
                        gvi=1;
                        result.i=-2;
                        getContext().startActivity(new Intent(getContext(),result.class));
//                        supermain.gv.setAdapter(new gridviewAdapter(getContext(), R.layout.gridview, food));
                    }*/


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