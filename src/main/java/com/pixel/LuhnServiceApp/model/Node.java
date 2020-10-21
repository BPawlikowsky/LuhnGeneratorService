package com.pixel.LuhnServiceApp.model;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import lombok.Getter;

import java.util.*;

public class Node {
    @Getter
    private HashMap<Double, Node> distances;
    @Getter
    private final double latitude;
    @Getter
    private final double longitude;
    @Getter
    private final String name;

    public Node(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.distances = new HashMap<>();
    }

    public void calculateDistances(List<Node> nodes) {
        HashMap<Double, Node> dist = new HashMap<>();
        SortedMap<Double, Node> temp = new TreeMap<>();
        //geocalc's data types for converting lat and lng in WGS84 to the internal coordinate system
        //Point will help to calculate distances between locations
        Coordinate originLat = Coordinate.fromDegrees(latitude);
        Coordinate originLng = Coordinate.fromDegrees(longitude);
        Point origin = Point.at(originLat, originLng);

        for (Node node : nodes) {
            if(!node.getName().equals(name)) {
                Coordinate nodeLat = Coordinate.fromDegrees(node.latitude);
                Coordinate nodeLng = Coordinate.fromDegrees(node.longitude);
                Point pNode = Point.at(nodeLat, nodeLng);
                double distance = EarthCalc.gcd.distance(origin, pNode); //calculated in meters
                temp.put(distance, node);
            }
        }
        for (Map.Entry<Double, Node> entry : temp.entrySet()) {
            dist.put(entry.getKey(), entry.getValue());
        }
        distances = dist;
    }


}
