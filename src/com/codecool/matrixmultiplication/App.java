package com.codecool.matrixmultiplication;

import com.codecool.matrixmultiplication.model.Matrix;

public class App {

    public static void main(String[] args) {

        System.out.println("Matrix One:");
        Matrix matrixOne = new Matrix(3, 5, 0, 9);
        System.out.println(matrixOne);

        System.out.println("Matrix Two:");
        Matrix matrixTwo = new Matrix(2, 6, 0, 9);
        System.out.println(matrixTwo);

        System.out.println("Product Matrix:");
        Matrix product = matrixOne.multiply(matrixTwo);
        System.out.println(product);

    }

}
