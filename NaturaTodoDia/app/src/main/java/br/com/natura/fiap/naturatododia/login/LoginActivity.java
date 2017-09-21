package br.com.natura.fiap.naturatododia.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.main.MainActivity;
import br.com.natura.fiap.naturatododia.utils.NaturaDialog;
import br.com.natura.fiap.naturatododia.utils.SaveSharedPreference;

public class LoginActivity extends AppCompatActivity {
    CadastroChatFragment cadastroChatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cadastroChatFragment = new CadastroChatFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLogin, cadastroChatFragment);
        ft.commit();

        if (SaveSharedPreference.getUserName(LoginActivity.this).length() != 0) {
            Intent intent;
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }
}
