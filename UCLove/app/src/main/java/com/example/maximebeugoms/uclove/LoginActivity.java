package com.example.maximebeugoms.uclove;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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
        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.drawer_layout, new LoginFragment())
                    .commit();
        }*/
        connexion = (Button) findViewById(R.id.btn_co);
        inscription= (Button) findViewById(R.id.btn_insc);

        connexion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vue){
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
    /**
     * Ce fragment contient le login_view
     * Created by Menal_000
     */
    public static class LoginFragment extends Fragment {
        public LoginFragment() {

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.login_view, container, false);
        }
    }
}


