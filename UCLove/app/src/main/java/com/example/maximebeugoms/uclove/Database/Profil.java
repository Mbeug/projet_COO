package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 28/04/16.
 */
public class Profil {

        private String mail_user;
        private String nom;
        private String sexe;
        private int age;
        private String couleur_cheveux;
        private String couleur_yeux;
        private String orientation;
        private String localisation;

        //localisation + photo + date_dispo

        public Profil(String mail_user, String sexe, int age, String couleur_cheveux, String couleur_yeux, String orientation, String localisation) {
            super();
            this.mail_user = mail_user;
            this.sexe = sexe;
            this.age = age;
            this.couleur_cheveux = couleur_cheveux;
            this.couleur_yeux = couleur_yeux;
            this.orientation = orientation;
            this.localisation = localisation;
        }

        public String getNom(){
            return nom;
        }

        public void setNom(String nom){
            this.nom = nom;
        }

        public String getSexe(){
            return sexe;
        }

        public void setSexe(String sexe){
            this.sexe = sexe;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
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

        public String getOrientation() {
            return orientation;
        }

        public void setOrientation(String orientation) {

            this.orientation = orientation;
        }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getMail() {
        return mail_user;
    }

    public void setMail(String mail_user) {
        this.mail_user = mail_user;
    }

}


