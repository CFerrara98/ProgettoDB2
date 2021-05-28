package com.example.progettodb22.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Objects;

@Document
public class Location {
    private String type;
    private Double[] coordinates;

    public Location(){

    }


    public String getType() {
        return type;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(type, location.type) && Arrays.equals(coordinates, location.coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type);
        result = 31 * result + Arrays.hashCode(coordinates);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
