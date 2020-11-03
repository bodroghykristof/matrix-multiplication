package com.codecool.matrixmultiplication;

import com.codecool.matrixmultiplication.business.MatrixFactory;
import com.codecool.matrixmultiplication.model.Matrix;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) {

        MatrixFactory matrixFactory = new MatrixFactory();

        List<Integer> sizes = Arrays.asList(10, 50, 100, 200, 500, 1000);
        List<Integer> threads = Arrays.asList(0, 1, 2, 3, 4, 5);
        int repeatTime = 3;

        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Time vs. Threads").xAxisTitle("Matrix Size").yAxisTitle("Time").theme(Styler.ChartTheme.GGPlot2).build();


        for (int thread : threads) {
            List<Long> timeValues = new ArrayList<>();
            for (int size : sizes) {
                    Matrix matrixOne = new Matrix(size, size, 0, 9, matrixFactory);
                    Matrix matrixTwo = new Matrix(size, size, 0, 9, matrixFactory);
                    Date start = new Date();
                    for (int j = 0; j < repeatTime; j++) {
                        matrixOne.multiply(matrixTwo, thread);
                    }
                    Date end = new Date();
                    timeValues.add(end.getTime() - start.getTime());
            }
            chart.addSeries(String.format("%d thread(s)", thread), sizes, timeValues);
        }
        new SwingWrapper<>(chart).displayChart();
    }
}
