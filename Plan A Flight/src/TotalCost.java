import java.io.*;
import java.util.*;

public class TotalCost {
    List<String> destination = new ArrayList<String>();
    File dataFile = new File("");
    Integer totalTime = 0;
    Integer totalCost = 0;

    TotalCost(List<String> destiny, File fileIn){
        destination = destiny;
        dataFile = fileIn;
    }
    TotalCost(){}

    public Integer priceRead(String from, String to)
            throws IOException {
        Integer Cost = 0;
        BufferedReader dataReader = new BufferedReader(new FileReader(dataFile));
        String str = dataReader.readLine();
        Integer n = Integer.parseInt(str);
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
            j++;
            String flightCost = "";
            while (str.charAt(j) != '|') {
                flightCost += str.charAt(j);
                j++;
            }
            Integer cost = Integer.parseInt(flightCost);

            if((cityTo.equals(from)) && cityFrom.equals(to)){
                Cost = cost;
                break;
            }
            else if((cityTo.equals(to)) && cityFrom.equals(from)){
                Cost = cost;
                break;
            }
            else{
                continue;
            }

        }
        dataReader.close();
        return Cost;

    }

    public Integer timeRead(String from, String to)
            throws IOException {
        Integer Time = 0;
        BufferedReader dataReader = new BufferedReader(new FileReader(dataFile));
        String str = dataReader.readLine();
        Integer n = Integer.parseInt(str);
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
            j++;
            String flightCost = "";
            while (str.charAt(j) != '|') {
                flightCost += str.charAt(j);
                j++;
            }
            Integer cost = Integer.parseInt(flightCost);
            j++;
            String flightTime = "";
            for (int k = j; k < str.length(); k++) {
                flightTime += str.charAt(k);
            }
            Integer time = Integer.parseInt(flightTime);

            if((cityTo.equals(from)) && cityFrom.equals(to)){
                Time = time;
                break;
            }
            else if((cityTo.equals(to)) && cityFrom.equals(from)){
                Time = time;
                break;
            }
            else{
                continue;
            }

        }
        dataReader.close();
        return Time;

    }

    public void getPrice()
            throws IOException{
        Integer cost = 0;
        for(int i = 0; i < destination.size() - 1; i++){
            cost += priceRead(destination.get(i), destination.get(i + 1));
        }

        Integer time = 0;
        for(int i = 0; i < destination.size() - 1; i++){
            time += timeRead(destination.get(i), destination.get(i + 1));
        }
        totalCost = cost;
        totalTime = time;
    }

    public Integer getTotalCost()
            throws IOException{
        getPrice();
        return totalCost;
    }


    @Override
    public String toString(){
        String str = destination.get(0);
        for(int i = 0; i < destination.size() - 1; i++){
            str += " -> " + destination.get(i + 1);
        }
        str += ". Time: " + Integer.toString(totalTime) + " Cost: " + Integer.toString(totalCost) + ".00";
        return str;
    }

}