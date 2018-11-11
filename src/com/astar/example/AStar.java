package com.astar.example;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class AStar {
    private static int[] arrayOfNumbers;
    private static int[][] table;
    private static Set<Block> blocks = new LinkedHashSet<>();
    /**
     * Initialize the static array of numbers.
     *
     * @param numbers is the amount of numbers per row.
     */
    private static void init(int numbers) {
        int sizeOfArray = (numbers * numbers) - 1;
        arrayOfNumbers = new int[sizeOfArray];

        for(int index = 0; index < sizeOfArray; index++) {
            arrayOfNumbers[index] = index + 1;
        }
    }

    /**
     * Method that shuffles the arrayOfNumbers.
     */
    private static void shuffle() {
        Random rand = new Random();

        for (int index = arrayOfNumbers.length - 1; index > 0; index--) {
            int index2 = rand.nextInt(index + 1);
            // Simple swap
            int temp = arrayOfNumbers[index2];
            arrayOfNumbers[index2] = arrayOfNumbers[index];
            arrayOfNumbers[index] = temp;
        }
    }

    /**
     * Get randomized table with one empty block.
     *
     * @param size is the size of the table.
     *
     * @return randomized table with one empty block.
     */
    private static int[][] getRandomizedTable(int size) {
        Random rand = new Random();
        table = new int[size][size];

        init(size);
        shuffle();

        //randomizing the empty block
        int randRow = rand.nextInt(size);
        int randCol = rand.nextInt(size);

        //initialize the other blocks
        int counter = 0;
        for(int row = 0; row < table.length; row++) {
            for(int col = 0; col < table[row].length; col++) {
                if(row == randRow && col == randCol){
                    continue;
                }
                table[row][col] = arrayOfNumbers[counter++];
            }
        }

        return table;
    }

    private static void initializeNeighbours() {
        for(int row = 0; row < table.length; row++) {
            for(int col = 0; col < table[row].length; col++) {
                Block b = new Block(col, row, table[row][col]);
                if(col + 1 < table.length) {
                    b.addNeighbour(new Block(col+1, row, table[row][col+1]));
                }
                if(col - 1 >= 0) {
                    b.addNeighbour(new Block(col-1, row, table[row][col-1]));
                }
                if(row + 1 < table.length) {
                    b.addNeighbour(new Block(col, row+1, table[row+1][col]));
                }
                if(row - 1 >= 0) {
                    b.addNeighbour(new Block(col, row-1, table[row-1][col]));
                }
                blocks.add(b);
            }
        }
    }

    public static void main(String[] args) {
        int[][] table = getRandomizedTable(3);

        System.out.println(Arrays.toString(table[0]));
        System.out.println(Arrays.toString(table[1]));
        System.out.println(Arrays.toString(table[2]));

        initializeNeighbours();



    }
}
