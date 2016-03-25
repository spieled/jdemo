package com.studease.jdemo.json.studytrails;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * Author: liushaoping
 * Date: 2016/3/25.
 */
public class SerializeZoo {
    public static void main(String[] args) throws IOException {
        Zoo zoo = new Zoo("London Zoo", "London");
        Lion lion = new Lion("Simba");
        Elephant elephant = new Elephant("Manny");
        zoo.addAnimal(lion).add(elephant);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(System.out, zoo);
    }
}
