package com.example.maximebeugoms.uclove;

/**
 * Created by damien on 25/04/16.
 */
public class Message {

    private long id_message;
    private String texte;
    private String date;

    public Message(long id_message, long id_relation, String texte, String date){
        super();
        this.id_message = id_message;
        this.texte = texte;
        this.date = date;

    }

    public long getId_message() {
        return id_message;
    }

    public void setId_message(long id_message) {
        this.id_message = id_message;
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
