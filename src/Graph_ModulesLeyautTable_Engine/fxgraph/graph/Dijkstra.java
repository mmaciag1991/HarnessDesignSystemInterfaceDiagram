package Graph_ModulesLeyautTable_Engine.fxgraph.graph;


import javafx.scene.paint.Color;

import java.util.*;

public class Dijkstra {
    private final Set<Cell> nodes;
    public List<Cell> cells;
    private final boolean directed;

    Dijkstra(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
    //
//    public void addNode(Node... n) {
//        // We're using a var arg method so we don't have to call
//        // addNode repeatedly
//        nodes.addAll(Arrays.asList(n));
//    }

    void addEdge(Cell source, Cell destination, double weight) {

        nodes.add(source);
        nodes.add(destination);

        // We're using addEdgeHelper to make sure we don't have duplicate edges
        addEdgeHelper(source, destination, weight);

        if (!directed && source != destination) {
            addEdgeHelper(destination, source, weight);
        }
    }

    void ModifyEdgeWeight(Cell a, Cell b, double weight) {

        for (Edge edge : a.getEdges()) {
            if (edge.source == a && edge.target == b) {
                // Update the value
                edge.lenght = weight;
                return;
            }
        }
    }

    boolean DeleteEd(Cell a, Cell b) {
        for (Edge edge : a.getEdges()) {
            if (edge.source == a && edge.target == b) {
                // Update the value in case it's a different one now
                a.getEdges().remove(edge);
                return true;
            }
        }
        return false;
    }
    void DeleteNo(Cell from){
        for(Cell node : nodes){
            for (Edge edge : node.getEdges()) {
//                //System.out.println(edge.source.name+" "+edge.destination.name+" "+edge.weight);
                if (edge.source == from || edge.target == from) {
                    node.getEdges().remove(edge);
//                    //System.out.println(edge.source.name+" "+edge.destination.name+" "+edge.weight);
                }
            }
        }
        nodes.remove(from);
    }

    void copyEdge(ArrayList<Edge> edges){
        for(Cell node : nodes){
            edges.addAll(node.getEdges());
        }
    }

    private void addEdgeHelper(Cell a, Cell b, double weight) {

        for (Edge edge : a.getEdges()) {
            if (edge.source == a && edge.target == b) {
                // Update the value in case it's a different one now
                edge.lenght = weight;
                return;
            }
        }
        // If it hasn't been added already
        a.getEdges().add(new Edge(a, b, weight,1, Color.ALICEBLUE));
    }

    boolean hasEdge(Cell source, Cell destination) {
        LinkedList<Edge> edges = source.getEdges();
        for (Edge edge : edges) {
            if (edge.target == destination) {
                return true;
            }
        }
        return false;
    }

    double Weight(Cell source, Cell destination) {
        LinkedList<Edge> edges = source.getEdges();
        for (Edge edge : edges) {
            if (edge.target == destination) {
                return edge.lenght;
            }
        }
        return 0d;
    }

    void resetNodesVisited() {
        for (Cell node : cells/*nodes*/) {
            node.unvisited();
        }
    }
    public String DijkstraShortestPath(Cell start, Cell end) {
        this.resetNodesVisited();
        String output ="";
        HashMap<Cell, Cell> changedAt = new HashMap<>();
        changedAt.put(start, null);

        HashMap<Cell, Double> shortestPathMap = new HashMap<>();

        for (Cell node : cells/*nodes*/) {
            if (node == start)
                shortestPathMap.put(start, 0.0);
            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
        }

        for (Edge edge : start.getEdges()) {
            if (!start.equals(edge.target)){
                shortestPathMap.put(edge.target, edge.lenght);
                changedAt.put(edge.target, start);
            }else{
                shortestPathMap.put(edge.source, edge.lenght);
                changedAt.put(start ,edge.source);
            }

        }

        start.visit();

        while (true) {
            Cell currentNode = closestReachableUnvisited(shortestPathMap);

            if (currentNode == null) {

                return ("There isn't a path between " + start.getCellId() + " and " + end.getCellId());

            }

            if (currentNode == end) {

                Cell child = end;

                StringBuilder path = new StringBuilder(end.getCellId());
                while (true) {
                    Cell parent = changedAt.get(child);
                    if (parent == null) {
                        break;
                    }

                    path.insert(0, parent.getCellId() + "->");
                    child = parent;
                }
                output += path;
                //System.out.println(path);
                output += ("\nThe path costs: " + shortestPathMap.get(end));
                return output;
            }
            currentNode.visit();

            for (Edge edge : currentNode.getEdges()) {
                if (edge.target.isVisited())
                    continue;

                if (currentNode.equals(edge.target)){
                    if (shortestPathMap.get(currentNode)
                            + edge.lenght
                            < shortestPathMap.get(edge.source)) {
                        shortestPathMap.put(edge.source,
                                shortestPathMap.get(currentNode) + edge.lenght);
                        changedAt.put(currentNode, edge.source);
                    }
                }else{
                if (shortestPathMap.get(currentNode)
                        + edge.lenght
                        < shortestPathMap.get(edge.target)) {
                    shortestPathMap.put(edge.target,
                            shortestPathMap.get(currentNode) + edge.lenght);
                    changedAt.put(edge.target, currentNode);
                }
                }
            }
        }
    }
    Stack<Cell> animatePath(Cell start, Cell end) {

        Stack<Cell> path = new Stack<>();
        HashMap<Cell, Cell> changedAt = new HashMap<>();
        changedAt.put(start, null);

        HashMap<Cell, Double> shortestPathMap = new HashMap<>();

        for (Cell node : cells/*nodes*/) {
            if (node == start)
                shortestPathMap.put(start, 0.0);
            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
        }

        for (Edge edge : start.getEdges()) {
            shortestPathMap.put(edge.target, edge.lenght);
            changedAt.put(edge.target, start);
        }

        start.visit();

        while (true) {
            Cell currentNode = closestReachableUnvisited(shortestPathMap);

            if (currentNode == null) {
                return null;
            }

            if (currentNode == end) {

                Cell child = end;
                path.push(child);
                while (true) {
                    Cell parent = changedAt.get(child);
                    if (parent == null) {
                        break;
                    }

                    path.push(parent);
                    child = parent;
                }
                return path;
            }
            currentNode.visit();

            for (Edge edge : currentNode.getEdges()) {
                if (edge.target.isVisited())
                    continue;

                if (shortestPathMap.get(currentNode)
                        + edge.lenght
                        < shortestPathMap.get(edge.target)) {
                    shortestPathMap.put(edge.target,
                            shortestPathMap.get(currentNode) + edge.lenght);
                    changedAt.put(edge.target, currentNode);
                }
            }
        }
    }
    private Cell closestReachableUnvisited(HashMap<Cell, Double> shortestPathMap) {

        double shortestDistance = Double.POSITIVE_INFINITY;
        Cell closestReachableNode = null;
        for (Cell node : cells/*nodes*/) {
           // //System.out.println(node.cellId);
            if (node.isVisited())
                continue;

            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.POSITIVE_INFINITY)
                continue;

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }

}
