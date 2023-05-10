package com.example.firstteskspongebob.activities.fregments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firstteskspongebob.MSPV;
import com.example.firstteskspongebob.R;
import com.example.firstteskspongebob.logic.Player;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GoogleMap googleMap;
    private ArrayList<Player> playerList;

    public  Fragment_Map(ArrayList<Player> topTen){
        this.playerList = topTen;
    }
    public  Fragment_Map(){}
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Map newInstance(String param1, String param2) {
        Fragment_Map fragment = new Fragment_Map(MSPV.getMe().readTopTenPlayers().getTopTen());
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng centerMapLocation = new LatLng(0,0);
        setCameraPosition(0,centerMapLocation);
        for (Player player: playerList){
            String name = player.getName();
            LatLng myLocation = new LatLng(player.getLat(),player.getLng());
            addMarker(myLocation, name);
        }
    }


    public void showOnMap(Player player) {
        googleMap.clear();
        String name = player.getName();
        LatLng myLocation = new LatLng(player.getLat(),player.getLng());
        addMarker(myLocation, name);
        setCameraPosition(10,myLocation);
    }

    private void addMarker(LatLng myLocation, String name) {
        googleMap.addMarker(new MarkerOptions()
                .position(myLocation)
                .title(name));
    }
    private void setCameraPosition(int zoom, LatLng myLocation) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(myLocation)
                .zoom(zoom)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}