package com.example.maximebeugoms.uclove;


import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        ArrayList<Relation> list = relDb.select(user.getMail());
        relDb.close();

        // creer la liste pour peupler l'adapter
        ArrayList<SubRelation> listFriends = getSubRelationList(list, user.getMail());



        final ListView listView = (ListView) findViewById(R.id.friendslistView);

        // adapter
        ArrayAdapter<SubRelation> adapter = new ArrayAdapter<SubRelation>(this,android.R.layout.simple_list_item_1,listFriends);
        listView.setAdapter(adapter);


    }


    public ArrayList<SubRelation> getSubRelationList(ArrayList<Relation> list, String mail) {
        ArrayList<SubRelation> listFriends = new ArrayList<SubRelation>();
        for (int i=0; i<list.size(); i++) {
            Relation rel = list.get(i);
            if (rel.getReceiver().equals(mail)){
                listFriends.add(new SubRelation(rel.getSender(),rel.getEtat_acceptation()));
            }
            listFriends.add(new SubRelation(rel.getReceiver(),rel.getEtat_acceptation()));
        }
        return listFriends;
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

