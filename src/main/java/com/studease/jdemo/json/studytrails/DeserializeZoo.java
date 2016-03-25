package com.studease.jdemo.json.studytrails;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * Author: liushaoping
 * Date: 2016/3/25.
 */
public class DeserializeZoo {
    public static void main(String[] args) throws IOException {
        String json = "{\"name\":\"London Zoo\",\"city\":\"London\",\"animals\":[{\"@class\":\"com.studease.jdemo.json.studytrails.Lion\",\"name\":\"Simba\"},{\"@class\":\"com.studease.jdemo.json.studytrails.Elephant\",\"name\":\"Manny\"}]}";
        ObjectMapper mapper = new ObjectMapper();
        final Zoo zoo = mapper.readValue(json, Zoo.class);
        mapper.writeValue(System.out, zoo);
    }
}
