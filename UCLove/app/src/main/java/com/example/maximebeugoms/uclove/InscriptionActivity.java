package com.example.maximebeugoms.uclove;

import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;
/*
 * Created by maximebeugoms on 28/04/16.
 */

public class InscriptionActivity extends MainActivity implements OnItemSelectedListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText nom = (EditText) findViewById(R.id.nom);
        final EditText mdp = (EditText) findViewById(R.id.mdp);
        final EditText mail = (EditText) findViewById(R.id.email);
        final EditText naissance = (EditText) findViewById(R.id.dateNaissance);
        
        
        // Spinner element
        final Spinner sexSpinner = (Spinner) findViewById(R.id.sexeSpinner);
        final Spinner couleurCheveuxSpinner = (Spinner) findViewById(R.id.couleurCheveuxSpinner);
        final Spinner orientationSpinner = (Spinner) findViewById(R.id.orientationSpinner);
        final Spinner longueurCheveuxSpinner = (Spinner) findViewById(R.id.longueurCheveuxSpinner);
        final Spinner couleurYeuxSpinner = (Spinner) findViewById(R.id.couleurYeuxSpinner);



        Button button = (Button) findViewById(R.id.Soumettre);

        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String mNom = nom.getText().toString();

                String mMdp = mdp.getText().toString();

                String mMail = mail.getText().toString();

                String mDate = naissance.getText().toString();

                String mSexe = sexSpinner.getSelectedItem().toString();

                String mCouleurCheveux = couleurCheveuxSpinner.getSelectedItem().toString();

                String mOrientation = orientationSpinner.getSelectedItem().toString();

                String mLongueurCheveux = longueurCheveuxSpinner.getSelectedItem().toString();

                String mCouleurYeux = couleurYeuxSpinner.getSelectedItem().toString();

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP| Gravity.START, 0, 0);

                if(mNom == null || mNom.isEmpty()) {
                    toast.makeText(InscriptionActivity.this, R.string.nom_non_conforme, toast.LENGTH_SHORT).show();
                }

                try {
                    String currentDateString = "30/04/1998";
                    SimpleDateFormat sd = new SimpleDateFormat("dd/mm/yyyy");
                    Date allowedDate = sd.parse(currentDateString);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
                    Date parsedDate = dateFormat.parse(mDate);

                    //java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    // If the string can be parsed in date, it matches the SimpleDateFormat
                    // Do whatever you want to do if String matches SimpleDateFormat.
                    if (parsedDate.after(allowedDate)) {
                        System.out.println("After");
                        toast.makeText(InscriptionActivity.this, R.string.date_non_conforme, toast.LENGTH_SHORT).show();
                    }else{
                        //peut etre soumis
                        System.out.println(parsedDate.toString());
                        System.out.println(mDate);
                    }

                }
                catch (java.text.ParseException e) {
                    // Else if there's an exception, it doesn't
                    // Do whatever you want to do if it doesn't.
                    toast.makeText(InscriptionActivity.this, R.string.date_non_conforme, toast.LENGTH_SHORT).show();
                }



                if (!mMail.contains("@") || !mMail.contains(".")) {
                    toast.makeText(InscriptionActivity.this, R.string.email_non_conforme, toast.LENGTH_SHORT).show();
                }

                if(mMdp == null || mMdp.isEmpty()) {
                    toast.makeText(InscriptionActivity.this, R.string.mdp_non_conforme, toast.LENGTH_SHORT).show();
                }
            }

        });

        

        // Spinner click listener
        sexSpinner.setOnItemSelectedListener(this);
        couleurCheveuxSpinner.setOnItemSelectedListener(this);
        orientationSpinner.setOnItemSelectedListener(this);
        longueurCheveuxSpinner.setOnItemSelectedListener(this);
        couleurYeuxSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> sexCategories = new ArrayList<String>();
        sexCategories.add(getResources().getString(R.string.homme));
        sexCategories.add(getResources().getString(R.string.femme));

        List<String> couleurCheveuxCategories = new ArrayList<String>();
        couleurCheveuxCategories.add(getResources().getString(R.string.brun));
        couleurCheveuxCategories.add(getResources().getString(R.string.blond));
        couleurCheveuxCategories.add(getResources().getString(R.string.noir));
        couleurCheveuxCategories.add(getResources().getString(R.string.roux));
        couleurCheveuxCategories.add(getResources().getString(R.string.chatain));
        couleurCheveuxCategories.add(getResources().getString(R.string.blanc));

        List<String> orientationCategories = new ArrayList<String>();
        orientationCategories.add(getResources().getString(R.string.homme));
        orientationCategories.add(getResources().getString(R.string.femme));
        orientationCategories.add("Bi");

        List<String> longueurCheveuxCategories = new ArrayList<String>();
        longueurCheveuxCategories.add(getResources().getString(R.string.court));
        longueurCheveuxCategories.add(getResources().getString(R.string.milong));
        longueurCheveuxCategories.add(getResources().getString(R.string.Long));

        List<String> couleurYeuxCategories = new ArrayList<String>();
        couleurYeuxCategories.add(getResources().getString(R.string.bleu));
        couleurYeuxCategories.add(getResources().getString(R.string.brun));
        couleurYeuxCategories.add(getResources().getString(R.string.vert));


        // Creating adapter for spinner
        ArrayAdapter<String> sexDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexCategories);
        ArrayAdapter<String> couleurCheveuxDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, couleurCheveuxCategories);
        ArrayAdapter<String> orientationDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, orientationCategories);
        ArrayAdapter<String> longueurCheveuxDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, longueurCheveuxCategories);
        ArrayAdapter<String> couleurYeuxDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, couleurYeuxCategories);

        // Drop down layout style - list view with radio button
        sexDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        couleurCheveuxDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orientationDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        longueurCheveuxDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        couleurYeuxDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sexSpinner.setAdapter(sexDataAdapter);
        couleurCheveuxSpinner.setAdapter(couleurCheveuxDataAdapter);
        orientationSpinner.setAdapter(orientationDataAdapter);
        longueurCheveuxSpinner.setAdapter(longueurCheveuxDataAdapter);
        couleurYeuxSpinner.setAdapter(couleurYeuxDataAdapter);

        Button photoButton = (Button) findViewById(R.id.photo);

        photoButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                selectImage();
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

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
/*
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        */
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    private static final int REQUEST_CAMERA = 0;
    private static final int SELECT_FILE = 1;
    private void selectImage() {
        final CharSequence[] items = {getResources().getString(R.string.prendrePhoto), getResources().getString(R.string.galerie), getResources().getString(R.string.annuler)};
        AlertDialog.Builder builder = new AlertDialog.Builder(InscriptionActivity.this);
        builder.setTitle(R.string.ajouterPhoto);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getResources().getString(R.string.prendrePhoto))) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals(getResources().getString(R.string.galerie))) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals(getResources().getString(R.string.annuler))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageTest = (ImageView) findViewById(R.id.imageTest);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;

                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination.getPath());
                    fo.write(bytes.toByteArray());
                    fo.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Uri selectedImageUri = data.getData();
                String takenImagePath = getPath(getApplicationContext() , selectedImageUri);
                System.out.println(takenImagePath);

                ExifInterface ei = null;
                try {
                    ei = new ExifInterface(takenImagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                System.out.println(orientation);
                switch(orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        imageTest.setImageBitmap(rotateImage(thumbnail, 90));
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        imageTest.setImageBitmap(rotateImage(thumbnail, 180));
                        break;
                    default:
                        imageTest.setImageBitmap(thumbnail);
                }



                //imageTest.setImageBitmap(thumbnail);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();/*
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();*/
                String selectedImagePath = getPath(getApplicationContext() , selectedImageUri);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);

                ExifInterface ei = null;
                try {
                    ei = new ExifInterface(selectedImagePath);
                    System.out.println(selectedImagePath);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                System.out.println(orientation);
                switch(orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        imageTest.setImageBitmap(rotateImage(bm, 90));
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        imageTest.setImageBitmap(rotateImage(bm, 180));
                        break;
                    default:
                        imageTest.setImageBitmap(bm);
                }


                //imageTest.setImageBitmap(bm);
            }
        }
    }

    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
    }
}
