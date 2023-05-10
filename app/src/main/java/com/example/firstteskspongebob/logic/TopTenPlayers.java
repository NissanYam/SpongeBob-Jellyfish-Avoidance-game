package com.example.firstteskspongebob.logic;

import java.util.ArrayList;

public class TopTenPlayers {
    private ArrayList<Player> topTen;

    public TopTenPlayers() {
        this.topTen = new ArrayList<>();
    }

    public ArrayList<Player> getTopTen() {
        return topTen;
    }

    public TopTenPlayers setTopTen(ArrayList<Player> topTen) {
        this.topTen = topTen;
        return this;
    }
}
