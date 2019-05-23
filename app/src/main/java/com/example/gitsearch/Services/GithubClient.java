package com.example.gitsearch.Services;

import com.example.gitsearch.Model.AccessToken;
import com.example.gitsearch.Model.GithubUser;
import com.example.gitsearch.Model.GithubUsersPreview;
import com.example.gitsearch.Model.Repository;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubClient {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String client,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );

    @GET("/users/{user}/repos")
    Call<List<Repository>> reposFourUser(@Path("user") String user, @Header("Authorization") String auth);

    @GET("users/{user}")
    Call<GithubUser> userInfo(@Path(value = "user", encoded = true) String user, @Header("Authorization") String auth);

    @GET("/search/users")
    Call<GithubUsersPreview> searchUsersLike(@Query("q") String q, @Header("Authorization") String auth);

    @GET("{languagesUrl}")
    Call<JsonObject> listOfLanguages(@Path(value = "languagesUrl", encoded = true) String languagesUrl, @Header("Authorization") String auth);

    @GET("/user")
    Call<JsonObject> userInformation(@Header("Authorization") String auth);

    @GET("{commits}")
    Call<List<JsonObject>> listOfCommits(@Path(value = "commits", encoded = true) String commits, @Header("Authorization") String auth);
}
