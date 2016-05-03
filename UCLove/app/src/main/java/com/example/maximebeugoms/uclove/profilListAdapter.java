package com.example.maximebeugoms.uclove;

import static com.example.maximebeugoms.uclove.Constants.FIRST_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.SECOND_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.THIRD_COLUMN;
import static com.example.maximebeugoms.uclove.Constants.FOURTH_COLUMN;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dilara on 3/05/2016.
 */
public class profilListAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    ImageView imageFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;
    public profilListAdapter(Activity activity, ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.column_row, null);

            imageFirst=(ImageView) convertView.findViewById(R.id.image);
            txtSecond=(TextView) convertView.findViewById(R.id.name);
            txtThird=(TextView) convertView.findViewById(R.id.age);
            txtFourth=(TextView) convertView.findViewById(R.id.status);

        }

        HashMap<String, String> map=list.get(position);
        imageFirst.setImageBitmap(setImage(map.get(FIRST_COLUMN)));
        txtSecond.setText(map.get(SECOND_COLUMN));
        txtThird.setText(map.get(THIRD_COLUMN));
        txtFourth.setText(map.get(FOURTH_COLUMN));

        return convertView;
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
