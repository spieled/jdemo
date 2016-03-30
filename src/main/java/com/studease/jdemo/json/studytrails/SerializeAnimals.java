package com.studease.jdemo.json.studytrails;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: liushaoping
 * Date: 2016/3/25.
 */
public class SerializeAnimals {
    public static void main(String[] args) throws IOException {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Lion("Samba"));
        animals.add(new Elephant("Manny"));
        animals.add(new Horse("Jesse"));
        ObjectMapper mapper = new ObjectMapper();
        // mapper.writeValue(System.out, animals);
        mapper.writerFor(new TypeReference<List<Animal>>() {
        }).writeValue(System.out, animals);
    }
}
