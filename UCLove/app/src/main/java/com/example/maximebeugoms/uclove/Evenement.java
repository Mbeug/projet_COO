package com.example.maximebeugoms.uclove;

/**
 * Created by damien on 25/04/16.
 */
public class Evenement {

    private long id_user;
    private String date;
    private String type;

    public Evenement(long id_user, long id_historique, String date, String type){
        super();
        this.date = date;
        this.id_user = id_user;
        this.type = type;
    }

    public long getId_event() {
        return id_user;
    }

    public void setId_event(long id_event) {
        this.id_user = id_user;
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
