package com.example.maximebeugoms.uclove;

import android.content.Intent;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Ceci est la classe de base, qui definit le menu deroulant.
 * Toutes les activity doivent etendre cette classe
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayAdapter<String> mAdapter;
    private List<String> options;
    private ListView mDrawerList;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // creer le tableau d'item du menu glissant
        options = new ArrayList<String>();
        Resources res = getResources();
        options.add(res.getString(R.string.profil));
        options.add(res.getString(R.string.decouverte));
        options.add(res.getString(R.string.settings_recherche));
        options.add(res.getString(R.string.calendrier));
        options.add(res.getString(R.string.amis));
        options.add(res.getString(R.string.rencontre));
        options.add(res.getString(R.string.action_settings));

        // instancer l'adapter
        mAdapter = new ArrayAdapter<String>(this, R.layout.view_menu, R.id.list_item_menu, options);
        // peupler la listview grace a l'adapter
        mDrawerList = (ListView) findViewById(R.id.listview_menu);
        mDrawerList.setAdapter(mAdapter);

        // action en cas de click
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(view.getContext(), Calendar.class));
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        /*// Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profil) {

            // Handle the action

            Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(intent);
            return true;


        } else if (id == R.id.nav_decouverte) {
            Intent intent = new Intent(MainActivity.this, ProfileOtherActivity.class );
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_settings_recherche) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class );
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_calendrier) {
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class );
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_amis) {
            Intent intent = new Intent(MainActivity.this, FriendListActivity.class );
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_rencontre) {
            //Intent intent = new Intent(MainActivity.this, Rencontre.class );
            //startActivity(intent);
            return true;

        } else if (id == R.id.nav_settings) {
            //Intent intent = new Intent(MainActivity.this, Main.class );
            //startActivity(intent);
            return true;

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
