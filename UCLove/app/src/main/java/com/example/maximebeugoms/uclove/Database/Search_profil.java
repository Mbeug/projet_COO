package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 28/04/16.
 */
public class Search_profil {

        private String sexe;
        private int age;
        private Strin mail_user;
        private String orientation;
        private String localisation;
        private String langue;
        private String taille;


        //localisation + photo + date_dispo

        public Search_profil(String sexe, int age, String orientation, String localisation, String langue, String taille, String mail_user) {
            super();
            this.langue = langue;
            this.sexe = sexe;
            this.age = age;
            this.orientation = orientation;
            this.localisation = localisation;
            this.taille = taille;
            this.mail_user = mail_user;

        }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getLangue() {
        return langue;
    }

    public String getTaille() {
        return taille;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setTaille(String taille) {
        this.taille = taille;
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

        public String getOrientation() {
            return orientation;
        }

        public void setOrientation(String orientation) {

            this.orientation = orientation;
        }

    public void setMail_user(long mail_user) {
        this.mail_user = mail_user;
    }

    public String getMail_user() {
        return mail_user;
    }
}
