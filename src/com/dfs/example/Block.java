package com.dfs.example;

import java.util.Objects;

public class Block {
    private int xAxis;
    private int yAxis;
    private boolean visited;

    Block(int xAxis, int yAxis){
        this.setXAxis(xAxis);
        this.setYAxis(yAxis);
        this.visited = false;
    }

    public int getXAxis() {
        return this.xAxis;
    }

    public void setXAxis(int xAxis) {
        if (xAxis >= 0) {
            this.xAxis = xAxis;
        }

    }

    public int getYAxis() {
        return this.yAxis;
    }

    public void setYAxis(int yAxis){
        if (yAxis >= 0) {
            this.yAxis = yAxis;
        }
    }

    public void setVisited() {
        this.visited = true;
    }

    public boolean isVisited() {
        return this.visited;
    }

    @Override
    public String toString() {
        return "(" + this.getXAxis() + "," + this.getYAxis() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Block))
            return false;
        Block block = (Block) o;
        return xAxis == block.xAxis &&
                yAxis == block.yAxis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xAxis, yAxis);
    }
}
