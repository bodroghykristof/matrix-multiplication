package com.codecool.matrixmultiplication;

import com.codecool.matrixmultiplication.model.Matrix;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello Matrix");
        Matrix matrix = new Matrix(3, 5, 0, 9);
        System.out.println(matrix);
    }

}
