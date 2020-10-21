package com.pixel.LuhnServiceApp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Point {

    double lat;
    double lng;
    String name;

    @JsonCreator
    public Point(
            @JsonProperty(value = "lat", required = true) double lat,
            @JsonProperty(value = "lng", required = true) double lng,
            @JsonProperty(value = "name", required = true) String name
                 ) {
                this.lat = lat;
                this.lng = lng;
                this.name = name;
    }
}
