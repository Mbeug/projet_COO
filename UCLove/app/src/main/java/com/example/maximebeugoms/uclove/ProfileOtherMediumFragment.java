package com.example.maximebeugoms.uclove;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

/**
 * Created by Menal_000 on 05-05-16.
 */
public class ProfileOtherMediumFragment extends FragmentProfileBase {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // layout pour ce fragment
        View rootview = inflater.inflate(R.layout.profil_other_medium_view, container,false);

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

                Relation rel = relationDb.select(other, user);

                // cas 1 : l'autre n'a pas encore like/dislike l'utilisateur
                if (rel==null) {

                    relationDb.add(new Relation(user,1,other));
                }

                // cas 2 : l'utilisateur et l'autre sont deja amis
                else if (rel.getEtat_acceptation()==2 || relationDb.select(user,other)!=null) {
                    Toast.makeText(getContext(),getString(R.string.addfriends_error),Toast.LENGTH_SHORT)
                            .show();
                }

                // cas general
                else {

                    rel.setEtat_acceptation(rel.getEtat_acceptation()+1);
                    relationDb.update(rel);
                }
                relationDb.close();
                Log.v("Fragment medium", "par le Fragment");
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

                Relation rel = relationDb.select(other, user);

                // cas 1 : l'autre n'a pas encore like/dislike l'utilisateur
                if (rel==null) {

                    relationDb.add(new Relation(user,0,other));
                }

                // cas 2 : l'utilisateur et l'autre ne s'apprecient pas
                else if (rel.getEtat_acceptation()==0 || relationDb.select(user,other).getEtat_acceptation()==0) {
                    Toast.makeText(getContext(),getString(R.string.withdrfriends_error),Toast.LENGTH_SHORT)
                            .show();
                }

                // cas general
                else {

                    rel.setEtat_acceptation(rel.getEtat_acceptation()-1);
                    relationDb.update(rel);
                }
                relationDb.close();

            }
        });
    }
}
