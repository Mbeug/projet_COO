package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 25/04/16.
 */
public class Message {

    private long id_relation;
    private String texte;
    private String date;

    public Message(long id_relation, String texte, String date){
        super();
        this.id_relation = id_relation;
        this.texte = texte;
        this.date = date;
    }

    public void setId_relation(long id_relation) {
        this.id_relation = id_relation;
    }

    public long getId_relation() {
        return id_relation;
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
