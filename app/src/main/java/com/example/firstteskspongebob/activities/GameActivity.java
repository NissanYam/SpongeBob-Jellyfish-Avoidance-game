package com.example.firstteskspongebob.activities;

import static com.example.firstteskspongebob.logic.GameLogic.BUBBLE;
import static com.example.firstteskspongebob.logic.GameLogic.JELLYFISH;
import static com.example.firstteskspongebob.logic.GameLogic.NONE;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.firstteskspongebob.interfaces.CallBack_movement;
import com.example.firstteskspongebob.logic.GameLogic;
import com.example.firstteskspongebob.MySignal;
import com.example.firstteskspongebob.R;
import com.example.firstteskspongebob.MoveDetector;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    public static final String KEY_MODE = "KEY_MODE";
    public static final String KEY_PLAYER_IMAGE = "KEY_PLAYER_IMAGE";
    private ArrayList<LinearLayoutCompat> LinearLayouts;
    private ArrayList<AppCompatImageView> hearts;
    private AppCompatImageView[][] game_board_img;
    private ArrayList<AppCompatImageView> actor;
    private MaterialButton right_btn;
    private MaterialButton left_btn;
    private GameLogic game;
    private int rows ;
    private int cols ;
    public static final int SLOW = 800;
    public static final int FAST = 400;
    private int speed;
    private final boolean RIGHT = true;
    private final boolean LEFT = false;
    private Timer timer = null;
    private MaterialTextView game_lbl_score;
    private Bundle bundle;
    private float lastSensorNum;
    private MoveDetector moveDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViews();
        bundle = getIntent().getBundleExtra(MainActivity.KEY_BUNDLE);
        createGame();
        setActor();
        setGameType();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(bundle.getInt(KEY_MODE) == MainActivity.SENSOR_MODE)
            moveDetector.start();
        startGame();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(bundle.getInt(KEY_MODE) == MainActivity.SENSOR_MODE)
            moveDetector.stop();
        stopGame();
    }
    /** Set settings to game*/
    private void setActor() {
        int playerType = bundle.getInt(KEY_PLAYER_IMAGE);
        if(playerType == PlayerSelectionActivity.SPONGBOB || playerType == NONE){ /// Default value
            for (AppCompatImageView img : actor) {
                img.setImageResource(R.drawable.img_spongebob);
            }
        }
        if(playerType == PlayerSelectionActivity.PETRICK){
            for (AppCompatImageView img : actor) {
                img.setImageResource(R.drawable.img_petrick);
            }
        }
        if(playerType == PlayerSelectionActivity.SANDY){
            for (AppCompatImageView img : actor) {
                img.setImageResource(R.drawable.img_sandy);
            }
        }
        updateActor();
    }
    private void setGameType() {
        if(bundle.getInt(KEY_MODE) == MainActivity.SENSOR_MODE){
            /**switch to SENSOR_MODE*/
            setSpeed(FAST);
            right_btn.setVisibility(View.GONE);
            left_btn.setVisibility(View.GONE);
            lastSensorNum = 0;
            initSensors();
        }
        else{
            /**switch to BUTTON_MODE*/
            right_btn.setVisibility(View.VISIBLE);
            left_btn.setVisibility(View.VISIBLE);
            setSpeedByMode();
            initViews();
        }
    }
    private void initSensors() {
        this.moveDetector = new MoveDetector(this , new CallBack_movement() {
            @Override
            public void movePlayer(float currentSensorXval) {
                if (getPosition(lastSensorNum) != getPosition(currentSensorXval)) {
                    int lastPosition = getPosition(lastSensorNum).ordinal();
                    int newPosition = getPosition(currentSensorXval).ordinal();
                    int distance = lastPosition - newPosition;
                    if (distance > 0) {
                        for (; distance > 0; distance--)
                            turnActor(LEFT);
                    } else {
                        for (; distance < 0; distance++)
                            turnActor(RIGHT);
                    }
                }
                lastSensorNum = currentSensorXval;
            }
        });
    }
    private void setSpeedByMode() {
        int type = bundle.getInt(KEY_MODE);
        if(type == MainActivity.BUTTON_MODE_FAST)
            setSpeed(FAST);
        if(type == MainActivity.BUTTON_MODE_SLOW)
            setSpeed(SLOW);
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
    /** functions for sensors*/
    private void setSpeed(int speed){
        this.speed = speed;
    }
    public enum Position {
        Left,
        LeftCenter,
        Center,
        RightCenter,
        Right
    }
    private Position getPosition(float sensor) {
        if(sensor > 2.5)
            return Position.Left;
        if(2.5 > sensor && sensor > 1)
            return Position.LeftCenter;
        if(1 > sensor && sensor > -1)
            return Position.Center;
        if( -1 > sensor && sensor > -2.5)
            return Position.RightCenter;
        if( -2.5 > sensor)
            return Position.Right;
        return null;
    }
    /**
    * Set UI
    * */
    private void findViews() {
        hearts = new ArrayList<>(
                Arrays. asList(findViewById(R.id.game_img_heart1),
                        findViewById(R.id.game_img_heart2),
                        findViewById(R.id.game_img_heart3)));


        LinearLayouts = new ArrayList<>(
                Arrays. asList(findViewById(R.id.game_LL1),
                        findViewById(R.id.game_LL2),
                        findViewById(R.id.game_LL3),
                        findViewById(R.id.game_LL4),
                        findViewById(R.id.game_LL5),
                        findViewById(R.id.game_LL6)));


        game_board_img = new AppCompatImageView[][]{
                {
                        findViewById(R.id.game_img_11),
                        findViewById(R.id.game_img_12),
                        findViewById(R.id.game_img_13),
                        findViewById(R.id.game_img_14),
                        findViewById(R.id.game_img_15),
                },
                {
                        findViewById(R.id.game_img_21),
                        findViewById(R.id.game_img_22),
                        findViewById(R.id.game_img_23),
                        findViewById(R.id.game_img_24),
                        findViewById(R.id.game_img_25),
                },
                {
                        findViewById(R.id.game_img_31),
                        findViewById(R.id.game_img_32),
                        findViewById(R.id.game_img_33),
                        findViewById(R.id.game_img_34),
                        findViewById(R.id.game_img_35),
                },
                {
                        findViewById(R.id.game_img_41),
                        findViewById(R.id.game_img_42),
                        findViewById(R.id.game_img_43),
                        findViewById(R.id.game_img_44),
                        findViewById(R.id.game_img_45),
                },
                {
                        findViewById(R.id.game_img_51),
                        findViewById(R.id.game_img_52),
                        findViewById(R.id.game_img_53),
                        findViewById(R.id.game_img_54),
                        findViewById(R.id.game_img_55),
                },
                {
                        findViewById(R.id.game_img_61),
                        findViewById(R.id.game_img_62),
                        findViewById(R.id.game_img_63),
                        findViewById(R.id.game_img_64),
                        findViewById(R.id.game_img_65),
                },
        };
        actor =  new ArrayList<>(
                Arrays. asList(findViewById(R.id.game_img_bob1),
                        findViewById(R.id.game_img_bob2),
                        findViewById(R.id.game_img_bob3),
                        findViewById(R.id.game_img_bob4),
                        findViewById(R.id.game_img_bob5)));
        right_btn = findViewById(R.id.game_btn_right);
        left_btn = findViewById(R.id.game_btn_left);
        rows = LinearLayouts.size();
        cols = actor.size();
        game_lbl_score = findViewById(R.id.game_lbl_score);
    }
    private void initViews() {
        right_btn.setOnClickListener(v -> turnActor(RIGHT));
        left_btn.setOnClickListener(v -> turnActor(LEFT));
    }
    /**
    * Update UI
    * */
    private void turnActor(boolean direction) {
        if(direction){
            game.turnRightActor();
        }else {
            game.turnLeftActor();
        }
        updateActor();
    }
    private void updateActor() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < actor.size(); i++) {
                    actor.get(i).setVisibility(game.getActorPlace() == i ? View.VISIBLE : View.INVISIBLE);
                }
            }
        });

    }
    private void updateGameBoard(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        game_board_img[i][j].setVisibility(View.INVISIBLE);
                        if(game.getGameBoard()[i][j] == JELLYFISH){
                            game_board_img[i][j].setImageResource(R.drawable.jellyfish);
                            game_board_img[i][j].setVisibility(View.VISIBLE);
                        }
                        else if(game.getGameBoard()[i][j] == BUBBLE){
                            game_board_img[i][j].setImageResource(R.drawable.bubbel);
                            game_board_img[i][j].setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
    }
    private void updateLife(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < game.getLife(); i++) {
                    hearts.get(i).setVisibility(View.VISIBLE);
                }
                for(int i = game.getLife() ; i < hearts.size(); i ++){
                    hearts.get(i).setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    private void setScore() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                game_lbl_score.setText("score : "+game.getScore());
            }
        });
    }
    /**
     * The game running
    **/
    private void startGame() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkGame();
                game.refreshGameBoard();
                updateGameBoard();
                setScore();
            }
        }, speed, speed);

    }
    private void stopGame(){
        timer.cancel();
    }
    private void checkGame() {
        int lastLineInBoard[] = new int [game.getColsGameBoard()];
        System.arraycopy(game.getGameBoard()[game.getRowsGameBoard()-1], 0,lastLineInBoard , 0, game.getColsGameBoard()); /// take the last row in board and compare with actor
        if(game.collisionTest(lastLineInBoard)){
            MySignal.getInstance().toast("Crash!");
            MySignal.getInstance().vibrate(200);
            MySignal.getInstance().playSound(GameActivity.this,R.raw.bubbelssound);
            updateLife();
            if(game.getLife() == 0) {
                gameOver();
            }
        }
    }
    private void gameOver() {
        if(bundle.getInt(KEY_MODE) == MainActivity.SENSOR_MODE) {
            moveDetector.stop();
        }
        stopGame();
        MySignal.getInstance().toast("Game Over");
        Intent intent = new Intent(GameActivity.this, TopPlayersActivity.class);
        bundle.putInt(TopPlayersActivity.KEY_SCORE, game.getScore());
        bundle.putInt(TopPlayersActivity.KEY_SPEED,speed);
        intent.putExtra(MainActivity.KEY_BUNDLE, bundle);
        startActivity(intent);
        finish();
    }

}