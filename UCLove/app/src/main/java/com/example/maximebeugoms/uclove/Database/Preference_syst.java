package com.example.maximebeugoms.uclove.Database;

import android.content.res.Configuration;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;


import com.example.maximebeugoms.uclove.MainActivity;
import com.example.maximebeugoms.uclove.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by damien on 29/04/16.
 */
public class Preference_syst  {

    private String langue;
    private String niveau_confidentialite;  // either LOW, MEDIUM or HIGH
    private String mail_user;

    // privacy levels
    public static final String LOW = "l";
    public static final String MEDIUM = "m";
    public static final String HIGH = "h";
    public Preference_syst(String langue, String niveau_confidentialite, String mail_user)
    {
        super();
        this.langue = langue;
        this.niveau_confidentialite = niveau_confidentialite;
        this.mail_user = mail_user;
    }

    public void setMail_user(String mail_user) {
        this.mail_user = mail_user;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getLangue() {
        return langue;
    }

    public String getMail_user() {
        return mail_user;
    }

    public String getNiveau_confidentialite() {
        return niveau_confidentialite;
    }

    public void setNiveau_confidentialite(String niveau_confidentialite) {
        this.niveau_confidentialite = niveau_confidentialite;
    }



}
