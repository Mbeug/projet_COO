package com.example.maximebeugoms.uclove;

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

    public long getId_message() {
        return id_relation;
    }

    public void setId_message(long id_message) {
        this.id_relation = id_message;
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
