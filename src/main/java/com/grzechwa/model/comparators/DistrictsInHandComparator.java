package com.grzechwa.model.comparators;

import com.grzechwa.model.Player;

import java.util.Comparator;

public class DistrictsInHandComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if(o2 == null) return -1;
        if(o1.getDistrictsInHand().size() > o2.getDistrictsInHand().size()) return 1;
        else if(o1.getDistrictsInHand().size() < o2.getDistrictsInHand().size()) return -1;
        else return 0;
    }
}
