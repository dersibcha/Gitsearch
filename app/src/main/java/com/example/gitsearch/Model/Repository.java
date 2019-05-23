package com.example.gitsearch.Model;

import java.util.HashMap;

public class Repository {

    private String id;
    private String name;
    private String html_url;
    private String url;
    private String languages_url;
    private String language;
    private String description;
    private String created_at;
    private String forks;
    private String default_branch;
    private int commitsCount;
    private String commits_url;
    private HashMap<String, Object> myLanguagesInfo;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getUrl() {
        return url;
    }

    public String getLanguages_url() {
        return languages_url;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() { return description; }

    public String getCreated_at() { return created_at; }

    public String getForks() { return forks; }

    public String getDefault_branch() { return default_branch; }

    public HashMap<String, Object> getMyLanguagesInfo() {
        return myLanguagesInfo;
    }

    public void setMyLanguagesInfo(HashMap<String, Object> myLanguagesInfo) {
        this.myLanguagesInfo = myLanguagesInfo;
    }

    public int getCommitsCount() {
        return commitsCount;
    }

    public void setCommitsCount(int commitsAverage) {
        this.commitsCount = commitsAverage;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }
}
