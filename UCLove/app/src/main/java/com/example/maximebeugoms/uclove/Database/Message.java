package com.example.maximebeugoms.uclove.Database;

import java.util.Date;

/**
 * Created by damien on 25/04/16.
 */
public class Message {

    private static int autoincrement=0;
    private String mail_user;
    private String texte;
    private String date;
    private int id_relation;
    private int id;

    public Message(String mail_user, String texte, int id_relation){
        super();
        this.mail_user = mail_user;
        this.texte = texte;
        date = new Date().toString();
        this.id_relation = id_relation;
        id=++autoincrement;
    }

    /**
     * Constructeur uniquement utilise apres lecture de la db
     */
    public Message(String mail_user, String texte, String date, int id_relation, int id){
        super();
        this.mail_user = mail_user;
        this.texte = texte;
        this.date = date;
        this.id_relation = id_relation;
        id=++autoincrement;
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

    public int getId_relation() {
        return id_relation;
    }

    public void setId_relation(int id_relation) {
        this.id_relation = id_relation;
    }

    public int getId() {
        return id;
    }

}
