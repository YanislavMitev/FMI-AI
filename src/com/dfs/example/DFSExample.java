package com.dfs.example;

import java.util.*;

public class DFSExample {
    private static int iteration = 0;
    private static List<Block> visited;
    private static List<Block> path;
    private static Map<Block, List<Block>> neighbours;

    public static char[][] matrix = {
            {'0', '*', '0', '0', '0'},
            {'0', '0', '*', '0', '0'},
            {'0', '0', '*', '0', '0'},
            {'0', '0', '0', '0', '0'},
            {'0', '0', '*', '*', '0'}
    };

    /**
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
     * Initializing the static collection that contains all neighbours
     */
    static {
        visited = new ArrayList<>();
        path = new ArrayList<>();
        neighbours = new HashMap<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                neighbours.put(new Block(row, col), new ArrayList<>());
            }
        }

        for (Map.Entry<Block, List<Block>> block : neighbours.entrySet()) {
            if (isItNeighbour(block.getKey().getXAxis() + 1, block.getKey().getYAxis())) {
                block.getValue().add(new Block(block.getKey().getXAxis() + 1, block.getKey().getYAxis()));
            }
            if (isItNeighbour(block.getKey().getXAxis(), block.getKey().getYAxis() + 1)) {
                block.getValue().add(new Block(block.getKey().getXAxis(), block.getKey().getYAxis() + 1));
            }
            if (isItNeighbour(block.getKey().getXAxis() - 1, block.getKey().getYAxis())) {
                block.getValue().add(new Block(block.getKey().getXAxis() - 1, block.getKey().getYAxis()));
            }
            if (isItNeighbour(block.getKey().getXAxis(), block.getKey().getYAxis() - 1)) {
                block.getValue().add(new Block(block.getKey().getXAxis(), block.getKey().getYAxis() - 1));
            }

        }

    }

    public static void dfs(char[][] maze, Block start, Block end) {
        if (start.equals(end)) {
            path.add(end);
            return;
        }

        Stack<Block> adj = new Stack<>();
        adj.push(start);
        path.add(start);

        while (!adj.isEmpty()) {
            List<Block> nodeNeighbours = neighbours.get(adj.peek());
//            System.out.println(adj.toString());
            adj.pop();

            for(Block b : nodeNeighbours){

//                System.out.println(b);

                if(!b.isVisited() && maze[b.getXAxis()][b.getYAxis()] != '*'){
                    visited.add(b);
                    b.setVisited();
                    adj.push(b);
                }
            }
        }

        path.addAll(adj);

    }

    public static void main(String[] args) throws Exception {
        Block start = new Block(0, 0);
//        System.out.println(matrix.length + "," +  matrix[0].length);
        Block end = new Block(4, 4);

        dfs(matrix, start, end);
//        System.out.println(neighbours.keySet().size());
//        System.out.println(path.size());
//        System.out.println(visited.size());
//        System.out.println(start.getNeighbours());

        for(Map.Entry<Block, List<Block>> entry : neighbours.entrySet()){
            for(Block b : entry.getValue()){
                System.out.print(b + ",");
            }
            System.out.println();
            System.out.println("---------------------");
        }

    }
}
