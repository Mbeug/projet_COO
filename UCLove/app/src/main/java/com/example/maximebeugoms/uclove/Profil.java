package com.example.maximebeugoms.uclove;

/**
 * Created by damien on 28/04/16.
 */
public class Profil {

        private long id_profil;
        private String nom;
        private String sexe;
        private int age;
        private String couleur_cheveux;
        private String couleur_yeux;
        private String orientation;

        //localisation + photo + date_dispo

        public Profil(long id_profil, String sexe, int age, String couleur_cheveux, String couleur_yeux, String orientation) {
            super();
            this.id_profil = id_profil;
            this.sexe = sexe;
            this.age = age;
            this.couleur_cheveux = couleur_cheveux;
            this.couleur_yeux = couleur_yeux;
            this.orientation = orientation;
        }

        public long getId(){
            return id_profil;
        }

        public void setId(long id){
            this.id_profil = id;
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
    }


