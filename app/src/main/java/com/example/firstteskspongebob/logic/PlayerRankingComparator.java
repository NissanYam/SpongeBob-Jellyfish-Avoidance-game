package com.example.firstteskspongebob.logic;

import com.example.firstteskspongebob.logic.Player;

import java.util.Comparator;

public class PlayerRankingComparator implements Comparator<Player> {

    @Override
    public int compare(Player firstPlayer, Player secondPlayer) {
        if(firstPlayer.getScore() > secondPlayer.getScore())
            return -1;
        else if (firstPlayer.getScore() < secondPlayer.getScore())
            return 1;
        return 0;
    }

}
