package com.example.maximebeugoms.uclove;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DatabaseHandler extends SQLiteOpenHelper
{
    // Pour la table Profil
    public static final String PROFIL_NOM = "nom";
    public static final String PROFIL_SEXE = "sexe";
    public static final String PROFIL_AGE = "age";
    public static final String PROFIL_COULEUR_CHEVEUX = "couleur_cheveux";
    public static final String PROFIL_ORIENTATION = "orientation";


    //Pour la table Utilisateur

    public static final String UTILISATEUR_ID_USER = "id_user";
    public static final String UTILISATEUR_LOGIN = "login";
    public static final String UTILISATEUR_MAIL = "mail";
    public static final String UTILISATEUR_PASSWORD = "password";
    public static final String UTILISATEUR_ID_PROFIL = "id_profil";


    // Pour la table Evenement

    public static final String EVENEMENT_ID_EVENEMENT = "id_event";
    public static final String EVENEMENT_DATE = "date";
    public static final String EVENEMENT_TYPE = "type";


    // Pour la table relation

    public static final String RELATION_ID_RELATION = "id_relation";
    public static final String RELATION_ETAT_ACCEPTATION = "etat_acceptation";

    //Pour la table Message

    public static final String MESSAGE_ID_MESSAGE = "id_message";
    public static final String MESSAGE_TEXTE = "texte";
    public static final String MESSAGE_DATE = "date";

    // Pour la table Preference

    public static final String PREFERENCE_ID_PREF = "id_pref";
    public static final String PREFERENCE_LANGUE ="langue";
    public static final String PREFERENCE_NIVEAU_CONFIDENTIALITE = "niveau_confidentialite";


    // Pour avoir les noms des différentes tables


    public static final String PROFIL_TABLE_NAME = "Profil";
    public static final String UTILISATEUR_TABLE_NAME = "Utilisateur";
    public static final String EVENEMENT_TABLE_NAME = "Evenement";
    public static final String RELATION_TABLE_NAME = "Relation";
    public static final String MESSAGE_TABLE_NAME = "Message";
    public static final String PREFERENCE_TABLE_NAME = "Preference";


     //  Creation des différentes tables dans des strings

    public static final String PROFIL_TABLE_CREATE =
            "CREATE TABLE " + PROFIL_TABLE_NAME + " (" +

                    PROFIL_NOM + " TEXT, " +
                    PROFIL_SEXE + " TEXT, " +
                    PROFIL_AGE + "INTEGER, " +
                    PROFIL_COULEUR_CHEVEUX + "TEXT, " +
                    PROFIL_ORIENTATION + "TEXT);";

    public static final String UTILISATEUR_TABLE_CREATE =
            "CREATE TABLE" + UTILISATEUR_TABLE_NAME + "(" +
                    UTILISATEUR_ID_USER + "INTEGER" +
                    UTILISATEUR_LOGIN + "TEXT, " +
                    UTILISATEUR_MAIL + "TEXT, " +
                    UTILISATEUR_PASSWORD + "TEXT, " +
                    UTILISATEUR_ID_PROFIL + "INTEGER);";

    public static final String EVENEMENT_TABLE_CREATE =
            "CREATE TABLE" + EVENEMENT_TABLE_NAME + "(" +
                    EVENEMENT_ID_EVENEMENT + "INTEGER" +
                    EVENEMENT_DATE + "DATE, " +
                    EVENEMENT_TYPE + "STRING);";

    public static final String RELATION_TABLE_CREATE =
            "CREATE TABLE" + RELATION_TABLE_NAME +"(" +
                    RELATION_ID_RELATION + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RELATION_ETAT_ACCEPTATION + "BOOLEAN);";

    public static final String MESSAGE_TABLE_CREATE =
            "CREATE TABLE" + MESSAGE_TABLE_NAME + "(" +
                    MESSAGE_ID_MESSAGE + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MESSAGE_TEXTE + "TEXT, " +
                    MESSAGE_DATE + "DATE);";

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
        db.execSQL(MESSAGE_TABLE_CREATE);
    }

    public static final String PROFIL_TABLE_DROP = "DROP TABLE IF EXISTS " + PROFIL_TABLE_NAME + ";";
    public static final String UTILISATEUR_TABLE_DROP = "DROP TABLE IF EXISTS " + UTILISATEUR_TABLE_NAME + ";";
    public static final String EVENEMENT_TABLE_DROP = "DROP TABLE IF EXISTS " + EVENEMENT_TABLE_NAME +";";
    public static final String PREFERENCE_TABLE_DROP = "DROP TABLE IF EXISTS" + PREFERENCE_TABLE_NAME+";";
    public static final String RELATION_TABLE_DROP = "DROP TABLE IF EXISTS" + RELATION_TABLE_NAME+";";
    public static final String MESSAGE_TABLE_DROP = "DROP TABLE If EXISTS" + MESSAGE_TABLE_NAME + ";";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try
        {
            BufferedReader buf = new BufferedReader(new FileReader("file.java")); // lire fichier pour mettre des data dans les tables
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        db.execSQL(PROFIL_TABLE_DROP); //requete sql dont on ignore la réponse (execSQL())
        db.execSQL(UTILISATEUR_TABLE_DROP);
        db.execSQL(EVENEMENT_TABLE_DROP);
        db.execSQL(PREFERENCE_TABLE_DROP);
        db.execSQL(RELATION_TABLE_DROP);
        db.execSQL(MESSAGE_TABLE_DROP);
        onCreate(db);
    }

}

