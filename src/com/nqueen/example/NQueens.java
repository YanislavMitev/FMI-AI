/*
 * Copyright 14.10.2018 (C) YanislavMitev
 */

package com.nqueen.example;

/**
 * Starter class of the N Queens problem.
 *
 * @author Yanislav Mitev
 */
public class NQueens {
    public static void main(String[] args) {
        new INQueenImpl(9).nQueenMinConflicts();
    }
}
