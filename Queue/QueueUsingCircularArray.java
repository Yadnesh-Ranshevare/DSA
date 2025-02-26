public class QueueUsingCircularArray {
    public static class queue{
        static int arr[];
        static int size;
        static int rear = -1;   //represent the end of the queue 
        static int front = -1;  //represent the start of the queue

        public queue(int n) {
            arr = new int[n];
            this.size = n;
        }

        public static boolean isEmpty(){
            return rear == -1 && front ==-1;
        }

        public static boolean  isFull(){
            return (rear+1)%size == front;
        }

        public static void add(int data){
            if(isFull()){
                System.out.println("queue is full");
                return;
            }
            rear = (rear + 1) % size;
            if(front == -1){
                front = 0;
            }
            arr[rear] = data;
        }

        public static int remove(){
            if(isEmpty()){
                System.out.println("queue is empty");
                return -1;
            }
            int result = arr[front];
            if(rear == front){
                rear = front = -1;
            }else{
                front = (front + 1) % size;
            }
            return result;
        }

        public static int peek(){
            if(isEmpty()){
                System.out.println("queue is empty");
                return -1;
            }
            return  arr[front];
        }

        public static void print(){
            while(!isEmpty()){
                System.out.println(peek());
                remove();
            }
        }
        

    }

    public static void main(String[] args) {

        queue q = new queue(5);
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.print();
    }
}
