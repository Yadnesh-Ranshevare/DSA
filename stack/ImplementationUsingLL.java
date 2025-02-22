public class ImplementationUsingLL {
    static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    static class Stack{
        public static Node head;
        static int size = 0;

        public static boolean  isEmpty(){
            return  head == null;
        }

        public static void push(int data){
            Node NewNode = new Node(data);
            if(isEmpty()){
                head = NewNode;
                size++;
                return;
            }
            NewNode.next = head;
            head = NewNode; 
            size++;
        }

        public static int pop(){
            if(isEmpty()){
                return -1;
            }
            int top = head.data;
            head = head.next;
            size--;
            return  top;
        }

        public static int peek(){
            if(isEmpty()){
                return -1;
            }
            return  head.data; 
        }

        public static int size(){
            return size;
        }
    }

    public static void main(String[] args) {
        System.out.println("hello");
        Stack s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        System.out.println(s.size());
        while(!s.isEmpty()){
            System.out.println(s.peek());
            s.pop();
        }
    }
}
