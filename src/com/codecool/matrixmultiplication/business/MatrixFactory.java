package com.codecool.matrixmultiplication.business;

import com.codecool.matrixmultiplication.model.Matrix;
import com.codecool.matrixmultiplication.utility.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixFactory {

    public int[][] generateRandomMatrix(int rows, int columns, int lowerLimitInclusive, int upperLimitInclusive) {
        int[][] matrix = new int[rows][columns];
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                row[i] = RandomUtils.randomizeInRange(lowerLimitInclusive, upperLimitInclusive);
            }
        }
        return matrix;
    }

    public Matrix calculateProduct(Matrix matrixOne, Matrix matrixTwo, int threads) {

        Matrix product = new Matrix(matrixOne.getRows(), matrixTwo.getColumns());
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Callable<Object>> tasks = new ArrayList<>();

        createThreadsForMultiplication(matrixOne, matrixTwo, product, tasks);

        try {
            executor.invokeAll(tasks);
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return product;
    }

    private void createThreadsForMultiplication(Matrix matrixOne, Matrix matrixTwo, Matrix product, List<Callable<Object>> tasks) {
        for (int rowIndex = 0; rowIndex < product.getRows(); rowIndex++) {
            int finalRowIndex = rowIndex;
            Runnable runnable = () -> {
                for (int columnIndex = 0; columnIndex < product.getColumns(); columnIndex++) {
                    int value = 0;
                    for (int i = 0; i < matrixOne.getColumns(); i++) {
                        value += (matrixOne.get(finalRowIndex, i) * matrixTwo.get(i, columnIndex));
                    }
                    product.set(finalRowIndex, columnIndex, value);
                }
            };
            tasks.add(Executors.callable(runnable));
        }
    }

}
