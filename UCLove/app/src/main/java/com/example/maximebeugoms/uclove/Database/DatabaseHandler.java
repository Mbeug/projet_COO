package com.example.maximebeugoms.uclove.Database;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/*
 *  définition générale des schémas de la base de donnée
 */

public class DatabaseHandler extends SQLiteOpenHelper
{
    //Pour la table Utilisateur

    public static final String UTILISATEUR_LOGIN = "login";
    public static final String UTILISATEUR_MAIL = "mail";
    public static final String UTILISATEUR_PASSWORD = "password";

    // Pour la table Profil

    public static final String PROFIL_NOM = "nom";
    public static final String PROFIL_SEXE = "sexe";
    public static final String PROFIL_AGE = "age";
    public static final String PROFIL_COULEUR_CHEVEUX = "couleur_cheveux";
    public static final String PROFIL_COULEUR_YEUX = "couleur_yeux";
    public static final String PROFIL_ORIENTATION = "orientation";
    public static final String PROFIL_LOCALISATION = "localisation";
    public static final String PROFIL_LONGUEUR_CHEVEUX = "longueur_cheveux";
    public static final String PROFIL_PHOTO_PATH = "photo_path";

    // Pour la table Evenement

    public static final String EVENEMENT_DATE = "date";
    public static final String EVENEMENT_TYPE = "type";

    // Pour la table relation


    public static final String RELATION_ETAT_ACCEPTATION = "etat_acceptation";
    public static final String RELATION_ID_RELATION = "id_relation";
    public static final String RELATION_SENDER = "expediteur";
    public static final String RELATION_RECEIVER = "destinateur";

    //Pour la table Message

    public static final String MESSAGE_ID_MESSAGE = "id_message";
    public static final String MESSAGE_TEXTE = "texte";
    public static final String MESSAGE_DATE = "date";

    // Pour la table Preference système

    public static final String PREFERENCE_LANGUE ="langue";
    public static final String PREFERENCE_NIVEAU_CONFIDENTIALITE = "niveau_confidentialite";

    // Pour la table Search

    public static final String SEARCH_AGE = "age";
    public static final String SEARCH_GENRE = "genre";
    public static final String SEARCH_LOCALISATION = "localisation";
    public static final String SEARCH_COULEUR_CHEVEUX = "couleur_cheveux";
    public static final String SEARCH_LONGUEUR_CHEVEUX = "longueur_cheveux";
    public static final String SEARCH_COULEUR_YEUX = "couleur_yeux";


    //Pur la table Disponibilite

    public static final String DISPONIBILITE_DATE = "date";
    public static final String DISPONIBILITE_DISPO = "dispo";

    /*
    * Pour avoir les noms des différentes tables
    */
    // Pour avoir les noms des différentes tables


    public static final String PROFIL_TABLE_NAME = "Profil";
    public static final String UTILISATEUR_TABLE_NAME = "Utilisateur";
    public static final String EVENEMENT_TABLE_NAME = "Evenement";
    public static final String RELATION_TABLE_NAME = "Relation";
    public static final String MESSAGE_TABLE_NAME = "Message";
    public static final String PREFERENCE_TABLE_NAME = "Preference";
    public static final String SEARCH_TABLE_NAME = "search";
    public static final String DISPONIBILITE_TABLE_NAME = "Disponibilite";


     //  Creation des différentes tables dans des strings

    public static final String PROFIL_TABLE_CREATE =
            "CREATE TABLE " + PROFIL_TABLE_NAME + " ( " +
                    PROFIL_NOM + " TEXT, " +
                    PROFIL_SEXE + " TEXT, " +
                    PROFIL_AGE + " INTEGER, " +
                    PROFIL_COULEUR_YEUX + " TEXT, " +
                    PROFIL_COULEUR_CHEVEUX + " TEXT, " +
                    PROFIL_LONGUEUR_CHEVEUX + " TEXT, " +
                    PROFIL_LOCALISATION + " TEXT, " +
                    PROFIL_ORIENTATION + " TEXT, " +
                    PROFIL_PHOTO_PATH + " TEXT, " +
                    UTILISATEUR_MAIL + " STRING," +
                    "FOREIGN KEY (" + UTILISATEUR_MAIL + ") REFERENCES " + UTILISATEUR_TABLE_NAME + " (" + UTILISATEUR_MAIL + "));";

    public static final String UTILISATEUR_TABLE_CREATE =
            "CREATE TABLE " + UTILISATEUR_TABLE_NAME + " ( " +
                    UTILISATEUR_MAIL + " STRING PRIMARY KEY, " +
                    UTILISATEUR_LOGIN + " TEXT, " +
                    UTILISATEUR_PASSWORD + " TEXT);";

    public static final String EVENEMENT_TABLE_CREATE =
            "CREATE TABLE " + EVENEMENT_TABLE_NAME + " ( " +
                    EVENEMENT_DATE + " DATE, " +
                    EVENEMENT_TYPE + " STRING, " +
                    UTILISATEUR_MAIL + " STRING, " +
                    "FOREIGN KEY (" + UTILISATEUR_MAIL + ") REFERENCES " + UTILISATEUR_TABLE_NAME + " (" + UTILISATEUR_MAIL + "));";

