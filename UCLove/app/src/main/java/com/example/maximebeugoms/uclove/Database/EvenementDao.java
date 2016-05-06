package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by damien on 29/04/16.
 */
public class EvenementDao extends DAOBase {

    public static final String TABLE_NAME = "Evenement";
    public static final String KEY = "mail";
    public static final String DATE = "Date";
    public static final String TYPE = "Type";


    public EvenementDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY, "
            + DATE + " TEXT, " +  TYPE + "TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    public void add(Evenement e){
        ContentValues values = new ContentValues();
        values.put(KEY, e.getMail_user());
        values.put(DATE, e.getDate());
        values.put(TYPE, e.getType());
        mDb.insert(TABLE_NAME,null,values);
    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Evenement e){
        ContentValues values = new ContentValues();
        values.put(KEY, e.getMail_user());
        values.put(DATE, e.getDate());
        values.put(TYPE, e.getType());

        mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(e.getMail_user())});
    }

   public ArrayList<Evenement> select (String Email){
       Cursor c = mDb.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE mail = ?", new String[] {Email});
       ArrayList<Evenement> list = new ArrayList<Evenement>();
       while(c.moveToNext()){
           String mail_user = c.getString(0);
           String date = c.getString(1);
           String type = c.getString(2);
           list.add(new Evenement(mail_user, date, type));
       }

           c.close();
           return list;

    }
}
