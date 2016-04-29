package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;

/**
 * Created by damien on 29/04/16.
 */
public class UserDao extends DAOBase, DatabaseHandler {

        public static final String TABLE_NAME = "user";
        public static final String KEY = "id_user";
        public static final String LOGIN = "login";
        public static final String MAIL = "mail";
        public static final String PASSWORD = "password";

        public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LOGIN + " TEXT, " + MAIL + " TEXT, " + PASSWORD + "TEXT);";

        public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

        /**
         * @param u l'utilisateur à ajouter à la base
         */
        public void add(User u)
        {
            ContentValues values = new ContentValues();
            values.put(UTILISATEUR_ID_USER, u.getId());
            values.put(UTILISATEUR_LOGIN, u.getLogin());
            values.put(UTILISATEUR_MAIL, u.getMail());
            values.put(UTILISATEUR_PASSWORD, u.getPassword());
            mDb.insert(UserDao.TABLE_NAME, null, values);
        }

        /**
         * @param id l'identifiant de l'utilisateur à supprimer
         */
        public void delete(long id)
        {
                mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        }

        /**
         * @param u l'utilisateur modifié
         */
        public void update(User u)
        {
            ContentValues values = new ContentValues();
            values.put(UTILISATEUR_ID_USER, u.getId());
            values.put(UTILISATEUR_LOGIN, u.getLogin());
            values.put(UTILISATEUR_MAIL, u.getMail());
            values.put(UTILISATEUR_PASSWORD, u.getPassword());
            mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(u.getId())});
        }

        /**
         * @param id l'identifiant du métier à récupérer
         */
        public User selectionner(long id) {

        }
    }
