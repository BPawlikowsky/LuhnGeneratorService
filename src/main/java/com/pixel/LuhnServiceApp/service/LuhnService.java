package com.pixel.LuhnServiceApp.service;

import com.pixel.LuhnServiceApp.utils.Luhn;
import org.springframework.stereotype.Service;

@Service
public class LuhnService {


    public boolean verifyNumber(String number) {
        return Luhn.luhnVerifyNumber(number);
    }

    public int generateDigit(String number) {
        String cleanNumber = Luhn.cleanupNumber(number);
        if(cleanNumber.length() > 0)
            return Luhn.generateDigit(number);
        else
            return -1;
    }
}
