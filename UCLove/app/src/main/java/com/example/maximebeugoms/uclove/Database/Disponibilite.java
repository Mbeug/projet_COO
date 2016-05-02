package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 2/05/16.
 */
public class Disponibilite {

    private String mail_user;
    private String dispo;
    private String date;

    public Disponibilite(String mail_user, String dispo, String date){
        super();
        this.date = date;
        this.dispo = dispo;
        this.mail_user = mail_user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMail_user(String mail_user) {
        this.mail_user = mail_user;
    }

    public String getMail_user() {
        return mail_user;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getDispo() {
        return dispo;
    }

    public String getDate() {
        return date;
    }


}
