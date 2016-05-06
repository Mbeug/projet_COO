package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 25/04/16.
 */
public class Relation {

    private static int autoincrement=0;
    private int etat_acceptation;
    private String sender;
    private String receiver;
    private int id;

    public Relation(String sender, int etat_acceptation, String receiver){
        super();
        this.receiver = receiver;
        this.sender = sender;
        this.etat_acceptation = etat_acceptation;
        id=++autoincrement;
    }

    /**
     * Constructeur utilise uniquement pour generer des instances de Relations
     * depuis la db.
     */
    public Relation(String sender, int etat_acceptation, String receiver, int id){
        super();
        this.receiver = receiver;
        this.sender = sender;
        this.etat_acceptation = etat_acceptation;
        this.id=id;
    }



    public int getId() {
        return id;
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


