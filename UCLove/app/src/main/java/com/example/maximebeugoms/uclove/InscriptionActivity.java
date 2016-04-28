package com.example.maximebeugoms.uclove;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by maximebeugoms on 28/04/16.
 */


public class InscriptionActivity extends MainActivity {
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.inscription_view);

            final EditText nom = (EditText) findViewById(R.id.nom);
            final EditText mdp = (EditText) findViewById(R.id.mdp);
            final EditText mail = (EditText) findViewById(R.id.email);
            final EditText naissance = (EditText) findViewById(R.id.dateNaissance);



            Button button = (Button) findViewById(R.id.Soumettre);

            button.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP| Gravity.START, 0, 0);
                    toast.makeText(InscriptionActivity.this, nom.getText(), toast.LENGTH_SHORT).show();
                    toast.makeText(InscriptionActivity.this, mdp.getText(), toast.LENGTH_SHORT).show();
                    toast.makeText(InscriptionActivity.this, mail.getText(), toast.LENGTH_SHORT).show();
                    toast.makeText(InscriptionActivity.this, naissance.getText(), toast.LENGTH_SHORT).show();

                }
            });


        }
}
