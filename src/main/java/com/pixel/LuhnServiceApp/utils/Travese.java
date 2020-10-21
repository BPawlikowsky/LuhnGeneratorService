package com.pixel.LuhnServiceApp.utils;

import com.pixel.LuhnServiceApp.model.Node;
import com.pixel.LuhnServiceApp.model.PathBetweenNodes;

import java.util.*;

public class Travese {

    public static LinkedList<PathBetweenNodes> calculateRoute(Node startNode, List<Node> nodes) {
        LinkedList<PathBetweenNodes> route = new LinkedList<>();
        HashSet<Node> visitedNodes = new HashSet<>();
        Node visitingNode = startNode;

        boolean unvisited = true;
        while (unvisited) {
            double distance;
            int ittr = 0;
            List<Double> distances = new ArrayList<>(visitingNode.getDistances().keySet());
            distance = distances.get(ittr);
            Node checknode = visitingNode.getDistances().get(distance);
            while (visitedNodes.contains(checknode)) {

                ittr++;
                if(ittr == distances.size()) {
                    unvisited = false;
                    route.add(new PathBetweenNodes(visitingNode, startNode, distance));
                    break;
                }

                distance = distances.get(ittr);
                checknode = visitingNode.getDistances().get(distance);
            }
            if(unvisited) {
                Node nextNode = visitingNode.getDistances().get(distance);
                route.add(new PathBetweenNodes(visitingNode, nextNode, distance));

                visitedNodes.add(visitingNode);

                visitingNode = nextNode;
            }
        }

        return route;
    }


    public static void main(String[] args) {
        Node n1 = new Node(51.0, -0.29, "A");
        Node n2 = new Node(55.0, 2.0, "B");
        Node n3 = new Node(25.0, -2.0, "C");
        Node n4 = new Node(45.0, 2.0, "D");
        Node n5 = new Node(15.0, -2.0, "E");

        List<Node> nodes = List.of(n1, n2, n3, n4, n5);
        nodes.stream().forEach(n -> {
            n.calculateDistances(nodes);
        });

        for(Node n : nodes){
            LinkedList<PathBetweenNodes> route = Travese.calculateRoute(n, nodes);
        }

    }
}
