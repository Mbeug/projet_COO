package com.example.maximebeugoms.uclove;

/**
 * Created by damien on 25/04/16.
 */
public class User {

    private long id;
    private String login;
    private String mail;
    private String password;

    public User(long id,String login,String mail, String password){
        super();
        this.id = id;
        this.login = login;
        this.mail = mail;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

}
