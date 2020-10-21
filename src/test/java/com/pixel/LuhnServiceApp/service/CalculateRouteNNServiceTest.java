package com.pixel.LuhnServiceApp.service;

import com.pixel.LuhnServiceApp.model.Node;
import com.pixel.LuhnServiceApp.model.Point;
import com.pixel.LuhnServiceApp.model.Route;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CalculateRouteNNServiceTest {

    @Mock
    private CalculateRouteNNService calculateRouteNNService;

    @Test
    public void calculateRoute_OK() {
        Route expectedResponse = new Route(
                "A->D->E->C->B->A",
                8906.77392246409
        );
        List<Node> request = List.of(
                new Node(51.0, 0.2, "A"),
                new Node(41.0, 1.2, "B"),
                new Node(31.0, 2.2, "C"),
                new Node(21.0, 3.2, "D"),
                new Node(11.0, 4.2, "E")
        );
        when(calculateRouteNNService.calculateRoute(request.get(0), request)).thenReturn(expectedResponse);
        Route actualResponse = calculateRouteNNService.calculateRoute(request.get(0), request);
        assertEquals(expectedResponse, actualResponse);
    }
}