    public static final String RELATION_TABLE_CREATE =
            "CREATE TABLE " + RELATION_TABLE_NAME +" ( " +
                    RELATION_ID_RELATION + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RELATION_ETAT_ACCEPTATION + " INTEGER, " +
                    RELATION_RECEIVER + " STRING, " +
                    RELATION_SENDER + " STRING, " +
                    UTILISATEUR_MAIL + "  STRING, " +
                    "FOREIGN KEY (" + UTILISATEUR_MAIL + ") REFERENCES " + UTILISATEUR_TABLE_NAME + " (" + UTILISATEUR_MAIL + "));";

    public static final String MESSAGE_TABLE_CREATE =
            "CREATE TABLE " + MESSAGE_TABLE_NAME + " ( " +
                    MESSAGE_TEXTE + " TEXT, " +
                    MESSAGE_DATE + " DATE, " +
                    UTILISATEUR_MAIL + " STRING," +
                    "FOREIGN KEY (" + UTILISATEUR_MAIL + ") REFERENCES " + UTILISATEUR_TABLE_NAME + " (" + UTILISATEUR_MAIL + "));";

    public static final String PREFERENCE_TABLE_CREATE =
            "CREATE TABLE " + PREFERENCE_TABLE_NAME + " ( " +
                    PREFERENCE_LANGUE + " TEXT, " +
                    PREFERENCE_NIVEAU_CONFIDENTIALITE + " TEXT, " +
                    UTILISATEUR_MAIL + " STRING," +
                    "FOREIGN KEY (" + UTILISATEUR_MAIL + ") REFERENCES " + UTILISATEUR_TABLE_NAME + " (" + UTILISATEUR_MAIL + "));";

    public static final String SEARCH_TABLE_CREATE =
            "CREATE TABLE " + SEARCH_TABLE_NAME + " ( " +
                    SEARCH_AGE + " INTEGER, " +
                    SEARCH_GENRE + " TEXT, " +
                    SEARCH_COULEUR_CHEVEUX + " TEXT, " +
                    SEARCH_LONGUEUR_CHEVEUX + " TEXT, " +
                    SEARCH_LOCALISATION + " TEXT, " +
                    SEARCH_COULEUR_YEUX + " TEXT, " +
                    UTILISATEUR_MAIL + " STRING, " +
                    "FOREIGN KEY (" + UTILISATEUR_MAIL + ") REFERENCES " + UTILISATEUR_TABLE_NAME + " (" + UTILISATEUR_MAIL + "));";

    public static final String DISPONIBILITE_TABLE_CREATE =
            "CREATE TABLE " + DISPONIBILITE_TABLE_NAME + "(" +
                    DISPONIBILITE_DATE + " TEXT," +
                    DISPONIBILITE_DISPO +" STRING," +
                    UTILISATEUR_MAIL + " STRING," +
                    "FOREIGN KEY (" + UTILISATEUR_MAIL + ") REFERENCES " + UTILISATEUR_TABLE_NAME + " (" + UTILISATEUR_MAIL + "));";

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
        db.execSQL(SEARCH_TABLE_CREATE);
        db.execSQL(DISPONIBILITE_TABLE_CREATE);
    }

    public static final String PROFIL_TABLE_DROP = "DROP TABLE IF EXISTS " + PROFIL_TABLE_NAME + ";";
    public static final String UTILISATEUR_TABLE_DROP = "DROP TABLE IF EXISTS " + UTILISATEUR_TABLE_NAME + ";";
    public static final String EVENEMENT_TABLE_DROP = "DROP TABLE IF EXISTS " + EVENEMENT_TABLE_NAME +";";
    public static final String PREFERENCE_TABLE_DROP = "DROP TABLE IF EXISTS " + PREFERENCE_TABLE_NAME+";";
    public static final String RELATION_TABLE_DROP = "DROP TABLE IF EXISTS " + RELATION_TABLE_NAME+";";
    public static final String MESSAGE_TABLE_DROP = "DROP TABLE If EXISTS " + MESSAGE_TABLE_NAME + ";";
    public static final String SEARCH_TABLE_DROP = "DROP TABLE IF EXISTS " + SEARCH_TABLE_NAME + ";";
    public static final String DISPONIBILITE_TABLE_DROP = "DROP TABLE IF EXISTS " + DISPONIBILITE_TABLE_NAME + ";";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PROFIL_TABLE_DROP);
        db.execSQL(UTILISATEUR_TABLE_DROP);
        db.execSQL(EVENEMENT_TABLE_DROP);
        db.execSQL(PREFERENCE_TABLE_DROP);
        db.execSQL(RELATION_TABLE_DROP);
        db.execSQL(MESSAGE_TABLE_DROP);
        db.execSQL(SEARCH_TABLE_DROP);
        db.execSQL(DISPONIBILITE_TABLE_DROP);
        onCreate(db);
    }

}

