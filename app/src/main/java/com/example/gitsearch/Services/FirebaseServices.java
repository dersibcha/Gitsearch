package com.example.gitsearch.Services;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;


import com.example.gitsearch.Commons.GlobalVariables;
import com.example.gitsearch.Model.DatabaseUser;
import com.example.gitsearch.Model.GithubUser;
import com.example.gitsearch.Model.AccessToken;
import com.example.gitsearch.NavigationDrawerActivity;
import com.example.gitsearch.R;
import com.example.gitsearch.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GithubAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class FirebaseServices {

    // static variable single_instance of type Singleton
    private static FirebaseServices single_instance = null;
    private FirebaseAuth mAuth;
    private Long offerNumber;


    // private constructor restricted to this class itself
    private FirebaseServices() {

    }

    // static method to create instance of Singleton class
    public static FirebaseServices getInstance() {
        if (single_instance == null)
            single_instance = new FirebaseServices();
        return single_instance;
    }

    public void authenticateGitHub(final String token, final Context context, final DatabaseReference mDatabase) {
        mAuth = FirebaseAuth.getInstance();
        if(token!=null) {
            if (!token.isEmpty()) {
                AuthCredential credential = GithubAuthProvider.getCredential(token);
                mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Utils.Message(context.getText(R.string.authError).toString(), context);
                        } else {
                            getUserData(context, mDatabase);
                        }
                    }
                });
            }
        }
    }

    public void getUserData(final Context context, final DatabaseReference mDatabase) {
        SharedPreferences prefs = context.getSharedPreferences(GlobalVariables.Token, MODE_PRIVATE);
        String token = prefs.getString(GlobalVariables.Token, GlobalVariables.Token_NULL);

        Retrofit retro = API_Builder.getInstance().getBuilderRetroAPI(GlobalVariables.Github_API_URL);

        GithubClient client = retro.create(GithubClient.class);

        Call<JsonObject> accessUserInfo = client.userInformation("Bearer " + token);
        accessUserInfo.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject userInfo = response.body();
                if (userInfo != null) {
                    getUserInformation(userInfo.get("login").toString().substring(1, userInfo.get("login").toString().length() - 1), context, mDatabase);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Utils.Message(context.getText(R.string.unknownError).toString(), context);
            }
        });
    }

    public void getUserInformation(final String user, final Context context, final DatabaseReference mDatabase) {
        Retrofit retro = API_Builder.getInstance().getBuilderRetroAPI(GlobalVariables.Github_API_URL);

        GithubClient client = retro.create(GithubClient.class);

        Call<GithubUser> accessUserInfo = client.userInfo(user, "Bearer " + API_Builder.getAccestoken().getAccesToken());

        accessUserInfo.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                GithubUser userInfo = response.body();
                if (userInfo != null) {
                    writeNewUser(user, userInfo.getEmail(),userInfo.getAvatar_url(), mDatabase);
                    SharedPreferences.Editor informacion = context.getSharedPreferences(GlobalVariables.Information, MODE_PRIVATE).edit();
                    informacion.putString(GlobalVariables.name, user);
                    informacion.putString(GlobalVariables.email, userInfo.getEmail());
                    informacion.putString(GlobalVariables.photo, userInfo.getAvatar_url());
                    informacion.putString(GlobalVariables.repos, userInfo.getRepos_url());
                    informacion.putString(GlobalVariables.nameexact, userInfo.getName());
                    informacion.commit();
                    Intent intent = new Intent(context, NavigationDrawerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                Utils.Message(context.getText(R.string.unknownError).toString(), context);
            }

        });
    }

    public void writeNewUser(String name, String email,String profilepic, DatabaseReference mDatabase) {
        DatabaseUser user = new DatabaseUser(name, email,profilepic, GlobalVariables.session_user, false,0,0);
        mDatabase.child("users").child(name).setValue(user);
    }

    public void writeNewUser(String displayname, String name, String email,String profilepic, DatabaseReference mDatabase) {
        DatabaseUser user = new DatabaseUser(displayname, email, profilepic,true, false, 0,0);
        mDatabase.child("users").child(name).setValue(user);
    }



}


