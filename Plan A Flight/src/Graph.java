import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    Map<String, List<String>> adjList;
    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(String source, String destination) {
        adjList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        adjList.computeIfAbsent(destination, k -> new ArrayList<>()).add(source); // for an undirected graph

    }


}