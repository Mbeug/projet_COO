package com.example.maximebeugoms.uclove;

import static com.example.maximebeugoms.uclove.Constants.FIFTH_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.FIRST_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.SECOND_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.THIRD_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.FOURTH_COLUMN;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maximebeugoms.uclove.Database.Evenement;
import com.example.maximebeugoms.uclove.Database.EvenementDao;
import com.example.maximebeugoms.uclove.Database.Profil;
import com.example.maximebeugoms.uclove.Database.ProfilDao;
import com.example.maximebeugoms.uclove.Database.Search_profil;
import com.example.maximebeugoms.uclove.Database.Search_profilDao;
import com.example.maximebeugoms.uclove.Database.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;


/**
 * Created by damien on 22/04/16.
 */
public class SearchActivity extends MainActivity {

    private ListView mainListView;
    //private ArrayAdapter<String> listAdapter ;
    private ArrayList<HashMap<String, String>> list;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Application application = (Application) Uclove.getContext();
        Uclove app = (Uclove) application;

        //On get User
        final User currentUser = app.getUser();

        //Open databases and get filters
        Search_profilDao search_profilDb = new Search_profilDao(getApplicationContext());
        SQLiteDatabase spDb = search_profilDb.open();
        Search_profil currentUserFilters = search_profilDb.selectionner(currentUser.getMail());
        ProfilDao profilDb = new ProfilDao(getApplicationContext());
        SQLiteDatabase pDb = profilDb.open();
        Profil currentProfil = profilDb.selectionner(currentUser.getMail());

        //get current users filters
        String orientation = currentProfil.getOrientation();
        String location = currentUserFilters.getLocalisation();
        String couleur_cheveux = currentUserFilters.getCouleur_cheveux();
        String longueur_cheveux = currentUserFilters.getLongueur_cheveux();
        String couleur_yeux = currentUserFilters.getCouleur_yeux();
        int age = currentUserFilters.getAge();
        int maxAge = 0;
        int minAge = 0;
        if (age != 0) {
            maxAge = age + 2;
            minAge = age - 2;
        }

        String queryOrientation = "";
        String queryLocation = "";
        String queryCouleur_cheveux = "";
        String queryLongueur_cheveux = "";
        String queryCouleur_yeux = "";
        String queryAge = "";

        if(!orientation.equals("Bi")) queryOrientation = " AND sexe = '" + orientation + "' ";
        if(!location.equals(" ")) queryLocation = " AND localisation = '" + location + "' ";
        if(!couleur_cheveux.equals(" ")) queryCouleur_cheveux = " AND couleur_cheveux = '" + couleur_cheveux + "' ";
        if(!longueur_cheveux.equals(" ")) queryLongueur_cheveux = " AND longueur_cheveux = '" + longueur_cheveux + "' ";
        if(!couleur_yeux.equals(" ")) queryCouleur_yeux = " AND couleur_yeux = '" + couleur_yeux + "' ";
        if(age != 0) queryAge = " AND age >= " + minAge + " AND age <= " + maxAge;

        // Find the ListView resource.
        mainListView = (ListView) findViewById(R.id.rechercheProfils);
        Log.v("SearchActivity", "mainListView null ? " + (mainListView == null));
        list = new ArrayList<HashMap<String, String>>();

        Cursor cursor = null;

        cursor = pDb.rawQuery("SELECT * FROM Profil WHERE mail != ? "
                + queryOrientation
                + queryLocation
                + queryCouleur_cheveux
                + queryLongueur_cheveux
                + queryCouleur_yeux
                + queryAge
                , new String[]{currentUser.getMail()});

        //ArrayList<String> profilList = new ArrayList<String>();
        //listAdapter = new ArrayAdapter<String>(this, R.layout.column_row, profilList);
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                String mail = cursor.getString(cursor
                        .getColumnIndex("mail"));

                Profil cursorProfil = profilDb.selectionner(mail);

                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put(FIRST_COLUMN, cursorProfil.getPhoto_path());
                temp.put(SECOND_COLUMN, cursorProfil.getNom());
                temp.put(THIRD_COLUMN, Integer.toString(cursorProfil.getAge()));
                temp.put(FOURTH_COLUMN, cursorProfil.getLocalisation());
                temp.put(FIFTH_COLUMN, cursorProfil.getMail());
                list.add(temp);

                cursor.moveToNext();
            }
        }

        final profilListAdapter adapter = new profilListAdapter(this, list);
        mainListView.setAdapter(adapter);

        profilDb.close();
        search_profilDb.close();

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //int pos=position+1;
                //Toast.makeText(SearchActivity.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();

                HashMap<String, String> profilList = (HashMap<String, String>) adapter.getItem(position);
                String mail = profilList.get(FIFTH_COLUMN);
                System.out.println(mail);

                //Open profil database, get selected profil and close
                ProfilDao profilDb = new ProfilDao(getApplicationContext());
                SQLiteDatabase pDb = profilDb.open();
                Profil profilDecouverte = profilDb.selectionner(mail);
                profilDb.close();

                //On récupère l'application
                Application application = (Application) Uclove.getContext();
                Uclove app = (Uclove) application;

                //On set Profil
                app.setProfil(profilDecouverte);

                // On ajoute la visite de profil a l'historique
                EvenementDao eventDb = new EvenementDao(getApplicationContext());
                SQLiteDatabase eDb = eventDb.open();
                Evenement event = new Evenement(app.getUser().getMail(), new Date().toString(),getString(R.string.event_type_profil) + " " + app.getProfil().getMail());
                eventDb.add(event);
                eventDb.close();



                Intent intent = new Intent(SearchActivity.this, ProfileOtherActivity.class);
                startActivity(intent);
                //finish();
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
