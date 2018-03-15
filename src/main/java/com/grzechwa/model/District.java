package com.grzechwa.model;

import lombok.Getter;

public abstract class District extends Card implements Comparable<District> {
    @Getter
    protected int districtCost;

    public District(String cardName,
                    String cardColor,
                    boolean isVisible,
                    int districtCost){
        super(cardName,cardColor,isVisible);
        this.districtCost = districtCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof District)) return false;
        if (!super.equals(o)) return false;

        District district = (District) o;

        return districtCost == district.districtCost;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + districtCost;
        return result;
    }

    @Override
    public int compareTo(District district){
        return Integer.compare(districtCost,district.districtCost);
    }

    @Override
    public String toString() {
        return cardName+" "+districtCost;
    }
}
