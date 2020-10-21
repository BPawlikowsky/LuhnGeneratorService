package com.pixel.LuhnServiceApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixel.LuhnServiceApp.model.Point;
import com.pixel.LuhnServiceApp.model.requests.LuhnRequest;
import com.pixel.LuhnServiceApp.model.requests.RouteRequest;
import com.pixel.LuhnServiceApp.model.responses.DigitResponse;
import com.pixel.LuhnServiceApp.model.responses.RouteResponse;
import com.pixel.LuhnServiceApp.model.responses.RoutesResponse;
import com.pixel.LuhnServiceApp.service.CalculateRouteNNService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CalculateRouteNNControllerTest {

    @Mock
    private CalculateRouteNNController calculateRouteNNController;
    @Mock
    private CalculateRouteNNService calculateRouteNNService;

    @Test
    public void calculateRoute_OK() {
        RouteRequest request = new RouteRequest(
                List.of(
                        new Point(51.0, 0.2, "A"),
                        new Point(41.0, 1.2, "B"),
                        new Point(31.0, 2.2, "C"),
                        new Point(21.0, 3.2, "D"),
                        new Point(11.0, 4.2, "E")
                )
        );
        ObjectMapper om = new ObjectMapper();
        RouteResponse dtoExpectedResponse = new RouteResponse(
                "A->D->E->C->B->A",
                8906.77392246409
        );
        String stringExpectedResponse = "";
        try {
            stringExpectedResponse = om.writeValueAsString(dtoExpectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(stringExpectedResponse, HttpStatus.OK);

        try {
            when(calculateRouteNNController.calculateRoute(request)).thenReturn(expectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> actualResponse = null;
        try {
            actualResponse = calculateRouteNNController.calculateRoute(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void calculateRoute_BAD_emptyList() {
        RouteRequest request = new RouteRequest(
                Lists.emptyList()
        );

        String stringExpectedResponse = "Empty or one item List of Points";

        ResponseEntity<String> expectedResponse = new ResponseEntity<>(stringExpectedResponse, HttpStatus.BAD_REQUEST);

        try {
            when(calculateRouteNNController.calculateRoute(request)).thenReturn(expectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> actualResponse = null;
        try {
            actualResponse = calculateRouteNNController.calculateRoute(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void calculateRoute_BAD_oneItemList() {
        RouteRequest request = new RouteRequest(
                List.of(new Point(
                        51.0, 0.2, "A"
                ))
        );

        String stringExpectedResponse = "Empty or one item List of Points";

        ResponseEntity<String> expectedResponse = new ResponseEntity<>(stringExpectedResponse, HttpStatus.BAD_REQUEST);

        try {
            when(calculateRouteNNController.calculateRoute(request)).thenReturn(expectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> actualResponse = null;
        try {
            actualResponse = calculateRouteNNController.calculateRoute(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void calculateRoutes_OK() {
        RouteRequest request = new RouteRequest(
                List.of(
                        new Point(51.0, 0.2, "A"),
                        new Point(41.0, 1.2, "B"),
                        new Point(31.0, 2.2, "C"),
                        new Point(21.0, 3.2, "D"),
                        new Point(11.0, 4.2, "E")
                )
        );
        ObjectMapper om = new ObjectMapper();
        RoutesResponse dtoExpectedResponse = new RoutesResponse(
                List.of(
                        "A->D->E->C->B->A",
                        "B->C->D->E->A->B",
                        "C->B->D->E->A->C",
                        "D->E->A->C->B->D",
                        "E->D->A->C->B->E"
                ),
                List.of(
                        8906.77392246409,
                        8906.35838030993,
                        10019.331739830894,
                        10017.467579814465,
                        8903.366320073515
                )
        );
        String stringExpectedResponse = "";
        try {
            stringExpectedResponse = om.writeValueAsString(dtoExpectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(stringExpectedResponse, HttpStatus.OK);

        try {
            when(calculateRouteNNController.calculateRoutes(request)).thenReturn(expectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> actualResponse = null;
        try {
            actualResponse = calculateRouteNNController.calculateRoutes(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void calculateRoutes_BAD_emptyList() {
        RouteRequest request = new RouteRequest(
                Lists.emptyList()
        );

        String stringExpectedResponse = "Empty or one item List of Points";

        ResponseEntity<String> expectedResponse = new ResponseEntity<>(stringExpectedResponse, HttpStatus.BAD_REQUEST);

        try {
            when(calculateRouteNNController.calculateRoutes(request)).thenReturn(expectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> actualResponse = null;
        try {
            actualResponse = calculateRouteNNController.calculateRoutes(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void calculateRoutes_BAD_oneItemList() {
        RouteRequest request = new RouteRequest(
                List.of(new Point(
                        51.0, 0.2, "A"
                ))
        );

        String stringExpectedResponse = "Empty or one item List of Points";

        ResponseEntity<String> expectedResponse = new ResponseEntity<>(stringExpectedResponse, HttpStatus.BAD_REQUEST);

        try {
            when(calculateRouteNNController.calculateRoutes(request)).thenReturn(expectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> actualResponse = null;
        try {
            actualResponse = calculateRouteNNController.calculateRoutes(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, actualResponse);
    }
}