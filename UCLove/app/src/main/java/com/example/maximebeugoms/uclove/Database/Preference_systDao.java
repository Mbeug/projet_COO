package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by damien on 29/04/16.
 */
public class Preference_systDao extends DAOBase
{

        public static final String TABLE_NAME = "preference système";
        public static final String KEY = "mail_user";
        public static final String LANGUE = "langue";
        public static final String NIVEAU_CONFIDENTIALITE = "niveau confidentialite";

    public Preference_systDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + "STRING PRIMARY KEY "
            + LANGUE + " TEXT, " + NIVEAU_CONFIDENTIALITE + "TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void add(Preference_syst ps){
        ContentValues values = new ContentValues();
        values.put(KEY, ps.getMail_user());
        values.put(LANGUE, ps.getLangue());
        values.put(NIVEAU_CONFIDENTIALITE, ps.getNiveau_confidentialite());
    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Preference_syst ps){
        ContentValues values = new ContentValues();
        values.put(KEY, ps.getMail_user());
        values.put(LANGUE, ps.getLangue());
        values.put(NIVEAU_CONFIDENTIALITE, ps.getNiveau_confidentialite());

        mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(ps.getMail_user())});
    }

    public Preference_syst select(String Email){
		
		 Cursor c = mDb.rawQuery("SELECT * FROM "+ TABLE_NAME+" WHERE mail = ?", new String [] {Email});
		 if(c.moveToNext()){
		 	String langue = c.getString(0);
		 	String niveau_confidentialite = c.getString(1);
		 	String mail_user = c.getString(2);
		 	return new Preference_syst(langue,niveau_confidentialite,mail_user);
		 }
		 else{
		 	c.close();
            return null;
		 }
    }
}
