package com.genetic.example;

import java.util.*;

public class Rucksack {
    private static Rucksack racksack = null;
    private static final List<Item> allItems = new ArrayList<>();
    private final int maxCapacity;

    private int filledCapacity;

    private final List<Item> items;
    private int fitness;

    public Rucksack(final int maxCapacity) {
        this.maxCapacity = maxCapacity;
        items = new ArrayList<>();
    }

    private Rucksack(final int maxCapacity, final List<Item> itemsCollection) {
        this(maxCapacity);
        allItems.addAll(itemsCollection);
    }

    public boolean add(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal item passed as an argument.");
        }

        if (filledCapacity >= maxCapacity) {
            System.out.println("Capacity filled.");
            return false;
        }
        items.add(item);
        filledCapacity += item.getWeight();

        return true;
    }

    public void remove(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal item passed as an argument.");
        }
        items.remove(item);
        filledCapacity -= item.getWeight();
    }

    public int getFilledCapacity() {
        return filledCapacity;
    }

    public boolean isFull() {
        return filledCapacity >= maxCapacity;
    }

    public static Rucksack racksackBuilder(int maxCapacity, final List<Item> itemsCollection) {
        if (racksack == null) {
            if (maxCapacity < 0 || itemsCollection.size() == 0 ) {
                throw new IllegalArgumentException("Invalid max capacity.");
            }
            racksack = new Rucksack(maxCapacity, itemsCollection);
        }

        return racksack;
    }

    public Rucksack crossover(Rucksack parent) {
        Rucksack child = new Rucksack(maxCapacity);
        if (parent != null) {
            Rucksack parentTour = (Rucksack) parent;
            child = new Rucksack(maxCapacity);

            // Get start and end sub tour positions for parent1's tour
            int startPos = (int) (Math.random() * allItems.size());
            int endPos = (int) (Math.random() * allItems.size());

            if (startPos > endPos) {
                int aux = endPos;
                endPos = startPos;
                startPos = aux;
            }

            // Loop and add the sub tour from parent1 to our child
            for (int i = 0; i < allItems.size(); i++) {
                // If our start position is less than the end position
                if (i >= startPos && i <= endPos) {
                    child.add(items.get(i));
                } else {
                    child.add(parentTour.items.get(i));
                }
            }
        }
        return child;
    }

    public void mutate() {
        int pos = (int) (allItems.size() * Math.random());

        if (items.get(pos) != null) {
            items.remove(pos);
        } else {
            items.add(allItems.get(pos));
        }

        fitness = 0;
    }

    public int getFitness() {
        if (fitness == 0) {
            int weight = getWeight();
            fitness = weight + getValue();

            if (weight > maxCapacity) {
                fitness = -fitness;
            }
        }
        return fitness;
    }

    public int getWeight() {
        int weight = 0;

        for (Item i : items) {
            weight += i.getWeight();
        }
        return weight;
    }

    public int getValue() {
        int value = 0;

        for(Item i : items) {
            value += i.getValue();
        }
        return value;
    }


    public List<Item> getAllItems() {
        return Collections.unmodifiableList(allItems);
    }
}
