package com.example.maximebeugoms.uclove;

import android.content.ContentValues;

/**
 * Created by damien on 28/04/16.
 */
public class UserDao {

    public static final String TABLE_NAME = "User";
    public static final String ID = "id_profil";
    public static final String NOM = "nom";
    public static final String SEXE = "Sexe";
    public static final String AGE = "Age";
    public static final String COULEUR_CHEVEUX = "couleur_cheveux";
    public static final String ORIENTATION = "orientation";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOM + " TEXT, " + SEXE + "TEXT, " + AGE + "INTEGER, " + COULEUR_CHEVEUX + "TEXT , " + ORIENTATION + "TEXT);";

        public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

        /**
         * @param u l'utilisateur à ajouter à la base
         */
        public void add(User u) {
            // long insert(String table, String nullColumnHack, ContentValues value)

            // créer contentvalue

        }

        /**
         * @param id l'identifiant de l'utilisateur à supprimer
         */
        public void delete(long id) {
            // int delete (String table, String whereClause, String [] whereArgs)
        }

        /**
         * @param u l'utilisateur modifié
         */
        public void update(User u) {
            // int update (String table, String whereClause, String [] whereArgs)
        }

        /**
         * @param id l'identifiant de l'utilisateur à récupérer
         */
        public User select(long id) {
            // utiliser des cursor !!
            return null; // a changer
        }
    }

