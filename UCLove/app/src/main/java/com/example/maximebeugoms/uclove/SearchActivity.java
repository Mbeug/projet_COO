package com.example.maximebeugoms.uclove;

import static com.example.maximebeugoms.uclove.Constants.FIRST_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.SECOND_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.THIRD_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.FOURTH_COLUMN;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.maximebeugoms.uclove.Database.Profil;
import com.example.maximebeugoms.uclove.Database.ProfilDao;
import com.example.maximebeugoms.uclove.Database.User;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by damien on 22/04/16.
 */
public class SearchActivity extends MainActivity{

    private ListView mainListView ;
    //private ArrayAdapter<String> listAdapter ;
    private ArrayList<HashMap<String, String>> list;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Application application = (Application) Uclove.getContext();
        Uclove app = (Uclove) application;

        //On get User
        final User currentUser = app.getUser();


        // Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.rechercheProfils );
        list = new ArrayList<HashMap<String,String>>();

        //Open profil database and query
        ProfilDao profilDb = new ProfilDao(getApplicationContext());
        SQLiteDatabase pDb = profilDb.open();
        /*Cursor cursor = pDb.rawQuery("select * from Profil "
                + "where mail != " + currentUser.getMail(), null);*/
        Cursor cursor = pDb.rawQuery("SELECT " + "*" + " FROM " + "Profil" + " WHERE mail != ?", new String[] {currentUser.getMail()});

        //ArrayList<String> profilList = new ArrayList<String>();
        //listAdapter = new ArrayAdapter<String>(this, R.layout.column_row, profilList);
        if (cursor .moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                String mail = cursor.getString(cursor
                        .getColumnIndex("mail"));

                Profil cursorProfil = profilDb.selectionner(mail);

                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put(FIRST_COLUMN, cursorProfil.getPhoto_path());
                temp.put(SECOND_COLUMN, cursorProfil.getNom());
                temp.put(THIRD_COLUMN, Integer.toString(cursorProfil.getAge()));
                temp.put(FOURTH_COLUMN, cursorProfil.getLocalisation());
                list.add(temp);

                cursor.moveToNext();
            }
        }

        profilListAdapter adapter = new profilListAdapter(this, list);
        mainListView.setAdapter(adapter);



        profilDb.close();

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
