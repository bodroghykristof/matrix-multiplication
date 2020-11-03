package com.codecool.matrixmultiplication.model;

import com.codecool.matrixmultiplication.business.MatrixFactory;

import java.util.Optional;


public class Matrix {

    private MatrixFactory matrixFactory;
    private final int[][] matrix;
    private final int rows;
    private final int columns;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new int[rows][columns];
    }

    public Matrix(int rows, int columns, int lowerLimitInclusive, int upperLimitInclusive, MatrixFactory matrixFactory) {
        this.rows = rows;
        this.columns = columns;
        this.matrixFactory = matrixFactory;
        this.matrix = matrixFactory.generateRandomMatrix(rows, columns, lowerLimitInclusive, upperLimitInclusive);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
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

    public Optional<Matrix> multiply(Matrix matrix, int threads) {

        if (this.columns != matrix.rows) {
            System.out.println("Matrices could not be multiplied " +
                    "as Matrix One needs to have as many columns as the number of rows in Matrix Two");
            return Optional.empty();
        }

        long start = System.nanoTime();
        Matrix product = matrixFactory.calculateProduct(this, matrix, threads);
        System.out.println("Time taken: " + (System.nanoTime() - start) / 1000000 + " ms");
        return Optional.of(product);
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
