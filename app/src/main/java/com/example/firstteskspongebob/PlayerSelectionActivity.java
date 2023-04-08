package com.example.firstteskspongebob;

import static com.example.firstteskspongebob.GameLogic.JELLYFISH;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerSelectionActivity extends AppCompatActivity {
    private LinearLayoutCompat select_LL_actors;
    private AppCompatImageView select_img_spongebob;
    private AppCompatImageView select_img_petrick;
    private AppCompatImageView select_img_sandy;
    public static int PlayerType;
    public static final int SPONGBOB = 1;
    public static final int PETRICK = 2;
    public static final int SANDY = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);
        findViews();
        initViews();
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
        Intent intent = new Intent(PlayerSelectionActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }
    private void findViews() {
        select_LL_actors = findViewById(R.id.select_LL_actors);
        select_img_spongebob = findViewById(R.id.select_img_spongebob);
        select_img_petrick = findViewById(R.id.select_img_petrick);
        select_img_sandy = findViewById(R.id.select_img_sandy);
    }

}