package com.example.gitsearch.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gitsearch.Model.GithubUser;
import com.example.gitsearch.Model.Repository;
import com.example.gitsearch.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {

    /*public static void showPopup(String textToShow, final Dialog myDialog) {
        TextView txtclose;
        TextView txtModal;
        Button continueButton;
        myDialog.setContentView(R.layout.custommodal);
        continueButton = myDialog.findViewById(R.id.btnfollow);
        txtclose = (TextView) myDialog.findViewById(R.id.closeModal);
        txtclose.setText("X");

        txtModal = (TextView) myDialog.findViewById(R.id.textModal);
        txtModal.setText(textToShow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }*/

    public static void DialogHideInfo(final GithubUser user, final Context context) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Esta es la info recolecatda!");
        builder1.setCancelable(true);
        builder1.setPositiveButton("VER MI INFO RECOLECTADA",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Message(user.getName() + " -> " + user.getId() + " -> " + user.getAvatar_url() + " -> " + user.getBio(), context);
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static void Message(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void readFromFile(int id, List<String> list, Context context) {
        InputStream myFile = context.getResources().openRawResource(id);
        list.clear();
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(myFile));
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void chargeSpinner(String[] list, int id, Activity activity) {
        Spinner s1 = activity.findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, list);
        s1.setAdapter(adapter);
    }

    public static void MensajeOK(String msg, Activity activity) {
        View v1 = activity.getWindow().getDecorView().getRootView();
        android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(v1.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        android.app.AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static float commitsAverage(List<Repository> repos) {
        float acummulator = 0;
        for (Repository repo : repos) {
            acummulator += repo.getCommitsCount();
        }
        return acummulator / repos.size();
    }
    public static String compareDateToNow(String date){

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Calendar compare = Calendar.getInstance();
        //compare.setTime(simpleDateFormat.parse(date));

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date compare = format.parse(setStringDate(date));
            Long currentDateTime = System.currentTimeMillis();
            Long period = currentDateTime - compare.getTime();
            Date result = new Date(period);
            return setResultString(result);
        }catch(java.text.ParseException pe){
            return "ERROR PARSING THE PERIOD TIME";
        }
    }

    public static String setStringDate(String date){
        String result =  date.substring(0,10);
        return result;
    }

    public static String setResultString(Date date){
        String yearString = " years ";
        String monthString = " months ";
        int year = date.getYear()-70;
        if(year == 1){
            yearString = " year ";
        }
        int months = date.getMonth()-1;
        if(months == 1){
            monthString = " month ";
        }
        return  year+yearString+"+ "+ months+monthString;
    }

    public static String EncodeString(String string) {
        return string.replace("#", "SHARP");
    }

}

