package com.example.gitsearch.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.gitsearch.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.w3c.dom.Text;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;


    public CustomInfoWindowAdapter(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window,null);
    }

    private void rendowWindowText(Marker marker, View view){

        String user = marker.getTitle();
        TextView tvUser = (TextView) view.findViewById(R.id.user);

        if(!user.equals("")){
            tvUser.setText(user);
        }

        String snippet = marker.getSnippet();
        TextView tvSnippet = (TextView) view.findViewById(R.id.snippet);

        if(!snippet.equals("")){
            tvSnippet.setText(snippet);
        }

    }

    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker,mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker,mWindow);
        return mWindow;
    }
}
