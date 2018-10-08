/**
 * Copyright 14.10.2018 (C) YanislavMitev
 */

package com.dfs.example;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Yanislav Mitev
 */
public class DFSExample {
    private static List<Node> path = new ArrayList<>();
    private static Stack<Node> visited = new Stack<>();
    private static List<Node> allNodes = new ArrayList<>();

    public static char[][] matrix = {
            {'0', '*', '0', '0', '0'},
            {'0', '0', '*', '0', '0'},
            {'0', '0', '0', '0', '0'},
            {'0', '0', '*', '0', '0'},
            {'0', '0', '*', '*', '0'}
    };

    /**
     * Static initialization block for the static collection with allNodes
     */
    static {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                allNodes.add(new Node(row, col));
            }
        }
    }

    /**
     * Function that returns Node with specific index
     */
    public static Node getNode(int x, int y) {
        for (Node n : allNodes) {
            if (n.getXAxis() == x && n.getYAxis() == y) {
                return n;
            }
        }
        return new Node(x, y);
    }

    /**
     * Function that checks if a position is a valid one.
     *
     * @param x the x coordinate of the Node
     * @param y the y coordinate of the Node
     * @return true if x and y are valid neighbour coordinates, false if x and y are off the board
     */
    public static boolean isItNeighbour(int x, int y) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) {
            return false;
        }
        return true;
    }

    /**
     * Function that returns all valid neighbours of a Node.
     *
     * @param potentialNeighbours contains all positions of the matrix that may happen to be valid neighbours,
     *                            they may not be valid!
     * @param currentlyInStack    the used for the implementation of DFS
     * @return list of all valid, non-traversed neighbours
     */
    public static List<Node> addNeighboursToList(List<Node> potentialNeighbours, Stack<Node> currentlyInStack) {

        Iterator<Node> it = potentialNeighbours.iterator();

        while (it.hasNext()) {
            Node n = it.next();

            if (isItNeighbour(n.getXAxis(), n.getYAxis())) {
                if (matrix[n.getXAxis()][n.getYAxis()] == '*' || currentlyInStack.contains(n) || visited.contains(n)) {
                    it.remove();
                }

            } else {
                it.remove();
            }
        }

        return potentialNeighbours;
    }

    /**
     * Function to get all the neighbours of a specific node
     *
     * @param node             is the node which neighbours we are looking for
     * @param currentlyInStack the used for the implementation of DFS
     * @return all neighbours of a specific node
     */
    public static List<Node> getNeighbourNodes(Node node, Stack<Node> currentlyInStack) {
        List<Node> neighbours = new LinkedList<>();
        Node nodeRight = getNode(node.getXAxis(), node.getYAxis() + 1);
        Node nodeDown = getNode(node.getXAxis() + 1, node.getYAxis());
        Node nodeLeft = getNode(node.getXAxis(), node.getYAxis() - 1);
        Node nodeUp = getNode(node.getXAxis() - 1, node.getYAxis());

        List<Node> potentialNeighbours = new ArrayList<>();
        Collections.addAll(potentialNeighbours, nodeRight, nodeDown, nodeLeft, nodeUp);

        neighbours.addAll(addNeighboursToList(potentialNeighbours, currentlyInStack));

        return neighbours;
    }


    /**
     * Implementation of depth first search
     *
     * @param start says the algorithm from which position it should start
     * @throws IllegalNodeException when starting node is null
     */
    public static void dfs(Node start) throws IllegalNodeException {
        Stack<Node> stackOfNodes = new Stack<>();

        if (start == null) {
            throw new IllegalNodeException("Cannot start with null node!");
        }

        if (matrix[start.getXAxis()][start.getYAxis()] == '*') {
            throw new IllegalNodeException("Cannot start from '*' node!");
        }

        stackOfNodes.push(start);
        visited.add(start);

        while (!stackOfNodes.empty()) {

            Node node = stackOfNodes.pop();

            if (node.equals(getNode(0, 0))) {
                node.setParent(null);
            }

            visited.push(node);
            path.add(node);

            Iterator<Node> it = getNeighbourNodes(node, stackOfNodes).iterator();
            while (it.hasNext()) {
                Node nextNode = it.next();
                stackOfNodes.push(nextNode);
                nextNode.setParent(node);
            }

        }

    }

    /**
     * Finds and returns (if exists) the destination node
     *
     * @param destinationX X coordinate of the node
     * @param destinationY Y coordinate of the node
     * @return the destination Node
     * @throws IllegalNodeException if we start from '*' position
     */
    private static Node getDestinationNodeFromTraversed(byte destinationX, byte destinationY) throws IllegalNodeException {

        if (matrix[destinationX][destinationY] == '*') {
            throw new IllegalNodeException("Cannot end with '*' node!\nThe algorithm cannot proceed.\nTerminating...");
        }

        return path
                .stream()
                .filter(n -> n.getXAxis() == destinationX && n.getYAxis() == destinationY)
                .collect(Collectors.toCollection(ArrayList::new))
                .get(0);
    }

    public static void main(String[] args) {
        byte destinationX = 0;
        byte destinationY = 0;

        Node start = new Node(0, 0);
        Node end;

        try {
            dfs(start);
            end = getDestinationNodeFromTraversed(destinationX, destinationY);
        } catch (IllegalNodeException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("The shortest path is: ");

        if (!end.equals(start)) {
            System.out.print(end + " <-- ");
        } else {
            System.out.println(end + " <-- root");
        }

        while (true) {
            Node next = end.getParent();
            while (next != null) {
                if (next.equals(start)) {
                    System.out.println(next + " <-- root");
                    break;
                }
                System.out.print(next + " <-- ");
                next = next.getParent();
            }
            break;
        }

    }
}
