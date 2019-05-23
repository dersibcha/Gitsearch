package com.example.gitsearch;

//import android.app.Fragment;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

public class ClaseGlobalFragments extends Fragment {

    private static String userName = "testUser";
    private static String gitUser = "testGit";
    private static String email = "test@email.com";



    //private static int defaultPhoto = R.drawable.user;
    private static Bitmap userPhoto;

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setGitUser(String gitUser) {
        this.gitUser = gitUser;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    /*public static void setDefaultPhoto(int defaultPhoto) {
        ClaseGlobalFragments.defaultPhoto = defaultPhoto;
    }*/
    public static void setUserPhoto(Bitmap userPhoto) {
        ClaseGlobalFragments.userPhoto = userPhoto;
    }

    public String getUserName() {
        return userName;
    }
    public String getGitUser() {
        return gitUser;
    }
    public String getEmail() {
        return email;
    }
   /* public static int getDefaultPhoto() {
        return defaultPhoto;
    }*/
    public static Bitmap getUserPhoto() {
        return userPhoto;
    }


}
