package com.pixel.LuhnServiceApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixel.LuhnServiceApp.model.Node;
import com.pixel.LuhnServiceApp.model.Route;
import com.pixel.LuhnServiceApp.model.requests.RouteRequest;
import com.pixel.LuhnServiceApp.model.responses.RouteResponse;
import com.pixel.LuhnServiceApp.model.responses.RoutesResponse;
import com.pixel.LuhnServiceApp.service.CalculateRouteNNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CalculateRouteNNController {

    @Autowired
    private CalculateRouteNNService calculateRouteNNService;

    @PostMapping("/route")
    public ResponseEntity<String> calculateRoute(@RequestBody RouteRequest request) throws JsonProcessingException {

        if(!request.getListOfPoints().isEmpty() || request.getListOfPoints().size() <= 1) {
            List<Node> nodes = calculateRouteNNService.pointsToNodes(request.getListOfPoints());
            Route route = calculateRouteNNService.calculateRoute(nodes.get(0), nodes);
            ObjectMapper objMap = new ObjectMapper();
            RouteResponse response = new RouteResponse(route.getRoute(), route.getDistance());
            String stringResponse = objMap.writeValueAsString(response);

            return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Empty or one item List of Points", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/routes")
    public ResponseEntity<String> calculateRoutes(@RequestBody RouteRequest request) throws JsonProcessingException {

        if(!request.getListOfPoints().isEmpty() || request.getListOfPoints().size() <= 1) {
            List<Node> nodes = calculateRouteNNService.pointsToNodes(request.getListOfPoints());
            List<Route> routes = calculateRouteNNService.calculateRoutes(nodes);
            List<String> sRoutes = new ArrayList<>();
            List<Double> distances = new ArrayList<>();
            ObjectMapper objMap = new ObjectMapper();

            for (Route r : routes) {
                sRoutes.add(r.getRoute());
                distances.add(r.getDistance());
            }
            RoutesResponse response= new RoutesResponse(sRoutes, distances);
            String stringResponse = objMap.writeValueAsString(response);

            return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Empty or one item List of Points", HttpStatus.BAD_REQUEST);
    }
}
