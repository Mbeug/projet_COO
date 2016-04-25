package com.example.maximebeugoms.uclove;

/**
 * Created by damien on 25/04/16.
 */
public class Evenement {

    private long id_event;
    private String date;
    private String type;
    private long id_historique;

    public Evenement(long id_event, long id_historique, String date, String type){
        super();
        this.date = date;
        this.id_event = id_event;
        this.id_historique = id_historique;
        this.type = type;
    }

    public long getId_event() {
        return id_event;
    }

    public void setId_event(long id_event) {
        this.id_event = id_event;
    }

    public long getId_historique() {
        return id_historique;
    }

    public void setId_historique(long id_historique) {
        this.id_historique = id_historique;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
