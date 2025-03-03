import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueUsingCollectionFramework{

    public static void print(Queue q){
        while(!q.isEmpty()){
            System.out.print(q.peek()+ " ");
            q.remove();
        }
        System.out.println();
    } 
    public static void main(String[] args) {
        Queue<Integer> LL = new LinkedList<>();
        LL.add(1);
        LL.add(2);
        LL.add(3);
        LL.add(4);
        LL.add(5);
        print(LL);

        Queue<Integer> AA = new ArrayDeque<>();
        AA.add(1);
        AA.add(2);
        AA.add(3);
        AA.add(4);
        AA.add(5);
        print(AA);
        
    }
}