package com.example.maximebeugoms.uclove;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Ceci est la classe de base, qui definit le menu deroulant.
 * Toutes les activity doivent etendre cette classe
 */
public class MainActivity extends AppCompatActivity {




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intent = new Intent(this,PrefSystActivity.class);
            startActivity(intent);
            return true;
        }
         else if (id == R.id.action_search) {
            intent = new Intent(this,SearchActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_calendar) {
            intent = new Intent(this,CalendarActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_filter) {
            intent = new Intent(this,FiltersActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_friends) {
            intent = new Intent(this,FriendListActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_profile) {
            intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_meet) {
            //intent = new Intent(this,A_COMPLETER.class);
            //startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
