package Routing;

import AStar.Node;
import java.util.*;

public class Route {
    private static double DEFALT_TIME_COST = 150;
    private static double DEFALT_UNIT_MATERIAL_COST = 1;
    private Node startNode;
    private Node endNode;
    private List<Node> path;
    private double timeCost;
    private double unitMaterialCost;
    private int length;
    private double cost;
    private Route parent;
    private boolean isNewRoute;

    public Route(List<Node> path, Node initialNode, int length, double timeCost, double unitMaterialCost) {
        this.path = path;
        this.length = length;
        this.timeCost = timeCost;
        this.unitMaterialCost = unitMaterialCost;
        startNode = path.get(0);
        endNode = path.get(path.size() - 1);
        checkIsNewRoute(initialNode);
        calCost();
    }

    public void checkIsNewRoute(Node initialNode) {
        isNewRoute = (startNode == initialNode);
    }

    public void calCost() {
        cost = length * unitMaterialCost;
        if (isNewRoute) {
            cost = cost + timeCost;
        }
    }

    public Route(List<Node> path, Node initialNode, int length) {
        this(path, initialNode, length, DEFALT_TIME_COST, DEFALT_UNIT_MATERIAL_COST);
    }

    public void setParent(Route parent) {
        this.parent = parent;
        if (parent != null) {
            length = length + parent.getLength();
        }
    }

    public Route getParent() {
        return parent;
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }

    public List<Node> getPath() {
        return path;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public double getCost() {
        return cost;
    }

    public boolean isNewRoute() {
        return isNewRoute;
    }

    @Override
    public String toString() {
        return "Route{" +
                "path=" + path +
                ", cost=" + cost +
                '}';
    }
}

