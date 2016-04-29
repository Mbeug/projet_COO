package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;

/**
 * Created by damien on 29/04/16.
 */
public class ProfilDao extends DAOBase, DatabaseHandler {

        public static final String TABLE_NAME = "profil";
        public static final String KEY = "id_user";
        public static final String NOM = "nom";
        public static final String SEXE = "sexe";
        public static final String AGE = "age";
        public static final String ORIENTATION= "orientation";
        public static final String LOCALISATION = "localisation";


        public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOM + " TEXT, " + SEXE + "TEXT," + AGE + " REALs" + ORIENTATION + "TEXT," +  LOCALISATION + "TEXT);";
        public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

        /**
         * @param p le profil à ajouter à la base
         */
        public void add(Profil p)
        {
            ContentValues values = new ContentValues();
            values.put(PROFIL_AGE, p.getAge());
            values.put(PROFIL_COULEUR_CHEVEUX, p.getCouleur_cheveux());
            values.put(PROFIL_COULEUR_YEUX, p.getCouleur_yeux());
            values.put(PROFIL_NOM, p.getNom());
            values.put(PROFIL_ORIENTATION, p.getOrientation());
            values.put(PROFIL_LOCALISATION, p.getLocalisation());
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
            values.put(UTILISATEUR_ID_USER, p.getId());
            values.put(PROFIL_COULEUR_CHEVEUX, p.getCouleur_cheveux());
            values.put(PROFIL_COULEUR_YEUX, p.getCouleur_yeux());
            values.put(PROFIL_NOM, p.getNom());
            values.put(PROFIL_AGE, p.getAge());
            values.put(PROFIL_SEXE, p.getSexe());
            values.put(PROFIL_ORIENTATION, p.getOrientation());

            mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(p.getId())});
        }

        /**
         * @param id l'identifiant du métier à récupérer
         */
        public Profil select(long id) {

        }
    }
