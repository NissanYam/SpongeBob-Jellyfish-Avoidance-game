package com.example.firstteskspongebob.activities;
import android.app.Application;
import com.example.firstteskspongebob.MSPV;
import com.example.firstteskspongebob.MySignal;
import com.example.firstteskspongebob.R;
import com.example.firstteskspongebob.logic.Player;
import com.example.firstteskspongebob.logic.TopTenPlayers;
import java.util.ArrayList;

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        MSPV.init(this);
        if(MSPV.getMe().readTopTenPlayers().getTopTen().size() == 0){
            MSPV.getMe().saveTopTenPlayers(new TopTenPlayers().setTopTen(creatDate()));
        }
        MySignal.init(this);
    }

    private ArrayList<Player> creatDate() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player().setName("Nissan").setImage(R.drawable.img_spongebob).setScore(100).setLat(20.63).setLng(15.2).setGameType(MainActivity.BUTTON_MODE_FAST).setSpeed(GameActivity.FAST));
        players.add(new Player().setName("Linoy").setImage(R.drawable.img_sandy).setScore(50).setLat(9.63).setLng(16.1232).setGameType(MainActivity.SENSOR_MODE).setSpeed(GameActivity.FAST));
        players.add(new Player().setName("AVI").setImage(R.drawable.img_petrick).setScore(80).setLat(5.63).setLng(25.1232).setGameType(MainActivity.BUTTON_MODE_SLOW).setSpeed(GameActivity.SLOW));
        players.add(new Player().setName("Koko").setImage(R.drawable.img_spongebob).setScore(10).setLat(5.063).setLng(5.1232).setGameType(MainActivity.BUTTON_MODE_SLOW).setSpeed(GameActivity.SLOW));
        players.add(new Player().setName("Moshe").setImage(R.drawable.img_petrick).setScore(50).setLat(15.163).setLng(15.1232).setGameType(MainActivity.BUTTON_MODE_SLOW).setSpeed(GameActivity.SLOW));
        players.add(new Player().setName("Yakov").setImage(R.drawable.img_petrick).setScore(8).setLat(45.163).setLng(35.1232).setGameType(MainActivity.BUTTON_MODE_SLOW).setSpeed(GameActivity.SLOW));
        players.add(new Player().setName("Roni").setImage(R.drawable.img_sandy).setScore(5).setLat(15.63).setLng(25.1232).setGameType(MainActivity.BUTTON_MODE_SLOW).setSpeed(GameActivity.SLOW));
        return players;
    }


}
