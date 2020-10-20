package com.pixel.LuhnServiceApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixel.LuhnServiceApp.model.VerifyRequest;
import com.pixel.LuhnServiceApp.model.VerifyResponse;
import com.pixel.LuhnServiceApp.service.LuhnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LuhnServiceController {

    @Autowired
    private LuhnService luhnService;

    @PostMapping("/verify")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<String> verifyNumber(@RequestBody VerifyRequest request) throws JsonProcessingException {

        ObjectMapper objMap = new ObjectMapper();
        VerifyResponse response = new VerifyResponse(request.getNumber(), luhnService.verifyNumber(request.getNumber()));
        String stringResponse = objMap.writeValueAsString(response);

        return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
    }
}
