package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by damien on 29/04/16.
 */
public class MessageDao extends DAOBase {

    public static final String TABLE_NAME = "Message";
    public static final String KEY = "mail";
    public static final String DATE = "Date";
    public static final String TEXTE = "Texte";


    public MessageDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DATE + " TEXT, " +  TEXTE + "TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void add(Message m){
        ContentValues values = new ContentValues();
        values.put(KEY, m.getMail_user());
        values.put(DATE, m.getDate());
        values.put(TEXTE, m.getTexte());
    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Message m){
        ContentValues values = new ContentValues();
        values.put(KEY, m.getMail_user());
        values.put(DATE, m.getDate());
        values.put(TEXTE, m.getTexte());

        mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(m.getMail_user())});
    }

    public ArrayList<Message> select (String Email){
        Cursor c = mDb.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE mail = ?", new String[] {Email});
        ArrayList<Message> messageArrayList = new ArrayList<Message>();
        while(c.moveToNext()){

            String texte = c.getString(0);
            String date = c.getString(1);
            String mail_user = c.getString(2);

            Message newmess = new Message(mail_user, date, texte);
            messageArrayList.add(newmess);
        }
        c.close();
        return messageArrayList;

    }
}
