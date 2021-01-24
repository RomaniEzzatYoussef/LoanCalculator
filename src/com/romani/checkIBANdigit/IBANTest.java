package com.romani.checkIBANdigit;

public class IBANTest {
    public static void main(String[] args) throws CheckDigitException {
        String code = "EG000010000100000001000001337";
        IBAN iban = new IBAN();
        String check = iban.calculate(code);
        System.out.println("check digit: "+check);
    }
}
