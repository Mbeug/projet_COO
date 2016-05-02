package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by damien on 29/04/16.
 */
public class Search_profilDao extends DAOBase{

    public static final String TABLE_NAME = "search_profil";
    public static final String KEY = "mzil_user";
    public static final String SEXE = "sexe";
    public static final String AGE = "age";
    public static final String ORIENTATION= "orientation";
    public static final String LOCALISATION = "localisation";
    public static final String LANGUE = "langue";
    public static final String TAILLE = "taille";

    public Search_profilDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " STRING PRIMARY KEY, "
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
		Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE mail = ?", new String[] {Email});
            if(c.moveToNext()){
                String sexe = c.getString(0);
                int age = c.getInt(1);
                String orientation = c.getString(2);
                String localisation = c.getString(3);
                String langue = c.getString(4);
                String taille = c.getString(5)
                String mail = c.getString(6);

                return new Profil(sexe,age,orientation,localisation,langue,taille,mail);
            }
            else{
                c.close();
                return null;
            }

    }
}
