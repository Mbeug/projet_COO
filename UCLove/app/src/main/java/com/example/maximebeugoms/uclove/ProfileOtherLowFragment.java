package com.example.maximebeugoms.uclove;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.v4.app.*;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maximebeugoms.uclove.Database.Preference_syst;
import com.example.maximebeugoms.uclove.Database.Preference_systDao;
import com.example.maximebeugoms.uclove.Database.Profil;
import com.example.maximebeugoms.uclove.Database.Relation;
import com.example.maximebeugoms.uclove.Database.RelationDao;

import java.io.IOException;

/**
 * Created by Menal_000 on 05-05-16.
 */
public class ProfileOtherLowFragment extends FragmentProfileBase {
    public ProfileOtherLowFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // layout pour ce fragment
        View rootview = inflater.inflate(R.layout.profil_other_low_view, container,false);

        //On récupère l'application
        Application application = (Application)Uclove.getContext();
        Uclove app = (Uclove)application;

        //On get Profil selectionné
        Profil profilDecouverte = app.getProfil();


        ImageView imageDecouverte = (ImageView) rootview.findViewById(R.id.imageProfilDec);
        imageDecouverte.setImageBitmap(setImage(profilDecouverte.getPhoto_path()));

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

        TextView localDecouverte = (TextView) rootview.findViewById(R.id.localisation);
        localDecouverte.setText(profilDecouverte.getLocalisation());

        // les boutons
        likeButton(rootview);
        dislikeButton(rootview);
        return rootview;
    }

    protected void likeButton(View rootview){
        ImageButton likeButton = (ImageButton) rootview.findViewById(R.id.imageButtonLike);
        likeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vue){
                Application application = (Application)Uclove.getContext();
                Uclove app = (Uclove)application;

                // verifier la relation dans la db
                String user = app.getUser().getMail();
                String other = app.getProfil().getMail();

                RelationDao relationDb = new RelationDao(getContext());
                SQLiteDatabase rDb = relationDb.open();

                Relation invRel = relationDb.select(other, user);
                Relation rel = relationDb.select(user,other);
                // cas 1 : l'autre n'a pas encore like/dislike l'utilisateur
                if (invRel==null) {
                    if (rel==null) {
                        Relation nRel = new Relation(user,1,other);
                        relationDb.add(nRel);
                    } else {
                        Toast.makeText(getContext(),"like",Toast.LENGTH_SHORT)
                                .show();
                    }
                }

                // cas 2 : l'utilisateur et l'autre sont deja amis
                else if (invRel.getEtat_acceptation()==2 || rel.getEtat_acceptation()==2) {
                    Toast.makeText(getContext(),getString(R.string.addfriends_error),Toast.LENGTH_SHORT)
                            .show();
                }

                // cas general
                else {

                    invRel.setEtat_acceptation(invRel.getEtat_acceptation()+1);
                    relationDb.update(invRel);
                }
                relationDb.close();

            }
        });
    }

    protected void dislikeButton(View rootview){
        ImageButton dislikeButton = (ImageButton) rootview.findViewById(R.id.imageButtonDislike);
        dislikeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vue){
                Application application = (Application)Uclove.getContext();
                Uclove app = (Uclove)application;

                // verifier la relation dans la db
                String user = app.getUser().getMail();
                String other = app.getProfil().getMail();

                RelationDao relationDb = new RelationDao(getContext());
                SQLiteDatabase rDb = relationDb.open();

                Relation invRel = relationDb.select(other, user);
                Relation rel = relationDb.select(user,other);

                // cas 1 : l'autre n'a pas encore like/dislike l'utilisateur
                if (invRel==null) {
                    if (rel==null) {
                        Relation nRel = new Relation(user,0,other);
                        relationDb.add(nRel);
                    } else {
                        Toast.makeText(getContext(),"dislike",Toast.LENGTH_SHORT)
                                .show();
                    }
                }

                // cas 2 : l'utilisateur et l'autre ne s'apprecient pas
                else if (invRel.getEtat_acceptation()==0 || rel.getEtat_acceptation()==0) {
                    Toast.makeText(getContext(),getString(R.string.withdrfriends_error),Toast.LENGTH_SHORT)
                            .show();
                }

                // cas general
                else {

                    invRel.setEtat_acceptation(invRel.getEtat_acceptation()-1);
                    relationDb.update(invRel);
                }
                relationDb.close();
                Log.v("Fragment Low", "check");
            }
        });
    }


}
