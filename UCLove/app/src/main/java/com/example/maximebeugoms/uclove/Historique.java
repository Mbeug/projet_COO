package com.example.maximebeugoms.uclove;

/**
 * Created by damien on 25/04/16.
 */
public class Historique {
    private long id_historique;
    private long id_event;

    public Historique(long id_historique, long id_event){
        super();
        this.id_event = id_event;
        this.id_historique = id_historique;
    }

    public long getId_historique() {
        return id_historique;
    }

    public void setId_historique(long id_historique) {
        this.id_historique = id_historique;
    }

    public void setId_event(long id_event) {
        this.id_event = id_event;
    }

    public long getId_event() {
        return id_event;
    }
}
