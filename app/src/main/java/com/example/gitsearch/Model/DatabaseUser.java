package com.example.gitsearch.Model;

public class DatabaseUser {

    private String username;
    private String email;
    private String profilepic;
    private boolean isUser;
    private boolean premium;
    private double x;
    private double y;

    public DatabaseUser(String username, String email,String profilepic,boolean isUser, boolean premium,double x,double y) {
        this.username = username;
        this.email = email;
        this.profilepic = profilepic;
        this.isUser = isUser;
        this.premium = premium;
        this.x = x;
        this.y = y;
    }

    public boolean isPremium() {
        return premium;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email; }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }
}
