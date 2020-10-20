package com.pixel.LuhnServiceApp.utils;

public class Luhn {
    private static final int WEIGHT_1 = 1;
    private static final int WEIGHT_2 = 2;

    public static String cleanupNumber(String number) {
        StringBuilder newNumber = new StringBuilder();
        for (char c : number.toCharArray()) {
            if(Character.isDigit(c))
                newNumber.append(c);
        }

        return newNumber.toString();
    }

    public static Boolean luhnVerifyNumber(String number) {

        String numberBeingChecked = number.substring(0, number.length()-1);
        String expectedDigit = String.valueOf(number.charAt(number.length()-1));

        int sum = luhnGenerateSum(numberBeingChecked);

        int control = luhnControlDigit(sum);
        if((sum + control)%10 == 0 && control == Integer.parseInt(expectedDigit))
            return true;
        else
            return false;
    }

    public static int generateDigit(String number) {
        return luhnControlDigit(luhnGenerateSum(number));
    }

    private static Integer luhnGenerateSum(String parseNumber) {

        int sum = 0;
        int digits = 0;

        for (int i = parseNumber.length() - 1; i >= 0; i--) {
            char c = parseNumber.charAt(i);

            if (Character.isDigit(c)) {
                digits++;
                int digit = Integer.parseInt(String.valueOf(c));
                int weight;

                if((digits+1)%2 != 0)
                    weight = WEIGHT_1;
                else
                    weight = WEIGHT_2;
                digit = parseDigit(digit * weight);
                sum += digit;
            }
        }
        return sum;
    }

    private static int parseDigit(int digit){

        if(digit>9){
            String digitToStr = String.valueOf(digit);
            int lowerDigit=Integer.parseInt(String.valueOf(digitToStr.charAt(1)));
            int higherDigit=Integer.parseInt(String.valueOf(digitToStr.charAt(0)));
            digit = lowerDigit + higherDigit;
        }
        return digit;
    }

    private static int luhnControlDigit(int sum) {
        return (sum%10 == 0) ? 0 : 10-(sum%10);
    }
}