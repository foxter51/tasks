import java.util.*;

public class Transportation {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        List<Node> inputList = new ArrayList<>();  //cities list
        inputCities(inputList);  //fill

        Graph graph = new Graph();

        graph.setNodes(new HashSet<>(inputList));  //graph create

        calculateShortestPathFromSource(inputList.get(0));  //calculating shortest distances

        Node destinationNode = null;

        while (destinationNode == null){
            System.out.print("Enter the destination city: ");
            String destinationCityName = in.nextLine();
            destinationNode = getNodeWithName(inputList, destinationCityName);  //get node to see the minimal cost
            if(destinationNode == null) System.out.println("Error!");
        }

        int minimalCost = destinationNode.getDistance();  //minimal cost to city

        System.out.println("Minimal cost from source to " +destinationNode.getCityName()+ ": " +minimalCost);
    }

    public static void inputCities(List<Node> inputList){
        int inputQuantity;

        System.out.print("Enter cities quantity: ");
        inputQuantity = in.nextInt();
        in.nextLine();
        System.out.println();

        for (int i = 0; i < inputQuantity; i++) {
            System.out.print("Enter city name["+(i+1)+"]: ");
            String cityName = in.nextLine();
            Node node = new Node(cityName);
            inputList.add(node);
        }

        for (int i = 0; i < inputList.size(); i++){
            Node currentCity = inputList.get(i);
            System.out.print("\nEnter number of neighbours of city "+currentCity.getCityName()+": ");
            int neighborNumber = in.nextInt();
            in.nextLine();
            if(neighborNumber == 0){
                continue;
            }
            if(neighborNumber < 0){
                System.out.println("Error!");
                i--;
                continue;
            }
            for (int j = 0; j < neighborNumber; j++) {
                System.out.print("Enter neighbor city name["+(j+1)+"]: ");
                String neighborName = in.nextLine();
                if(currentCity.getCityName().equals(neighborName)){
                    System.out.println("Error!");
                    j--;
                    continue;
                }
                Node neighborNode = getNodeWithName(inputList, neighborName);
                if(neighborNode != null){
                    System.out.print("Enter distance: ");
                    int distance = in.nextInt();
                    in.nextLine();
                    if(distance < 1){
                        j--;
                    }
                    else{
                        currentCity.addDestination(neighborNode, distance);
                    }
                }
                else{
                    System.out.println("Error!");
                    j--;
                }
            }
        }
    }

    public static void calculateShortestPathFromSource(Node source) {
        source.setDistance(0);  //initial distance

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {  //while all vertices won't be settled
            Node currentNode = getLowestDistanceNode(unsettledNodes);  //select the lowest distance node
            unsettledNodes.remove(currentNode);  //delete current node from unsettled
            for (Map.Entry<Node, Integer> adjacencyPair : currentNode.getNeighborNodes().entrySet()) {  //iterate neighbor nodes
                Node neighborNode = adjacencyPair.getKey();  //get node
                Integer edgeWeight = adjacencyPair.getValue();  //get edge weight
                if (!settledNodes.contains(neighborNode)) {  //if node isn't settled
                    calculateMinimumDistance(neighborNode, edgeWeight, currentNode);  //calculate distance from current to neighbour nodes
                    unsettledNodes.add(neighborNode);  //add this node to unsettled
                }
            }
            settledNodes.add(currentNode);  //settle this node
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {  //find the lowest distance node
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {  //if node distance < lowest distance
                lowestDistance = nodeDistance;  //then it's a new lowest distance
                lowestDistanceNode = node;  //and it's a new lowest distance node
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {  //if new distance is cheaper in cost
            evaluationNode.setDistance(sourceDistance + edgeWeight);  //set new distance
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());  //make a list with the shortest path
            shortestPath.add(sourceNode);  //add source node to path
            evaluationNode.setShortestPath(shortestPath);  //set new path
        }
    }

    public static Node getNodeWithName(List<Node> nodes, String cityName){
        for(Node node : nodes){
            if(node.getCityName().equals(cityName)){
                return node;
            }
        }
        return null;
    }
}
