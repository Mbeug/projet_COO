package com.example.maximebeugoms.uclove;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


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


                Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
                startActivity(intent);
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


