package com.happn.poi.controllers;

import com.happn.poi.exceptions.BadRequestException;
import com.happn.poi.models.EZoneLimit;
import com.happn.poi.models.Zone;
import com.happn.poi.services.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static java.util.Objects.isNull;

@RestController
public class PoiController {

    @Autowired
    private PoiService poiService;

    /***
     * get number of poi for a specific zone
     * @param min_lat is lat we want to check
     * @param min_lon is lon we want to check
     * @return Poi number for this area
     */
    @RequestMapping("/numberByZone")
    public long numberByZone(@RequestParam(value="min_lat") Double min_lat, @RequestParam(value="min_lon") Double min_lon) {

        //validity part
        if (isNull(min_lat) || isNull(min_lon))
            throw new BadRequestException("min_lat and min_lon are mandatory");
        if (min_lat < EZoneLimit.LAT_MIN.getSlot() || min_lat > EZoneLimit.LAT_MAX.getSlot())
            throw new BadRequestException("min_lat must to between " + EZoneLimit.LAT_MIN.getSlot() + " and " + EZoneLimit.LAT_MAX.getSlot());
        if (min_lon < EZoneLimit.LON_MIN.getSlot() || min_lon > EZoneLimit.LON_MAX.getSlot())
            throw new BadRequestException("min_lon must to between " + EZoneLimit.LON_MIN.getSlot() + " and " + EZoneLimit.LON_MAX.getSlot());

        //get number of poi for a specific zone
        return poiService.getNumberByZone(min_lat,min_lon);
    }

    /***
     * get the N densest areas
     * @param nb_zone is N densest areas we want
     * @return the area we ask
     */
    @RequestMapping("/betterZone")
    public Set<Zone> betterZone(@RequestParam(value="nb_zone") Integer nb_zone) {

        //validity part
        if (isNull(nb_zone))
            throw new BadRequestException("nb_zone is mandatory");
        if (nb_zone < 0)
            throw new BadRequestException("nb_zone must be positive");

        //get the N densest areas
        return poiService.getBetterZone(nb_zone);
    }

}
