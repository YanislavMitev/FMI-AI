/*
 * Copyright 14.10.2018 (C) YanislavMitev
 */

package com.nqueen.example;

import java.util.List;

/**
 * N Queens problem interface.
 *
 * @author Yanislav Mitev
 */
public interface INQueen {

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
    int[] getRandomQueenColumnPosition(int size);

    /**
     * Gets the number of conflicts for a queen at a specified position.
     *
     * @param row is the row of the queen.
     * @param column is the column of the queen.
     * @param table is the table with queens' positions.
     *
     * @return the number of conflicts for a queen at the specified row and column.
     */
    int getConflictsForQueenWithPosition(int row, int column, int[] table);

    /**
     * Resolves all conflicts for the table passed as argument.
     *
     * @param table is the table with queens' positions.
     *
     * @return resolved table.
     */
    int[] resolveConflicts (int[] table);

    /**
     * Moves a queen to a safe position.
     *
     * @param table is the table with queens' positions.
     * @param candidates are the queens with conflicts.
     * @param worstQueenColumn is the queen with the most conflicts.
     * @param minConflicts is the position where the queen has the least conflicts.
     */
    void moveQueenToSafeRow(int[] table, List<Integer> candidates, int worstQueenColumn, int minConflicts);

    /**
     * Gets the queen with the most conflicts.
     *
     * @param table is the table with queens' positions.
     * @param candidates are the queens with conflicts.
     * @param maxConflicts is the queen with the most conflicts.
     *
     * @return the maximum conflicts.
     */
    int getMaxConflicts(int[] table, List<Integer> candidates, int maxConflicts);

    /**
     * Initializes the table.
     *
     * Prints the unresolved table.
     *
     * Resolves conflicts.
     *
     * Prints the conflicts-free table.
     */
    void nQueenMinConflicts();
}
