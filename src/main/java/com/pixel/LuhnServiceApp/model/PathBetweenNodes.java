package com.pixel.LuhnServiceApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PathBetweenNodes {
    @Getter
    private final Node nodeA;
    @Getter
    private final Node nodeB;
    @Getter
    double distance;
}
