package com.example.firstteskspongebob.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.firstteskspongebob.R;
import com.example.firstteskspongebob.activities.GameActivity;
import com.example.firstteskspongebob.activities.MainActivity;
import com.example.firstteskspongebob.logic.Player;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);
        bundle = getIntent().getBundleExtra(MainActivity.KEY_BUNDLE);
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
        Intent intent = new Intent(this, GameActivity.class);
        this.bundle.putInt(GameActivity.KEY_PLAYER_IMAGE,PlayerType);
        intent.putExtra(MainActivity.KEY_BUNDLE, bundle);
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