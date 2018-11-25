package com.genetic.example;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private static final int TOURNAMENT_SIZE = 5;
    private static final double MUTATION_RATE = 0.015;

    public List<Rucksack> population;


    public Population(int size) {
        population = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Rucksack newBag = new Rucksack(5000);
            population.add(newBag);
        }
    }

    public Rucksack getFittest() {

        Rucksack fittest = null;
        if (!population.isEmpty()) {
            fittest = population.get(0);

            for (int i = 1; i < population.size(); i++) {
                if (fittest.getFitness() <= population.get(i).getFitness()) {
                    fittest = population.get(i);
                }
            }
        }
        return fittest;
    }

    public void evolvePopulation(boolean elitism) {
        System.out.println(population);
        System.out.println(this.getFittest());

        int elitismOffset = 0;
        List<Rucksack> newPopulation = new ArrayList<>(population.size());

        if (elitism) {
            newPopulation.add(this.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from Current population
        for (int i = 1; i < population.size(); i++) {
            // Select parents
            Rucksack parent1 = tournamentSelection();
            Rucksack parent2 = tournamentSelection();
            // Crossover parents
            Rucksack crossover = parent1.crossover(parent2);
            Rucksack child = Rucksack.racksackBuilder(crossover.getFilledCapacity(), crossover.getAllItems());
            // Add child to new population
            newPopulation.add(child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            if (Math.random() < MUTATION_RATE) {
                newPopulation.get(i).mutate();
            }
        }

        population = new ArrayList<>(newPopulation);
    }

    private Rucksack tournamentSelection() {
        // Create a tournament population
        Population tournament = new Population(TOURNAMENT_SIZE);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * this.populationSize());
            tournament.population.set(i, this.getBag(randomId));
        }
        // Get the fittest tour
        Rucksack fittest = Rucksack.racksackBuilder(tournament.getFittest().getFilledCapacity(),
                                                    tournament.getFittest().getAllItems());
        return fittest;
    }

    // Gets a bag from population
    public Rucksack getBag(int index) {
        return population.get(index);
    }

    public int populationSize() {
        return population.size();
    }
}
