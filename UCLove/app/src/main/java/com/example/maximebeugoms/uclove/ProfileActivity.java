package com.example.maximebeugoms.uclove;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

import com.example.maximebeugoms.uclove.Database.Profil;
import com.example.maximebeugoms.uclove.Database.ProfilDao;
import com.example.maximebeugoms.uclove.Database.User;
import com.example.maximebeugoms.uclove.Database.UserDao;

/**
 * Created by damien on 22/04/16.
 */
public class ProfileActivity extends MainActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Application application = (Application)Uclove.getContext();
        Uclove app = (Uclove)application;

        //On get User
        User currentUser = app.getUser();

        //Open db
        ProfilDao profilDb = new ProfilDao(getApplicationContext());
        SQLiteDatabase mDb = profilDb.open();
        Profil currentProfil = profilDb.selectionner(currentUser.getMail());
        System.out.println(currentUser.getMail());
        //TextView nomProfil = (TextView) findViewById(R.id.nomProfil);
        //System.out.println(currentProfil.getMail());
       // nomProfil.setText(currentProfil.getNom());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return super.onCreateOptionsMenu(menu);
    }
}
