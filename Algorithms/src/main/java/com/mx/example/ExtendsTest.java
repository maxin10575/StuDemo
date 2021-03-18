package com.mx.example;

/**
 * TODO:
 *美菜网笔试题
 * @author mx
 * @version 1.0, 2019/2/26/14:40
 */
public class ExtendsTest {
    static class A{
        static {
            System.out.println("static A");
        }
        private A(){
            System.out.println("class A");
        }
    }
    static class B extends A{
        private B(){
            System.out.println("class B");
        }
    }

    public static void main(String[] args) {
        B b = new B();
        /**
         * static A
         * class A
         * class B
         */
    }
}
