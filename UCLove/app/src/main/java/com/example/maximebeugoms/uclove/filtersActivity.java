package com.example.maximebeugoms.uclove;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maximebeugoms.uclove.Database.Profil;
import com.example.maximebeugoms.uclove.Database.ProfilDao;
import com.example.maximebeugoms.uclove.Database.Search_profil;
import com.example.maximebeugoms.uclove.Database.Search_profilDao;
import com.example.maximebeugoms.uclove.Database.User;
import com.example.maximebeugoms.uclove.Database.UserDao;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dilara on 4/05/2016.
 */
public class FiltersActivity extends MainActivity implements AdapterView.OnItemSelectedListener {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filters_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Application application = (Application) Uclove.getContext();
        Uclove app = (Uclove) application;

        //On get User
        final User currentUser = app.getUser();

        //Open db
        final Search_profilDao filtreDb = new Search_profilDao(getApplicationContext());
        SQLiteDatabase mDb = filtreDb.open();
        final Search_profil currentFilters = filtreDb.selectionner(currentUser.getMail());
        filtreDb.close();

        final EditText ageFiltre = (EditText) findViewById(R.id.dateNaissance);
        final EditText localisationFiltre = (EditText) findViewById(R.id.localisation);

        if(currentFilters.getAge() != 0)
            ageFiltre.setHint(String.valueOf(currentFilters.getAge()));
        if(!currentFilters.getLocalisation().equals(" "))
            localisationFiltre.setHint(currentFilters.getLocalisation());

        // Spinner element
        final Spinner couleurCheveuxSpinner = (Spinner) findViewById(R.id.couleurCheveuxSpinner);
        final Spinner longueurCheveuxSpinner = (Spinner) findViewById(R.id.longueurCheveuxSpinner);
        final Spinner couleurYeuxSpinner = (Spinner) findViewById(R.id.couleurYeuxSpinner);

        assert couleurCheveuxSpinner != null;
        couleurCheveuxSpinner.post(new Runnable() {
            @Override
            public void run() {

                couleurCheveuxSpinner.setSelection(((ArrayAdapter) couleurCheveuxSpinner.getAdapter()).getPosition(currentFilters.getCouleur_cheveux()));
            }
        });

        assert longueurCheveuxSpinner != null;
        longueurCheveuxSpinner.post(new Runnable() {
            @Override
            public void run() {

                longueurCheveuxSpinner.setSelection(((ArrayAdapter) longueurCheveuxSpinner.getAdapter()).getPosition(currentFilters.getLongueur_cheveux()));
            }
        });
        assert couleurYeuxSpinner != null;
        couleurYeuxSpinner.post(new Runnable() {
            @Override
            public void run() {

                couleurYeuxSpinner.setSelection(((ArrayAdapter) couleurYeuxSpinner.getAdapter()).getPosition(currentFilters.getCouleur_yeux()));
            }
        });


        Button button = (Button) findViewById(R.id.Submit);

        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String age = ageFiltre.getText().toString();
                String localisation = localisationFiltre.getText().toString();
                String couleurCheveux = couleurCheveuxSpinner.getSelectedItem().toString();
                String longueurCheveux = longueurCheveuxSpinner.getSelectedItem().toString();
                String couleurYeux = couleurYeuxSpinner.getSelectedItem().toString();

                System.out.println(age);
                System.out.println(localisation);
                System.out.println(couleurCheveux);
                System.out.println(longueurCheveux);
                System.out.println(couleurYeux);

                //Check multiple conditions
                if (age == null || age.isEmpty()) {
                    age = String.valueOf(currentFilters.getAge());
                }
                if (localisation == null || localisation.isEmpty()) {
                    localisation = currentFilters.getLocalisation();
                }

                //Open db
                Search_profilDao upFiltreDb = new Search_profilDao(getApplicationContext());
                SQLiteDatabase mDb = upFiltreDb.open();

                // create updated user and profile
                Search_profil upFilters = new Search_profil(Integer.parseInt(age), localisation, longueurCheveux, couleurCheveux, couleurYeux, currentUser.getMail());

                //We update user and profile in the database
                upFiltreDb.update(upFilters);

                //Close db
                upFiltreDb.close();


                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP | Gravity.START, 0, 0);

                toast.makeText(FiltersActivity.this, R.string.filtresModifie, toast.LENGTH_SHORT).show();

                Intent intent = new Intent(FiltersActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();

            }


        });


        // Spinner click listener
        couleurCheveuxSpinner.setOnItemSelectedListener(this);
        longueurCheveuxSpinner.setOnItemSelectedListener(this);
        couleurYeuxSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements

        List<String> couleurCheveuxCategories = new ArrayList<String>();
        couleurCheveuxCategories.add(" ");
        couleurCheveuxCategories.add(getResources().getString(R.string.brun));
        couleurCheveuxCategories.add(getResources().getString(R.string.blond));
        couleurCheveuxCategories.add(getResources().getString(R.string.noir));
        couleurCheveuxCategories.add(getResources().getString(R.string.roux));
        couleurCheveuxCategories.add(getResources().getString(R.string.chatain));
        couleurCheveuxCategories.add(getResources().getString(R.string.blanc));

        List<String> longueurCheveuxCategories = new ArrayList<String>();
        longueurCheveuxCategories.add(" ");
        longueurCheveuxCategories.add(getResources().getString(R.string.court));
        longueurCheveuxCategories.add(getResources().getString(R.string.milong));
        longueurCheveuxCategories.add(getResources().getString(R.string.Long));

        List<String> couleurYeuxCategories = new ArrayList<String>();
        couleurYeuxCategories.add(" ");
        couleurYeuxCategories.add(getResources().getString(R.string.bleu));
        couleurYeuxCategories.add(getResources().getString(R.string.brun));
        couleurYeuxCategories.add(getResources().getString(R.string.vert));


        // Creating adapter for spinner
        ArrayAdapter<String> couleurCheveuxDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, couleurCheveuxCategories);
        ArrayAdapter<String> longueurCheveuxDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, longueurCheveuxCategories);
        ArrayAdapter<String> couleurYeuxDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, couleurYeuxCategories);

        // Drop down layout style - list view with radio button
        couleurCheveuxDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        longueurCheveuxDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        couleurYeuxDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        couleurCheveuxSpinner.setAdapter(couleurCheveuxDataAdapter);
        longueurCheveuxSpinner.setAdapter(longueurCheveuxDataAdapter);
        couleurYeuxSpinner.setAdapter(couleurYeuxDataAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);


    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
/*
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        */
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }



}
