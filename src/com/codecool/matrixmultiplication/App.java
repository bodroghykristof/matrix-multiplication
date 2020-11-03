package com.codecool.matrixmultiplication;

import com.codecool.matrixmultiplication.model.Matrix;

import java.util.Optional;

public class App {

    public static void main(String[] args) {

        System.out.println("Matrix One:");
        Matrix matrixOne = new Matrix(3, 5, 0, 9);
        System.out.println(matrixOne);

        System.out.println("Matrix Two:");
        Matrix matrixTwo = new Matrix(5, 6, 0, 9);
        System.out.println(matrixTwo);

        System.out.println("Product Matrix:");
        Optional<Matrix> product = matrixOne.multiply(matrixTwo);
        product.ifPresent(System.out::println);

    }

}
