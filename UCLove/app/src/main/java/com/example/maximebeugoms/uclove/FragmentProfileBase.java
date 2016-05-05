package com.example.maximebeugoms.uclove;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

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
}
