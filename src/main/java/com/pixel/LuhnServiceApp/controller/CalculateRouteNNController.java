package com.pixel.LuhnServiceApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixel.LuhnServiceApp.model.Route;
import com.pixel.LuhnServiceApp.model.requests.RouteRequest;
import com.pixel.LuhnServiceApp.model.responses.RouteResponse;
import com.pixel.LuhnServiceApp.service.CalculateRouteNNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CalculateRouteNNController {

    @Autowired
    private CalculateRouteNNService calculateRouteNNService;

    @PostMapping("/route")
    public ResponseEntity<String> calculateRoute(@RequestBody RouteRequest request) throws JsonProcessingException {

        if(!request.getListOfPoints().isEmpty()) {
            Route route = calculateRouteNNService.calculateRoute(request.getListOfPoints());
            ObjectMapper objMap = new ObjectMapper();
            RouteResponse response = new RouteResponse(route.getRoute(), route.getDistance());
            String stringResponse = objMap.writeValueAsString(response);

            return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Empty List of Points", HttpStatus.BAD_REQUEST);
    }
}
