package com.example.maximebeugoms.uclove;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by damien on 26/04/16.
 */

/*
*   Je ne suis pas sur pour les clés étrangères qui font références a d'autre table lors de la création des tables
*/

public class DatabaseHandler extends SQLiteOpenHelper
{
    // Pour la table Profil
    public static final String PROFIL_ID = "id_profil";
    public static final String PROFIL_NOM = "Nom";
    public static final String PROFIL_SEXE = "Sexe";
    public static final String PROFIL_AGE = "Sexe";
    public static final String PROFIL_COULEUR_CHEVEUX = "Sexe";
    public static final String PROFIL_ORIENTATION = "Sexe";
    public static final String PROFIL_ID_USER = "Sexe";
    // photo + localisation + date_dispo

    //Pour la table Utilisateur

    public static final String UTILISATEUR_ID_USER = "id_user";
    public static final String UTILISATEUR_LOGIN = "login";
    public static final String UTILISATEUR_MAIL = "mail";
    public static final String UTILISATEUR_PASSWORD = "password";
    public static final String UTILISATEUR_ID_PROFIL = "id_profil";
    public static final String UTILISATEUR_ID_HISTORIQUE = "id_historique";
    public static final String UTILISATEUR_ID_PREFERENCE = "id preference";

    // Pour la table Historique

    public static final String HISTORIQUE_ID_HISTORIQUE = "id_historique";
    public static final String HISTORIQUE_ID_EVENT = "id_event";

    // Pour la table Evenement

    public static final String EVENEMENT_ID_EVENT = "id_event";
    public static final String EVENEMENT_DATE = "date";
    public static final String EVENEMENT_TYPE = "type";
    public static final String EVENEMENT_ID_HISTORIQUE ="id_historique";

    // Pour la table relation

    public static final String RELATION_ID_RELATION = "id_relation";
    public static final String RELATION_ETAT_ACCEPTATION = "etat_acceptation";
    public static final String RELATION_ID_UTILISATEUR1 = "id_utilisateur1";
    public static final String RELATION_ID_UTILISATEUR2 = "id_utilisateur2";

    //Pour la table Message

    public static final String MESSAGE_ID_MESSAGE = "id_message";
    public static final String MESSAGE_TEXTE = "texte";
    public static final String MESSAGE_DATE = "date";
    public static final String MESSAGE_ID_RELATION ="id_relation";

    // Pour la table Preference

    public static final String PREFERENCE_ID_PREF = "id_pref";
    public static final String PREFERENCE_LANGUE ="langue";
    public static final String PREFERENCE_NIVEAU_CONFIDENTIALITE = "niveau_confidentialite";

    /*
    * Pour avoir les noms des différentes tables
    */

    public static final String PROFIL_TABLE_NAME = "Profil";
    public static final String UTILISATEUR_TABLE_NAME = "Utilisateur";
    public static final String HISTORIQUE_TABLE_NAME = "Historique";
    public static final String EVENEMENT_TABLE_NAME = "Evenement";
    public static final String RELATION_TABLE_NAME = "Relation";
    public static final String MESSAGE_TABLE_NAME = "Message";
    public static final String PREFERENCE_TABLE_NAME = "Preference";

    /*
     *  Creation des différentes tables dans des strings
     */
    public static final String PROFIL_TABLE_CREATE =
            "CREATE TABLE " + PROFIL_TABLE_NAME + " (" +
                    PROFIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PROFIL_NOM + " TEXT, " +
                    PROFIL_SEXE + " TEXT, " +
                    PROFIL_AGE + "INTEGER, " +
                    PROFIL_COULEUR_CHEVEUX + "TEXT, " +
                    PROFIL_ORIENTATION + "TEXT," +
                    PROFIL_ID_USER + "INTEGER FOREIGN KEY REFERENCES ID_USER);";

    public static final String UTILISATEUR_TABLE_CREATE =
            "CREATE TABLE" + UTILISATEUR_TABLE_NAME + "(" +
                    UTILISATEUR_ID_USER + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UTILISATEUR_LOGIN + "TEXT, " +
                    UTILISATEUR_MAIL + "TEXT, " +
                    UTILISATEUR_PASSWORD + "TEXT, " +
                    UTILISATEUR_ID_PROFIL + "INTEGER, " +
                    UTILISATEUR_ID_HISTORIQUE + "INTEGER FOREIGN KEY REFERENCES ID_HISTORIQUE, " +
                    UTILISATEUR_ID_PREFERENCE + "INTEGER FOREIGN KEY REFERENCES ID_PREFERENCE); ";

    public static final String HISTORIQUE_TABLE_CREATE =
            "CREATE TABLE" + HISTORIQUE_TABLE_NAME + "(" +
                    HISTORIQUE_ID_HISTORIQUE + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                    HISTORIQUE_ID_EVENT + "INTEGER FOREIGN KEY);";

    public static final String EVENEMENT_TABLE_CREATE =
            "CREATE TABLE" + EVENEMENT_TABLE_NAME + "(" +
                    EVENEMENT_ID_EVENT + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EVENEMENT_DATE + "DATE, " +
                    EVENEMENT_TYPE + "STRING, " +
                    EVENEMENT_ID_HISTORIQUE + "INTEGER FOREIGN KEY REFERENCES ID_HISTORIQUE );";

    public static final String RELATION_TABLE_CREATE =
            "CREATE TABLE" + RELATION_TABLE_NAME +"(" +
                    RELATION_ID_RELATION + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RELATION_ETAT_ACCEPTATION + "BOOLEAN ," +
                    RELATION_ID_UTILISATEUR1 + "INTEGER REFERENCES UTILISATEUR, " +
                    RELATION_ID_UTILISATEUR2 + "INTEGER REFERENCES UTILISATEUR);";

    public static final String MESSAGE_TABLE_CREATE =
            "CREATE TABLE" + MESSAGE_TABLE_NAME + "(" +
                    MESSAGE_ID_MESSAGE + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MESSAGE_TEXTE + "TEXT, " +
                    MESSAGE_DATE + "DATE ," +
                    MESSAGE_ID_RELATION + "INTEGER FOREIGN KEY REFERENCES ID_RELATION);";

    public static final String PREFERENCE_TABLE_CREATE =
            "CREATE TABLE" + PREFERENCE_TABLE_NAME + "(" +
                    PREFERENCE_ID_PREF + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PREFERENCE_LANGUE + "TEXT, " +
                    PREFERENCE_NIVEAU_CONFIDENTIALITE + "TEXT); ";



    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PROFIL_TABLE_CREATE);
        db.execSQL(UTILISATEUR_TABLE_CREATE);
        db.execSQL(EVENEMENT_TABLE_CREATE);
        db.execSQL(PREFERENCE_TABLE_CREATE);
        db.execSQL(RELATION_TABLE_CREATE);
        db.execSQL(HISTORIQUE_TABLE_CREATE);
        db.execSQL(MESSAGE_TABLE_CREATE);
    }

    public static final String PROFIL_TABLE_DROP = "DROP TABLE IF EXISTS " + PROFIL_TABLE_NAME + ";";
    public static final String 

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PROFIL_TABLE_DROP); //requete sql dont on ignore la réponse
        onCreate(db);
    }

}

