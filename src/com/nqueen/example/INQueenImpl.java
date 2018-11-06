/*
 * Copyright 14.10.2018 (C) YanislavMitev
 */

package com.nqueen.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * N Queens problem implementation.
 *
 * @author Yanislav Mitev
 */
public class INQueenImpl implements INQueen {

    private static final String QUEEN = "\u2655\t";
    private static final String EMPTY_SQUARE = "\u25A1\t";

    private final int size;

    /**
     * Constructor.
     *
     * @param size is the table size.
     */
    public INQueenImpl(final int size) {
        if(size < 4) throw new IllegalArgumentException("Invalid table size.");
        this.size = size;
    }

    /**
     * @see INQueen#getRandomQueenColumnPosition(int)
     */
    @Override
    public int[] getRandomQueenColumnPosition(int size) {
        int[] table = new int[size];
        Random rand = new Random();

        for(int index = 0; index < size; index++) {
            int randomNum = rand.nextInt(size);
            table[index] = randomNum;
        }

        return table;
    }

    /**
     * @see INQueen#getConflictsForQueenWithPosition(int row, int column, int[] table)
     */
    @Override
    public int getConflictsForQueenWithPosition(int row, int column, int[] table) {
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
     * @see INQueen#resolveConflicts (int[] table)
     */
    @Override
    public int[] resolveConflicts (int[] table) {
        List<Integer> candidates = new ArrayList<>();

        while (true) {

            int maxConflicts = 0;
            candidates.clear();
            maxConflicts = getMaxConflicts(table, candidates, maxConflicts);

            if (maxConflicts == 0) break;

            int worstQueenColumn = candidates.get(new Random().nextInt(candidates.size()));

            int minConflicts = table.length;
            candidates.clear();

            moveQueenToSafeRow(table, candidates, worstQueenColumn, minConflicts);
        }

        return table;
    }

    /**
     * @see INQueen#moveQueenToSafeRow(int[], List, int, int)
     */
    @Override
    public void moveQueenToSafeRow(int[] table, List<Integer> candidates, int worstQueenColumn, int minConflicts) {
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
     * @see INQueen#getMaxConflicts(int[], List, int)
     */
    @Override
    public int getMaxConflicts(int[] table, List<Integer> candidates, int maxConflicts) {
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
     * Prints the table.
     *
     * @param table is NxN table of N queens, each column is consisted of only one queen.
     */
    private void printTable(int[] table) {
        for(int row = 0; row < table.length; row++) {
            for (int col : table) {
                if (row == col) {
                    System.out.print(QUEEN);
                    continue;
                }
                System.out.print(EMPTY_SQUARE);
            }
            System.out.println();
        }
    }

    /**
     *@see INQueen#nQueenMinConflicts()
     */
    @Override
    public void nQueenMinConflicts() {
        int[] table = getRandomQueenColumnPosition(size);
        System.out.println("Scrambled table\n----------------------------------");

        printTable(table);

        System.out.println("Resolved table\n----------------------------------");

        table = resolveConflicts(table);

        printTable(table);
    }
}
