package Routing;

public class test2 {
    public static void main(String[] args) {
        try {
            Route route = new Route(null, null, 0);
        } catch (NullPointerException e) {
            System.out.println("null");
        }
    }
}
