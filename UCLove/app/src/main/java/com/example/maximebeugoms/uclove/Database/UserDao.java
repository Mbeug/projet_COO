package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by damien on 29/04/16.
 */
public class UserDao extends DAOBase {

        public static final String TABLE_NAME = "Utilisateur";
        public static final String LOGIN = "login";
        public static final String MAIL = "mail";
        public static final String PASSWORD = "password";

        public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " ( " + MAIL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LOGIN + " TEXT, " + PASSWORD + " TEXT);";

        public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

        public UserDao(Context pContext) {
        super(pContext);
    }

    /**
         * @param u l'utilisateur à ajouter à la base
         */
        public void add(User u)
        {
            ContentValues values = new ContentValues();
            values.put(LOGIN, u.getLogin());
            values.put(MAIL, u.getMail());
            values.put(PASSWORD, u.getPassword());
            mDb.insert(UserDao.TABLE_NAME, null, values);
        }

        /**
         * @param mail l'identifiant de l'utilisateur à supprimer
         */
        public void delete(String mail)
        {
                mDb.delete(TABLE_NAME, MAIL + " = ?", new String[] {mail});
        }

        /**
         * @param u l'utilisateur modifié
         */
        public void update(User u)
        {
            ContentValues values = new ContentValues();
            values.put(LOGIN, u.getLogin());
            values.put(MAIL, u.getMail());
            values.put(PASSWORD, u.getPassword());
            mDb.update(TABLE_NAME, values, MAIL  + " = ?", new String[] {u.getMail()});
        }

        /**
         * @param Email l'identifiant du métier à récupérer
         */
        public User selectionner(String Email) {
            Cursor c = mDb.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE mail = ?", new String[] {Email});
            if(c.moveToNext()){
                String mail = c.getString(0);
                String login = c.getString(1);
                String mdp = c.getString(2);
                return new User(login,mail,mdp);
            }
            else{
                c.close();
                return null;
            }
        }
    }
