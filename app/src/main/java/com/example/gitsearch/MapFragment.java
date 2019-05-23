package com.example.gitsearch;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.gitsearch.Commons.GlobalVariables;
import com.example.gitsearch.Model.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends  ClaseGlobalFragments implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private LocationListener locationListener;
    private LocationManager locationManager;

    private final long MIN_TIME = 1000; // 1 second
    private final long MIN_DIST = 5; // 5 Meters
    private  Marker option = null;

    private LatLng latLng;
    private LatLng latLng2;
    private LatLng latLng3;
    private LatLng latLng4;

    private List<User> listUsers = new LinkedList<>();

    public MapFragment() {
        // Required empty public constructor
    }

    SupportMapFragment mapFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);



        if(GlobalVariables.first_prompting) {
            option = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(GlobalVariables.pred_lat, GlobalVariables.pred_long))
                    .title("Posicion Predeterminada"));
            GlobalVariables.first_prompting = false;
            GlobalVariables.marker_user_id = option.getId();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(GlobalVariables.pred_lat, GlobalVariables.pred_long)));


        latLng2 = new LatLng(GlobalVariables.pred_lat+0.5, GlobalVariables.pred_long+0.5);
        mMap.addMarker(new MarkerOptions().position(latLng2).title("Usuario 1"));

        latLng3 = new LatLng(GlobalVariables.pred_lat+0.2, GlobalVariables.pred_long+0.2);
        mMap.addMarker(new MarkerOptions().position(latLng3).title("Usuario 2"));

        latLng4 = new LatLng(GlobalVariables.pred_lat+0.1, GlobalVariables.pred_long+0.3);
        mMap.addMarker(new MarkerOptions().position(latLng4).title("Usuario 3"));


        User User1 = new User("dersibcha", "Usuario 1", "derfelsib@gmail.com", true,
                GlobalVariables.pred_lat+0.5, GlobalVariables.pred_long+0.5, true, "https://github.com/dersibcha");


        User User2 = new User("dersibcha", "Usuario 2", "derfelsib@gmail.com", true,
                GlobalVariables.pred_lat+0.2, GlobalVariables.pred_long+0.2, true, "https://github.com/dersibcha");

        User User3 = new User("dersibcha", "Usuario 3", "derfelsib@gmail.com", true,
                GlobalVariables.pred_lat+0.1, GlobalVariables.pred_long+0.1, true, "https://github.com/dersibcha");


        listUsers.add(User1);
        listUsers.add(User2);
        listUsers.add(User3);


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                try {
                    if(option!=null) {
                        latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        //mMap.(new MarkerOptions().position(latLng).title());
                        option.remove();

                        option = mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title("Posicion Actual"));

                        GlobalVariables.marker_user_id = option.getId();


                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                }
                catch (SecurityException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }


    }



    private void createDialogOther(final String user) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setTitle(user);
        List<String> aux = new ArrayList<>();
        aux.add("Profile");
        //aux.add("-------------------------");
        aux.add("Github");
       // aux.add("-------------------------");
        aux.add("Mail");
        aux.add("Close");
        String[] types = aux.toArray(new String[aux.size()]);
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int arg) {
                if(arg == 0){
                    User ue = getUser(user);
                    setUserName(ue.getUsername());
                    setGitUser(ue.getGituser());
                    setEmail(ue.getEmail());
                    ((NavigationDrawerActivity)getActivity()).replaceFragments(getUser(user).getUsername()+" Profile");
                }
                if(arg == 1){
                    openLink(getUser(user));
                }

                if(arg == 2){
                    sentMail(getUser(user));
                }
            }
        });
        b.show();


    }



    private void createDialogUser(String user) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setTitle(user);
        List<String> aux = new ArrayList<>();
        aux.add("Profile");
        aux.add("Close");
        String[] types = aux.toArray(new String[aux.size()]);
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int arg) {
                if(arg == 0){
                    ((NavigationDrawerActivity)getActivity()).replaceFragments("User Profile");
                }
            }
        });
        b.show();


    }



    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();}

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.getTitle().equals( "Posicion Predeterminada") || marker.getTitle().equals("Posicion Actual")){
        //
            createDialogUser("User");
        }
        else{
            //createDialogUser("User");
            createDialogOther(marker.getTitle());
        }
        return false;
    }

    public void sentMail(User u){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("mailto:"));
        String[] to = { u.getEmail() , "alguienmas@example.com" };
        i.putExtra(Intent.EXTRA_EMAIL, to);
        i.putExtra(Intent.EXTRA_SUBJECT, "Consulta");
        i.putExtra(Intent.EXTRA_TEXT, "Consulta");
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i, "Email"));
    }


    public void openLink(User u){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse( u.getGitlink() ));
        startActivity(i);
    }

    public User getUser(String username){
        if(!listUsers.isEmpty())
        for(User u : listUsers){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
   return null;
    }
}
