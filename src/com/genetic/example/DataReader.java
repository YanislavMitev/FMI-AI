package com.genetic.example;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class DataReader implements IReader{
    private String path;
    private int rucksackCapacity;
    private List<Item> items;

    private DataReader(String path) {
        this.path = path;
    }

    public static DataReader of(String path) {
        if (path == null || path.trim().length() <= 0) {
            throw new IllegalArgumentException("Invalid path.");
        }
        return new DataReader(path);
    }

    @Override
    public void read() {
        File testData = new File(Paths.get(path).toUri());

        try {
            BufferedReader streamReader = new BufferedReader(new FileReader(testData));

            String line = streamReader.readLine();
            String[] data = line.split(" ");

            rucksackCapacity = Integer.parseInt(data[0]);
            items = new ArrayList<>(Integer.parseInt(data[1]));

            while (true) {
                line = streamReader.readLine();

                if (line == null) {
                    break;
                }

                data = line.split(" ");
                items.add(new Item(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRucksackCapacity() {
        return rucksackCapacity;
    }

    @Override
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
}
