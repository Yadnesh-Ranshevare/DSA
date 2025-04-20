import java.util.*;


public class HashmapUsingCollectionFramework {

    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<>();

        map.put("india",1000);
        map.put("us",500);
        map.put("china",2000);

        System.out.println(map);
        
        map.put("us",700);      //update
        
        System.out.println(map);

        if(map.containsKey("japan")){
            System.out.println("key is present in the map");
        }else{
            System.out.println("key is not present in the map");
        }


        System.out.println(map.get("india"));   // key exist
        System.out.println(map.get("japan"));   // key doesn't exist

        // iteration
        for(Map.Entry<String,Integer> e : map.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }

        Set<String> keys = map.keySet();
        System.out.println(keys);

        map.remove("china");
        System.out.println(map);

    }
    
}
