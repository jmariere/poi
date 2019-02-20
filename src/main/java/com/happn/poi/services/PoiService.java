package com.happn.poi.services;

import com.happn.poi.models.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

@Component
public class PoiService {

    @Autowired
    private File file;

    /**
     * Compute the number of POIs of an area
     *
     * @param minLat is lat of area
     * @param minLon is lon of area
     * @return the POIs number for the given area
     */
    public long getNumberByZone(double minLat, double minLon) {
        if (CollectionUtils.isEmpty(file.getPoiList())) return 0;

        return file.getPoiList().stream().filter(poi -> poi.getLat() > minLat && poi.getLon() > minLon).count();
    }


    /**
     * Find the N densest areas
     *
     * @param nbZone is the number of zone we want
     * @return @{@link Set} of @{@link Zone}
     */
    public Set<Zone> getBetterZone(int nbZone) {
        if (CollectionUtils.isEmpty(file.getPoiList())) return new HashSet<>();

        Map<Zone, List<Poi>> zoneWithPoiList = new HashMap<>();

        //we collect all poi by zone
        for (Poi poi : file.getPoiList()) {
            Zone poiZoned = new Zone(poi.getLon(), poi.getLat());
            zoneWithPoiList.computeIfAbsent(poiZoned, k -> new ArrayList<>());
            zoneWithPoiList.get(poiZoned).add(poi);
        }

        //sort map by number of Poi by zone and only the N better
        Map<Zone, List<Poi>> sorted = zoneWithPoiList.entrySet().stream()
                .sorted(comparingInt(e->((Map.Entry<Poi, List<Poi>>)e).getValue().size()).reversed())
                .limit(nbZone)
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b) -> {throw new AssertionError();},
                        LinkedHashMap::new
                ));

        //return list of better zone
        return sorted.keySet();
    }


}
