package com.codecool.matrixmultiplication.model;

import com.codecool.matrixmultiplication.utility.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Matrix {

    private final int[][] matrix;
    private final int rows;
    private final int columns;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new int[rows][columns];
    }

    public Matrix(int rows, int columns, int lowerLimitInclusive, int upperLimitInclusive) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new int[rows][columns];
        generateMatrix(lowerLimitInclusive, upperLimitInclusive);
    }

    public int get(int row, int column) {
        try {
            return matrix[row][column];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException
                    ("Could not fetch the desired value as it is out of bounds for the given matrix.");
        }
    }

    public void set(int row, int column, int value) {
        try {
            matrix[row][column] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException
                    ("Could not set the desired value as it is out of bounds for the given matrix.");
        }
    }

    private void generateMatrix(int lowerLimitInclusive, int upperLimitInclusive) {
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                row[i] = RandomUtils.randomizeInRange(lowerLimitInclusive, upperLimitInclusive);
            }
        }
    }

    public Optional<Matrix> multiply(Matrix matrix, int threads) {

        if (this.columns != matrix.rows) {
            System.out.println("Matrices could not be multiplied " +
                    "as Matrix One needs to have as many columns as the number of rows in Matrix Two");
            return Optional.empty();
        }

        long start = System.nanoTime();
        Matrix product = calculateProductValues(matrix, threads);
        System.out.println("Time taken: " + (System.nanoTime() - start) / 1000000 + " ms");
        return Optional.of(product);
    }

    private Matrix calculateProductValues(Matrix matrix, int threads) {

        Matrix product = new Matrix(this.rows, matrix.columns);
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Callable<Object>> tasks = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < product.rows; rowIndex++) {
            int finalRowIndex = rowIndex;
            Runnable runnable = () -> {
                for (int columnIndex = 0; columnIndex < product.columns; columnIndex++) {
                    int value = 0;
                    for (int i = 0; i < this.columns; i++) {
                        value += (this.get(finalRowIndex, i) * matrix.get(i, columnIndex));
                    }
                    product.set(finalRowIndex, columnIndex, value);
                }
            };
            tasks.add(Executors.callable(runnable));
        }

        try {
            executor.invokeAll(tasks);
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int[] row : matrix) {
            for (int value : row) {
                stringBuilder.append(value).append("  ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
