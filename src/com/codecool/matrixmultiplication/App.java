package com.codecool.matrixmultiplication;

import com.codecool.matrixmultiplication.business.MatrixFactory;
import com.codecool.matrixmultiplication.model.Matrix;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {

        MatrixFactory matrixFactory = new MatrixFactory();

        List<Integer> sizes = Arrays.asList(10, 50, 100, 200, 500, 1000);
        List<Integer> threads = Arrays.asList(0, 1, 2, 3, 4, 5);
        int repeatTime = 3;


        for (int size : sizes) {
            for (int thread : threads) {
                for (int i = 0; i < 1; i++) {
                    Matrix matrixOne = new Matrix(size, size, 0, 9, matrixFactory);
                    Matrix matrixTwo = new Matrix(size, size, 0, 9, matrixFactory);
                    Date start = new Date();
                    for (int j = 0; j < repeatTime; j++) {
                        matrixOne.multiply(matrixTwo, thread);
                    }
                    Date end = new Date();
                    System.out.printf("Time taken in milli seconds for %d threads on %s size: %s%n", thread, size, (end.getTime() - start.getTime()));
                }
            }
        }
    }
}
