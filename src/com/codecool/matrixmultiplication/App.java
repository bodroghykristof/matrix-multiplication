package com.codecool.matrixmultiplication;

import com.codecool.matrixmultiplication.model.Matrix;

import java.util.Optional;

public class App {

    public static void main(String[] args) {

        System.out.println("Matrix One:");
        Matrix matrixOne = new Matrix(500, 500, 0, 9);
//        System.out.println(matrixOne);

        System.out.println("Matrix Two:");
        Matrix matrixTwo = new Matrix(500, 500, 0, 9);
//        System.out.println(matrixTwo);

        Optional<Matrix> product = matrixOne.multiply(matrixTwo, 0);
//        System.out.println("Product Matrix:");
//        product.ifPresent(System.out::println);

    }

}
