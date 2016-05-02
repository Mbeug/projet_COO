package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 25/04/16.
 */
public class Message {

    private String mail_user;
    private String texte;
    private String date;

    public Message(String mail_user, String texte, String date){
        super();
        this.mail_user = mail_user;
        this.texte = texte;
        this.date = date;
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

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

}
