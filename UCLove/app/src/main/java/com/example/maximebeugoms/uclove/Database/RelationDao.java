package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by damien on 29/04/16.
 */


public class RelationDao extends DAOBase{

    public static final String TABLE_NAME = "Relation";
    public static final String KEY = "mail";
    public static final String ETAT_ACCEPTATION = "etat_acceptation";
    public static final String SENDER = "expediteur";
    public static final String RECEIVER = "destinataire";


    public RelationDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  ETAT_ACCEPTATION + "INTEGER," + SENDER + "TEXT," + RECEIVER + "TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void add(Relation r){
        ContentValues values = new ContentValues();
        values.put(KEY, r.getMail_user());
        values.put(ETAT_ACCEPTATION, r.getEtat_acceptation());
        values.put(SENDER, r.getSender());
        values.put(RECEIVER, r.getReceiver());
    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Relation r){
        ContentValues values = new ContentValues();
        values.put(ETAT_ACCEPTATION, r.getEtat_acceptation());
        values.put(SENDER, r.getSender());
        values.put(RECEIVER, r.getReceiver());

        mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(r.getMail_user())});
    }

    public Relation select(String Email){
        Cursor c = mDb.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE mail = ?", new String[] {Email});
        if(c.moveToNext()){
            String sender = c.getString(0);
            int etat_relation = c.getInt(1);
            String receiver = c.getString(2);
            String mail_user = c.getString(3);
            return new Relation(sender,etat_relation,receiver,mail_user);
        }
        else{
            c.close();
            return null;
        }

    }
}
