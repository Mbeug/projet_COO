package com.example.maximebeugoms.uclove;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.example.maximebeugoms.uclove.Database.Preference_syst;
import com.example.maximebeugoms.uclove.Database.Preference_systDao;
import com.example.maximebeugoms.uclove.Database.Profil;

/**
 * Created by damien on 22/04/16.
 */

public class ProfileOtherActivity extends MainActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profil_other_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.pocontainer, getFragment(getPrivacyLevel()))
                    .commit();
        }
    }

    private Fragment getFragment(String privacyLevel){
        switch (privacyLevel){
            case Preference_syst.LOW :
                return new ProfileOtherLowFragment();
            case Preference_syst.HIGH :
                return new ProfileOtherHighFragment();
            case Preference_syst.MEDIUM :
                return new ProfileOtherMediumFragment();
            default:
                return new ProfileOtherLowFragment();
        }
    }

    private String getPrivacyLevel() {
        //On récupère l'application
        Application application = (Application)Uclove.getContext();
        Uclove app = (Uclove)application;

        //On get Profil selectionné
        Profil profilDecouverte = app.getProfil();

        //open la db pour avoir les preferences du user selectionne
        Preference_systDao prefsDB = new Preference_systDao(this);
        SQLiteDatabase pDb = prefsDB.open();
        Preference_syst prefSyst = prefsDB.select(profilDecouverte.getMail());
        prefsDB.close();
        if (prefSyst!=null) {
            return prefSyst.getNiveau_confidentialite();
        } else {
            return Preference_syst.MEDIUM;
        }
    }


}
