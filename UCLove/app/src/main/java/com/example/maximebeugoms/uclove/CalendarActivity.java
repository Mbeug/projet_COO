package com.example.maximebeugoms.uclove;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;

import com.example.maximebeugoms.uclove.Database.Profil;
import com.example.maximebeugoms.uclove.Database.ProfilDao;
import com.example.maximebeugoms.uclove.Database.User;
import com.example.maximebeugoms.uclove.Database.UserDao;
import com.example.maximebeugoms.uclove.Database.DisponibiliteDao;
import com.example.maximebeugoms.uclove.Database.Disponibilite;

/**
 * Created by damien on 22/04/16.
 */
public class CalendarActivity extends MainActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    /*
        Application application = (Application) Uclove.getContext();
        Uclove app = (Uclove) application;

        //On get User
        final Disponibilite currentDisponibilite = app.getDisponibilite();

        //Open db
        final DisponibiliteDao dispoDb = new DisponibiliteDao(getApplicationContext());
        SQLiteDatabase mDb = dispoDb.open();
        final Disponibilite currentDisponibilite = dispoDb.selectionner(currentDisponibilite.getMail());

        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //Open db
    DisponibiliteDao dispoDb = new DisponibiliteDao(getApplicationContext());
    SQLiteDatabase mDb = dispoDb.open();


    // create updated disponibilite
    Disponibilite updatedDisponibilite = new Disponibilite(...);


    //We update disponibilite
    dispoDb.update(updatedDisponibilite);

    //Close db
    dispoDb.close();
    dispoDb.close();

    public void setOnDateChangeListener(CalendarView view, int year, int month, int day){

        String date = String.valueOf(day) + "-" + String.valueOf(month) + "-" + String.valueOf(year);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Disponibilites");

        // set dialog message
        alertDialogBuilder
                .setMessage("Etes-vous disponible ou pas pour cette date ?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // ajouter valeur dans bdd
                        CalendarActivity.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        //ajouter valeur deans bdd
                        dialog.cancel();
                    }
                })
        .setNeutralButton("Maybe", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    //ajouter valeur dans bdd
                   dialog.cancel();
                }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}

