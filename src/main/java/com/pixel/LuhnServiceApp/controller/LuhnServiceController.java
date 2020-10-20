package com.pixel.LuhnServiceApp.controller;

import com.pixel.LuhnServiceApp.model.VerifyRequest;
import com.pixel.LuhnServiceApp.model.VerifyResponse;
import com.pixel.LuhnServiceApp.service.LuhnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LuhnServiceController {

    @Autowired
    private LuhnService luhnService;

    @PostMapping("/verify")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<VerifyResponse> verifyNumber(@RequestBody VerifyRequest request) {
        return new ResponseEntity<VerifyResponse>(new VerifyResponse(), HttpStatus.OK);
    }
}
