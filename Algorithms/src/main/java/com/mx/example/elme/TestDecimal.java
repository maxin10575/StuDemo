package com.mx.example.elme;

import java.math.BigDecimal;

public class TestDecimal {
    public static void main(String[] args) {
        BigDecimal test = BigDecimal.valueOf(100.00);
        Long testNum = 5l;
        BigDecimal num = new BigDecimal(testNum);
        System.out.println(test.divide(num));
        System.out.println("+++++++++++++++++++++");
        System.out.println(10%1000);
    }
}
