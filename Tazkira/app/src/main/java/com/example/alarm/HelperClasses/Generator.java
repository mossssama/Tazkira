package com.example.alarm.HelperClasses;

import java.util.Random;

public class Generator {

    public static int getRandomIntegerInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
