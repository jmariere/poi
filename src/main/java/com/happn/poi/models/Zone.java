package com.happn.poi.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class Zone {

    private double min_lat;
    private double max_lat;
    private double min_lon;
    private double max_lon;

    /**
     * Construct zone with a lon and a lat
     * we round lon and lat for get the correct zone value
     *
     * @param lon is the minimal lon
     * @param lat is the minimal lat
     */
    public Zone(double lon, double lat) {
        this.min_lat = poiRounded(lat, lat < 0) ;
        this.min_lon = poiRounded(lon, lon < 0);
        this.max_lat = this.min_lat + 0.5d;
        this.max_lon = this.min_lon + 0.5d;
    }

    /***
     * round to nearest multiple of 0.5 down
     *
     * exemple :
     * -48.6 -48.5
     *-27.1 -27.0
     * 6.6 6.5
     * -2.3 -2.0
     * 6.8 6.5
     * -2.5 -2.5
     * 0.1 0.0
     * -2.1 2.0
     *
     * @param value that want round
     * @return the rounded value
     */
    private double poiRounded(double value, boolean down) {
        if (down) {
            value = -(0.5*(Math.ceil(Math.abs(value/0.5))));
        } else {
            value -= value % 0.5;
        }
        return value;
    }

}
