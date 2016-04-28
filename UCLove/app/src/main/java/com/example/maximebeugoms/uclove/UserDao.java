package com.example.maximebeugoms.uclove;

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
            // CODE
        }

        /**
         * @param id l'identifiant de l'utilisateur à supprimer
         */
        public void delete(long id) {
            // CODE
        }

        /**
         * @param u l'utilisateur modifié
         */
        public void update(User u) {
            // CODE
        }

        /**
         * @param id l'identifiant de l'utilisateur à récupérer
         */
        public User select(long id) {
            // CODE
            return null; // a changer
        }
    }

