package com.example.gitsearch;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gitsearch.Commons.GlobalVariables;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends ClaseGlobalFragments {


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        final TextView tvUserName = (TextView) view.findViewById(R.id.tvUserNameB);
        final TextView tvGitUser = (TextView) view.findViewById(R.id.tvGitUserB);
        final TextView tvEmail = (TextView) view.findViewById(R.id.tvEmailB);
        final ImageView ivUser = (ImageView) view.findViewById(R.id.ivUser);



        SharedPreferences informacion = getContext().getSharedPreferences(GlobalVariables.Information, MODE_PRIVATE);
        String name = informacion.getString(GlobalVariables.name, "None");
        String email = informacion.getString(GlobalVariables.email, "None");
        String nameexact = informacion.getString(GlobalVariables.nameexact, "None");
        String photo = informacion.getString(GlobalVariables.photo, String.valueOf(0));



        tvUserName.setText(nameexact);
        tvGitUser.setText(name);
        tvEmail.setText(email);


        //cargar url de imagen
        Glide.with(getActivity())
                .load(photo)
                .into(ivUser);
        return view;
    }

}
