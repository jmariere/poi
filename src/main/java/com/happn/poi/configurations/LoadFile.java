package com.happn.poi.configurations;

import com.happn.poi.models.File;
import com.happn.poi.services.FileService;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * This class is started automatically when the application
 * starts to put the values of the files into memory
 */
@Configuration
public class LoadFile {

    @Autowired
    private File file;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {

            //convert file
            File fileConverted =  FileService.convert(Paths.get(this.getClass().getClassLoader().getResource("poi.tsv").toURI()));

            //put all poi into file session
            file.setPoiList(new ArrayList<>(fileConverted.getPoiList()));
        };
    }

}
