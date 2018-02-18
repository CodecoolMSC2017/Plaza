package main.java.com.codecool.plaza.api;

import java.util.concurrent.ThreadLocalRandom;

public class BarCodeGen {

    public static long genBarCode() {
        return ThreadLocalRandom.current().nextLong(10000, 100000);
    }
}
