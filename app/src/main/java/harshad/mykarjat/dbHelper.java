package harshad.mykarjat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbHelper extends SQLiteOpenHelper {
    private static final String db_name="user.db";
    private static final int db_version=1;


    public dbHelper(Context context){
        super(context,db_name,null,db_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_create_query="create table user(name text,shopname text,address text,phone text)";
        db.execSQL(table_create_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);// create table again

    }

    String[][] showitems(){

        SQLiteDatabase dbase=getReadableDatabase();
        String query="select * from user";
        Cursor cursor=dbase.rawQuery(query,null);
        String[][] str=new String[cursor.getCount()][19];
        if(cursor.getCount()>0){
            for(int i=0;i<cursor.getCount();i++){
                cursor.moveToNext();
//                str=str+" "+cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+"\n";
                str[i][0]=cursor.getString(0);
                str[i][1]=cursor.getString(1);
                str[i][2]=cursor.getString(2);
                str[i][3]=cursor.getString(3);

                //Log.d("dbhelper",str[i][0]+" "+str[i][1]+" "+str[i][2]+" \n");
            }
        }
        return str;
    }


    void enterdata(String name,String shopname,String address,String phone){
        try{
            SQLiteDatabase dbase=getWritableDatabase();
            ContentValues values=new ContentValues();

            values.put("name",name);
            values.put("shopname",shopname);
            values.put("address",address);
            values.put("phone",phone);

            dbase.insert("user",null,values);

          // return "success";

        }
        catch (Exception e){
            Log.d("log",""+e.getMessage());
            //return "Failed"+e.getMessage();

        }
    }

}
