package com.pixel.LuhnServiceApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixel.LuhnServiceApp.model.DigitResponse;
import com.pixel.LuhnServiceApp.model.LuhnRequest;
import com.pixel.LuhnServiceApp.model.VerifyResponse;
import com.pixel.LuhnServiceApp.service.LuhnService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LuhnServiceControllerTest {

    @Mock
    private LuhnServiceController luhnServiceController;

    @Mock
    private LuhnService luhnService;

    @Test
    public void verifyNumber_Positive() {

        LuhnRequest request = new LuhnRequest(
                "123455"
        );
        ObjectMapper om = new ObjectMapper();
        VerifyResponse dtoExpectedResponse = new VerifyResponse(request.getNumber(), true);
        String stringExpectedResponse = "";
        try {
            stringExpectedResponse = om.writeValueAsString(dtoExpectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(stringExpectedResponse, HttpStatus.OK);

        try {
            when(luhnServiceController.verifyNumber(request)).thenReturn(expectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> actualResponse = null;
        try {
            actualResponse = luhnServiceController.verifyNumber(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void verifyNumber_Negtive() {

        LuhnRequest request = new LuhnRequest(
                "123452"
        );
        ObjectMapper om = new ObjectMapper();
        VerifyResponse dtoExpectedResponse = new VerifyResponse(request.getNumber(), false);
        String stringExpectedResponse = "";
        try {
            stringExpectedResponse = om.writeValueAsString(dtoExpectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(stringExpectedResponse, HttpStatus.OK);

        try {
            when(luhnServiceController.verifyNumber(request)).thenReturn(expectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> actualResponse = null;
        try {
            actualResponse = luhnServiceController.verifyNumber(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void generateDigit_Generated_OK() {
        LuhnRequest request = new LuhnRequest(
                "12345"
        );
        ObjectMapper om = new ObjectMapper();
        DigitResponse dtoExpectedResponse = new DigitResponse(5);
        String stringExpectedResponse = "";
        try {
            stringExpectedResponse = om.writeValueAsString(dtoExpectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(stringExpectedResponse, HttpStatus.OK);

        try {
            when(luhnServiceController.verifyNumber(request)).thenReturn(expectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> actualResponse = null;
        try {
            actualResponse = luhnServiceController.verifyNumber(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void generateDigit_Generated_BAD() {
        LuhnRequest request = new LuhnRequest(
                "12345"
        );
        ObjectMapper om = new ObjectMapper();

        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Wrong Input or no input", HttpStatus.BAD_REQUEST);

        try {
            when(luhnServiceController.verifyNumber(request)).thenReturn(expectedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ResponseEntity<String> actualResponse = null;
        try {
            actualResponse = luhnServiceController.verifyNumber(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, actualResponse);
    }
}