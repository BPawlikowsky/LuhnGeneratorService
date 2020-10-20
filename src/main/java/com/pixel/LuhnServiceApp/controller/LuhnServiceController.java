package com.pixel.LuhnServiceApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixel.LuhnServiceApp.model.DigitResponse;
import com.pixel.LuhnServiceApp.model.LuhnRequest;
import com.pixel.LuhnServiceApp.model.VerifyResponse;
import com.pixel.LuhnServiceApp.service.LuhnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
@CrossOrigin
public class LuhnServiceController {

    @Autowired
    private LuhnService luhnService;

    @PostMapping("/verify")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<String> verifyNumber(@RequestBody LuhnRequest request) throws JsonProcessingException {

        ObjectMapper objMap = new ObjectMapper();
        VerifyResponse response = new VerifyResponse(request.getNumber(), luhnService.verifyNumber(request.getNumber()));
        String stringResponse = objMap.writeValueAsString(response);

        return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
    }

    @PostMapping("/digit")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<String> generateDigit(@RequestBody LuhnRequest request) throws JsonProcessingException {

        ObjectMapper objMap = new ObjectMapper();
        int generatedDigit = luhnService.generateDigit(request.getNumber());
        if(request.getNumber().length() > 0 && generatedDigit != -1) {
            DigitResponse response = new DigitResponse(generatedDigit);
            String stringResponse = objMap.writeValueAsString(response);

            return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
        }
        else return new ResponseEntity<>("Wrong Input or no input", HttpStatus.BAD_REQUEST);
    }
}
