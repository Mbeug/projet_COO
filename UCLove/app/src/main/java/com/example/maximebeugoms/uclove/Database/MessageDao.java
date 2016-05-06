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
    public static final String KEY = "id_message";
    public static final String DATE = "Date";
    public static final String TEXTE = "Texte";
    public static final String ID_RELATION = "id_relation";


    public MessageDao(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DATE + " TEXT, " +  TEXTE + "TEXT);";
    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void add(Message m){
        ContentValues values = new ContentValues();
        values.put(KEY, m.getId());
        values.put(DATE, m.getDate());
        values.put(TEXTE, m.getTexte());
        values.put(ID_RELATION, m.getId_relation());
        mDb.insert(TABLE_NAME,null,values);
    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Message m){
        ContentValues values = new ContentValues();
        values.put(KEY, m.getId());
        values.put(DATE, m.getDate());
        values.put(TEXTE, m.getTexte());
        values.put(ID_RELATION, m.getId_relation());
        mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(m.getId())});
    }

    public ArrayList<Message> select (int id_relation){
        Cursor c = mDb.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE id_relation = ? ORDER BY " + DATE, new String[] {String.valueOf(id_relation)});
        ArrayList<Message> messageArrayList = new ArrayList<Message>();
        while(c.moveToNext()){

            String texte = c.getString(0);
            String date = c.getString(1);
            String mail = c.getString(2);
            int id = c.getInt(3);
            int relation = c.getInt(4);

            Message newmess = new Message(mail, texte,date,relation,id);
            messageArrayList.add(newmess);
        }
        c.close();
        return messageArrayList;

    }
}
