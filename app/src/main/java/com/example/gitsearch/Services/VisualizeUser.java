package com.example.gitsearch.Services;


import com.example.gitsearch.Model.GithubUser;

public class VisualizeUser {

    // static variable single_instance of type Singleton
    private static VisualizeUser single_instance = null;
    private GithubUser user;

    // private constructor restricted to this class itself
    private VisualizeUser() {
        this.user = new GithubUser();
    }

    // static method to create instance of Singleton class
    public static VisualizeUser getInstance() {
        if (single_instance == null)
            single_instance = new VisualizeUser();
        return single_instance;
    }

    public static VisualizeUser getSingle_instance() {
        return single_instance;
    }

    public GithubUser getUser() {
        return user;
    }

    public void setUser(GithubUser user) {
        this.user = user;
    }
}
