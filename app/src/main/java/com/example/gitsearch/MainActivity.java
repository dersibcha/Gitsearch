package com.example.gitsearch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.gitsearch.Commons.GlobalVariables;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mAuth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            public void run() {
                FirebaseUser user = mAuth.getCurrentUser();

                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, NavigationDrawerActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, Entrada.class);
                    startActivity(intent);
                }
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicacion

                finish();

            }

            ;
        }, GlobalVariables.DURACION_SPLASH);
    }
}
