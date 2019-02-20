package com.happn.poi;

import com.happn.poi.models.Zone;
import com.happn.poi.services.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoiApplicationTests {

	@Test
	public void contextLoads() {
	}

    /***
     * test for to make sure the poi value return the correct zone
     */
	@Test
	public void createZoneAndCheckIfTheRoundIsCorrect() {

		//38.3  -2.3  must be give [min_lat: -2.5 ; max_lat: -2 ; min_lon:38 ; max_lon: 38.5]
		Zone zone = new Zone(38.3, -2.3 );
		assertThat("incorrect min_lat", zone.getMin_lat(), is(-2.5));
		assertThat("incorrect max_lat", zone.getMax_lat(), is(-2.0));
		assertThat("incorrect min_lon", zone.getMin_lon(), is(38.0));
		assertThat("incorrect max_lat", zone.getMax_lon(), is(38.5));

		//-6.9  6.8 must be give [min_lat: 6.5 ; max_lat: 7 ; min_lon:-7 ; max_lon: -6.5]
		zone = new Zone(-6.9, 6.8 );
		assertThat("incorrect min_lat", zone.getMin_lat(), is(6.5));
		assertThat("incorrect max_lat", zone.getMax_lat(), is(7.0));
		assertThat("incorrect min_lon", zone.getMin_lon(), is(-7.0));
		assertThat("incorrect max_lat", zone.getMax_lon(), is(-6.5));
	}

}
