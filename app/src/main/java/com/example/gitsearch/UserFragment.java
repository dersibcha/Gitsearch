package com.example.gitsearch;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


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


        tvUserName.setText(getUserName());
        tvGitUser.setText(getGitUser());
        tvEmail.setText(getEmail());
        Bitmap bitmap = getUserPhoto();
        if(bitmap != null){
            ivUser.setImageBitmap(bitmap);
        }

        return view;
    }

}
