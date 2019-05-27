package com.example.gitsearch;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gitsearch.Commons.GlobalVariables;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        MensajeAB("Map");

        FragmentManager fm = getSupportFragmentManager();
        MapFragment  fragMap = new MapFragment ();
        fm.beginTransaction()
                .replace(R.id.fragContainer, fragMap)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SharedPreferences informacion = getApplicationContext().getSharedPreferences(GlobalVariables.Information, MODE_PRIVATE);
        String name = informacion.getString(GlobalVariables.name, "None");
        String email = informacion.getString(GlobalVariables.email, "None");
        String photo = informacion.getString(GlobalVariables.photo, String.valueOf(0));

        TextView txtName = (TextView) findViewById(R.id.tvName);
        txtName.setText(name);
        TextView txtEmail = (TextView) findViewById(R.id.tvEmail);
        txtEmail.setText(email);
        ImageView imgPhoto = (ImageView) findViewById(R.id.ivProfilepic);
        //cargar url de imagen
        Glide.with(getApplicationContext())
                .load(photo)
                .into(imgPhoto);
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intento = new Intent(getApplicationContext(), PaypalActivity.class);
            startActivity(intento);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            //FragmentManager fm = getSupportFragmentManager();
            MapFragment  fragMap2 = new MapFragment ();
            fm.beginTransaction()
                    .replace(R.id.fragContainer, fragMap2)
                    .commit();
            MensajeAB("Map");

        } else if (id == R.id.nav_profile) {
            //FragmentManager fm = getSupportFragmentManager();



            UserFragment  fragProfile = new UserFragment ();

            fragProfile.setUserName(GlobalVariables.userNameCurrent);
            fragProfile.setGitUser(GlobalVariables.gitUserCurrent);
            fragProfile.setEmail(GlobalVariables.emailCurrent);

            fm.beginTransaction()
                    .replace(R.id.fragContainer, fragProfile)
                    .commit();
            MensajeAB("User Profile");

        } else if (id == R.id.nav_repository) {
            //FragmentManager fm = getSupportFragmentManager();
            RepositoryFragment  fragRep = new RepositoryFragment ();
            fm.beginTransaction()
                    .replace(R.id.fragContainer, fragRep)
                    .commit();
            MensajeAB("Repository");

        } else if (id == R.id.nav_edit) {
            //FragmentManager fm = getSupportFragmentManager();
            EditProfileFragment  fragEdit = new EditProfileFragment ();
            fm.beginTransaction()
                    .replace(R.id.fragContainer, fragEdit)
                    .commit();
            MensajeAB("Edit Profile");

        }  else if (id == R.id.nav_authors) {
            //FragmentManager fm = getSupportFragmentManager();
            AutoresFragment  fragAutores = new AutoresFragment ();
            fm.beginTransaction()
                    .replace(R.id.fragContainer, fragAutores )
                    .commit();
            MensajeAB("Authors");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void MensajeAB(String msg){
        getSupportActionBar().setTitle(msg);
    }

    public void replaceFragments(String text) {
        UserFragment  fragmentClass = new UserFragment ();
        fm.beginTransaction()
                .replace(R.id.fragContainer, fragmentClass)
                .commit();
        MensajeAB(text);
    }
}
