package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 25/04/16.
 */
public class Relation {

    private long id_relation;
    private long id_user;
    private int etat_acceptation;
    private long other_user;

    public Relation(long id_relation, long other_user, int etat_acceptation, long id_user){
        super();
        this.id_relation = id_relation;
        this.id_user = id_user;
        this.other_user = other_user;
        this.etat_acceptation = etat_acceptation;
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

    public long getOther_user() {return other_user;}

    public void setOther_user(long other_user) {
        this.other_user = other_user;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }
}

