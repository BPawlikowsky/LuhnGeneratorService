package com.pixel.LuhnServiceApp.service;

import com.pixel.LuhnServiceApp.utils.Luhn;
import org.springframework.stereotype.Service;

@Service
public class LuhnService {


    public boolean verifyNumber(String number) {
        return Luhn.luhnVerifyNumber(number);
    }
}
