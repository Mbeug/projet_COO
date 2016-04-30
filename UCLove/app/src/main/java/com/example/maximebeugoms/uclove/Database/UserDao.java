package com.example.maximebeugoms.uclove.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by damien on 29/04/16.
 */
public class UserDao extends DAOBase {

        public static final String TABLE_NAME = "user";
        public static final String KEY = "id_user";
        public static final String LOGIN = "login";
        public static final String MAIL = "mail";
        public static final String PASSWORD = "password";

        public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LOGIN + " TEXT, " + MAIL + " TEXT, " + PASSWORD + "TEXT);";

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
            values.put(KEY, u.getId());
            values.put(LOGIN, u.getLogin());
            values.put(MAIL, u.getMail());
            values.put(PASSWORD, u.getPassword());
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
            values.put(KEY, u.getId());
            values.put(LOGIN, u.getLogin());
            values.put(MAIL, u.getMail());
            values.put(PASSWORD, u.getPassword());
            mDb.update(TABLE_NAME, values, KEY  + " = ?", new String[] {String.valueOf(u.getId())});
        }

        /**
         * @param Email l'identifiant du métier à récupérer
         */
        public User selectionner(String Email) {
            Cursor c = mDb.rawQuery("select " + MAIL + " from " + TABLE_NAME + " where mail = ?", new String[] {Email});
            if(c.moveToNext()){
                long id = c.getLong(0);
                String login = c.getString(1);
                String mail = c.getString(2);
                String mdp = c.getString(3);
                return new User(id,login,mail,mdp);
            }
            else{
                c.close();
                return null;
            }
        }
    }
