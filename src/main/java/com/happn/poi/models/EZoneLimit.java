package com.happn.poi.models;

import lombok.Getter;

public enum EZoneLimit {
    LON_MIN(-90),
    LON_MAX(90),
    LAT_MIN(-180),
    LAT_MAX(180);

    @Getter
    private int slot;

    EZoneLimit(int slot) {
        this.slot = slot;
    }

}
