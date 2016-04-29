package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by damien on 29/04/16.
 */
public class Preference_systDao extends DAOBase
{

        public static final String TABLE_NAME = "preference système";
        public static final String KEY = "id_user";
        public static final String LANGUE = "langue";
        public static final String NIVEAU_CONFIDENTIALITE = "niveau confidentialite";

    public Preference_systDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LANGUE + " TEXT, " + NIVEAU_CONFIDENTIALITE + "TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void add(Preference_syst ps){
        ContentValues values = new ContentValues();
        values.put(KEY, ps.getId_user());
        values.put(LANGUE, ps.getLangue());
        values.put(NIVEAU_CONFIDENTIALITE, ps.getNiveau_confidentialite());
    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Preference_syst ps){
        ContentValues values = new ContentValues();
        values.put(KEY, ps.getId_user());
        values.put(LANGUE, ps.getLangue());
        values.put(NIVEAU_CONFIDENTIALITE, ps.getNiveau_confidentialite());

        mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(ps.getId_user())});
    }

    public Preference_syst select(long id){

    }
}
