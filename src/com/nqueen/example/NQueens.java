/**
 * Copyright 14.10.2018 (C) YanislavMitev
 */

package com.nqueen.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementation of the N Queens problem.
 *
 * @author Yanislav Mitev
 */
public class NQueens {

    private static final String QUEEN = "\u2655\t";
    private static final String EMPTY_SQUARE = "\u25A1\t";

    /**
     * Initializes an array of integers of queens' positions.
     * Each index is column.
     * Each value is row.
     *
     * @param size
     * is the table size.
     *
     * @return randomized position for all n (= size) queens.
     */
    public static int[] getRandomQueenColumnPosition(int size) {
        int[] table = new int[size];
        Random rand = new Random();

        for(int index = 0; index < size; index++) {
            int randomNum = rand.nextInt(size);
            table[index] = randomNum;
        }

        return table;
    }

    /**
     * Gets the number of conflicts for a queen at a specified position.
     *
     * @param row is the row of the queen.
     * @param column is the column of the queen.
     * @param table is the table with queens' positions.
     *
     * @return the number of conflicts for a queen at the specified row and column.
     */
    public static int getConflictsForQueenWithPosition(int row, int column, int[] table) {
        int conflictsCounter = 0;

        for (int columnWithQueen = 0; columnWithQueen < table.length; columnWithQueen++) {
            if (columnWithQueen == column) continue;

            int rowWithQueen = table[columnWithQueen];

            if (rowWithQueen == row || Math.abs(rowWithQueen-row) == Math.abs(columnWithQueen-column)){
                conflictsCounter++;
            }
        }
        return conflictsCounter;
    }

    /**
     * Resolves all conflicts for the table passed as argument.
     *
     * @param table is the table with queens' positions.
     *
     * @return resolved table.
     */
    public static int[] resolveConflicts (int[] table) {
        // This would be a lot faster if we used arrays of ints instead.
        List<Integer> candidates = new ArrayList<>();

        while (true) {

            // Find the queen with the most conflicts.
            int maxConflicts = 0;
            candidates.clear();

            maxConflicts = getMaxConflicts(table, candidates, maxConflicts);

            if (maxConflicts == 0) break;

            // Pick a random queen candidates.
            int worstQueenColumn = candidates.get(new Random().nextInt(candidates.size()));

            // Move the picked queen to the place with the least conflicts.
            int minConflicts = table.length;
            candidates.clear();

            moveQueenToSafeRow(table, candidates, worstQueenColumn, minConflicts);
        }

        return table;
    }

    /**
     * Moves a queen to a safe position.
     *
     * @param table is the table with queens' positions.
     * @param candidates are the queens with conflicts.
     * @param worstQueenColumn is the queen with the most conflicts.
     * @param minConflicts is the position where the queen has the least conflicts.
     */
    private static void moveQueenToSafeRow(int[] table, List<Integer> candidates, int worstQueenColumn, int minConflicts) {
        for (int row = 0; row < table.length; row++) {
            int conflicts = getConflictsForQueenWithPosition(row, worstQueenColumn, table);

            if (conflicts == minConflicts) {
                candidates.add(row);
            }

            if (conflicts < minConflicts) {
                minConflicts = conflicts;
                candidates.clear();
                candidates.add(row);
            }
        }

        if (!candidates.isEmpty()) {
            table[worstQueenColumn] = candidates.get(new Random().nextInt(candidates.size()));
        }
    }

    /**
     * Gets the queen with the most conflicts.
     *
     * @param table is the table with queens' positions.
     * @param candidates are the queens with conflicts.
     * @param maxConflicts is the queen with the most conflicts.
     *
     * @return the maximum conflicts.
     */
    private static int getMaxConflicts(int[] table, List<Integer> candidates, int maxConflicts) {
        for (int column = 0; column < table.length; column++) {
            int conflicts = getConflictsForQueenWithPosition(table[column], column, table);

            if (conflicts == maxConflicts) {
                candidates.add(column);
            }

            if (conflicts > maxConflicts) {
                maxConflicts = conflicts;
                candidates.clear();
                candidates.add(column);
            }
        }
        return maxConflicts;
    }

    /**
     * Initializes the table.
     *
     * Prints the unresolved table.
     *
     * Resolves conflicts.
     *
     * Prints the conflicts-free table.
     */
    private static void nQueenMinConflicts() {
        int[] table = getRandomQueenColumnPosition(9);
        System.out.println("Scrambled table\n----------------------------------");

        printTable(table);

        System.out.println("Resolved table\n----------------------------------");

        table = resolveConflicts(table);

        printTable(table);
    }

    /**
     * Prints the table.
     *
     * @param table is NxN table of N queens, each column is consisted of only one queen.
     */
    public static void printTable(int[] table) {
        for(int row = 0; row < table.length; row++) {
            for(int col = 0; col < table.length; col++) {
                if(row == table[col]) {
                    System.out.print(QUEEN);
                    continue;
                }
                System.out.print(EMPTY_SQUARE);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        nQueenMinConflicts();
    }
}
