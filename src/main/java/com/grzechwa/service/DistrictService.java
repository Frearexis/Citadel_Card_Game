package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DistrictService {

    public District getCheapestDistrict(ArrayList<District> districts){
        Collections.sort(districts);
        return  districts.get(0);
    }

    public District getMostExpensiveDistrict(ArrayList<District> districts){
        Collections.sort(districts,Collections.reverseOrder());
        return districts.isEmpty() ? null : districts.get(0);
    }

    public ArrayList<District> getPlayerColorDistricts(Player player, ArrayList<District> districts){
        return districts.stream()
                .filter(d -> d.getCardColor().equals(player.getChoosenCharacter().getCardColor()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}