package com.happn.poi.services;

import com.happn.poi.models.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    /***
     * Convert TSV file to @{@link File} and put all poi into @{@link List} of @{@link Poi}
     * @param path is the TSV file
     * @return @{@link File} with all the values converted
     */
    public static File convert(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);

            File file = new File();
            file.setPoiList(new ArrayList<>());

            //remove the header
            lines.remove(0);

            for(String line : lines) {
                //for to be sure I replace 2 or more spaces with single space in string and delete leading and trailing spaces
                line = line.trim().replaceAll(" +", " ");

                //I put all the value into a array
                String value[] = line.split(" ");

                //Incorrect line if the size is different to 3
                if (value.length != 3) {
                    continue;
                }

                //Incorrect line if lat or lon are not a number
                if (!isNumeric(value[1])|| !isNumeric(value[2])) {
                    continue;
                }
                double lon = Double.valueOf(value[1]);
                double lat = Double.valueOf(value[2]);

                //Incorrect range for lat value
                if (lat < EZoneLimit.LAT_MIN.getSlot()  || lat > EZoneLimit.LAT_MAX.getSlot()){
                    continue;
                }

                //Incorrect range for lon value
                if (lon < EZoneLimit.LON_MIN.getSlot()  || lon > EZoneLimit.LON_MAX.getSlot()){
                    continue;
                }

                //I put all into my array
                file.getPoiList().add(new Poi(value[0], lon, lat));
            }

            return file;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * check if string value is number
     * @param str is checked value
     * @return @boolean is true if is number
     */
    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
