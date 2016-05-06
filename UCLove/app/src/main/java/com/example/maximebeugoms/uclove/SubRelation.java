package com.example.maximebeugoms.uclove;

import android.app.Application;

/**
 * Classe qui affiche les relations
 * Created by Menal_000 on 06-05-16.
 */
public class SubRelation {
    public String mail;
    public int etat;

    public SubRelation(String mail, int etat) {
        this.mail=mail;
        this.etat=etat;
    }

    public String toString() {
        Application app = (Application) Uclove.getContext();
        if (etat==2) {
            return (mail + "\t" + app.getString(R.string.relation_toString_ami));
        } else if (etat==3) {
            return (mail + "\t" + app.getString(R.string.relation_toString_fav));
        }
        return null;

    }
}
