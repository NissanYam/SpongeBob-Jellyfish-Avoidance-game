package com.example.firstteskspongebob;

import static com.example.firstteskspongebob.GameLogic.JELLYFISH;
import static com.example.firstteskspongebob.GameLogic.NONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private ArrayList<LinearLayoutCompat> LinearLayouts;
    private ArrayList<AppCompatImageView> hearts;
    private AppCompatImageView[][] game_board_img_jeli;
    private ArrayList<AppCompatImageView> actor;
    private MaterialButton right_btn;
    private MaterialButton left_btn;
    private GameLogic game;
    private int rows ;
    private int cols ;
    private final int SLOW = 800;
    private final int MID = 600;
    private final int FAST = 400;
    private final int VERY_FAST = 250;
    private MaterialButton game_btn_tryAgain;
    private MaterialButton game_btn_selectPlayer;
    private final boolean RIGHT = true;
    private final boolean LEFT = false;
    private Timer timer = null;
    private Vibrator v;
    private MaterialTextView game_lbl_score;
    private boolean gameIsOver;
    private boolean gameIsStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        findViews();
        gameIsOver = false;
        createGame();
        setActor();
        initViews();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!gameIsOver){
            startGame();
        }
        else{
            gameOver();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(!gameIsStart && !gameIsOver){
            startGame();
        }else if (gameIsStart && gameIsOver){
            gameOver();
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    private void createGame() {
        game = new GameLogic()
                .setRowsGameBoard(rows)
                .setColsGameBoard(cols)
                .setLife(hearts.size());
        game.setGameBoard(new int[game.getRowsGameBoard()][game.getColsGameBoard()])
                .setActorBoard(new int [game.getColsGameBoard()])
                .setActorPlace(game.getColsGameBoard()/2)
                .setScore(0);
    }
    private void findViews() {
        hearts = new ArrayList<>(
                Arrays. asList(findViewById(R.id.game_img_heart1),
                        findViewById(R.id.game_img_heart2),
                        findViewById(R.id.game_img_heart3)));


        LinearLayouts = new ArrayList<>(
                Arrays. asList(findViewById(R.id.game_LL1),
                        findViewById(R.id.game_LL2),
                        findViewById(R.id.game_LL3),
                        findViewById(R.id.game_LL4)));


        game_board_img_jeli = new AppCompatImageView[][]{
                {
                        findViewById(R.id.game_img_jeli1),
                        findViewById(R.id.game_img_jeli2),
                        findViewById(R.id.game_img_jeli3),
                },
                {
                        findViewById(R.id.game_img_jeli4),
                        findViewById(R.id.game_img_jeli5),
                        findViewById(R.id.game_img_jeli6),
                },
                {
                        findViewById(R.id.game_img_jeli7),
                        findViewById(R.id.game_img_jeli8),
                        findViewById(R.id.game_img_jeli9),
                },
                {
                        findViewById(R.id.game_img_jeli10),
                        findViewById(R.id.game_img_jeli11),
                        findViewById(R.id.game_img_jeli12),
                }
        };
        actor =  new ArrayList<>(
                Arrays. asList(findViewById(R.id.game_img_bob1),
                        findViewById(R.id.game_img_bob2),
                        findViewById(R.id.game_img_bob3)));
        right_btn = findViewById(R.id.game_btn_right);
        left_btn = findViewById(R.id.game_btn_left);
        rows = LinearLayouts.size();
        cols = actor.size();
        game_btn_tryAgain = findViewById(R.id.game_btn_tryAgain);
        game_btn_selectPlayer = findViewById(R.id.game_btn_selectPlayer);;
        game_lbl_score = findViewById(R.id.game_lbl_score);
    }
    private void initViews() {
        right_btn.setOnClickListener(v -> turnActor(RIGHT));
        left_btn.setOnClickListener(v -> turnActor(LEFT));
        game_btn_tryAgain.setOnClickListener(v -> tryAgain());
        game_btn_selectPlayer.setOnClickListener(v -> openSelectPlayer());
    }
    private void openSelectPlayer(){
        Intent intent = new Intent(GameActivity.this, PlayerSelectionActivity.class);
        startActivity(intent);
        finish();
    }
    private void setActor() {
        if(PlayerSelectionActivity.PlayerType == PlayerSelectionActivity.SPONGBOB || PlayerSelectionActivity.PlayerType == NONE){ /// Default value
            for (AppCompatImageView img : actor) {
                img.setImageResource(R.drawable.img_spongebob);
            }
        }
        if(PlayerSelectionActivity.PlayerType == PlayerSelectionActivity.PETRICK){
            for (AppCompatImageView img : actor) {
                img.setImageResource(R.drawable.img_petrick);
            }
        }
        if(PlayerSelectionActivity.PlayerType == PlayerSelectionActivity.SANDY){
            for (AppCompatImageView img : actor) {
                img.setImageResource(R.drawable.img_sandy);
            }
        }
        updateActor();
    }
    private void tryAgain() {
        left_btn.setVisibility(View.VISIBLE);
        right_btn.setVisibility(View.VISIBLE);
        game_btn_tryAgain.setVisibility(View.GONE);
        game_btn_selectPlayer.setVisibility(View.GONE);
        createGame();
        updateActor();
        updateGameBoard();
        updateLife();
        gameIsOver = false;
        startGame();
    }
    private void turnActor(boolean direction) {
        if(direction){
            game.turnRightActor();
        }else {
            game.turnLeftActor();
        }
        updateActor();
    }
    private void updateActor() {
        for (int i = 0; i < actor.size(); i++) {
            actor.get(i).setVisibility(game.getActorPlace() == i ? View.VISIBLE : View.INVISIBLE);
        }
    }
    private void updateGameBoard(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                game_board_img_jeli[i][j].setVisibility(game.getGameBoard()[i][j] == JELLYFISH ? View.VISIBLE : View.INVISIBLE);
            }
        }
    }
    private void updateLife(){
        for (AppCompatImageView heart: hearts) {
            heart.setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i < game.getLife(); i++) {
            hearts.get(i).setVisibility(View.VISIBLE);
        }
    }
    private void startGame() {
        gameIsStart = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int lastLineInBoard[] = new int [game.getColsGameBoard()];
                        System.arraycopy(game.getGameBoard()[game.getRowsGameBoard()-1], 0,lastLineInBoard , 0, game.getColsGameBoard());
                        if(game.collisionTest(lastLineInBoard)){
                            Toast.makeText(GameActivity.this, "Crash!", Toast.LENGTH_SHORT).show();
                            v.vibrate(400);
                            updateLife();
                        }
                        if(game.getLife() == 0) {
                            gameOver();
                        }
                        game.refreshGameBoard();
                        updateGameBoard();
                        ////TODO : remove game_lbl_score
                        game_lbl_score.setText("score : "+game.getScore());
                    }
                });
            }
        }, 0, SLOW);
    }
    private void gameOver() {
        gameIsOver = true;
        timer.cancel();
        left_btn.setVisibility(View.GONE);
        right_btn.setVisibility(View.GONE);
        Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
        game_btn_tryAgain.setVisibility(View.VISIBLE);
        game_btn_selectPlayer.setVisibility(View.VISIBLE);
    }
}