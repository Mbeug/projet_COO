package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by damien on 29/04/16.
 */
public class EvenementDao extends DAOBase {

    public static final String TABLE_NAME = "Evenement";
    public static final String KEY = "id_user";
    public static final String DATE = "Date";
    public static final String TYPE = "Type";


    public EvenementDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DATE + " TEXT, " +  TYPE + "TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    public void add(Evenement e){
        ContentValues values = new ContentValues();
        values.put(KEY, e.getId_user());
        values.put(DATE, e.getDate());
        values.put(TYPE, e.getType());
    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Evenement e){
        ContentValues values = new ContentValues();
        values.put(KEY, e.getId_user());
        values.put(DATE, e.getDate());
        values.put(TYPE, e.getType());

        mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(e.getId_user())});
    }

   /* public Evenement select (long id){

    }*/
}
