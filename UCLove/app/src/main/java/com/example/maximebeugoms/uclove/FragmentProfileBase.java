package com.example.maximebeugoms.uclove;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.maximebeugoms.uclove.Database.Relation;
import com.example.maximebeugoms.uclove.Database.RelationDao;

import java.io.IOException;

/**
 * Created by Menal_000 on 05-05-16.
 */
public class FragmentProfileBase extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    protected Bitmap setImage(String mPhotoPath) {
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
                    Relation nRel = new Relation(user,1,other);
                    relationDb.add(nRel);
                }

                // cas 2 : l'utilisateur et l'autre sont deja amis
                else if (rel.getEtat_acceptation()==2 || relationDb.select(user,other).getEtat_acceptation()==2) {
                    Toast.makeText(getContext(),getString(R.string.addfriends_error),Toast.LENGTH_SHORT)
                            .show();
                }

                // cas general
                else {

                    rel.setEtat_acceptation(rel.getEtat_acceptation()+1);
                    relationDb.update(rel);
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

                Relation rel = relationDb.select(other, user);

                // cas 1 : l'autre n'a pas encore like/dislike l'utilisateur
                if (rel==null) {
                    Relation nRel = new Relation(user,0,other);
                    relationDb.add(nRel);
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
