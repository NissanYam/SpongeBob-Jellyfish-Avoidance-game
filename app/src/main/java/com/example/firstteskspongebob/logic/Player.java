package com.example.firstteskspongebob.logic;

public class Player{
    private String Name;
    private int score;
    private int gameType;
    private int speed;
    private Double lat;
    private Double lng;

    private int image;
    public Player() {
    }
    public String getName() {
        return Name;
    }

    public Player setName(String name) {
        Name = name;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Player setScore(int score) {
        this.score = score;
        return this;
    }

    public int getGameType() {
        return gameType;
    }

    public Player setGameType(int gameType) {
        this.gameType = gameType;
        return this;
    }

    public int getSpeed() {
        return speed;
    }

    public Player setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public Double getLat() {
        return lat;
    }

    public Player setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public Player setLng(Double lng) {
        this.lng = lng;
        return this;
    }

    public int getImage(){
        return this.image;
    }
    public Player setImage(int image){
        this.image = image;
        return this;
    }

    @Override
    public String toString() {
        return "Player{" +
                "Name='" + Name + '\'' +
                ", score=" + score +
                ", gameType=" + gameType +
                ", speed=" + speed +
                ", lat=" + lat +
                ", lng=" + lng +
                ", image=" + image +
                '}';
    }
}
