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
        setContentView(R.layout.calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Application application = (Application) Uclove.getContext();
        Uclove app = (Uclove) application;

        //Création de l'objet Dao
        final DisponibiliteDao dispoDb = new DisponibiliteDao(getApplicationContext());
        SQLiteDatabase mDb = dispoDb.open();

        //Close db
        dispoDb.close();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public void setOnDateChangeListener(CalendarView view, int year, int month, int day){

        String date = String.valueOf(day) + "-" + String.valueOf(month) + "-" + String.valueOf(year);

        //String mail_user;

        final String[] dispo = new String[1];

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Disponibilites");

        // set dialog message
        alertDialogBuilder
                .setMessage("Etes-vous disponible ou pas pour cette date ?")
                .setCancelable(false)
                .setPositiveButton("Disponible",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // ajouter valeur "disponible" dans bdd

                        dispo[0] = "Disponible";

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Indisponible",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        //ajouter valeur "indisponible" dans bdd

                        dispo[0] = "Indisponible";

                        dialog.cancel();
                    }
                })
                .setNeutralButton("Ne sais pas encore", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        //ajouter valeur "ne sais pas" dans bdd

                        dispo[0] = "Ne sais pas encore";

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

        //Disponibilite d = new Disponibilite(mail_user, dispo[0], date);
        
        //ajouter la disponibilite a la bdd
    }
}

