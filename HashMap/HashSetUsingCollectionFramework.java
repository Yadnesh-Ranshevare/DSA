import java.util.HashSet;
import java.util.Iterator;

public class HashSetUsingCollectionFramework {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);   
        set.add(2);   
        set.add(3);   
        set.add(4);   
        set.add(1);   

        System.out.println("size of the set is: "+set.size());

        System.out.println(set);

        if(set.contains(1)){
            System.out.println("set contains 1");
        }
        if(!set.contains(5)){
            System.out.println("set does not contain 5");
        }
        
        set.remove(1);

        if(!set.contains(1)){
            System.out.println("set does not contain 1");
        }

        Iterator it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
