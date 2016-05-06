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

import java.util.Calendar;

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

        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                                             public void onSelectedDayChange(CalendarView calendar,int year , int month , int dayOfMonth){



                                                 final String[] date = new String[1];
                                                 date[0] = String.valueOf(dayOfMonth) + "-" + String.valueOf(month) + "-" + String.valueOf(year);

                                                 //String mail_user;

                                                 final String[] dispo = new String[1] ;

                                                 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CalendarActivity.this);

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

                                                                 finish(date[0],dispo[0]);

                                                                 dialog.cancel();
                                                             }
                                                         })
                                                         .setNegativeButton("Indisponible",new DialogInterface.OnClickListener() {
                                                             public void onClick(DialogInterface dialog,int id) {
                                                                 //ajouter valeur "indisponible" dans bdd

                                                                 dispo[0]  = "Indisponible";

                                                                 finish(date[0],dispo[0]);

                                                                 dialog.cancel();
                                                             }
                                                         })
                                                         .setNeutralButton("Ne sais pas encore", new DialogInterface.OnClickListener(){
                                                             public void onClick(DialogInterface dialog, int id){
                                                                 //ajouter valeur "ne sais pas" dans bdd

                                                                 dispo[0]  = "Ne sais pas encore";

                                                                 finish(date[0],dispo[0]);

                                                                 dialog.cancel();
                                                             }
                                                         });

                                                 AlertDialog alertDialog = alertDialogBuilder.create();

                                                 // show it
                                                 alertDialog.show();
                                             }

        }
        );

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

    public void finish (String date , String dispo){

        Application application = (Application)Uclove.getContext();
        Uclove app = (Uclove)application;

        String mail_user = app.getUser().getMail();

        Disponibilite d = new Disponibilite(mail_user, dispo, date);

        DisponibiliteDao disponibiliteDao = new DisponibiliteDao(CalendarActivity.this);
        disponibiliteDao.open();
        disponibiliteDao.add(d);

        disponibiliteDao.close();

    }

}

