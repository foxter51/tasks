import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
    private String cityName;
    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    private Map<Node, Integer> neighborNodes = new HashMap<>();

    public Node(String cityName) {
        this.cityName = cityName;
    }

    public void addDestination(Node destination, int distance){
        neighborNodes.put(destination, distance);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getNeighborNodes() {
        return neighborNodes;
    }

    public void setNeighborNodes(Map<Node, Integer> neighborNodes) {
        this.neighborNodes = neighborNodes;
    }
}
