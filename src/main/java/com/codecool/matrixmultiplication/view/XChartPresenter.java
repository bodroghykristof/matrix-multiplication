package com.codecool.matrixmultiplication.view;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class XChartPresenter extends DataPresenter {

    private final CategoryChart chart = new CategoryChartBuilder().width(800).height(600).theme(Styler.ChartTheme.GGPlot2).build();
    private final ExecutorService loadingExecutor = Executors.newSingleThreadExecutor();

    public XChartPresenter(String title, String xTitle, String yTitle) {
        this.chart.setTitle(title);
        this.chart.setXAxisTitle(xTitle);
        this.chart.setYAxisTitle(yTitle);
    }

    @Override
    public void saveDataUnit(String label, List<? extends Number> categories, List<? extends Number> values) {
        chart.addSeries(label, categories, values);
    }

    @Override
    public void presentStatistics() {
        new SwingWrapper<>(chart).displayChart();
    }

    @Override
    public void startLoading() {
        loadingExecutor.submit(() -> System.out.println("Loading has started"));
    }

    @Override
    public void finishLoading() {
        loadingExecutor.submit(() -> System.out.println("Loading has finished"));
        loadingExecutor.shutdown();
    }
}
