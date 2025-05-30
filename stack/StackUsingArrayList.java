import java.util.ArrayList;
public class StackUsingArrayList {
    static  class Stack{
        static ArrayList<Integer> list = new ArrayList<>();
        static int size = 0;

        public static boolean  isEmpty(){
            return list.size() == 0;
        }

        public static void push(int data){
            list.add(data);
            size++;
        }

        public static int pop(){
            if(isEmpty()){
                return -1;
            }
            int top = list.get(list.size()-1);
            list.remove(list.size()-1);
            size--;
            return top; 
        }

        public static int peek(){
            if(isEmpty()){
                return -1;
            }
            return list.get(list.size()-1);
        }
        public static int size(){
            return  size;
        }
    }
    public static void main(String[] args) {
        Stack s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        while(!s.isEmpty()){
            System.out.println(s.peek());
            s.pop();
        }
    }
}
