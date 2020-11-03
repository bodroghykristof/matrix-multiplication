package com.codecool.matrixmultiplication.model;

import com.codecool.matrixmultiplication.utility.RandomUtils;

import java.util.Optional;

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

    public Optional<Matrix> multiply(Matrix matrix) {

        if (this.columns != matrix.rows) {
            System.out.println("Matrices could not be multiplied " +
                    "as Matrix One needs to have as many columns as the number of rows in Matrix Two");
            return Optional.empty();
        }

        Matrix product = new Matrix(this.rows, matrix.columns);

        for (int rowIndex = 0; rowIndex < product.rows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < product.columns; columnIndex++) {
                int value = 0;
                for (int i = 0; i < this.columns; i++) {
                    value += (this.get(rowIndex, i) * matrix.get(i, columnIndex));
                }
                product.set(rowIndex, columnIndex, value);
            }
        }

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
