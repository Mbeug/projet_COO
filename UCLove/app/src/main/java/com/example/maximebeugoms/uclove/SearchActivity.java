package com.example.maximebeugoms.uclove;

import static com.example.maximebeugoms.uclove.Constants.FIFTH_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.FIRST_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.SECOND_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.THIRD_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.FOURTH_COLUMN;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        Log.v("SearchActivity","mainListView null ? " + (mainListView==null));
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
                temp.put(FIFTH_COLUMN, cursorProfil.getMail());
                list.add(temp);

                cursor.moveToNext();
            }
        }

        final profilListAdapter adapter = new profilListAdapter(this, list);
        mainListView.setAdapter(adapter);

        profilDb.close();

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                //int pos=position+1;
                //Toast.makeText(SearchActivity.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();

                HashMap<String,String> profilList = (HashMap<String,String>) adapter.getItem(position);
                String mail = profilList.get(FIFTH_COLUMN);
                System.out.println(mail);

                //Open profil database, get selected profil and close
                ProfilDao profilDb = new ProfilDao(getApplicationContext());
                SQLiteDatabase pDb = profilDb.open();
                Profil profilDecouverte = profilDb.selectionner(mail);
                profilDb.close();

                //On récupère l'application
                Application application = (Application)Uclove.getContext();
                Uclove app = (Uclove)application;

                //On set Profil
                app.setProfil(profilDecouverte);

                Intent intent = new Intent(SearchActivity.this, ProfileOtherActivity.class);
                startActivity(intent);
            }

        });

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
