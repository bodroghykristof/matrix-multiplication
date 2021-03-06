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


    public Matrix calculateProduct(Matrix matrixOne, Matrix matrixTwo) {

        Matrix product = new Matrix(matrixOne.getRows(), matrixTwo.getColumns());

        for (int rowIndex = 0; rowIndex < product.getRows(); rowIndex++) {
            calculateRowOfProduct(matrixOne, matrixTwo, product, rowIndex);

        }
        return product;
    }


    public Matrix calculateProduct(Matrix matrixOne, Matrix matrixTwo, int threads) {

        if (threads == 0) return calculateProduct(matrixOne, matrixTwo);

        Matrix product = new Matrix(matrixOne.getRows(), matrixTwo.getColumns());
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        if (threads == 5) executor = Executors.newWorkStealingPool();

        List<Callable<Object>> tasks = createTasksForMultiplication(matrixOne, matrixTwo, product);

        try {
            executor.invokeAll(tasks);
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return product;
    }


    private List<Callable<Object>> createTasksForMultiplication(Matrix matrixOne, Matrix matrixTwo, Matrix product) {

        List<Callable<Object>> tasks = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < product.getRows(); rowIndex++) {
            int finalRowIndex = rowIndex;
            Runnable runnable = () -> {
                calculateRowOfProduct(matrixOne, matrixTwo, product, finalRowIndex);
            };
            tasks.add(Executors.callable(runnable));
        }

        return tasks;
    }


    private void calculateRowOfProduct(Matrix matrixOne, Matrix matrixTwo, Matrix product, int finalRowIndex) {

        for (int columnIndex = 0; columnIndex < product.getColumns(); columnIndex++) {
            int value = 0;
            for (int i = 0; i < matrixOne.getColumns(); i++) {
                value += (matrixOne.get(finalRowIndex, i) * matrixTwo.get(i, columnIndex));
            }
            product.set(finalRowIndex, columnIndex, value);
        }
    }

}
