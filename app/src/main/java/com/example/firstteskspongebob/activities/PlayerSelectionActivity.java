package com.example.firstteskspongebob.activities;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import com.example.firstteskspongebob.MySignal;
import com.example.firstteskspongebob.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class PlayerSelectionActivity extends AppCompatActivity {
    private LinearLayoutCompat select_LL_actors;
    private AppCompatImageView select_img_spongebob;
    private AppCompatImageView select_img_petrick;
    private AppCompatImageView select_img_sandy;
    private int PlayerType;
    public static final int SPONGBOB = 1;
    public static final int PETRICK = 2;
    public static final int SANDY = 3;
    private Bundle bundle;
    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);
        bundle = getIntent().getBundleExtra(MainActivity.KEY_BUNDLE);
        findViews();
        initViews();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getCurrentLocation();

    }



    private void initViews() {
        select_img_spongebob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerType = SPONGBOB;
                openGame();
            }
        });
        select_img_petrick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerType = PETRICK;
                openGame();
            }
        });
        select_img_sandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerType = SANDY;
                openGame();
            }
        });
    }

    private void openGame() {
        Intent intent = new Intent(this, GameActivity.class);
        this.bundle.putInt(GameActivity.KEY_PLAYER_IMAGE, PlayerType);
        intent.putExtra(MainActivity.KEY_BUNDLE, bundle);
        try{
            bundle.putDouble(TopPlayersActivity.KEY_LAT, currentLocation.getLatitude());
            bundle.putDouble(TopPlayersActivity.KEY_LNG, currentLocation.getLongitude());
            MySignal.getInstance().toast("Latitude = "+currentLocation.getLatitude()+"\nLongitude = "+currentLocation.getLongitude());
        }catch (Exception e){
            bundle.putDouble(TopPlayersActivity.KEY_LAT, 0);
            bundle.putDouble(TopPlayersActivity.KEY_LNG, 0);
        }
        startActivity(intent);
        finish();
    }

    private void findViews() {
        select_LL_actors = findViewById(R.id.select_LL_actors);
        select_img_spongebob = findViewById(R.id.select_img_spongebob);
        select_img_petrick = findViewById(R.id.select_img_petrick);
        select_img_sandy = findViewById(R.id.select_img_sandy);
    }
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    currentLocation = location;
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                getCurrentLocation();
            } else {
                // Permission denied
                // TODO: Handle permission denied case
            }
        }
    }


}