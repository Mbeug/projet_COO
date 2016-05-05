package com.example.maximebeugoms.uclove;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.example.maximebeugoms.uclove.Database.Preference_syst;
import com.example.maximebeugoms.uclove.Database.Preference_systDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by Dilara on 4/05/2016.
 */
public class PrefSystActivity extends MainActivity implements OnItemSelectedListener {

    //String langue = "Français";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref_syst_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Spinner languages
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

        //langue = langues.getSelectedItem().toString();


        // Spinner privacy level
        final Spinner privLevels = (Spinner) findViewById(R.id.spinnerPriv);
        privLevels.setOnItemSelectedListener(this);
        List<String> pLevelCategories = new ArrayList<String>();
        pLevelCategories.add(getString(R.string.spinner_lvl_secret_low));
        pLevelCategories.add(getString(R.string.spinner_lvl_secret_medium));
        pLevelCategories.add(getString(R.string.spinner_lvl_secret_high));

        // adapter
        ArrayAdapter<String> pLAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,pLevelCategories);
        pLAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        privLevels.setAdapter(pLAdapter);


    }


    public void switchLocaleLanguage(String langue) {
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP| Gravity.START, 0, 0);
        Locale loc;
        Configuration config = new Configuration();

        switch (langue) {

            case "English":
                loc= Locale.ENGLISH;
                config.locale = loc;
                Locale.setDefault(loc);
                getBaseContext().getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                break;
            case "Français":
                 loc = Locale.FRENCH;
                config.locale = loc;
                Locale.setDefault(loc);
                getBaseContext().getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                break;
            default:
                //toast.makeText(PrefSystActivity.this, R.string.langSelected, toast.LENGTH_SHORT).show();
        }

    }

    public String privacyLevel(String item) {
        if (item.equals(getString(R.string.spinner_lvl_secret_low))) {
            return Preference_syst.LOW;
        }
        else if (item.equals(getString(R.string.spinner_lvl_secret_high))) {
            return Preference_syst.HIGH;
        }
        return Preference_syst.MEDIUM;
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        // open the db
        Uclove app = (Uclove) getApplication();
        String mailUser = app.getUser().getMail();
        Preference_systDao preferencesDb = new Preference_systDao(getApplicationContext());
        SQLiteDatabase psDb = preferencesDb.open();

        Preference_syst currentUserPrefs = preferencesDb.select(mailUser);

        switch (parent.getId()) {
            case R.id.spinnerLang :
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                // On selecting a spinner item
                switchLocaleLanguage(item);

                if (currentUserPrefs!=null) {
                    currentUserPrefs.setLangue(item);
                    preferencesDb.update(currentUserPrefs);
                } else {
                    currentUserPrefs = new Preference_syst(item, "",mailUser);
                }
                break;
            case R.id.spinnerPriv :

                // update the db according to the new privacy level
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                if (currentUserPrefs !=null) {
                    currentUserPrefs.setNiveau_confidentialite(privacyLevel(item));
                    preferencesDb.update(currentUserPrefs);
                }
                else {
                    currentUserPrefs = new Preference_syst("",privacyLevel(item),mailUser);
                    preferencesDb.add(currentUserPrefs);
                }

                break;

            }

        preferencesDb.close();




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}