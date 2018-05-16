package harshad.mykarjat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class foodmain extends AppCompatActivity {

    ListView lstFoodMain;
    String[][] snackCorner={{"Dagade Vadewale","Mr.Chandrakant V.Dagade","Gujarati Wada, Kacheri Road, Karjat-Raigad 410201","9011778442"},
            {"Ganesh Tea House","Mr.Krishna Bhoir","Kacheri Road, Karjat-Raigad 410201","9527282543"},
            {"Ravi Tea House","Mr.Ravindra Gholap","Mahavir Peth, Karjat-Raigad 410201  ","9225685887"},
            {"Sattu vadewale","Mr.Santosh Dabhane","Near Dr.Ambedkar Putala, Karjat-Raigad 410201","9371899979 / 9049098999"},
            {"Om Sai Ram Tea & Snack","Mr.Veer","Aamrai Road, Near Alankar Talkies, Karjat-Raigad 410201","8237133488"},
            {"Ulhas Uphargruha","Mr.Ramshet Hagavane","Amrai Road, Karjat-Raigad 410201","7507861303"},
            {"Sai Idli Center","Mr.Sudhakar Bangesh","Kacheri Road, Karjat-Raigad 410201","9673150173  "},
            {"Ramesh Lunch Home","","Kacheri Road, Karjat-Raigad 410201",""},
            {"Hanuman Uphar Gruha","Mr.Vinayak Mavkar","Karjat-Raigad 410201","9822426788"}};
    String[][] juiceCenter={{"Bhairvanath Raswanti","Mr. Ramdas Phanse","Bazapeth, Karjat-Raigad 410201","9372749546"}};
    String[][] restaurant={{"Hotel Devki","Mr.Rakesh Gupta","Near Dr. Ambedkar Putala, Karjat-Raigad 410201","9764848938"},
            {"Samadhan Hotel","Mr. Surendra Deshmukh","Kotwal Nagar, Karjat-Raigad 410201","9021899944"},
            {"Hotel Jay Ganesh","Mr. Arjun Kadu","Amira States, Infront of Nagarpalika,Karjat-Raigad 410201","8149676802"},
            {"Hotel Shivam","Mr.Deshmukh","Tilak Chowk, infron of Post Office, Karjat, Raigad ","02148-221515 / 9850056060"},
            {"Hotel New Santosh","Mr. Pankaj Vansant Bedekar","Near Railway station, Near UCO Bank, Kacheri Road, Karjat-Raigad-410201","9960999469 / 9970932929"},
            {"Hotel Dataray","Mr.Ganesh D.Yadav","Deecan Gymkhana, kacheri Road, Karjat-Raigad-410201","7588461608 / 9823217711"},
            {"Suleman Restaurant & Caterers","Mr. Mohammad Bhai","Shivaji Chowk, Kacheri road, Karjat-Raigad-410201","9595081093 / 9823310200 / 9028717478"},
            {"Hotel Satakar","Mr.Shankar Thombre","Shivaji Chowk, Kacheri road, Karjat-Raigad-410201","9422083631 / 7304261899"},
            {"The CBC Residency ","","Karjat-Murbad Highway, Mudre(BK)., Karjat-Raigad-410201","9326106060"},
            {"A-one Food Center","Mr. Kisan Dhisana","Patil Ali, Near Hanuman Mandir,  Karjat-Raigad 410201","8408991616"},
            {"Hotel shree Samarth Krupa","Mr. Rajendra Gaikar","Kacheri Road, Karjat-Raigad 410201","8793722274"},
            {"Patil Hotel","Mr. Anant Patil","Bazapeth, Karjat-Raigad 410201","8983330467"},
            {"Mayura Hotel","Mr. Mayur Joshi","Bazapeth, Karjat-Raigad 410201","02148-222926"},
            {"Hotel Tanmay","Mr. Rajesh Bhoir","Near Kapaleshvar Mandir, Karjat-Raigad 410201","8087578330"}};
    String[][] bakery={{"New Monali Bakery","Mr.VikasTupe/Mr.PrakashTupe","Near Shree Ram Bridge,Amrai,Karjat-Raigad 410201","9225733179"},
            {"Shree Kirsha Ayangar Bakery","","",""},{"Meher Bakery","Mr.Meher","Near Hanuman Mandir,Karjat-Raigad 410201","9371207527"}};
    String[][] cake={{"Monginis Cakes & Bakery","","Mahavir Peth, Karjat-Raigad 410201","9225733179 / 9270917884"}
    };
    String[][] icecream={{"New Santosh Ice-Cream Center","Mr. Mahesh shiram Thakur","Shiv Sai Darshan, Shop No. 4, Near Abhinav Gyan Mandir School, Karjat-Raigad 410201","9527282627 / 9850653675"},
            {"Vaidya ICE- Cream","Mr. Yogesh Vaidya","Near Govt. Hospital, Ambedkar Chawk, Bhide wada, Karjat-Raigad 410201","9822558264 / 9370631502"},
            {"Agrawal ICE-Cream Center","Mr. Sonu Agrawal","Shivaji Putal, Deecan Ghymkhana, Kacheri Road, Karjat-Raigad 410201","9021244445"},
            {"Sai Shraddha Cool Point Coffe","Mr. Milind Gholap","Near Kapaleshver Mandir, Karjat-Raigad 410201","9028555128 / 9595958582"},
            {"Siddhivinayak ICE-Cream Parlor","Mr. Pankaj Badekar","Sidhakala Apt., Near Ambedkar Chowk,Karjat-Raigad 410201","705935999"},};
    String[][] cafe={{"","","",""}};
    String[][] sweetmart={{"Bikaner Sweets & farsan","Mr. Sameer Patil","Shop No. 8/9, Hirapanna shopping Palza, Near Alankar Theatre, Amarai Road, Karjat-Raigad 410201","8149947944"},{"Maharaja Sweet","Mr. Prakash Prajapati","Infront of Marathi School,  Karjat-Raigad 410201","9372489062"},
            {"Puna Sweet","Mr. Nivruti Sawant","Shop No.10, Police Ground, Amarai Road, Mithila nagari,  Karjat-Raigad 410201","9260093603"},
            {"Laxumi Sweets","Mr. Suhas Gawade ","Kacheri Road, Bhaji Market,Karjat-Raigad 410201","9881023366"},
            {"Pooja Sweets","Mr. Amit Gupta","Bazarpeth, Karjat-Raigad 410201","9028626056"},
            {"Bombay Sweets Mart","Mr. Satish Gupta","Bazarpeth, Karjat-Raigad 410201","9373697335"},
            {"MabhaDeva Sweets","Mr. RamSingh Pardeshi","Near Arihant Towar, Mahavir Peth, Karjat-Raigad 410201","7798811515"},
            {"Balaji Sweets","Mr. Amrutlala Joshi","Bazarpeth, Station Road, Karjat-Raigad 410201","7219289994"},
            {"Trupti Sweetmart","Mr. Ravindra Kashelikar","Bazarpeth, Karjat-Raigad 410201","9226366472"},
            {"Karjat Sweet Mart","Mr. Prajapati","Bazarpeth, Karjat-Raigad 410201","9822665733"},
            {"Vaidya sweet Mart","Mr. Yogesh Vaidya"," Near Govt. Hospital, Ambedkar Chawk, Bhide wada, Karjat-Raigad 410201","9822558264 / 9370631502"},
            {"R.K.Sweets","Mr. Ritesh Gupta","Kacheri Road, Karjat-Raigad 410201","777697665"},};
    String[][] khanaval={{"Aai Ekvira Khanaval","Mr. Sanjivani Mhase","Near Maruti Mandir, Karjat-Raigad 410201","9225492524 / 7387202033"}};
    String[][] caterer={{"AYAN Caterers","Mr. Shafiq Khan / Qaum Khan","Mayur Apt., Shop No. 8 Sunil Chicki Patil Ali, Karjat-Raigad 410201","7507267609 / 7040287324"},
            {"BISMILLAH Caterers","Mr. Mohd. Israil / Mohd. Ashique Ali","Sunder Kalpana Building, Dahivali, Kondewade Road, Karjat-Raigad.","7506799873 / 8087548006"}};
    String[][] dairy={{"Shree Smaran"," Mr. Rushikesh Arun Gaikwad","Kitwal Nagar, Mahada Colony, Karjat-Raigad 410201","9657728228"},
            {"Mateshavari Dairy","Mr. K.P.Patel / Mr. P. Patel","Shop No.7, Nemenath Residency Mudre Road, Karjat-Raigad 410201","7507827607 / 8446872522"},
            {" Deepak Agency","Mrs. Chaha Manohar Rajmane","Infornt of Shivaji Putala, Kacheri Road, Karjat-Raigad 410201","8237679050"},
            {"Kajal Dairy"," Mr.Hanuman Badekar"," Kacheri Road, Karjat-Raigad 410201","9130262122"},
            {"Om Sai Dairy","Mr. Ashok More","Kacheri Road, Karjat-Raigad 410201","9029025290"},
            {"MahaLaxmi Dairy","Mr. Akshay Sonawale","Kacheri Road, Karjat-Raigad 410201","9689538890"},
            {"Sawariya Dairy","Mr. Tagir","Pratibha Apt.,Ambedkar Chawk, Karjat-Raigad 410201","9769828702"},
            {"Om SaiRam","Mr. Suresh Dalvi","Dhyapaya Mandir Road, Karjat-Raigad 410201","9158941516"},
            {"Pooja Milk Dairy","Mr. Manohar Patil","Hira Panna Shoping Plaza, Amarai Road, Karjat-Raigad 410201","7875222555"},
            {"Gyatri Milk Dairy","Mr. Mukesh Purohit","Near Alankar Talkies, Amarai Road, Karjat-Raigad 410201","9028564080"},
            {"Diksha Dairy","Mr. Ravindra Karad","Kotwal Nagar, Karjat-Raigad 410201","8287777491"}};

    myadapter2 adpfood;
    public static int i=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmain);
