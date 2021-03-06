package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by damien on 2/05/16.
 */
public class DisponibiliteDao extends DAOBase {

    private static final String TABLE_NAME = "Disponibilite";
    private static final String KEY = "mail";
    private static final String DATE = "date";
    private static final String DISPO = "dispo";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + DATE + " TEXT PRIMARY KEY, "
            + DISPO + " TEXT, " + KEY + " STRING, " + ");";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public DisponibiliteDao(Context pContext) {
        super(pContext);
    }

    public void add(Disponibilite d) {
        ContentValues values = new ContentValues();
        values.put(KEY, d.getMail_user());
        values.put(DATE, d.getDate());
        values.put(DISPO, d.getDispo());
        mDb.insert(TABLE_NAME,null,values);
    }

    public void delete(long id) {
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Disponibilite d){
        ContentValues values = new ContentValues();
        values.put(KEY, d.getMail_user());
        values.put(DATE, d.getDate());
        values.put(DISPO, d.getDispo());
        mDb.update(TABLE_NAME, values, DATE  + " = ?", new String[] {d.getDate()});
    }

    public Disponibilite selectionner(String date){
        Cursor c = mDb.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE date = ?", new String[] {date});
        if(c.moveToNext()){
            String mDate = c.getString(0);
            String dispo = c.getString(1);
            String mail_user = c.getString(2);
            c.close();
            return new Disponibilite(mail_user, dispo, mDate);
        }
        else{
            c.close();
            return null;
        }
    }
}
