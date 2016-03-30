package com.studease.jdemo.json.studytrails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: liushaoping
 * Date: 2016/3/25.
 */
public class Horse extends Animal {
    @JsonCreator
    public Horse(@JsonProperty("name") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Horse: " + name;
    }
}
