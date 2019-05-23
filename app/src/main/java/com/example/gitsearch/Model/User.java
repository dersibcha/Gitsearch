package com.example.gitsearch.Model;

public class User {

    private String gituser;
    private String username;
    private String email;
    private boolean premium;
    private double x;
    private double y;
    private boolean active;
    private String gitlink;


    public User(String gituser, String username, String email, boolean premium, double x, double y, boolean active, String gitlink) {
        this.gituser = gituser;
        this.username = username;
        this.email = email;
        this.premium = premium;
        this.x = x;
        this.y = y;
        this.active = active;
        this.gitlink = gitlink;
    }


    public String getGituser() {
        return gituser;
    }

    public void setGituser(String gituser) {
        this.gituser = gituser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPremium() {
        return premium;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getGitlink() {
        return gitlink;
    }

    public void setGitlink(String gitlink) {
        this.gitlink = gitlink;
    }

    @Override
    public String toString() {
        return "User{" +
                "gituser='" + gituser + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
