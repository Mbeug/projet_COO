package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by damien on 29/04/16.
 */
public class ProfilDao extends DAOBase {

        public static final String TABLE_NAME = "profil";
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
                + NOM + " TEXT, " + SEXE + " TEXT," + AGE + " REALS, " + ORIENTATION + " TEXT, " +  LOCALISATION + " TEXT, " + LONGUEUR_CHEVEUX + " TEXT, " + PHOTO_PATH + " TEXT " + ");";
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
         * @param id l'identifiant du métier à récupérer
         */
        /*public Profil select(long id) {

        }*/
    }

