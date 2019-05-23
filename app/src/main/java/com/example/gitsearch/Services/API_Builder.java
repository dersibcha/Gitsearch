package com.example.gitsearch.Services;

import com.example.gitsearch.Model.AccessToken;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Builder {

    // static variable single_instance of type Singleton
    private static API_Builder single_instance = null;
    private static AccessToken accestoken = null;

    // private constructor restricted to this class itself
    private API_Builder() {

    }

    // static method to create instance of Singleton class
    public static API_Builder getInstance() {
        if (single_instance == null)
            single_instance = new API_Builder();
        return single_instance;
    }

    public Retrofit getBuilderRetroAPI(String path) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(path).addConverterFactory(GsonConverterFactory.create());

        return builder.build();

    }

    public static AccessToken getAccestoken() {
        return accestoken;
    }

    public static void setAccestoken(AccessToken accestoken) {
        API_Builder.accestoken = accestoken;
    }
}
