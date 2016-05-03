package com.example.maximebeugoms.uclove;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by Dilara on 4/05/2016.
 */
public class PrefSystActivity extends MainActivity implements OnItemSelectedListener {

    String langue = "Français";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref_syst_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Spinner
        final Spinner langues = (Spinner) findViewById(R.id.spinnerLang);

        langues.setOnItemSelectedListener(this);
        List<String> languesCategories = new ArrayList<String>();
        languesCategories.add("English");
        languesCategories.add("Français");

        // Creating adapter for spinner
        ArrayAdapter<String> languesDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languesCategories);
        // Drop down layout style - list view with radio button
        languesDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        langues.setAdapter(languesDataAdapter);

        langue = langues.getSelectedItem().toString();



    }


    public void switchLocaleLanguage(String langue) {
        if (langue.equals("English")) {
            Locale loc = Locale.ENGLISH;
            Configuration config = new Configuration();
            config.locale = loc;
            Locale.setDefault(loc);
            getBaseContext().getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        } else {
            Locale loc = Locale.FRANCE;
            Configuration config = new Configuration();
            config.locale = loc;
            Locale.setDefault(loc);
            getBaseContext().getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        }
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        switchLocaleLanguage(langue);

        /*Intent intent = new Intent(PrefSystActivity.this, PrefSystActivity.class);
        startActivity(intent);
        finish();*/
/*
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        */
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}