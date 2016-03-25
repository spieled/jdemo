package com.studease.jdemo.json.studytrails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: liushaoping
 * Date: 2016/3/25.
 */
public class Lion extends Animal {
    @JsonCreator
    public Lion(@JsonProperty("name") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Lion: " + name;
    }
}
