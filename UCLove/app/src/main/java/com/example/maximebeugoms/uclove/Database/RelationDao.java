package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by damien on 29/04/16.
 */


public class RelationDao extends DAOBase{

    public static final String TABLE_NAME = "Relation";
    public static final String ID_RELATION = "id_relation";
    public static final String ETAT_ACCEPTATION = "etat_acceptation";
    public static final String SENDER = "expediteur";
    public static final String RECEIVER = "destinateur";


    public RelationDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + ID_RELATION + " INTEGER,"
            +  ETAT_ACCEPTATION + " INTEGER,"  + RECEIVER + " TEXT," + SENDER + " TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void add(Relation r){
        ContentValues values = new ContentValues();
        values.put(ETAT_ACCEPTATION, r.getEtat_acceptation());
        values.put(SENDER, r.getSender());
        values.put(RECEIVER, r.getReceiver());
        values.put(ID_RELATION, r.getId());
        mDb.insert(TABLE_NAME,null,values);
    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, ID_RELATION + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Relation r){
        ContentValues values = new ContentValues();
        values.put(ETAT_ACCEPTATION, r.getEtat_acceptation());
        values.put(SENDER, r.getSender());
        values.put(RECEIVER, r.getReceiver());
        values.put(ID_RELATION, r.getId());
        mDb.update(TABLE_NAME, values, ID_RELATION  + " = ?", new String[] {String.valueOf(r.getId())});
    }

    public ArrayList<Relation> select(String Email){
        Cursor c = mDb.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE expediteur = ?", new String[] {Email});
        Cursor d = mDb.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE destinateur = ?", new String[] {Email});
        ArrayList<Relation> relationArrayList = new ArrayList<Relation>();

        //We get every relation where the user is the sender
        while(c.moveToNext()){
            int etat_relation = c.getInt(0);
            String receiver = c.getString(1);
            String sender = c.getString(2);
            int id = c.getInt(3);
            Relation rel = new Relation(sender,etat_relation,receiver, id);
            relationArrayList.add(rel);
        }

        //We get every relation where the user is the receiver
        while(d.moveToNext()){
            int etat_relation = c.getInt(0);
            String receiver = c.getString(1);
            String sender = c.getString(2);
            int id = c.getInt(3);
            Relation rel = new Relation(sender,etat_relation,receiver,id);
            relationArrayList.add(rel);
        }

        c.close();
        d.close();
        return relationArrayList;
    }

    public Relation select(String expediteur, String destinataire){
        Cursor c = mDb.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE " + SENDER + " = ? AND " + RECEIVER + " = ?", new String[] {expediteur,destinataire});


        Relation rel = null;
        //We get every relation where the user is the sender
        if (c.moveToNext()){
            int etat_relation = c.getInt(0);
            String receiver = c.getString(1);
            String sender = c.getString(2);
            int id = c.getInt(3);
            rel = new Relation(sender,etat_relation,receiver, id);

        }


        c.close();
        return rel;
    }
}
