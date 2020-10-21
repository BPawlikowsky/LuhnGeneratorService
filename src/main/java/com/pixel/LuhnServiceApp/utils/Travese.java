package com.pixel.LuhnServiceApp.utils;

import com.pixel.LuhnServiceApp.model.Node;
import com.pixel.LuhnServiceApp.model.PathBetweenNodes;

import java.util.*;

public class Travese {

    public static LinkedList<PathBetweenNodes> calculateRoute(Node startNode, List<Node> nodes) {
        LinkedList<PathBetweenNodes> route = new LinkedList<>();
        HashSet<Node> visitedNodes = new HashSet<>();
        Node visiting = startNode;

        boolean unvisitedLeft = true;
        while (unvisitedLeft) {
            double distance;
            int ittr = 0;
            //Distances from closest to furthest, also keys to Nodes
            List<Double> distances = new ArrayList<>(visiting.getDistances().keySet());

            //Checking if the closest node was visited
            distance = distances.get(ittr);
            Node checknode = visiting.getDistances().get(distance);
            while (visitedNodes.contains(checknode)) {
                ittr++;
                //Checks if all nodes has been visited
                if(ittr == distances.size()) {
                    unvisitedLeft = false;
                    route.add(new PathBetweenNodes(visiting, startNode, distance));
                    break;
                }

                distance = distances.get(ittr);
                checknode = visiting.getDistances().get(distance);
            }

            //If there are any nodes to visit
            if(unvisitedLeft) {
                //Set next node to be visited
                Node next = visiting.getDistances().get(distance);
                route.add(new PathBetweenNodes(visiting, next, distance));

                //Mark as visited
                visitedNodes.add(visiting);

                //Traverse
                visiting = next;
            }
        }

        return route;
    }
}
