import java.util.LinkedList;
public class collectionFramework {

    public static void main(String[] args) {
        LinkedList <Integer> list = new LinkedList <Integer> ();
        list.addFirst(1);
        list.add(2);
        list.add(3);
        list.addFirst(0);

        System.out.println(list);

        System.out.println(list.get(2));

        list.remove(1);
        System.out.println(list);
        
        list.removeLast();
        list.removeFirst();
        System.out.println(list);
    }
    
}
