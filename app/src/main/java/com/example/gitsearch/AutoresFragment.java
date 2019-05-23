package com.example.gitsearch;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AutoresFragment extends Fragment {


    public AutoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_autores, container, false);
        Button btnVideo = (Button) view.findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url = "http://www.youtube.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return view;
    }

}
