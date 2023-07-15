package com.example.countryborders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Region {

    AFRICA(0),
    ASIA(0),
    EUROPE(0),

    AMERICAS(1),
    ANTARCTIC(2),
    OCEANIA(3);

    private int group;

    public boolean connectedByLandWith(Region region) {
        if (this == region) {
            return true;
        }
        if (this.group == region.getGroup()) {
            return true;
        }

        return false;
    }
}
