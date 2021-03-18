package com.mx.example;

/**
 * test
 *
 * @author mx
 * @version v1.0, 2018-02-09 13:59
 **/


public class DzwTest {

    public  String S_GrayId;
    public String S_Updator;


    public DzwTest() {
    }

    public String getS_GrayId() {
        return S_GrayId;
    }

    public void setS_GrayId(String s_GrayId) {
        S_GrayId = s_GrayId;
    }

    public String getS_Updator() {
        return S_Updator;
    }

    public void setS_Updator(String s_Updator) {
        S_Updator = s_Updator;
    }

    public static void main(String[] args) {
        DzwTest test = null;
        test = new DzwTest();
        test.setS_GrayId("11");
        test.setS_Updator("dzw");
        System.out.println(test);
    }
}
