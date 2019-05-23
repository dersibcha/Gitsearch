package com.example.gitsearch;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gitsearch.Commons.GlobalVariables;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends ClaseGlobalFragments {

    public EditProfileFragment() {
        // Required empty public constructor
    }

    ImageView ivEditUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        final View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        final EditText etUserName = (EditText) view.findViewById(R.id.etUserName);
        final EditText etGitUser = (EditText) view.findViewById(R.id.etGitUser);
        final EditText etEmail = (EditText) view.findViewById(R.id.etEmail);
        ivEditUser = (ImageView) view.findViewById(R.id.ivEditUser);
        ImageButton btnCamera = (ImageButton) view.findViewById(R.id.btnCamera);

        etUserName.setText(getUserName());
        etGitUser.setText(getGitUser());
        etEmail.setText(getEmail());
        Bitmap bitmap = getUserPhoto();
        if(bitmap != null){
            ivEditUser.setImageBitmap(bitmap);
        }

        btnCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);
            }
        });

        Button btnSaveUser = (Button) view.findViewById(R.id.btnSaveUser);
        btnSaveUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setUserName(etUserName.getText().toString());
                setGitUser(etGitUser.getText().toString());
                setEmail(etEmail.getText().toString());

                GlobalVariables.userNameCurrent = etUserName.getText().toString();
                GlobalVariables.gitUserCurrent = etGitUser.getText().toString();
                GlobalVariables.emailCurrent = etEmail.getText().toString();

                Mensaje("Se han guardado los cambios.");
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0 && resultCode == Activity.RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivEditUser.setImageBitmap(bitmap);
            setUserPhoto(bitmap);

            Mensaje("Se ha cambiado la foto");
        }
    }

    public void Mensaje(String msg){
        Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();
    }

}
