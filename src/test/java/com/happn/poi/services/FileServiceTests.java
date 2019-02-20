package com.happn.poi.services;

import com.happn.poi.models.File;
import com.happn.poi.models.Zone;
import com.happn.poi.services.FileService;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTests {

	private final String ressourceDirectory = "src/test/resources/";

	/***
	 * test for to make sure the file converter work properly
	 * we try to parse a tsv file with 8 poi(s)
	 */
	@Test
	public void tryToConvertTSVFile() {
		//method to test
		File file = FileService.convert(Paths.get(ressourceDirectory + "test.tsv"));

		//make sure we convert all of 8 poi
		assertThat("incorrect nb POI converted", 8, is(file.getPoiList().size()));
	}

	/***
	 * test for to make sure the file converter work properly
	 * we try to parse a tsv file with only the header
	 */
	@Test
	public void tryToConvertEmptyTSVFile() {
		//method to test
		File file = FileService.convert(Paths.get(ressourceDirectory + "emptyTest.tsv"));

		//make sure we convert all of 8 poi
		assertThat("incorrect nb POI converted", 0, is(file.getPoiList().size()));
	}

	/***
	 * test for to make sure the file converter work properly
	 * we try to parse a tsv file with nothing inside
	 */
	@Test
	public void tryToConvertNullTSVFile() {
		//method to test
		File file = FileService.convert(Paths.get(ressourceDirectory + "nullTest.tsv"));

		//make sure we convert all of 8 poi
		assertThat("incorrect nb POI converted", 0, is(file.getPoiList().size()));
	}


	/***
	 * test for to make sure the file converter work properly
	 * we try to parse a tsv file with wrongs values (string instead number, wrong range etc...)
	 */
	@Test
	public void tryToConvertWrongTSVFile() {
		//method to test
		File file = FileService.convert(Paths.get(ressourceDirectory + "WrongTest.tsv"));

		//make sure we convert all of 8 poi
		assertThat("incorrect nb POI converted", 0, is(file.getPoiList().size()));
	}

}
