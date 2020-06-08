package com.example.medicareapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper implements Serializable {
    public static final String Database_name = "logs.db";
    public static final String table_1 = "log_data";
    public static final String table_2 = "current_log";
    public static final String col_0 = "id";
    public static final String col_1 = "log_name";
    public static final String col_2 = "image_name";

    public static final String table_3 = "recent_accessed_logs";


    public DatabaseHelper(Context context) {
        super(context, Database_name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
      //  delete_db();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + table_1 + " (id INTEGER PRIMARY KEY,log_name VARCHAR(20), image_name VARCHAR(20))");
        db.execSQL("create table " + table_2 + " (id INTEGER PRIMARY KEY ,log_name VARCHAR(20), image_name VARCHAR(20))");
        db.execSQL("Insert into "+ table_1+" values (1,'Height','height_icon')");
        db.execSQL("Insert into "+ table_1+" values (2,'Weigth','weight_icon')");
        db.execSQL("Insert into "+ table_1+" values (3,'Blood_pressure','blood_pressure_icon')");
        db.execSQL("Insert into "+ table_1+" values (4,'Heart_rate','heart_rate_icon')");
        db.execSQL("Insert into "+ table_1+" values (5,'Blood_glucose','blood_glucose_icon')");
        db.execSQL("Insert into "+ table_1+" values (6,'Exercise','exercise_icon')");
        db.execSQL("Insert into "+ table_1+" values (7,'Food_intake','food_intake_icon')");
        db.execSQL("Insert into "+ table_1+" values (8,'Temperature','temperature_icon')");
        db.execSQL("Insert into "+ table_1+" values (9,'Symptoms','symtoms_icon')");
        db.execSQL("Insert into "+ table_1+" values (10,'SPO2','spo_icon')");
        db.execSQL("Insert into "+ table_1+" values (11,'Thyroid','thyroid_icon')");
        db.execSQL("Insert into "+ table_1+" values (12,'Blood_grp','blood_group_icon')");

        db.execSQL("Insert into "+ table_2+" values (1,'Height','height_icon')");
        db.execSQL("Insert into "+ table_2+" values (2,'Weigth','weight_icon')");
        db.execSQL("Insert into "+ table_2+" values (4,'Heart_rate','heart_rate_icon')");
        db.execSQL("Insert into "+ table_2+" values (6,'Exercise','exercise_icon')");
        db.execSQL("Insert into "+ table_2+" values (7,'Food_intake','food_intake_icon')");
        db.execSQL("Insert into "+ table_2+" values (8,'Temperature','temperature_icon')");
        db.execSQL("Insert into "+ table_2+" values (12,'Blood_grp','blood_group_icon')");

        db.execSQL("create table " + table_3 + " (id INTEGER PRIMARY KEY ,log_name VARCHAR(20), image_name VARCHAR(20))");
        db.execSQL("Insert into "+ table_3+" values (1,'Height','height_icon')");
        db.execSQL("Insert into "+ table_3+" values (2,'Weigth','weight_icon')");
        db.execSQL("Insert into "+ table_3+" values (4,'Heart_rate','heart_rate_icon')");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_1);
        db.execSQL("DROP TABLE IF EXISTS " + table_2);
        onCreate(db);

    }
    public void delete_db() {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_1);
        db.execSQL("DROP TABLE IF EXISTS " + table_2);
        db.execSQL("DROP TABLE IF EXISTS " + table_3);
        onCreate(db);

    }

    public void add_log(int id,String log_name,String image_name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("Insert into "+ table_2+" values ("+id + ",'"+log_name+"','"+image_name+"')");
        Log.e("add_log",id+log_name+image_name);
    }

    public boolean search(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor= (Cursor) db.rawQuery("SELECT * FROM "+ table_1 + " WHERE "
                + col_0 + " = " + id  ,null);

        Cursor ifPresent=(Cursor) db.rawQuery("SELECT * FROM "+ table_2 + " WHERE "
                + col_0 + " = " + id  ,null);


        if(cursor.getCount()>0 && ifPresent.getCount()<=0) {
            cursor.moveToFirst();
            id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String log_name = cursor.getString(cursor.getColumnIndex("log_name"));
            String image_name = cursor.getString(cursor.getColumnIndex("image_name"));

            add_log(id,log_name,image_name);
            return true;
        }
        else
            return false;
    }

    public LogModel[] give_display(String table_name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= (Cursor) db.rawQuery("SELECT * FROM "+ table_name ,null);

        LogModel logModels[]=new LogModel[15];

        if(cursor.moveToFirst())
        {
            int i=0;
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String log_name = cursor.getString(cursor.getColumnIndex("log_name"));
                String image_name = cursor.getString(cursor.getColumnIndex("image_name"));
                logModels[i] = new LogModel(id, log_name, image_name);
                i++;
            }while(cursor.moveToNext());
        }
        return logModels;
    }

    public void recent_used(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor ifPresent=(Cursor) db.rawQuery("SELECT * FROM "+ table_2 + " WHERE "
                + col_0 + " = " + id  ,null);

        Cursor cursor= (Cursor) db.rawQuery("SELECT * FROM "+ table_3 ,null);

        Cursor already_exists=(Cursor) db.rawQuery("SELECT * FROM "+ table_3 + " WHERE "
                + col_0 + " = " + id  ,null);
        String log_name="",image_name="";

        if(cursor.getCount()>=3 && already_exists.getCount()==0)
        {
            if(ifPresent.getCount()>0)
            {
                ifPresent.moveToFirst();
                id = Integer.parseInt(ifPresent.getString(ifPresent.getColumnIndex("id")));
                 log_name = ifPresent.getString(ifPresent.getColumnIndex("log_name"));
                 image_name = ifPresent.getString(ifPresent.getColumnIndex("image_name"));

                 cursor.moveToFirst();
                Random r=new Random();
                int inc=r.nextInt(3);
                int i=0;
                Log.e("ïnc", String.valueOf(inc));
                while(i<inc)
                {
                    cursor.moveToNext();
                    i++;
                }
                int id2= Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                db.execSQL("delete from "+ table_3+" where "+ col_0 +"=" +id2);

                db.execSQL("Insert into "+ table_3+" values ("+id + ",'"+log_name+"','"+image_name+"')");

            }

        }
        else
        {
            if(ifPresent.getCount()>0 && already_exists.getCount()==0)
            {
                ifPresent.moveToFirst();
                id = Integer.parseInt(ifPresent.getString(ifPresent.getColumnIndex("id")));
                 log_name = ifPresent.getString(ifPresent.getColumnIndex("log_name"));
                 image_name = ifPresent.getString(ifPresent.getColumnIndex("image_name"));
                db.execSQL("Insert into "+ table_3+" values ("+id + ",'"+log_name+"','"+image_name+"')");
            }


        }



    }
}