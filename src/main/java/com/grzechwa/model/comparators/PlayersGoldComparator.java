package com.grzechwa.model.comparators;

import com.grzechwa.model.Player;

import java.util.Comparator;

public class PlayersGoldComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if(o2 == null) return -1;
        if(o1.getPlayerGold() > o2.getPlayerGold()) return 1;
        else if(o1.getPlayerGold() < o2.getPlayerGold()) return -1;
        else return 0;
    }
}
