package com.grzechwa.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public class District extends Card {
    private int districtCost;

    public District(String cardName,
                    String cardColor,
                    boolean isVisible,
                    int districtCost){
        super(cardName,cardColor,isVisible);
        this.districtCost = districtCost;
    }
}
