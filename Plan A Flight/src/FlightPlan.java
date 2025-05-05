import java.util.*;
import java.io.*;

public class FlightPlan {
    public static void main(String[] args)
            throws java.io.IOException {

        String flightDataFile = "data.txt";
        String PathsToCalculateFile = "path.txt";
        String OutputFile = "output.txt";
        File dataFile = new File(flightDataFile);
        File pathFile = new File(PathsToCalculateFile);
        File outputFile = new File(OutputFile);

        String writeToFile = "";

        BufferedReader dataReader = new BufferedReader(new FileReader(dataFile));
        String str = dataReader.readLine();
        Integer n = Integer.parseInt(str);
        Graph graph = new Graph();
        for (int i = 0; i < n; i++) {
            str = dataReader.readLine();
            int j = 0;
            String cityFrom = "";
            while (str.charAt(j) != '|') {
                cityFrom += str.charAt(j);
                j++;
            }
            String cityTo = "";
            j++;
            while (str.charAt(j) != '|') {
                cityTo += str.charAt(j);
                j++;
            }

            graph.addEdge(cityFrom, cityTo);
        }

        BufferedReader pathReader = new BufferedReader(new FileReader(pathFile));
        str = pathReader.readLine();
        n = Integer.parseInt(str);
        for (int i = 0; i < n; i++) {
            str = pathReader.readLine();
            int j = 0;
            String cityFrom = "";
            while (str.charAt(j) != '|') {
                cityFrom += str.charAt(j);
                j++;
            }
            String cityTo = "";
            j++;
            while (str.charAt(j) != '|') {
                cityTo += str.charAt(j);
                j++;
            }
            j++;
            String preference = "";
            preference += str.charAt(j);
            if(preference.equals("T")){
                writeToFile += findPathTime(graph, dataFile, cityFrom, cityTo, i + 1);
            }
            else{
                writeToFile += findPathCost(graph, dataFile, cityFrom, cityTo, i + 1);
            }
        }
        //System.out.println(writeToFile);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(writeToFile);
        writer.close();

    }


    public static String findPathTime(Graph graph, File file, String start, String destination, int n)
            throws IOException{
        List<List<String>> allPaths = findAllPaths(graph, start, destination);

        String returnStatement = "";

        if(allPaths.isEmpty()){
            returnStatement += "Sorry! there is no flight available from " + start + " to " + destination + "\n";
        }
        else {
            returnStatement += "Flight "+ n + ": " +start +", "+destination +" " + " (Time)" + "\n";
            int i = 1;
            if(allPaths.size() <= 3) {
                for (List<String> path : allPaths) {
                    returnStatement += "Path " + i + ": ";
                    TotalTime cost = new TotalTime(path, file);
                    cost.getPrice();
                    returnStatement += cost + "\n";
                    i++;
                }
            }
            else{
                Integer[] intArr = new Integer[allPaths.size()];
                int j = 0;
                for (List<String> path : allPaths) {
                    TotalTime cost = new TotalTime(path, file);
                    intArr[j] = cost.getTotalTime();
                    j++;
                }
                QuickSort quickSort = new QuickSort();
                Integer[] sortedArr = quickSort.sort(intArr);

                for (int k = 0; k < 3; k++) {
                    for(List<String> path : allPaths){
                        TotalTime cost = new TotalTime(path, file);
                        cost.getPrice();
                        if(cost.getTotalTime().equals(sortedArr[k])){
                            returnStatement += "Path " + i + ": ";
                            returnStatement += cost + "\n";
                            i++;
                        }
                    }
                }
            }
        }
        return returnStatement + "\n";
    }



    public static String findPathCost(Graph graph, File file, String start, String destination, int n)
            throws IOException{

        List<List<String>> allPaths = findAllPaths(graph, start, destination);
        String returnStatement = "";
        if(allPaths.isEmpty()){
            returnStatement += "Sorry! there is no flight available from " + start + " to " + destination + "\n";
        }
        else {
            returnStatement += "Flight "+ n + ": " +start +", "+destination +" " + " (Cost)" + "\n";
            int i = 1;
            if(allPaths.size() <= 3) {
                for (List<String> path : allPaths) {
                    returnStatement += "Path " + i + ": ";
                    TotalCost cost = new TotalCost(path, file);
                    cost.getPrice();
                    //System.out.println(cost);
                    returnStatement += cost + "\n";
                    i++;
                }
            }
            else{
                Integer[] intArr = new Integer[allPaths.size()];
                int j = 0;
                for (List<String> path : allPaths) {
                    TotalCost cost = new TotalCost(path, file);
                    intArr[j] = cost.getTotalCost();
                    j++;
                }
                QuickSort quickSort = new QuickSort();
                Integer[] sortedArr = quickSort.sort(intArr);

                for (int k = 0; k < 3; k++) {
                    for(List<String> path : allPaths){
                        TotalCost cost = new TotalCost(path, file);
                        cost.getPrice();
                        if(cost.getTotalCost().equals(sortedArr[k])){
                            returnStatement += "Path " + i + ": ";
                            returnStatement += cost + "\n";
                            i++;
                        }

                    }


                }

            }
        }
        return returnStatement + "\n";
    }


    public static List<List<String>> findAllPaths(Graph graph, String start, String destination) {
        List<List<String>> allPaths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        Map<String, Boolean> visited = new HashMap<>();
        dfs(graph, start, destination, visited, currentPath, allPaths);
        return allPaths;
    }

    public static void dfs(Graph graph, String current, String destination, Map<String, Boolean> visited, List<String> currentPath, List<List<String>> allPaths) {
        visited.put(current, true);
        currentPath.add(current);

        if (current.equals(destination)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for (String neighbor : graph.adjList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.getOrDefault(neighbor, false)) {
                    dfs(graph, neighbor, destination, visited, currentPath, allPaths);
                }
            }
        }

        visited.put(current, false);
        currentPath.remove(currentPath.size() - 1);
    }
}