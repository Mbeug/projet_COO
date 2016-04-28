package com.example.maximebeugoms.uclove;

/**
 * Created by damien on 25/04/16.
 */
public class Relation {

    private long id_relation;
    private int etat_acceptation;
    private long other_user;
    private long id_message;

    public Relation(long id_relation, long other_user, int etat_acceptation, long id_message){
        super();
        this.id_relation = id_relation;
        this.other_user = other_user;
        this.etat_acceptation = etat_acceptation;
        this.id_message = id_message;
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

    public void setEtat_acceptation(int etat_acceptation) {
        this.etat_acceptation = etat_acceptation;
    }

    public long getOther_user() {
        return other_user;
    }

    public void setOther_user(long other_user) {
        this.other_user = other_user;
    }

    public long getId_message() {
        return id_message;
    }

    public void setId_message(long id_message) {
        this.id_message = id_message;
    }
}

