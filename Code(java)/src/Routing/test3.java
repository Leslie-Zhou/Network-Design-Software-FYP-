package Routing;

import AStar.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class test3 {
    public static void main(String[] args) {
        Node point1 = new Node(2,0);
        Node point2 = new Node(3, 2);
        Node point3 = new Node(3, 4);
        Node point4 = new Node(1, 5);
        Node point1_ = new Node(2,0);

        List<Node> a = new LinkedList<>();
        a.add(point1);
        a.add(point2);
        a.add(point3);
        a.remove(point1_);
        a.remove(point1);
        a.remove(point2);
        a.remove(point3);

        if(a.isEmpty()) {
            System.out.println("empty");
        }
    }
}
