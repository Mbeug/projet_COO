package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 28/04/16.
 */
public class Search_profil {

        private int age;
        private String mail_user;
        private String orientation;
        private String localisation;
        private String longueur_cheveux;
        private String couleur_cheveux;
        private String couleur_yeux;



        public Search_profil(int age, String orientation, String localisation, String longueur_cheveux, String couleur_cheveux, String couleur_yeux, String mail_user) {
            super();
            this.longueur_cheveux = longueur_cheveux;
            this.couleur_cheveux = couleur_cheveux;
            this.age = age;
            this.orientation = orientation;
            this.localisation = localisation;
            this.couleur_yeux = couleur_yeux;
            this.mail_user = mail_user;
        }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {

        this.orientation = orientation;
    }

    public void setMail_user(String mail_user) {
        this.mail_user = mail_user;
    }

    public String getMail_user() {
        return mail_user;
    }

    public String getLongueur_cheveux() {
        return longueur_cheveux;
    }

    public void setLongueur_cheveux(String longueur_cheveux) {
        this.longueur_cheveux = longueur_cheveux;
    }

    public String getCouleur_cheveux() {
        return couleur_cheveux;
    }

    public void setCouleur_cheveux(String couleur_cheveux) {
        this.couleur_cheveux = couleur_cheveux;
    }

    public String getCouleur_yeux() {
        return couleur_yeux;
    }

    public void setCouleur_yeux(String couleur_yeux) {
        this.couleur_yeux = couleur_yeux;
    }
}
