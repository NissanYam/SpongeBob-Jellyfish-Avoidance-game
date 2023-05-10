package com.example.firstteskspongebob.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import com.example.firstteskspongebob.interfaces.CallBack_List;
import com.example.firstteskspongebob.MySignal;
import com.example.firstteskspongebob.activities.fregments.Fragment_List;
import com.example.firstteskspongebob.activities.fregments.Fragment_Map;
import com.example.firstteskspongebob.MSPV;
import com.example.firstteskspongebob.logic.Player;
import com.example.firstteskspongebob.logic.PlayerRankingComparator;
import com.example.firstteskspongebob.R;
import com.example.firstteskspongebob.logic.TopTenPlayers;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class TopPlayersActivity extends AppCompatActivity {

    public static final String KEY_PLAYER_NAME = "KEY_PLAYER_NAME";
    public static final String KEY_SCORE = "KEY_SCORE";
    private static final String KEY_BUNDLE = "KEY_BUNDLE";
    public static final String KEY_SPEED = "KEY_SPEED";
    public static final String KEY_PLAYER_IMAGE = "KEY_PLAYER_IMAGE";
    public final String LAT = "LAT";
    public final String LNG = "LNG";
    private Bundle bundle;
    private MaterialTextView top_players_LBL_title;
    private MaterialButton top_players_BTN_main_menu;
    private MaterialButton top_players_BTN_chos_char;
    private Fragment_List fragmentList;
    private Fragment_Map fragmentMap;
    private final int TOP = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_players);
        bundle = getIntent().getBundleExtra(TopPlayersActivity.KEY_BUNDLE);
        findViews();
        setTitleText();
        addNewPlayerToTopTen();
        fragmentList = new Fragment_List(MSPV.getMe().readTopTenPlayers().getTopTen());
        replaceFragmentList(fragmentList);
        fragmentList.setCallBackList(callBackList);
        fragmentMap = new Fragment_Map(MSPV.getMe().readTopTenPlayers().getTopTen());
        replaceFragmentMap(fragmentMap);
        onActionBTN();
    }
    private void onActionBTN() {
        top_players_BTN_main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenu();
            }
        });
        top_players_BTN_chos_char.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlayerSelection();
            }
        });
    }
    private void setTitleText() {
        top_players_LBL_title.setText("Hello " + bundle.getString(KEY_PLAYER_NAME));
    }
    private void replaceFragmentMap(Fragment_Map fragmentMap) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.top_players_LAY_frameMap, fragmentMap);
        fragmentTransaction.commit();
    }
    private void replaceFragmentList(Fragment_List fragment_list) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.top_players_LAY_frameTop10, fragment_list);
        fragmentTransaction.commit();

    }
    private void addNewPlayerToTopTen() {
        Player currentPlayer = new Player()
                .setName(bundle.getString(KEY_PLAYER_NAME))
                .setScore(bundle.getInt(KEY_SCORE))
                .setGameType(bundle.getInt(GameActivity.KEY_MODE))
                .setSpeed(bundle.getInt(KEY_SPEED))
                .setLng(getLocation(LNG))
                .setLat(getLocation(LAT));
        switch (bundle.getInt(KEY_PLAYER_IMAGE)) {
            case (PlayerSelectionActivity.SPONGBOB):
                currentPlayer.setImage(R.drawable.img_spongebob);
                break;
            case (PlayerSelectionActivity.PETRICK):
                currentPlayer.setImage(R.drawable.img_petrick);
                break;
            case (PlayerSelectionActivity.SANDY):
                currentPlayer.setImage(R.drawable.img_sandy);
                break;
            default:
                currentPlayer.setImage(R.drawable.img_spongebob);
                break;
        }
        ArrayList<Player> allPlayers = getHistoryPlayers();
        allPlayers.add(currentPlayer);
        allPlayers.sort(new PlayerRankingComparator());
        if (allPlayers.size() > TOP) {
            for (int i = TOP ; i < allPlayers.size(); i++) {
                allPlayers.remove(i);
            }
        }
        MSPV.getMe().saveTopTenPlayers(new TopTenPlayers().setTopTen(allPlayers));
    }
    private double getLocation(String option) {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return 0.0;
        } else {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                return 0.0;
            } else {
                switch (option) {
                    case LAT:
                        return location.getLatitude();
                    case LNG:
                        return location.getLongitude();
                    default:
                        return 0.0;
                }
            }
        }
    }

    private void goToPlayerSelection() {
        Intent intent = new Intent(this, PlayerSelectionActivity.class);
        intent.putExtra(MainActivity.KEY_BUNDLE, bundle);
        startActivity(intent);
        finish();
    }
    private void goToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.KEY_BUNDLE, bundle);
        startActivity(intent);
        finish();
    }
    private ArrayList<Player> getHistoryPlayers() {
        TopTenPlayers players = MSPV.getMe().readTopTenPlayers();
        return players.getTopTen();
    }
    private void findViews() {
        top_players_LBL_title = findViewById(R.id.top_players_LBL_title);
        top_players_BTN_main_menu = findViewById(R.id.top_players_BTN_main_menu);
        top_players_BTN_chos_char = findViewById(R.id.top_players_BTN_chos_char);
    }
    private CallBack_List callBackList = new CallBack_List() {
        @Override
        public void playerCliced(Player player) {
            MySignal.getInstance().toast(player.getName() +" "+ player.getScore());
            fragmentMap.showOnMap(player);
        }
    };

}