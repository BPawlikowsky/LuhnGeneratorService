package com.pixel.LuhnServiceApp.model;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Node {

    Logger logger = LoggerFactory.getLogger(Node.class);
    @Getter
    private SortedMap<Double, Node> distances;
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
        this.distances = new TreeMap<>();
    }

    public void calculateDistances(List<Node> nodes) {

        SortedMap<Double, Node> temp = new TreeMap<>();
        //geocalc's data types for converting lat and lng in WGS84 to the internal coordinate system
        //Point will help to calculate distances between locations
        Coordinate originLat = Coordinate.fromDegrees(latitude);
        Coordinate originLng = Coordinate.fromDegrees(longitude);
        Point origin = Point.at(originLat, originLng);

        for (Node node : nodes) {
            if(!node.getName().equals(name)) {
                logger.info("Node: " + name + " : " + node.getName());
                Coordinate nodeLat = Coordinate.fromDegrees(node.latitude);
                Coordinate nodeLng = Coordinate.fromDegrees(node.longitude);
                Point pNode = Point.at(nodeLat, nodeLng);
                double distance = EarthCalc.gcd.distance(origin, pNode); //calculated in meters
                distance /= 1000; // to kilometers
                temp.put(distance, node);
            }
        }

        temp.forEach((key, value) -> logger.info(key.toString() + " " + value.getName()));
        distances = temp;
    }
}
