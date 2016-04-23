package com.example.maximebeugoms.uclove;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Ce fragment contient le login_view
 * Created by Menal_000
 */
public class LoginFragment extends Fragment {
    public LoginFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_view, container, false);
    }
}