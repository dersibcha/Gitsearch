package com.example.gitsearch.Model;

public class DatabaseUser {

    private String username;
    private String email;
    private boolean isCompany;
    private boolean isUser;
    private boolean premium;

    public DatabaseUser(String username, String email, boolean isCompany, boolean isUser, boolean premium) {
        this.username = username;
        this.email = email;
        this.isCompany = isCompany;
        this.isUser = isUser;
        this.premium = premium;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email; }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }
}
