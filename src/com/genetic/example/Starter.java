package com.genetic.example;

public class Starter {
    private static final String testData = "E:\\Git\\FMI\\FMI-AI\\src\\com\\genetic\\example\\resources\\test-data";
    private static final String longTestData = "E:\\Git\\FMI\\FMI-AI\\src\\com\\genetic\\example\\resources\\long-test-data";

    public static void main(String[] args) {
        IReader reader = DataReader.of(testData);
        reader.read();

        Rucksack rucksack = Rucksack.racksackBuilder(reader.getRucksackCapacity(), reader.getItems());

    }
}
