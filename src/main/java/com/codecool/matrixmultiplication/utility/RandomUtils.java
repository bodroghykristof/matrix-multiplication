package com.codecool.matrixmultiplication.utility;

import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    public static int randomizeInRange(int lowerLimitInclusive, int upperLimitInclusive) {
        return lowerLimitInclusive + RANDOM.nextInt(upperLimitInclusive - lowerLimitInclusive + 1);
    }
}
