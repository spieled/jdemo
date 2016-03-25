package com.studease.jdemo.json.studytrails;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Author: liushaoping
 * Date: 2016/3/25.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({@JsonSubTypes.Type(value = Lion.class, name = "lion"), @JsonSubTypes.Type(value = Elephant.class, name = "elephant")})
public abstract class Animal {
    protected String name;

    public String getName() {
        return name;
    }
}
