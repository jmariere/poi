package com.happn.poi.models;

import lombok.Data;

@Data
public class Poi {

    private String id;
    private double lon;
    private double lat;

    public Poi(String id, double lon, double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }

}
