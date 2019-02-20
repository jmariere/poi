package com.happn.poi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class File {
    private List<Poi> poiList;
}
