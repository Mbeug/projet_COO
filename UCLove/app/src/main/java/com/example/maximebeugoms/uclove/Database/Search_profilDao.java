package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by damien on 29/04/16.
 */
public class Search_profilDao extends DAOBase{

    public static final String TABLE_NAME = "Search";
    public static final String KEY = "mail";
    public static final String AGE = "age";
    //public static final String ORIENTATION= "orientation";
    public static final String LOCALISATION = "localisation";
    public static final String LONGUEUR_CHEVEUX = "longueur_cheveux";
    public static final String COULEUR_CHEVEUX = "couleur_cheveux";
    public static final String COULEUR_YEUX = "couleur_yeux";

    public Search_profilDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " STRING PRIMARY KEY, "
            + LONGUEUR_CHEVEUX + " TEXT, " + COULEUR_CHEVEUX + " TEXT, " + AGE + " INTEGER, " + COULEUR_YEUX + " TEXT," + LOCALISATION  + " TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void add(Search_profil sp){
        ContentValues values = new ContentValues();
        values.put(KEY, sp.getMail_user());
        values.put(LONGUEUR_CHEVEUX, sp.getLongueur_cheveux());
        values.put(COULEUR_CHEVEUX, sp.getCouleur_cheveux());
        values.put(AGE, sp.getAge());
        values.put(LOCALISATION, sp.getLocalisation());
        values.put(COULEUR_YEUX, sp.getCouleur_yeux());
        mDb.insert(TABLE_NAME,null,values);
    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Search_profil sp){
        ContentValues values = new ContentValues();
        values.put(KEY, sp.getMail_user());
        values.put(LONGUEUR_CHEVEUX, sp.getLongueur_cheveux());
        values.put(COULEUR_CHEVEUX, sp.getCouleur_cheveux());
        values.put(AGE, sp.getAge());
        values.put(LOCALISATION, sp.getLocalisation());
        values.put(COULEUR_YEUX, sp.getCouleur_yeux());

        mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(sp.getMail_user())});
    }

   public Search_profil selectionner(String Email){
		Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE mail = ?", new String[] {Email});
            if(c.moveToNext()){
                int age = c.getInt(0);
                String couleur_cheveux = c.getString(1);
                String longueur_cheveux = c.getString(2);
                String localisation = c.getString(3);
                String couleur_yeux = c.getString(4);
                String mail = c.getString(5);

                return new Search_profil(age,localisation,longueur_cheveux, couleur_cheveux, couleur_yeux, mail);
            }
            else{
                c.close();
                return null;
            }

    }
}
