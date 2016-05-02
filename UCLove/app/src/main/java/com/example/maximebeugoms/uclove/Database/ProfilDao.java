package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by damien on 29/04/16.
 */
public class ProfilDao extends DAOBase {

        public static final String TABLE_NAME = "Profil";
        public static final String KEY = "mail";
        public static final String NOM = "nom";
        public static final String SEXE = "sexe";
        public static final String AGE = "age";
        public static final String ORIENTATION= "orientation";
        public static final String LOCALISATION = "localisation";
        public static final String COULEUR_CHEVEUX = "couleur_cheveux";
        public static final String COULEUR_YEUX = "couleur_yeux";
        public static final String LONGUEUR_CHEVEUX = "longueur_cheveux";
        public static final String PHOTO_PATH = "photo_path";


        public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " STRING PRIMARY KEY, "  //garder autoincrement?
                + NOM + " TEXT, " + SEXE + " TEXT, " + AGE + " INTEGER, " + ORIENTATION + " TEXT, " +  LOCALISATION + " TEXT, "
                +  COULEUR_CHEVEUX + " TEXT, " +  COULEUR_YEUX + " TEXT, " + LONGUEUR_CHEVEUX + " TEXT, " + PHOTO_PATH + " TEXT " + ");";
        public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public ProfilDao(Context pContext) {
        super(pContext);
    }

    /**
         * @param p le profil à ajouter à la base
         */
        public void add(Profil p)
        {
            ContentValues values = new ContentValues();
            values.put(KEY, p.getMail());
            values.put(AGE, p.getAge());
            values.put(COULEUR_CHEVEUX, p.getCouleur_cheveux());
            values.put(COULEUR_YEUX, p.getCouleur_yeux());
            values.put(NOM, p.getNom());
            values.put(ORIENTATION, p.getOrientation());
            values.put(LOCALISATION, p.getLocalisation());
            values.put(SEXE, p.getSexe());
            values.put(LONGUEUR_CHEVEUX, p.getLongueur_cheveux());
            values.put(PHOTO_PATH, p.getPhoto_path());
            mDb.insert(TABLE_NAME,null,values);
        }

        /**
         * @param id l'identifiant du profil à supprimer
         */
        public void delete(long id) {
            mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        }

        /**
         * @param p le profil modifié
         */
        public void update(Profil p) {
            ContentValues values = new ContentValues();
            //values.put(KEY, p.getMail());   //devrait on pouvoir changer la clé primaire?
            values.put(COULEUR_CHEVEUX, p.getCouleur_cheveux());
            values.put(COULEUR_YEUX, p.getCouleur_yeux());
            values.put(NOM, p.getNom());
            values.put(AGE, p.getAge());
            values.put(SEXE, p.getSexe());
            values.put(ORIENTATION, p.getOrientation());
            values.put(LONGUEUR_CHEVEUX, p.getLongueur_cheveux());
            values.put(PHOTO_PATH, p.getPhoto_path());
            values.put(LOCALISATION, p.getLocalisation());

            mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {p.getMail()});
        }

        /**
         * @param Email l'identifiant du métier à récupérer
         */
        public Profil selectionner(String Email) {
            Cursor c = mDb.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE mail = ?", new String[] {Email});
            if(c.moveToNext()){
                String nom = c.getString(0);
                String sexe = c.getString(1);
                int age = c.getInt(2);
                String couleur_yeux = c.getString(3);
                String couleur_cheveux = c.getString(4);
                String longueur_cheveux = c.getString(5);
                String localisation = c.getString(6);
                String orientation = c.getString(7);
                String photo_path = c.getString(8);
                String mail_user = c.getString(9);

                return new Profil(nom, mail_user, sexe, age, couleur_cheveux, longueur_cheveux, couleur_yeux, orientation, localisation, photo_path);
            }
            else{
                c.close();
                return null;
            }

        }
    }

