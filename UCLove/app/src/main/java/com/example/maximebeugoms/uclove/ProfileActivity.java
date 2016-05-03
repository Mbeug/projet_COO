package com.example.maximebeugoms.uclove;

import android.Manifest;
import android.app.Application;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.maximebeugoms.uclove.Database.Profil;
import com.example.maximebeugoms.uclove.Database.ProfilDao;
import com.example.maximebeugoms.uclove.Database.User;
import com.example.maximebeugoms.uclove.Database.UserDao;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 22/04/16.
 */
public class ProfileActivity extends MainActivity implements OnItemSelectedListener {



    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Application application = (Application)Uclove.getContext();
        Uclove app = (Uclove)application;

        //On get User
        User currentUser = app.getUser();

        //Open db
        ProfilDao profilDb = new ProfilDao(getApplicationContext());
        SQLiteDatabase mDb = profilDb.open();
        Profil currentProfil = profilDb.selectionner(currentUser.getMail());
        System.out.println(currentUser.getMail());
        //TextView nomProfil = (TextView) findViewById(R.id.nomProfil);
        //System.out.println(currentProfil.getMail());
       // nomProfil.setText(currentProfil.getNom());

    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    String mPhotoPath = "NoPhoto";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_profil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Application application = (Application) Uclove.getContext();
        Uclove app = (Uclove) application;

        //On get User
        final User currentUser = app.getUser();

        //Open db
        final ProfilDao profilDb = new ProfilDao(getApplicationContext());
        SQLiteDatabase mDb = profilDb.open();
        final Profil currentProfil = profilDb.selectionner(currentUser.getMail());

        TextView emailProfil = (TextView) findViewById(R.id.email);
        emailProfil.setText(currentProfil.getMail());

        TextView ageProfil = (TextView) findViewById(R.id.ageProfil);
        ageProfil.setText(String.valueOf(currentProfil.getAge()));

        final EditText nom = (EditText) findViewById(R.id.nom);
        final EditText pseudo = (EditText) findViewById(R.id.pseudo);
        final EditText mdp = (EditText) findViewById(R.id.mdp);
        final EditText localisation = (EditText) findViewById(R.id.localisation);

        nom.setHint(currentProfil.getNom());
        pseudo.setHint(currentUser.getLogin());
        mdp.setHint(currentUser.getPassword());
        localisation.setHint(currentProfil.getLocalisation());

        // Spinner element
        final Spinner sexSpinner = (Spinner) findViewById(R.id.sexeSpinner);
        final Spinner couleurCheveuxSpinner = (Spinner) findViewById(R.id.couleurCheveuxSpinner);
        final Spinner orientationSpinner = (Spinner) findViewById(R.id.orientationSpinner);
        final Spinner longueurCheveuxSpinner = (Spinner) findViewById(R.id.longueurCheveuxSpinner);
        final Spinner couleurYeuxSpinner = (Spinner) findViewById(R.id.couleurYeuxSpinner);


        assert sexSpinner != null;
        sexSpinner.post(new Runnable() {
            @Override
            public void run() {

                sexSpinner.setSelection(((ArrayAdapter) sexSpinner.getAdapter()).getPosition(currentProfil.getSexe()));
            }
        });

        assert couleurCheveuxSpinner != null;
        couleurCheveuxSpinner.post(new Runnable() {
            @Override
            public void run() {

                couleurCheveuxSpinner.setSelection(((ArrayAdapter) couleurCheveuxSpinner.getAdapter()).getPosition(currentProfil.getCouleur_cheveux()));
            }
        });
        assert orientationSpinner != null;
        orientationSpinner.post(new Runnable() {
            @Override
            public void run() {

                orientationSpinner.setSelection(((ArrayAdapter) orientationSpinner.getAdapter()).getPosition(currentProfil.getOrientation()));
            }
        });
        assert longueurCheveuxSpinner != null;
        longueurCheveuxSpinner.post(new Runnable() {
            @Override
            public void run() {

                longueurCheveuxSpinner.setSelection(((ArrayAdapter) longueurCheveuxSpinner.getAdapter()).getPosition(currentProfil.getLongueur_cheveux()));
            }
        });
        assert couleurYeuxSpinner != null;
        couleurYeuxSpinner.post(new Runnable() {
            @Override
            public void run() {

                couleurYeuxSpinner.setSelection(((ArrayAdapter) couleurYeuxSpinner.getAdapter()).getPosition(currentProfil.getCouleur_yeux()));
            }
        });

