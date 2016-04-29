package com.example.maximebeugoms.uclove;

/**
 * Created by damien on 25/04/16.
 */
public class User {

    private long id_user;
    private String login;
    private String mail;
    private String password;


    public User(long id_user,String login,String mail, String password){
        super();
        this.id_user = id_user;
        this.login = login;
        this.mail = mail;
        this.password = password;

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

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }
}
