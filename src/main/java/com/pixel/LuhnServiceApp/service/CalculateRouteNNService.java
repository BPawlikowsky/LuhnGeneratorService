package com.pixel.LuhnServiceApp.service;

import com.pixel.LuhnServiceApp.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateRouteNNService {


    public Route calculateRoute(List<Point> listOfPoints) {

        List<Node> nodes = new ArrayList<>();
        for (Point p : listOfPoints) {
            nodes.add(new Node(p.getLat(), p.getLng(), p.getName()));
        }
        for (Node n : nodes) {
            n.calculateDistances(nodes);
        }

        List<PathBetweenNodes> route = Travese.calculateRoute(nodes.get(0), nodes);
        double distance = 0;
        StringBuilder sRoute = new StringBuilder();
        for (PathBetweenNodes path : route) {
            sRoute.append(path.getNodeA().getName());
            sRoute.append("->");
            sRoute.append(path.getNodeB().getName());
            sRoute.append("->");
            distance += path.getDistance();
        }
        return new Route(sRoute.toString(), distance);
    }
}
