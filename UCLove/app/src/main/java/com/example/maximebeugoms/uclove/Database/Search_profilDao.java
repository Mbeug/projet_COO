package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by damien on 29/04/16.
 */
public class Search_profilDao extends DAOBase{

    public static final String TABLE_NAME = "search_profil";
    public static final String KEY = "id_user";
    public static final String SEXE = "sexe";
    public static final String AGE = "age";
    public static final String ORIENTATION= "orientation";
    public static final String LOCALISATION = "localisation";
    public static final String LANGUE = "langue";
    public static final String TAILLE = "taille";

    public Search_profilDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LANGUE + " TEXT, " + SEXE + "TEXT, " + AGE + "INTEGER, " + TAILLE + "REAL," + LOCALISATION + "TEXT, " +
            ORIENTATION + "TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void add(Search_profil sp){
        ContentValues values = new ContentValues();
        values.put(KEY, sp.getId_user());
        values.put(LANGUE, sp.getLangue());
        values.put(SEXE, sp.getSexe());
        values.put(AGE, sp.getAge());
        values.put(ORIENTATION, sp.getOrientation());
        values.put(LOCALISATION, sp.getLocalisation());
        values.put(TAILLE, sp.getTaille());
    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Search_profil sp){
        ContentValues values = new ContentValues();
        values.put(KEY, sp.getId_user());
        values.put(LANGUE, sp.getLangue());
        values.put(SEXE, sp.getSexe());
        values.put(AGE, sp.getAge());
        values.put(ORIENTATION, sp.getOrientation());
        values.put(LOCALISATION, sp.getLocalisation());
        values.put(TAILLE, sp.getTaille());

        mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(sp.getId_user())});
    }

    public Search_profil select(long id){

    }
}
