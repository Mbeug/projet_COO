package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 29/04/16.
 */
public class Preference_syst {

    private String langue;
    private String niveau_confidentialite;
    private int id_user;

    public Preference_syst(String langue, String niveau_confidentialite, int  id_user)
    {
        super();
        this.langue = langue;
        this.niveau_confidentialite = niveau_confidentialite;
        this.id_user = id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getLangue() {
        return langue;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNiveau_confidentialite() {
        return niveau_confidentialite;
    }

    public void setNiveau_confidentialite(String niveau_confidentialite) {
        this.niveau_confidentialite = niveau_confidentialite;
    }
}
