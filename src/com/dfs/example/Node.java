/**
 * Copyright 14.10.2018 (C) YanislavMitev
 */

package com.dfs.example;

import java.util.Objects;

/**
 * Class that represents a node.
 * Holds the coordinates of the node.
 * Holds the parent element of that node.
 */
public class Node {
    private int xAxis;
    private int yAxis;

    private Node parent;

    Node(int xAxis, int yAxis) {
        this.setXAxis(xAxis);
        this.setYAxis(yAxis);
    }

    public int getXAxis() {
        return this.xAxis;
    }

    public void setXAxis(int xAxis) {

        this.xAxis = xAxis;
    }

    public int getYAxis() {
        return this.yAxis;
    }

    public void setYAxis(int yAxis) {

        this.yAxis = yAxis;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "(" + this.getXAxis() + "," + this.getYAxis() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Node))
            return false;
        Node node = (Node) o;
        return xAxis == node.xAxis &&
                yAxis == node.yAxis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xAxis, yAxis);
    }

}
