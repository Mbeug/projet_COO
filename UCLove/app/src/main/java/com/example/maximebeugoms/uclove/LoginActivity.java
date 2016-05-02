package com.example.maximebeugoms.uclove;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maximebeugoms.uclove.Database.DAOBase;
import com.example.maximebeugoms.uclove.Database.DatabaseHandler;
import com.example.maximebeugoms.uclove.Database.User;
import com.example.maximebeugoms.uclove.Database.UserDao;


/**
 * Created by damien on 22/04/16.
 */
public class LoginActivity extends MainActivity
{
    private Button connexion=null;
    private Button inscription=null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final EditText nom = (EditText) findViewById(R.id.textLogger);
        final EditText mdp = (EditText) findViewById(R.id.textMdp);



        connexion = (Button) findViewById(R.id.btn_co);
        inscription= (Button) findViewById(R.id.btn_insc);

        connexion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vue){

                //Get the values from the input fields
                String mNom = nom.getText().toString();
                String mMdp = mdp.getText().toString();

                //Open db
                UserDao userDb = new UserDao(getApplicationContext());
                SQLiteDatabase mDb = userDb.open();

                //Get the user associated to email from db
                User checker = userDb.selectionner(mNom);

                //We create the toast to show some text
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP| Gravity.START, 0, 0);

                userDb.close(); // ajouté mais pas sur

                //If the account doesn't exist || the password doesn't match
                if(checker == null || !checker.getPassword().equals(mMdp)){
                    toast.makeText(LoginActivity.this, R.string.mauvais_login, toast.LENGTH_SHORT).show();
                }

                else {
                    toast.makeText(LoginActivity.this, R.string.connexion, toast.LENGTH_SHORT).show();

                    //On récupère l'application
                    Application application = (Application)Uclove.getContext();
                    Uclove app = (Uclove)application;

                    //On set User
                    app.setUser(checker);

                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

        inscription.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vue){
                Intent intent = new Intent(LoginActivity.this,InscriptionActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}


