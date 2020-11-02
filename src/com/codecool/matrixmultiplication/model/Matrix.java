package com.codecool.matrixmultiplication.model;

public class Matrix {

    private int[][] matrix;

    public Matrix(int rows, int columns) {
        this.matrix = new int[rows][columns];
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
