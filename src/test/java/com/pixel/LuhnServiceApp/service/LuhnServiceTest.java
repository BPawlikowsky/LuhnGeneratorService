package com.pixel.LuhnServiceApp.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LuhnServiceTest {

    @Mock
    private LuhnService luhnService;

    @Test
    public void verifyNumber_Positive() {

        boolean expectedResponse = true;
        String request = "123455";
        when(luhnService.verifyNumber(request)).thenReturn(expectedResponse);
        boolean actualResponse = luhnService.verifyNumber(request);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void verifyNumber_Negative() {

        boolean expectedResponse = false;
        String request = "123452";
        when(luhnService.verifyNumber(request)).thenReturn(expectedResponse);
        boolean actualResponse = luhnService.verifyNumber(request);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void generateDigit_OK() {
        String request = "12345";
        int expectedResponse = 5;
        when(luhnService.generateDigit(request)).thenReturn(expectedResponse);
        int actualResponse = luhnService.generateDigit(request);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void generateDigit_BAD() {
        String request = "12345";
        int expectedResponse = -1;
        when(luhnService.generateDigit(request)).thenReturn(expectedResponse);
        int actualResponse = luhnService.generateDigit(request);
        assertEquals(expectedResponse, actualResponse);
    }
}