        mPhotoPath = currentProfil.getPhoto_path();
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
        ImageView imageTest = (ImageView) findViewById(R.id.imageTest);
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
                imageTest.setImageBitmap(rotateImage(bm, 90));
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                System.out.println("ORIENTATION_ROTATE_180");
                imageTest.setImageBitmap(rotateImage(bm, 180));
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                System.out.println("ORIENTATION_ROTATE_270");
                imageTest.setImageBitmap(rotateImage(bm, 270));
                break;
            default:
                System.out.println("DEFAULT");
                imageTest.setImageBitmap(bm);
        }

        profilDb.close();

        Button button = (Button) findViewById(R.id.Modifier);

        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String mNom = nom.getText().toString();

                String mMdp = mdp.getText().toString();

                String mPseudo = pseudo.getText().toString();

                String mLocalisation = localisation.getText().toString();

                String mSexe = sexSpinner.getSelectedItem().toString();

                String mCouleurCheveux = couleurCheveuxSpinner.getSelectedItem().toString();

                String mOrientation = orientationSpinner.getSelectedItem().toString();

                String mLongueurCheveux = longueurCheveuxSpinner.getSelectedItem().toString();

                String mCouleurYeux = couleurYeuxSpinner.getSelectedItem().toString();


                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP | Gravity.START, 0, 0);

                //Check multiple conditions
                if (mNom == null || mNom.isEmpty()) {
                    mNom = currentProfil.getNom();
                }
                if (mPseudo == null || mPseudo.isEmpty()) {
                    mPseudo = currentUser.getLogin();
                }

                if (mMdp == null || mMdp.isEmpty()) {
                    mMdp = currentUser.getPassword();
                }
                if (mLocalisation == null || mLocalisation.isEmpty()) {
                    mLocalisation = currentProfil.getLocalisation();
                }

                //If all checks are passed add the information to the database and return to same profile page (for now) //TODO change page loaded

                //Open db
                UserDao userDb = new UserDao(getApplicationContext());
                SQLiteDatabase mDb = userDb.open();
                ProfilDao profilDb = new ProfilDao(getApplicationContext());
                SQLiteDatabase pDb = profilDb.open();


                // create updated user and profile
                User updatedUser = new User(mPseudo, currentUser.getMail(), mMdp);
                Profil updatedProfil = new Profil(mNom, currentUser.getMail(), mSexe, currentProfil.getAge(), mCouleurCheveux, mLongueurCheveux, mCouleurYeux, mOrientation, mLocalisation, mPhotoPath);


                //We update user and profile in the database
                userDb.update(updatedUser);
                profilDb.update(updatedProfil);

                //Close db
                userDb.close();
                profilDb.close();




                toast.makeText(ProfileActivity.this, R.string.compteModifie, toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
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
        sexCategories.add(

                getResources()

                        .

                                getString(R.string.homme)

        );
        sexCategories.add(

                getResources()

                        .

                                getString(R.string.femme)

        );

        List<String> couleurCheveuxCategories = new ArrayList<String>();
        couleurCheveuxCategories.add(

                getResources()

                        .

                                getString(R.string.brun)

        );
        couleurCheveuxCategories.add(

                getResources()

                        .

                                getString(R.string.blond)

        );
        couleurCheveuxCategories.add(

                getResources()

                        .

                                getString(R.string.noir)

        );
        couleurCheveuxCategories.add(

                getResources()

                        .

                                getString(R.string.roux)

        );
        couleurCheveuxCategories.add(

                getResources()

                        .

                                getString(R.string.chatain)

        );
        couleurCheveuxCategories.add(

                getResources()

                        .

                                getString(R.string.blanc)

        );

        List<String> orientationCategories = new ArrayList<String>();
        orientationCategories.add(

                getResources()

                        .

                                getString(R.string.homme)

        );
        orientationCategories.add(

                getResources()

                        .

                                getString(R.string.femme)

        );
        orientationCategories.add("Bi");

        List<String> longueurCheveuxCategories = new ArrayList<String>();
        longueurCheveuxCategories.add(

                getResources()

                        .

                                getString(R.string.court)

        );
        longueurCheveuxCategories.add(

                getResources()

                        .

                                getString(R.string.milong)

        );
        longueurCheveuxCategories.add(

                getResources()

                        .

                                getString(R.string.Long)

        );

        List<String> couleurYeuxCategories = new ArrayList<String>();
        couleurYeuxCategories.add(

                getResources()

                        .

                                getString(R.string.bleu)

        );
        couleurYeuxCategories.add(

                getResources()

                        .

                                getString(R.string.brun)

        );
        couleurYeuxCategories.add(

                getResources()

                        .

                                getString(R.string.vert)

        );


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

        assert photoButton != null;
        photoButton.setOnClickListener(new View.OnClickListener()

                                       {

                                           public void onClick(View view) {
                                               System.out.println("IN BUTTON");
                                               if (ContextCompat.checkSelfPermission(ProfileActivity.this,
                                                       Manifest.permission.READ_EXTERNAL_STORAGE)
                                                       != PackageManager.PERMISSION_GRANTED) {

                                                   System.out.println("PERMISSION NOT GRANTED");
                                                   // Should we show an explanation?
                                                   if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this,
                                                           Manifest.permission.READ_EXTERNAL_STORAGE)) {

                                                       // Show an expanation to the user *asynchronously* -- don't block
                                                       // this thread waiting for the user's response! After the user
                                                       // sees the explanation, try again to request the permission.

                                                   } else {

                                                       // No explanation needed, we can request the permission.

                                                       ActivityCompat.requestPermissions(ProfileActivity.this,
                                                               new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                                               MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                                                       // MY_PERMISSIONS_READ_EXTERNAL_STORAGE is an
                                                       // app-defined int constant. The callback method gets the
                                                       // result of the request.

                                                   }
                                               }

                                               selectImage();
                                           }

                                       }

        );
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).

                addApi(AppIndex.API)

                .

                        build();

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
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
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
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String takenImagePath;
                Uri selectedImageUri = data.getData();

                takenImagePath = getPath(getApplicationContext(), selectedImageUri);
                System.out.println(takenImagePath);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(takenImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                Bitmap bm = BitmapFactory.decodeFile(takenImagePath, options);

                ExifInterface ei = null;
                try {
                    ei = new ExifInterface(takenImagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                System.out.println(orientation);
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        System.out.println("ORIENTATION_ROTATE_90");
                        imageTest.setImageBitmap(rotateImage(bm, 90));
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        System.out.println("ORIENTATION_ROTATE_180");
                        imageTest.setImageBitmap(rotateImage(bm, 180));
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        System.out.println("ORIENTATION_ROTATE_270");
                        imageTest.setImageBitmap(rotateImage(bm, 270));
                        break;
                    default:
                        System.out.println("DEFAULT");
                        imageTest.setImageBitmap(bm);
                }


                mPhotoPath = takenImagePath;
                //imageTest.setImageBitmap(thumbnail);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();/*
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();*/
                String selectedImagePath = getPath(getApplicationContext(), selectedImageUri);
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
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        System.out.println("ORIENTATION_ROTATE_90");
                        imageTest.setImageBitmap(rotateImage(bm, 90));
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        System.out.println("ORIENTATION_ROTATE_180");
                        imageTest.setImageBitmap(rotateImage(bm, 180));
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        System.out.println("ORIENTATION_ROTATE_270");
                        imageTest.setImageBitmap(rotateImage(bm, 270));
                        break;
                    default:
                        System.out.println("DEFAULT");
                        imageTest.setImageBitmap(bm);
                }

                mPhotoPath = selectedImagePath;
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
                final String[] selectionArgs = new String[]{
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
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Profile Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.maximebeugoms.uclove/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Profile Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.maximebeugoms.uclove/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


}
