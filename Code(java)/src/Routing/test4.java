package Routing;

import AStar.*;

import java.util.*;

public class test4 {

    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;
        Node node1 = new Node(0, 2);
        Node node2 = new Node(2,0);
        Node node3 = new Node(3,2);
        Node node = new Node(0,0);

        List<Node> nodeList = new LinkedList<Node>();

        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);

        List<List<Node>> paths = new ArrayList<>();

        /*
        for(Node thisNode : nodeList) {
            AStar astar = new AStar(rows, cols, thisNode, node);
            paths.add(astar.findPath());
        }

         */

        AStar astar = new AStar(rows, cols, node1, node2);
        paths.add(astar.findPath());
        astar = new AStar(rows, cols, node2, node3);
        paths.add(astar.findPath());


        for(List<Node> path : paths) {
            System.out.println(path);
        }

    }
}
