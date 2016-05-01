package com.example.maximebeugoms.uclove;

import android.app.Application;
import android.content.Context;

import com.example.maximebeugoms.uclove.Database.*;

/**
 * Created by Okewii on 30-04-16.
 */
public class Uclove extends Application {

    private Profil profil;
    private User user;
    private static Context mContext;

    public void onCreate(){
        super.onCreate();
        this.mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }

    public Profil getProfil (){
        return profil;
    }

    public void setProfil (Profil newProfile){
        this.profil = newProfile;
    }

    public User getUser (){
        return user;
    }

    public void setUser (User newUser){
        this.user = newUser;
    }
}
