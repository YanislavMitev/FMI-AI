package com.astar.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Block {
    private final int xAxis;

    private final int yAxis;

    private int value;

    private final List<Block> neighbours;

    public Block(int xAxis, int yAxis, int value) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.value = value;
        neighbours = new ArrayList<>();
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public int getValue() {
        return value;
    }

    public void changeValue(int value) {
        this.value = value;
    }

    public void addNeighbour(Block block) {
        neighbours.add(block);
    }

    public List<Block> getNeighbours() {
        return Collections.unmodifiableList(neighbours);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (xAxis != block.xAxis) return false;
        if (yAxis != block.yAxis) return false;

        return value == block.value;
    }

    @Override
    public int hashCode() {
        int result = xAxis;
        result = 31 * result + yAxis;
        result = 31 * result + value;
        return result;
    }
}
