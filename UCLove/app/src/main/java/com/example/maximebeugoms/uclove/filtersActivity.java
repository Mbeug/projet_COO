package com.example.maximebeugoms.uclove;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.maximebeugoms.uclove.Database.User;
import com.example.maximebeugoms.uclove.Database.UserDao;

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

        final EditText ageFiltre = (EditText) findViewById(R.id.dateNaissance);
        final EditText localisationFiltre = (EditText) findViewById(R.id.localisation);

        // Spinner element
        final Spinner couleurCheveuxSpinner = (Spinner) findViewById(R.id.couleurCheveuxSpinner);
        final Spinner longueurCheveuxSpinner = (Spinner) findViewById(R.id.longueurCheveuxSpinner);
        final Spinner couleurYeuxSpinner = (Spinner) findViewById(R.id.couleurYeuxSpinner);

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
            }


        });


        // Spinner click listener
        couleurCheveuxSpinner.setOnItemSelectedListener(this);
        longueurCheveuxSpinner.setOnItemSelectedListener(this);
        couleurYeuxSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements

        List<String> couleurCheveuxCategories = new ArrayList<String>();
        couleurCheveuxCategories.add(getResources().getString(R.string.brun));
        couleurCheveuxCategories.add(getResources().getString(R.string.blond));
        couleurCheveuxCategories.add(getResources().getString(R.string.noir));
        couleurCheveuxCategories.add(getResources().getString(R.string.roux));
        couleurCheveuxCategories.add(getResources().getString(R.string.chatain));
        couleurCheveuxCategories.add(getResources().getString(R.string.blanc));

        List<String> longueurCheveuxCategories = new ArrayList<String>();
        longueurCheveuxCategories.add(getResources().getString(R.string.court));
        longueurCheveuxCategories.add(getResources().getString(R.string.milong));
        longueurCheveuxCategories.add(getResources().getString(R.string.Long));

        List<String> couleurYeuxCategories = new ArrayList<String>();
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
