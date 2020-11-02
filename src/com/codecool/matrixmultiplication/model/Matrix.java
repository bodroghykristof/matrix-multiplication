package com.codecool.matrixmultiplication.model;

import com.codecool.matrixmultiplication.utility.RandomUtils;

public class Matrix {

    private final int[][] matrix;

    public Matrix(int rows, int columns, int lowerLimitInclusive, int upperLimitInclusive) {
        this.matrix = new int[rows][columns];
        generateMatrix(lowerLimitInclusive, upperLimitInclusive);
    }

    private void generateMatrix(int lowerLimitInclusive, int upperLimitInclusive) {
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                row[i] = RandomUtils.randomizeInRange(lowerLimitInclusive, upperLimitInclusive);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : matrix) {
            for (int value : row) {
                stringBuilder.append(value + "  ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
