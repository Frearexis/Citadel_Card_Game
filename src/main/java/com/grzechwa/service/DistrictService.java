package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;

import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<District> coloredDistricts = new ArrayList<>();
        for(District district : districts){
            if(district.getCardColor().equals(player.getChoosenCharacter().getCardColor())){
                coloredDistricts.add(district);
            }
        }
        return coloredDistricts;
    }
}