package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 25/04/16.
 */
public class Relation {

    private long id_relation;
    private int etat_acceptation;
    private String sender;
    private String receiver;
    private String mail_user;

    public Relation(long id_relation, String sender, int etat_acceptation, String receiver, String mail_user){
        super();
        this.id_relation = id_relation;
        this.receiver = receiver;
        this.sender = sender;
        this.etat_acceptation = etat_acceptation;
        this.mail_user = mail_user;
    }

    public void setMail_user(String mail_user) {
        this.mail_user = mail_user;
    }

    public String getMail_user() {
        return mail_user;
    }

    public long getId_relation() {
        return id_relation;
    }

    public void setId_relation(long id_relation) {
        this.id_relation = id_relation;
    }

    public int getEtat_acceptation() {
        return etat_acceptation;
    }

    public void setEtat_acceptation(int etat_acceptation) {this.etat_acceptation = etat_acceptation;}

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}


