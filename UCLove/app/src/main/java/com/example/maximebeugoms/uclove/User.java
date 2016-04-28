package com.example.maximebeugoms.uclove;

/**
 * Created by damien on 25/04/16.
 */
public class User {

    private long id_user;
    private String login;
    private String mail;
    private String password;
    private long id_profil;
    private long id_historique;
    private long id_preference;
    private long id_relation;
    private long id_search;

    public User(long id_user,String login,String mail, String password, long id_historique, long id_preference, long id_profil,
                long id_relation, long id_search){
        super();
        this.id_user = id_user;
        this.login = login;
        this.mail = mail;
        this.password = password;
        this.id_profil = id_profil;
        this.id_historique = id_historique;
        this.id_preference = id_preference;
        this.id_relation = id_relation;
        this.id_search = id_search;

    }

    public long getId() {
        return id_user;
    }

    public void setId(long id) {
        this.id_user = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId_relation() {
        return id_relation;
    }

    public void setId_relation(long id_relation) {
        this.id_relation = id_relation;
    }

    public long getId_historique() {
        return id_historique;
    }

    public void setId_historique(long id_historique) {
        this.id_historique = id_historique;
    }

    public long getId_preference() {
        return id_preference;
    }

    public void setId_preference(long id_preference) {
        this.id_preference = id_preference;
    }

    public long getId_profil() {
        return id_profil;
    }

    public long getId_search() {
        return id_search;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_profil(long id_profil) {
        this.id_profil = id_profil;
    }

    public void setId_search(long id_search) {
        this.id_search = id_search;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }
}
