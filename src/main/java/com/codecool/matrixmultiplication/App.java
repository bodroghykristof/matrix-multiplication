package com.codecool.matrixmultiplication;

import com.codecool.matrixmultiplication.business.MatrixFactory;
import com.codecool.matrixmultiplication.model.Matrix;
import com.codecool.matrixmultiplication.view.DataPresenter;
import com.codecool.matrixmultiplication.view.XChartPresenter;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class App {

    private static final List<Integer> sizes = Arrays.asList(10, 50, 100, 200, 500, 1000);
    private static final List<Integer> threads = Arrays.asList(0, 1, 2, 3, 4, 5);
    private static final int repeatTime = 3;

    public static void main(String[] args) {

        MatrixFactory matrixFactory = new MatrixFactory();
        DataPresenter presenter = new XChartPresenter("Thread Optimization", "Size of Matrix", "Time [ms]");

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
            presenter.saveDataUnit(String.format("%d thread(s)", thread), sizes, timeValues);
        }

        presenter.presentStatistics();
    }
}
