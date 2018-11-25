package com.genetic.example;

import java.util.List;
import java.util.Set;

public interface IReader {

    void read();

    int getRucksackCapacity();

    List<Item> getItems();
}
