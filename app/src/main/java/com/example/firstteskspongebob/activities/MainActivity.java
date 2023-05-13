package com.example.firstteskspongebob.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.firstteskspongebob.MySignal;
import com.example.firstteskspongebob.R;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_BUNDLE = "KEY_BUNDLE";
    private MaterialButton menu_BTN_sensorMode;
    private MaterialButton menu_BTN_buttonMode_fast;
    private MaterialButton menu_BTN_buttonMode_slow;
    private EditText menu_EDT_playerName;
    public static final int BUTTON_MODE_SLOW = 0;
    public static final int BUTTON_MODE_FAST = 1;
    public static final int SENSOR_MODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        checkSensors();
        onActionBTN();

    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    private void checkSensors() {
        SensorManager mySensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor == null)
            menu_BTN_sensorMode.setVisibility(View.GONE);
    }
    private void onActionBTN() {
        menu_BTN_buttonMode_fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayerSelectionActivity(BUTTON_MODE_FAST);
            }
        });
        menu_BTN_sensorMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayerSelectionActivity(SENSOR_MODE);
            }
        });
        menu_BTN_buttonMode_slow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayerSelectionActivity(BUTTON_MODE_SLOW);
            }
        });
    }
    public static boolean isNotNullOrEmpty(String str) {
        return !str.equals("Enter your name:") && str != null && !str.trim().isEmpty();
    }
    private void findViews() {
        menu_BTN_buttonMode_fast = findViewById(R.id.menu_BTN_buttonMode_fast);
        menu_BTN_buttonMode_slow = findViewById(R.id.menu_BTN_buttonMode_slow);
        menu_BTN_sensorMode = findViewById(R.id.menu_BTN_senseorMode);
        menu_EDT_playerName = findViewById(R.id.menu_EDT_playerName);
    }
    private void openPlayerSelectionActivity(int mode) {
        if(!isNotNullOrEmpty(menu_EDT_playerName.getText().toString())){
            MySignal.getInstance().toast("Enter your name!");
            return;
        }
        Intent intent = new Intent(this, PlayerSelectionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(GameActivity.KEY_MODE, mode);
        bundle.putString(TopPlayersActivity.KEY_PLAYER_NAME, menu_EDT_playerName.getText().toString());
        intent.putExtra(KEY_BUNDLE, bundle);
        menu_EDT_playerName.setText(null);
        startActivity(intent);
    }

}