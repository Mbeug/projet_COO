package com.example.maximebeugoms.uclove.Database;

/**
 * Created by damien on 28/04/16.
 */
public class Search_profil {

        private String genre;
        private int age;
        private String mail_user;
        private String orientation;
        private String localisation;
        private String langue;
        private long taille;



        public Search_profil(String genre, int age, String orientation, String localisation, String langue, long taille, String mail_user) {
            super();
            this.langue = langue;
            this.genre = genre;
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

    public long getTaille() {
        return taille;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setTaille(long taille) {
        this.taille = taille;
    }

    public String getGenre(){
            return genre;
        }

        public void setGenre(String sexe){
            this.genre = genre;
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
}
