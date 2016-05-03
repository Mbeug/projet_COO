package com.example.maximebeugoms.uclove;


import android.app.Application;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.maximebeugoms.uclove.Database.Relation;
import com.example.maximebeugoms.uclove.Database.RelationDao;
import com.example.maximebeugoms.uclove.Database.User;

import java.util.ArrayList;

/**
 * Created by damien on 22/04/16.
 */
public class FriendListActivity extends MainActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendlist_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        RelationDao relDb = new RelationDao(FriendListActivity.this);
        relDb.open();

        //On récupère l'application
        Application application = (Application)Uclove.getContext();
        Uclove app = (Uclove)application;

        User user = app.getUser();

        ArrayList<Relation> arrayList = relDb.select(user.getMail());

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

