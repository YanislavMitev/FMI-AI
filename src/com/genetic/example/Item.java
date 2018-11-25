package com.genetic.example;

public class Item {
    private final int weight;
    private final int value;
    private int included;

    public Item(final int weight, final int value) {
        this.weight = weight;
        this.value = value;
        included = 0;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public void include() {
        included = 1;
    }

    public int isIncluded() {
        return included;
    }
}
