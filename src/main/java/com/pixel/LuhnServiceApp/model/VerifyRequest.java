package com.pixel.LuhnServiceApp.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class VerifyRequest {
    private String number;
    @JsonCreator
    public VerifyRequest(@JsonProperty(value = "number", required = true) String number) {
        this.number = number;
    }
}
