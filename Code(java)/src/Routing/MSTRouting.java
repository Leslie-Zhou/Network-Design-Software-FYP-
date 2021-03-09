package Routing;

import AStar.AStar;
import AStar.Node;
import java.util.*;

public class MSTRouting {
    //private static Route DEFALT_ROUTE = new Route(null,null,0);
    private Node dataCabinet;
    private List<Node> dataPoints;
    private Set<Node> startSet;
    private List<Node> endList;
    private List<Route> finalRoutes;
    private Route[][] routes;
    private AStar astar;
    private int rows;
    private int cols;
    private int numOfPoints;

    public MSTRouting(Node dataCabinet, List<Node> dataPoints, int rows, int cols, int[][] blockArray) {
        this.cols = cols;
        this.rows = rows;
        this.dataCabinet = dataCabinet;
        this.dataPoints = dataPoints;
        startSet = new HashSet<>();
        startSet.add(dataCabinet);
        endList = this.dataPoints;
        this.numOfPoints = this.dataPoints.size() + 1;
        this.routes = new Route[numOfPoints][numOfPoints];
        //this.astar = new AStar(rows, cols, dataCabinet, dataPoints.get(0));
        //this.astar.setBlocks(blockArray);
        this.finalRoutes = new LinkedList<>();

    }

    public List<Route> findRoute() {
        //Route currentRoute;
        HashMap<Node, Route> parentMap = new HashMap<Node, Route>();
        Route shortRoute;

        while(!endList.isEmpty()) {
            shortRoute = findSingleRoute(parentMap);
            // add the short route to the final routes list
            finalRoutes.add(shortRoute);

            if(shortRoute.isNewRoute()) {
                startSet.clear();
                startSet.add(dataCabinet);
                endList = dataPoints;
                for(Iterator<Route> iter = finalRoutes.iterator(); iter.hasNext();) {
                    Route route = iter.next();
                    if(route.getStartNode() != dataCabinet) {
                        //endList.add(route.getEndNode());
                        finalRoutes.remove(iter);
                    } else {
                        //startSet.add(route.getEndNode());
                        //startSet.add(new Node(route.getEndNode().getRow(), route.getEndNode().getCol()));
                        endList.remove(route.getEndNode());
                    }
                }
            } else {
                // add a start node
                //startSet.add(shortRoute.getEndNode());
                if(shortRoute.getStartNode() != dataCabinet) {
                    // remove the start node if it isn't the initial node
                    startSet.remove(shortRoute.getStartNode());
                }

            }
            Node tempEndNode = new Node(shortRoute.getEndNode().getRow(), shortRoute.getEndNode().getCol());
            parentMap.put(tempEndNode, shortRoute);
            // remove an end node
            endList.remove(shortRoute.getEndNode());
            //startSet.add(shortRoute.getEndNode());
            startSet.add(tempEndNode);
        }

        return finalRoutes;
    }

    private Route findSingleRoute(HashMap<Node, Route> parentMap) {
        Route currentRoute;
        //HashMap<Node, Route> parentMap = new HashMap<Node, Route>();
        Route parentRoute;
        Route shortRoute = null;
        for(Node startNode : startSet) {
            parentRoute = parentMap.get(startNode);
            for (Node endNode : endList) {
                /*
                if(routes[startNode.getId()][endNode.getId()] == null) {
                    currentRoute = p2pRouting(startNode, endNode);
                    currentRoute.setParent(parentRoute);
                    currentRoute.calCost();
                    routes[startNode.getId()][endNode.getId()] = currentRoute;
                    //parentRoute = currentRoute;
                } else {
                    currentRoute = routes[startNode.getId()][endNode.getId()];
                }

                */

                currentRoute = p2pRouting(startNode, endNode);
                currentRoute.setParent(parentRoute);
                currentRoute.calCost();

                if (shortRoute == null || currentRoute.getCost() < shortRoute.getCost()) {
                        shortRoute = currentRoute;
                }
            }
        }

        return shortRoute;
    }

    private Route p2pRouting(Node startNode, Node endNode) {
        astar = new AStar(rows, cols, startNode, endNode);
        //astar.setInitialNode(startNode);
        //astar.setFinalNode(endNode);
        List<Node> path = astar.findPath();
        return new Route(path, dataCabinet, astar.getLength());
    }

    public Set<Node> getStartSet() {
        return startSet;
    }
}
