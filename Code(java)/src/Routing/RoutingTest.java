package Routing;

import AStar.Node;
import java.util.*;

public class RoutingTest {
    public static void main(String[] args) {
        Node dataCabinet = new Node(0, 2, 0);
        int rows = 4;
        int cols = 6;
        Node point1 = new Node(2,0, 1);
        Node point2 = new Node(3, 2, 2);
        Node point3 = new Node(3, 4, 3);
        Node point4 = new Node(1, 5, 4);
        List<Node> dataPoints = new LinkedList<>();

        dataPoints.add(point1);
        dataPoints.add(point2);
        dataPoints.add(point3);
        dataPoints.add(point4);

        MSTRouting myRouting = new MSTRouting(dataCabinet, dataPoints, rows, cols, null);
        List<Route> finalRoute = myRouting.findRoute();

        for(Route route : finalRoute) {
            System.out.println(route);
            System.out.println("Start Node: " + route.getStartNode());
            System.out.println("End Node: " + route.getEndNode());
        }
        System.out.println(myRouting.getStartSet());

    }
}
