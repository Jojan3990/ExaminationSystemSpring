package com.rightfindpro.become.testIntern;

public class RecursionDemo {
    int product1ToN(int n) {
        // we assume n >= 1
//        System.out.println();
        return (n > 1) ? (n * product1ToN(n-1)) : 1;
    }

    public static void main(String[] args) {
        RecursionDemo recursionDemo=new RecursionDemo();
        int product=recursionDemo.product1ToN(2);
        System.out.println(product);
    }
}
