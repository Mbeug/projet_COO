package com.example.maximebeugoms.uclove;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maximebeugoms.uclove.Database.DAOBase;
import com.example.maximebeugoms.uclove.Database.DatabaseHandler;
import com.example.maximebeugoms.uclove.Database.Preference_syst;
import com.example.maximebeugoms.uclove.Database.Preference_systDao;
import com.example.maximebeugoms.uclove.Database.Profil;
import com.example.maximebeugoms.uclove.Database.ProfilDao;
import com.example.maximebeugoms.uclove.Database.Search_profil;
import com.example.maximebeugoms.uclove.Database.Search_profilDao;
import com.example.maximebeugoms.uclove.Database.User;
import com.example.maximebeugoms.uclove.Database.UserDao;

import java.util.Locale;


/**
 * Created by damien on 22/04/16.
 */
public class LoginActivity extends MainActivity
{
    private Button connexion=null;
    private Button inscription=null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here

            UserDao userDb = new UserDao(getApplicationContext());
            SQLiteDatabase mDb = userDb.open();
            ProfilDao profilDb = new ProfilDao(getApplicationContext());
            SQLiteDatabase pDb = profilDb.open();
            Search_profilDao searchProfilDb = new Search_profilDao(getApplicationContext());
            SQLiteDatabase spDb = searchProfilDb.open();
            Preference_systDao preferencesDb = new Preference_systDao(getApplicationContext());
            SQLiteDatabase psDb = preferencesDb.open();

            //We create matching user and profile in the database
            /*  attributs possibles - attention a l'orthographe
            Homme   Man
            Femme   Woman
            Bi      Bi
            Brun    Brown
            Blond   Blond
            Blanc   White
            Roux    Red
            Châtain Chestnut
            Noir    Black
            Court   Short
            Mi-long Half-long
            Long    Long
            Bleu    Blue
            Vert    Green

            userDb.add(new User("pseudo", "mail", "mot de passe"));
            profilDb.add(new Profil("Nom", "mail", "sexe", age, "couleur cheveux", "longueur cheveux", "couleur yeux", "orientation", "Localisation", "NoPhoto"));
            */



            System.out.println("Language: " + Locale.getDefault().getLanguage());

