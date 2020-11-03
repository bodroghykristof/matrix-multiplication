package com.codecool.matrixmultiplication;

import com.codecool.matrixmultiplication.business.MatrixFactory;
import com.codecool.matrixmultiplication.model.Measurement;
import com.codecool.matrixmultiplication.view.DataPresenter;
import com.codecool.matrixmultiplication.view.XChartPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    private static final List<Integer> sizes = Arrays.asList(10, 50, 100, 200, 500, 1000);
    private static final List<Integer> threads = Arrays.asList(0, 1, 2, 3, 4, 5);
    private static MatrixFactory matrixFactory = new MatrixFactory();
    private static DataPresenter presenter = new XChartPresenter("Thread Optimization", "Size of Matrix", "Time [ms]");;

    public static void main(String[] args) {
        presenter.startLoading();
        executeMatrixTasks();
        presenter.finishLoading();
    }

    private static void executeMatrixTasks() {
        for (int thread : threads) {
            List<Long> timeValues = new ArrayList<>();
            for (int size : sizes) {
                Measurement measurement = new Measurement(matrixFactory, size, thread, defineRepeatTime(size));
                long time = measurement.executeMeasurement();
                timeValues.add(time);
            }
            presenter.saveDataUnit(String.format("%d thread(s)", thread), sizes, timeValues);
        }

        presenter.presentStatistics();
    }

    private static int defineRepeatTime(int size) {
        //TODO: make column heights more similar
        return (int) (1000000 / Math.pow(size, 2));
//        return 1;
    }
}
