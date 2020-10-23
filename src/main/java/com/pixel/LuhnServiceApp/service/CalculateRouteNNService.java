package com.pixel.LuhnServiceApp.service;

import com.pixel.LuhnServiceApp.model.*;
import com.pixel.LuhnServiceApp.utils.Travese;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateRouteNNService {


    public Route calculateRoute(Node startNode, List<Node> nodes) {
        List<PathBetweenNodes> route = Travese.calculateRoute(startNode, nodes);
        double distance = 0;
        StringBuilder sRoute = new StringBuilder();
        sRoute.append(startNode.getName());
        sRoute.append("->");
        for (PathBetweenNodes path : route) {
            sRoute.append(path.getNodeB().getName());
            sRoute.append("->");
            distance += path.getDistance();
        }
        sRoute.delete(sRoute.length() - 2, sRoute.length());

        return new Route(sRoute.toString(), distance);
    }

    public List<Route> calculateRoutes(List<Node> nodes) {
        List<Route> routes = new ArrayList<>();
        for (Node n : nodes) {
            routes.add(calculateRoute(n, nodes));
        }
        return routes;
    }

    public List<Node> pointsToNodes(List<Point> listOfPoints) {
        List<Node> nodes = new ArrayList<>();
        for (Point p : listOfPoints) {
            nodes.add(new Node(p.getLat(), p.getLng(), p.getName()));
        }
        for (Node n : nodes) {
            n.calculateDistances(nodes);
        }
        return nodes;
    }
}
