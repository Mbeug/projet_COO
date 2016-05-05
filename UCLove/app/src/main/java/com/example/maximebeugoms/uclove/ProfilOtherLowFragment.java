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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maximebeugoms.uclove.Database.Preference_syst;
import com.example.maximebeugoms.uclove.Database.Preference_systDao;
import com.example.maximebeugoms.uclove.Database.Profil;

import java.io.IOException;

/**
 * Created by Menal_000 on 05-05-16.
 */
public class ProfilOtherLowFragment extends Fragment {
    public ProfilOtherLowFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

        //open la db pour avoir les preferences du user selectionne
        Preference_systDao prefsDB = new Preference_systDao(getContext());
        SQLiteDatabase pDb = prefsDB.open();
        Preference_syst prefSyst = prefsDB.select(profilDecouverte.getMail());
        prefsDB.close();

        //String confidential = prefSyst.getNiveau_confidentialite(); TODO donne erreur java.lang.NullPointerException
        //System.out.println(confidential);

        // determiner la vue a charger selon le niveau de confidentialite (a faire)

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

        // TODO ajouter fonctionalité boutons*/
        return rootview;
    }

    private Bitmap setImage(String mPhotoPath) {
        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mPhotoPath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(mPhotoPath, options);

        ExifInterface ei = null;
        try {
            ei = new ExifInterface(mPhotoPath);
            System.out.println(mPhotoPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        System.out.println(orientation);
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                System.out.println("ORIENTATION_ROTATE_90");
                return rotateImage(bm, 90);
            //break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                System.out.println("ORIENTATION_ROTATE_180");
                return rotateImage(bm, 180);
            //break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                System.out.println("ORIENTATION_ROTATE_270");
                return rotateImage(bm, 270);
            //break;
            default:
                System.out.println("DEFAULT");
                return bm;
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
    }
}
