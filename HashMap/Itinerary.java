// find itinerary from tickets
    // "Chennai" -> "Bengaluru"
    // "Mumbai" -> "Delhi"
    // "Goa" -> "Chennai"
    // "Delhi" -> "goa"
// Itinerary means a plan or schedule


import java.util.*;
public class Itinerary {

    public static String getStart(HashMap<String,String> originalMap){
        HashMap<String,String> revMap = new HashMap<>();

        for (String key : originalMap.keySet()){
            revMap.put(originalMap.get(key) , key);
        }

        for (String key : originalMap.keySet()){
            if(!revMap.containsKey(key)){
                return key;
            }
        }
        return  null;
    }

    public static void printPath(HashMap<String,String> originalMap){
        String startingPoint = getStart(originalMap);

        while(originalMap.containsKey(startingPoint)){
            System.out.print(startingPoint + " -> ");
            startingPoint = originalMap.get(startingPoint );
        }

        System.out.println(startingPoint);
    }


    public static void main(String[] args) {
        HashMap<String,String> tickets = new HashMap<>();

        tickets.put("Chennai", "Bengaluru");
        tickets.put("Mumbai", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");

        printPath(tickets);

       
    }
}
