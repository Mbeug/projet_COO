package com.example.maximebeugoms.uclove;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maximebeugoms.uclove.Database.Preference_syst;
import com.example.maximebeugoms.uclove.Database.Preference_systDao;
import com.example.maximebeugoms.uclove.Database.Profil;

/**
 * Created by Menal_000 on 05-05-16.
 */
public class ProfileOtherHighFragment extends FragmentProfileBase {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // layout pour ce fragment
        View rootview = inflater.inflate(R.layout.profil_other_high_view, container,false);

        //On récupère l'application
        Application application = (Application)Uclove.getContext();
        Uclove app = (Uclove)application;

        //On get Profil selectionné
        Profil profilDecouverte = app.getProfil();

        TextView nomDecouverte = (TextView) rootview.findViewById(R.id.nomDecouverte);
        nomDecouverte.setText(profilDecouverte.getNom());

        TextView sexeDecouverte = (TextView) rootview.findViewById(R.id.sexeDecouverte);
        sexeDecouverte.setText(profilDecouverte.getSexe());

        TextView ageDecouverte = (TextView) rootview.findViewById(R.id.ageDec);
        ageDecouverte.setText(Integer.toString(profilDecouverte.getAge()));
        System.out.println(Integer.toString(profilDecouverte.getAge()));

        TextView longueurCDecouverte = (TextView) rootview.findViewById(R.id.longueur);
        longueurCDecouverte.setText(profilDecouverte.getLongueur_cheveux());

        TextView couleurCDecouverte = (TextView) rootview.findViewById(R.id.couleur);
        couleurCDecouverte.setText(profilDecouverte.getCouleur_cheveux());

        TextView couleurYCDecouverte = (TextView) rootview.findViewById(R.id.coulYeuxDecouverte);
        couleurYCDecouverte.setText(profilDecouverte.getCouleur_yeux());

        TextView orientationDecouverte = (TextView) rootview.findViewById(R.id.orientDec);
        orientationDecouverte.setText(profilDecouverte.getOrientation());


        // TODO ajouter fonctionalité boutons*/
        return rootview;
    }
}
