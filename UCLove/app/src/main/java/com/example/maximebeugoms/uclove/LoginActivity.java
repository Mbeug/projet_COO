package com.example.maximebeugoms.uclove;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by damien on 22/04/16.
 */
public class LoginActivity extends MainActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.drawer_layout, new LoginFragment())
                    .commit();
        }
    }
    /**
     * Ce fragment contient le login_view
     * Created by Menal_000
     */
    public static class LoginFragment extends Fragment {
        public LoginFragment() {

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.login_view, container, false);
        }
    }

}