            if (Locale.getDefault().getLanguage().contentEquals("en")) {

                userDb.add(new User("gano", "gano@yopmail.com", "gano"));
                profilDb.add(new Profil("Gano", "gano@yopmail.com", "Man", 35, "Brown", "Short", "Brown", "Woman", "Namur", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "gano@yopmail.com")); //no filters added yet
                preferencesDb.add(new Preference_syst("English",Preference_syst.HIGH,"gano@yopmail.com"));

                userDb.add(new User("yves", "yves@yopmail.com", "yves"));
                profilDb.add(new Profil("Yves", "yves@yopmail.com", "Man", 21, "Blond", "Half-long", "Blue", "Woman", "Wavre", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "yves@yopmail.com")); //no filters added yet

                userDb.add(new User("armand", "armand@yopmail.com", "armand"));
                profilDb.add(new Profil("Armand", "armand@yopmail.com", "Man", 23, "Blond", "Short", "Green", "Bi", "Bruxelles", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "armand@yopmail.com")); //no filters added yet

                userDb.add(new User("violette", "violette@yopmail.com", "violette"));
                profilDb.add(new Profil("Violette", "violette@yopmail.com", "Woman", 36, "Chestnut", "Long", "Blue", "Man", "Wavre", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "violette@yopmail.com")); //no filters added yet
                preferencesDb.add(new Preference_syst("English",Preference_syst.MEDIUM,"violette@yopmail"));

                userDb.add(new User("julie", "julie@yopmail.com", "julie"));
                profilDb.add(new Profil("Julie", "julie@yopmail.com", "Woman", 27, "Black", "Half-long", "Brown", "Woman", "Namur", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "julie@yopmail.com")); //no filters added yet
                preferencesDb.add(new Preference_syst("English",Preference_syst.HIGH,"julie@yopmail.com"));

                userDb.add(new User("fleur", "fleur@yopmail.com", "fleur"));
                profilDb.add(new Profil("Fleur", "fleur@yopmail.com", "Woman", 33, "Red", "Long", "Green", "Man", "Bruxelles", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "fleur@yopmail.com")); //no filters added yet

                userDb.add(new User("etienne", "etienne@yopmail.com", "etienne"));
                profilDb.add(new Profil("Etienne", "etienne@yopmail.com", "Man", 30, "Brown", "Short", "Brown", "Man", "Louvain", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "etienne@yopmail.com")); //no filters added yet

                userDb.add(new User("benoît", "benoît@yopmail.com", "benoît"));
                profilDb.add(new Profil("Benoît", "benoît@yopmail.com", "Man", 20, "Blond", "Half-long", "Blue", "Woman", "Wavre", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "benoît@yopmail.com")); //no filters added yet

                userDb.add(new User("georges", "georges@yopmail.com", "georges"));
                profilDb.add(new Profil("Georges", "georges@yopmail.com", "Man", 25, "Black", "Long", "Green", "Woman", "Bruxelles", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "georges@yopmail.com")); //no filters added yet

                userDb.add(new User("carole", "carole@yopmail.com", "carole"));
                profilDb.add(new Profil("Carole", "carole@yopmail.com", "Woman", 30, "Chestnut", "Long", "Blue", "Bi", "Wavre", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "carole@yopmail.com")); //no filters added yet

                userDb.add(new User("angelique", "angelique@yopmail.com", "angelique"));
                profilDb.add(new Profil("Angelique", "angelique@yopmail.com", "Woman", 40, "Black", "Half-long", "Brown", "Woman", "Louvain", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "angelique@yopmail.com")); //no filters added yet

                userDb.add(new User("pascaline", "pascaline@yopmail.com", "pascaline"));
                profilDb.add(new Profil("Pascaline", "pascaline@yopmail.com", "Woman", 19, "Red", "Half-long", "Green", "Man", "Bruxelles", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "pascaline@yopmail.com")); //no filters added yet

            } else {

                userDb.add(new User("gano", "gano@yopmail.com", "gano"));
                profilDb.add(new Profil("Gano", "gano@yopmail.com", "Homme", 35, "Brun", "Court", "Brun", "Femme", "Namur", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "gano@yopmail.com")); //no filters added yet

                userDb.add(new User("yves", "yves@yopmail.com", "yves"));
                profilDb.add(new Profil("Yves", "yves@yopmail.com", "Homme", 21, "Blond", "Mi-long", "Bleu", "Femme", "Wavre", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "yves@yopmail.com")); //no filters added yet

                userDb.add(new User("armand", "armand@yopmail.com", "armand"));
                profilDb.add(new Profil("Armand", "armand@yopmail.com", "Homme", 23, "Blond", "Court", "Vert", "Bi", "Bruxelles", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "armand@yopmail.com")); //no filters added yet

                userDb.add(new User("violette", "violette@yopmail.com", "violette"));
                profilDb.add(new Profil("Violette", "violette@yopmail.com", "Femme", 36, "Châtain", "Long", "Bleu", "Homme", "Wavre", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "violette@yopmail.com")); //no filters added yet
                preferencesDb.add(new Preference_syst("English",Preference_syst.HIGH,"violette@yopmail"));

                userDb.add(new User("julie", "julie@yopmail.com", "julie"));
                profilDb.add(new Profil("Julie", "julie@yopmail.com", "Femme", 27, "Noir", "Mi-long", "Brun", "Femme", "Namur", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "julie@yopmail.com")); //no filters added yet
                preferencesDb.add(new Preference_syst("English",Preference_syst.MEDIUM,"julie@yopmail.com"));

                userDb.add(new User("fleur", "fleur@yopmail.com", "fleur"));
                profilDb.add(new Profil("Fleur", "fleur@yopmail.com", "Femme", 33, "Roux", "Long", "Vert", "Homme", "Bruxelles", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "fleur@yopmail.com")); //no filters added yet

                userDb.add(new User("etienne", "etienne@yopmail.com", "etienne"));
                profilDb.add(new Profil("Etienne", "etienne@yopmail.com", "Homme", 30, "Brun", "Court", "Brun", "Homme", "Louvain", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "etienne@yopmail.com")); //no filters added yet

                userDb.add(new User("benoît", "benoît@yopmail.com", "benoît"));
                profilDb.add(new Profil("Benoît", "benoît@yopmail.com", "Homme", 20, "Blond", "Mi-long", "Bleu", "Femme", "Wavre", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "benoît@yopmail.com")); //no filters added yet

                userDb.add(new User("georges", "georges@yopmail.com", "georges"));
                profilDb.add(new Profil("Georges", "georges@yopmail.com", "Homme", 25, "Noir", "Long", "Vert", "Femme", "Bruxelles", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "georges@yopmail.com")); //no filters added yet

                userDb.add(new User("carole", "carole@yopmail.com", "carole"));
                profilDb.add(new Profil("Carole", "carole@yopmail.com", "Femme", 30, "Châtain", "Long", "Bleu", "Bi", "Wavre", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "carole@yopmail.com")); //no filters added yet

                userDb.add(new User("angelique", "angelique@yopmail.com", "angelique"));
                profilDb.add(new Profil("Angelique", "angelique@yopmail.com", "Femme", 40, "Noir", "Mi-long", "Brun", "Femme", "Louvain", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "angelique@yopmail.com")); //no filters added yet

                userDb.add(new User("pascaline", "pascaline@yopmail.com", "pascaline"));
                profilDb.add(new Profil("Pascaline", "pascaline@yopmail.com", "Femme", 19, "Roux", "Mi-long", "Vert", "Homme", "Bruxelles", "NoPhoto"));
                searchProfilDb.add(new Search_profil(0, " ", " ", " ", " ", "pascaline@yopmail.com")); //no filters added yet

            }

            //Close db
            userDb.close();
            profilDb.close();
            searchProfilDb.close();
            preferencesDb.close();

            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }


        final EditText nom = (EditText) findViewById(R.id.textLogger);
        final EditText mdp = (EditText) findViewById(R.id.textMdp);



        connexion = (Button) findViewById(R.id.btn_co);
        inscription= (Button) findViewById(R.id.btn_insc);

        connexion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vue){

                //Get the values from the input fields
                String mNom = nom.getText().toString();
                String mMdp = mdp.getText().toString();

                //Open db
                UserDao userDb = new UserDao(getApplicationContext());
                SQLiteDatabase mDb = userDb.open();

                //Get the user associated to email from db
                User checker = userDb.selectionner(mNom);

                //We create the toast to show some text
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP| Gravity.START, 0, 0);

                userDb.close();

                //If the account doesn't exist || the password doesn't match
                if(checker == null || !checker.getPassword().equals(mMdp)){
                    toast.makeText(LoginActivity.this, R.string.mauvais_login, toast.LENGTH_SHORT).show();
                }

                else {
                    toast.makeText(LoginActivity.this, R.string.connexion, toast.LENGTH_SHORT).show();

                    //On récupère l'application
                    Application application = (Application)Uclove.getContext();
                    Uclove app = (Uclove)application;

                    //On set User
                    app.setUser(checker);

                    Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        inscription.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vue){
                Intent intent = new Intent(LoginActivity.this,InscriptionActivity.class);
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}


