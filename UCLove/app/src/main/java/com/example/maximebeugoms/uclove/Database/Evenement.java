package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 25/04/16.
 */
public class Evenement {

    private String mail_user;
    private String date;
    private String type;

    public Evenement(String mail_user, String date, String type){
        super();
        this.date = date;
        this.mail_user = mail_user;
        this.type = type;
    }

    public String getMail_user() {
        return mail_user;
    }

    public void setMail_user(String mail_user) {
        this.mail_user = mail_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {return (type + " | " + date);}

}
