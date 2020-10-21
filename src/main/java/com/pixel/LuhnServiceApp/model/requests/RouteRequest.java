package com.pixel.LuhnServiceApp.model.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pixel.LuhnServiceApp.model.Point;
import lombok.Getter;

import java.util.List;

public class RouteRequest {

    @Getter
    private List<Point> listOfPoints;

    @JsonCreator
    public RouteRequest(@JsonProperty(value = "listOfPoints", required = true) List<Point> listOfPoints) {
        this.listOfPoints = listOfPoints;
    }
}
