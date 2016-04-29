package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by damien on 29/04/16.
 */

// Résoudre le probleme des deux utilisateurs !!!!!

public class RelationDao extends DAOBase{

    public static final String TABLE_NAME = "Message";
    public static final String KEY = "id_relation";
    public static final String FOREIGN_KEY = "id_user";
    public static final String ETAT_ACCEPTATION = "etat_acceptation";
    public static final String OTHER_USER = "other_user";


    public RelationDao(Context pContext) {
        super(pContext);
    }

    public void add(Relation r){

    }

    public void delete(long id){
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Relation r){

    }

    /*public Relation select(long id){

    }*/
}
