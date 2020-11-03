package com.codecool.matrixmultiplication.view;

import java.util.List;

public abstract class DataPresenter {

    public abstract void saveDataUnit(String label, List<? extends Number> categories, List<? extends Number> values);

    public abstract void presentStatistics();

}
