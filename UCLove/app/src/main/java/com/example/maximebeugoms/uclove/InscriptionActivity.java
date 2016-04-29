package com.example.maximebeugoms.uclove;

import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;
/*
 * Created by maximebeugoms on 28/04/16.
 */


public class InscriptionActivity extends MainActivity implements OnItemSelectedListener{
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.inscription_view);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            final EditText nom = (EditText) findViewById(R.id.nom);
            final EditText mdp = (EditText) findViewById(R.id.mdp);
            final EditText mail = (EditText) findViewById(R.id.email);
            final EditText naissance = (EditText) findViewById(R.id.dateNaissance);



            Button button = (Button) findViewById(R.id.Soumettre);

            button.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {/*
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP| Gravity.START, 0, 0);
                    toast.makeText(InscriptionActivity.this, nom.getText(), toast.LENGTH_SHORT).show();
                    toast.makeText(InscriptionActivity.this, mdp.getText(), toast.LENGTH_SHORT).show();
                    toast.makeText(InscriptionActivity.this, mail.getText(), toast.LENGTH_SHORT).show();
                    toast.makeText(InscriptionActivity.this, naissance.getText(), toast.LENGTH_SHORT).show();
*/
                }

            });

            // Spinner element
            Spinner sexSpinner = (Spinner) findViewById(R.id.sexeSpinner);
            Spinner couleurCheveuxSpinner = (Spinner) findViewById(R.id.couleurCheveuxSpinner);
            Spinner orientationSpinner = (Spinner) findViewById(R.id.orientationSpinner);
            Spinner longueurCheveuxSpinner = (Spinner) findViewById(R.id.longueurCheveuxSpinner);
            Spinner couleurYeuxSpinner = (Spinner) findViewById(R.id.couleurYeuxSpinner);

            // Spinner click listener
            sexSpinner.setOnItemSelectedListener(this);
            couleurCheveuxSpinner.setOnItemSelectedListener(this);
            orientationSpinner.setOnItemSelectedListener(this);
            longueurCheveuxSpinner.setOnItemSelectedListener(this);
            couleurYeuxSpinner.setOnItemSelectedListener(this);

            // Spinner Drop down elements
            List<String> sexCategories = new ArrayList<String>();
            sexCategories.add(getResources().getString(R.string.homme));
            sexCategories.add(getResources().getString(R.string.femme));

            List<String> couleurCheveuxCategories = new ArrayList<String>();
            couleurCheveuxCategories.add(getResources().getString(R.string.brun));
            couleurCheveuxCategories.add(getResources().getString(R.string.blond));
            couleurCheveuxCategories.add(getResources().getString(R.string.noir));
            couleurCheveuxCategories.add(getResources().getString(R.string.roux));
            couleurCheveuxCategories.add(getResources().getString(R.string.chatain));
            couleurCheveuxCategories.add(getResources().getString(R.string.blanc));

            List<String> orientationCategories = new ArrayList<String>();
            orientationCategories.add(getResources().getString(R.string.homme));
            orientationCategories.add(getResources().getString(R.string.femme));
            orientationCategories.add("Bi");

            List<String> longueurCheveuxCategories = new ArrayList<String>();
            longueurCheveuxCategories.add(getResources().getString(R.string.court));
            longueurCheveuxCategories.add(getResources().getString(R.string.milong));
            longueurCheveuxCategories.add(getResources().getString(R.string.Long));

            List<String> couleurYeuxCategories = new ArrayList<String>();
            couleurYeuxCategories.add(getResources().getString(R.string.bleu));
            couleurYeuxCategories.add(getResources().getString(R.string.brun));
            couleurYeuxCategories.add(getResources().getString(R.string.vert));



            // Creating adapter for spinner
            ArrayAdapter<String> sexDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexCategories);
            ArrayAdapter<String> couleurCheveuxDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, couleurCheveuxCategories);
            ArrayAdapter<String> orientationDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, orientationCategories);
            ArrayAdapter<String> longueurCheveuxDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, longueurCheveuxCategories);
            ArrayAdapter<String> couleurYeuxDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, couleurYeuxCategories);

            // Drop down layout style - list view with radio button
            sexDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            couleurCheveuxDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            orientationDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            longueurCheveuxDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            couleurYeuxDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            sexSpinner.setAdapter(sexDataAdapter);
            couleurCheveuxSpinner.setAdapter(couleurCheveuxDataAdapter);
            orientationSpinner.setAdapter(orientationDataAdapter);
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