//        Toast.makeText(this, "Foodmain", Toast.LENGTH_SHORT).show();
//        Log.d("food","foodmain");

        lstFoodMain=(ListView)findViewById(R.id.lstFoodMain);
        adpfood=new myadapter2(this,R.layout.mylist2,snackCorner);
        switch(i){
            case 1:adpfood=new myadapter2(this,R.layout.mylist2,snackCorner);break;
            case 2:adpfood=new myadapter2(this,R.layout.mylist2,juiceCenter);break;
            case 3:adpfood=new myadapter2(this,R.layout.mylist2,restaurant);break;
            case 4:adpfood=new myadapter2(this,R.layout.mylist2,bakery);break;
            case 5:adpfood=new myadapter2(this,R.layout.mylist2,cake);break;
            case 6:adpfood=new myadapter2(this,R.layout.mylist2,icecream);break;
            case 7:adpfood=new myadapter2(this,R.layout.mylist2,cafe);break;
            case 8:adpfood=new myadapter2(this,R.layout.mylist2,sweetmart);break;
            case 9:adpfood=new myadapter2(this,R.layout.mylist2,khanaval);break;
            case 10:adpfood=new myadapter2(this,R.layout.mylist2,caterer);break;
            case 11:adpfood=new myadapter2(this,R.layout.mylist2,dairy);break;
        }
        lstFoodMain.setAdapter(adpfood);
    }
}
