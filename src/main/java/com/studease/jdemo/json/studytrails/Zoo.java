package com.studease.jdemo.json.studytrails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: liushaoping
 * Date: 2016/3/25.
 */
public class Zoo {
    private String name;
    private String city;
    private List<Animal> animals = new ArrayList<>();

    @JsonCreator
    public Zoo(@JsonProperty("name") String name, @JsonProperty("city") String city) {
        this.name = name;
        this.city = city;
    }

    public List<Animal> addAnimal(Animal animal) {
        animals.add(animal);
        return animals;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
