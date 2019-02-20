package com.happn.poi.services;

import com.happn.poi.models.Zone;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoiServiceTest {

    @Autowired
    private PoiService poiService;

    /***
     * test for to make sure we got the good number of poi for a specific zone
     */
    @Test
    public void tryToGetNumberByZone() {
        //method to test
        long nbZone = poiService.getNumberByZone(6.5, -7);

        //make sure we got 2
        assertThat("incorrect nbZone", nbZone, is(2L));
    }

    /***
     * test for to make sure we got the good better density zone
     */
    @Test
    public void tryToGetBetterZone() {
        //method to test
        Set<Zone> zones = poiService.getBetterZone(1);

        //make sure the first is Zone(min_lat=-2.5, max_lat=-2.0, min_lon=38.0, max_lon=38.5)
        assertThat("incorrect Zone", zones.iterator().next(),
            is(Zone.builder().min_lat(-2.5).max_lat(-2.0).min_lon(38.0).max_lon(38.5).build()));

    }
}
