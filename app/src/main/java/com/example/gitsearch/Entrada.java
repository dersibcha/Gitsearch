package com.example.gitsearch;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gitsearch.Commons.GlobalVariables;
import com.example.gitsearch.Model.AccessToken;
import com.example.gitsearch.R;
import com.example.gitsearch.Services.API_Builder;
import com.example.gitsearch.Services.FirebaseServices;
import com.example.gitsearch.Services.GithubClient;
import com.example.gitsearch.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Entrada extends AppCompatActivity {

    public AccessToken accessToken;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);

        OnclickDelButton(R.id.btnLogIn);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        SharedPreferences prefs = getApplicationContext().getSharedPreferences(GlobalVariables.Token, MODE_PRIVATE);
        String tokenGitHub = prefs.getString(GlobalVariables.Token, GlobalVariables.NULL_String_Value);
        if (!tokenGitHub.equals(GlobalVariables.NULL_String_Value)) {
            mAuth = FirebaseAuth.getInstance();

            AuthCredential credential = GithubAuthProvider.getCredential(tokenGitHub);
            mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Utils.Message(getText(R.string.signInWithCredential).toString() + task.isSuccessful(), getApplicationContext());

                    if (!task.isSuccessful()) {
                        Utils.Message(getText(R.string.authError).toString(), Entrada.this);
                    } else {
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        updateUI(currentUser);
                    }
                }
            });
        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

    }

    private void updateUI(FirebaseUser signedIn) {
        if (signedIn != null) {
            Intent intent = new Intent(Entrada.this, NavigationDrawerActivity.class);
            startActivity(intent);
        } else {
            Utils.Message(getText(R.string.GithubConnectingError).toString(), this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void OnclickDelButton(int ref) {

        View view = findViewById(ref);
        Button miButton = (Button) view;

        miButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.btnLogIn:

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(GlobalVariables.Github_OAuth_URL +
                                "?client_id=" + GlobalVariables.Client_ID + "&redirect_uri="));
                        startActivity(intent);

                        break;

                    default:
                        break;
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();

        if (uri != null && uri.toString().startsWith(GlobalVariables.Callback_URL)) {
            //Utils.Message(getText(R.string.loading).toString(), getApplicationContext());

            String code = uri.getQueryParameter(GlobalVariables.Github_Code); // Code client Secret

            Retrofit retro = API_Builder.getInstance().getBuilderRetroAPI(GlobalVariables.Github_URL);

            GithubClient client = retro.create(GithubClient.class);

            final Call<AccessToken> accessTokenCall = client.getAccessToken(GlobalVariables.Client_ID, GlobalVariables.Client_Secret, code);


            accessTokenCall.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    accessToken = response.body();
                    API_Builder.setAccestoken(accessToken);
                    Utils.Message(getText(R.string.successfulConnection).toString(), getApplicationContext());

                    //Para guardar el token del usuario que ingreso
                    SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(GlobalVariables.Token, MODE_PRIVATE).edit();
                    editor.putString(GlobalVariables.Token, accessToken.getAccesToken());
                    editor.commit();
                    FirebaseServices.getInstance().authenticateGitHub(accessToken.getAccesToken(), getApplicationContext(), mDatabase);
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Utils.Message(getText(R.string.connectingError).toString(), getApplicationContext());
                }
            });
        }
    }





}

