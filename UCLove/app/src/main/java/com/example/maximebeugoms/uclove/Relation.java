package com.example.maximebeugoms.uclove;

/**
 * Created by damien on 25/04/16.
 */
public class Relation {

    private long id_relation;
    private int etat_acceptation;
    private long id_user1;
    private long id_user2;

    public Relation(long id_relation, long id_user1, long id_user2, int etat_acceptation){
        super();
        this.id_relation = id_relation;
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
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

    public void setEtat_acceptation(int etat_acceptation) {
        this.etat_acceptation = etat_acceptation;
    }

    public long getId_user1() {
        return id_user1;
    }

    public void setId_user1(long id_user1) {
        this.id_user1 = id_user1;
    }

    public long getId_user2() {
        return id_user2;
    }

    public void setId_user2(long id_user2) {
        this.id_user2 = id_user2;
    }
}
