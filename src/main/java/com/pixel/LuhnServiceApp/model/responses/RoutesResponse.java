package com.pixel.LuhnServiceApp.model.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RoutesResponse {

    private List<String> routes;
    private List<Double> distances;
}
