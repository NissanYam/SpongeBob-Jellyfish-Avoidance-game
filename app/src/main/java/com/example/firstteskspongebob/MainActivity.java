package com.example.firstteskspongebob;

import static com.example.firstteskspongebob.GameLogic.JELLYFISH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
//
//    private ArrayList<LinearLayoutCompat> LinearLayouts;
//    private ArrayList<AppCompatImageView> hearts;
//    private AppCompatImageView[][] game_board_img_jeli;
//    private ArrayList<AppCompatImageView> actor;
//    private MaterialButton right_btn;
//    private MaterialButton left_btn;
//    private GameLogic game;
//    private int rows ;
//    private int cols ;
//    private final int DELAY = 1000;
//
//
//    private final boolean RIGHT = true;
//    private final boolean LEFT = false;
//    private Handler handler = new Handler();
//
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViews();
//        createGame();
//        initViews();

    }
    
//
//    private void createGame() {
//        game = new GameLogic()
//                .setRowsGameBoard(rows)
//                .setColsGameBoard(cols)
//                .setLife(hearts.size());
//        game.setGameBoard(new int[game.getRowsGameBoard()][game.getColsGameBoard()])
//                .setActorBoard(new int [game.getColsGameBoard()])
//                .setActorPlace(game.getColsGameBoard()/2);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        startTimer();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//    private void findViews() {
//        hearts = new ArrayList<>(
//                Arrays. asList(findViewById(R.id.game_img_heart1),
//                        findViewById(R.id.game_img_heart2),
//                        findViewById(R.id.game_img_heart3)));
//
//
//        LinearLayouts = new ArrayList<>(
//                Arrays. asList(findViewById(R.id.game_LL1),
//                        findViewById(R.id.game_LL2),
//                        findViewById(R.id.game_LL3),
//                        findViewById(R.id.game_LL4)));
//
//
//        game_board_img_jeli = new AppCompatImageView[][]{
//                {
//                        findViewById(R.id.game_img_jeli1),
//                        findViewById(R.id.game_img_jeli2),
//                        findViewById(R.id.game_img_jeli3),
//                },
//                {
//                        findViewById(R.id.game_img_jeli4),
//                        findViewById(R.id.game_img_jeli5),
//                        findViewById(R.id.game_img_jeli6),
//                },
//                {
//                        findViewById(R.id.game_img_jeli7),
//                        findViewById(R.id.game_img_jeli8),
//                        findViewById(R.id.game_img_jeli9),
//                },
//                {
//                        findViewById(R.id.game_img_jeli10),
//                        findViewById(R.id.game_img_jeli11),
//                        findViewById(R.id.game_img_jeli12),
//                }
//        };
//        actor =  new ArrayList<>(
//                Arrays. asList(findViewById(R.id.game_img_bob1),
//                        findViewById(R.id.game_img_bob2),
//                        findViewById(R.id.game_img_bob3)));
//        right_btn = findViewById(R.id.game_btn_right);
//        left_btn = findViewById(R.id.game_btn_left);
//        rows = LinearLayouts.size();
//        cols = actor.size();
//    }
//
//    private void initViews() {
//        right_btn.setOnClickListener(v -> clicked(RIGHT));
//        left_btn.setOnClickListener(v -> clicked(LEFT));
//    }
//
//    private void clicked(boolean b) {
//        if(b){
//            game.turnRightActor();
//        }else {
//            game.turnLeftActor();
//        }
//        updateActor();
//    }
//
//    private void updateActor() {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < actor.size(); i++) {
//                    actor.get(i).setVisibility(game.getActorPlace() == i ? View.VISIBLE : View.INVISIBLE);
//                }
//            }
//        });
//
//    }
//    private void updateGameBoard(){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < rows; i++) {
//                    for (int j = 0; j < cols; j++) {
//                        game_board_img_jeli[i][j].setVisibility(game.getGameBoard()[i][j] == JELLYFISH ? View.VISIBLE : View.INVISIBLE);
//                    }
//                }
//            }
//        });
//    }
//
//    private void updateLife(){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                for (AppCompatImageView heart: hearts) {
//                    heart.setVisibility(View.INVISIBLE);
//                }
//                for (int i = 0; i < game.getLife(); i++) {
//                    hearts.get(i).setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//    }
//
//    private Runnable runnable = new Runnable() {
//        public void run() {
//            handler.postDelayed(runnable, DELAY);
//            int lastArrInBoard[] = new int [game.getColsGameBoard()];
//            System.arraycopy(game.getGameBoard()[game.getRowsGameBoard()-1], 0,lastArrInBoard , 0, game.getColsGameBoard());
//            game.refreshGameBoard();
//            if(game.collisionTest(lastArrInBoard)){
//                updateLife();
//            }
//            if(game.getLife() == 0){
//                gameOver();
//            }
//            updateGameBoard();
//        }
//    };
//
//    private void startTimer() {
//        handler.postDelayed(runnable, DELAY);
//    }
//
//    private void gameOver() {
//        handler.removeCallbacks(runnable);
//        Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
//    }
}