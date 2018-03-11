package com.grzechwa.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public abstract class District extends Card implements Comparable<District> {
    protected int districtCost;

    public District(String cardName,
                    String cardColor,
                    boolean isVisible,
                    int districtCost){
        super(cardName,cardColor,isVisible);
        this.districtCost = districtCost;
    }

    @Override
    public int compareTo(District district){
        return Integer.compare(districtCost,district.districtCost);
    }
}
