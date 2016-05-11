package com.example.maximebeugoms.uclove;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.maximebeugoms.uclove.Database.Evenement;
import com.example.maximebeugoms.uclove.Database.EvenementDao;
import com.example.maximebeugoms.uclove.Database.User;

import java.util.ArrayList;

/**
 * Created by Menal_000 on 06-05-16.
 */
public class HistoryActivity extends MainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.history_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        EvenementDao eventDb = new EvenementDao(this);
        SQLiteDatabase eDb = eventDb.open();

        //On récupère l'application
        Application application = (Application)Uclove.getContext();
        Uclove app = (Uclove)application;

        User user = app.getUser();

        ArrayList<Evenement> listevent = eventDb.select(user.getMail());
        eventDb.close();



        ListView listView = (ListView) findViewById(R.id.histView);

        // adapter
        ArrayAdapter<Evenement> adapter = new ArrayAdapter<Evenement>(this,android.R.layout.simple_list_item_2,listevent);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
