package com.codecool.matrixmultiplication.model;

import com.codecool.matrixmultiplication.business.MatrixFactory;

import java.util.Date;

public class Measurement {

    private final MatrixFactory matrixFactory;
    private final int size;
    private final int threads;
    private final int repeatTime;

    public Measurement(MatrixFactory matrixFactory, int size, int threads, int repeatTime) {
        this.matrixFactory = matrixFactory;
        this.size = size;
        this.threads = threads;
        this.repeatTime = repeatTime;
    }

    public long executeMeasurement() {
        Matrix matrixOne = new Matrix(size, size, 0, 9, matrixFactory);
        Matrix matrixTwo = new Matrix(size, size, 0, 9, matrixFactory);
        Date start = new Date();
        for (int j = 0; j < repeatTime; j++) {
            matrixOne.multiply(matrixTwo, threads);
        }
        Date end = new Date();
        return end.getTime() - start.getTime();
    }

}
