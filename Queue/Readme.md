# content
1. [Introduction](#introduction)
2. [Implementation using Array](#implementation-using-array)

# Introduction:
In Data Structures and Algorithms (DSA), a queue is an abstract data type (ADT) that follows the First In First Out (FIFO) principle. This means that the first element added to the queue will be the first one to be removed. Think of it like a line at a ticket counter where the first person to get in line is the first one to be served.

In queue element enter form the `rear(end)` and exit fromm the `front(start)`


### time complexities
- add: O[1]
- remove: O[n]
- peek: O[1]

### Main operations in a queue:
- **Enqueue:** Add an element to the end of the queue.
- **Dequeue:** Remove an element from the front of the queue.
- **IsEmpty:** Check if the queue is empty.
- **IsFull:** Check if the queue is full (in case of a fixed-size queue).
- **Size:** Get the number of elements currently in the queue.
### Types of queues:
- **Linear Queue:** Elements are added at the rear and removed from the front, but there can be wasted space in the queue as elements move forward.
- **Circular Queue:** In a circular queue, the last element is connected back to the first element. This ensures no wasted space when elements are dequeued, and the queue is used efficiently.
- **Priority Queue:** Elements are dequeued based on priority rather than the order in which they were added.
- **Double-ended Queue (Deque):** Elements can be added or removed from both ends of the queue (front and rear).
### Queues are commonly used in scenarios like:

- **Job scheduling** in operating systems.
- **Breadth-first search (BFS)** in graph algorithms.
- **Print spooling** in printers.

[Go to Top](#content)

---

# implementation using Array
As array is of fixed size we can create a queue of fix size using array and to do that:
1. initialize the Queue class with following attribute:
```java
static class Queue{
    static int arr[];
    static int size;
    static int rear = -1;   //as rear represent the last filled index of array at first it will be -1 as queue is empty
    Queue(int n){
        arr = new int[n];
        this.size = n;
    }
}
```
2. Now whenever you created an object of Queue class a fix queue will be initialize
```java
Queue q = new Queue(5); //queue of size 5
``` 
### to check queue is empty or not:
```java
public static boolean isEmpty(){
    //as rear = -1; represent the empty queue
    return rear == -1;      
}
```

## add operation O[1]
**algorithm**
1. check is Array full or not
2. if full then print "queue is full" and return
3. if not increment the rear by 1 ad add the data at the rear 
```
// rare = -1;
    [  |  |  ]
 ^
rear

//rare = rear + 1:
[  |  |  ]
  ^
 rear

//arr[rear] = 5;
[ 5 |  |  ]
  ^
rear
```

### code
```java
public static void add(int data){
    if(rear == size-1){
        System.out.println("queue is full");
        return;
    }
    rear++;
    arr[rear] = data;
}
```

## remove Operation O[n]
**algorithm**
1. check is Array empty or not 
2. if isEmpty then print "queue is empty" and return
3. if not, get the first element of the Array
```
[1 | 2 | 3 | 4 | 5]     // before step three

[  | 2 | 3 | 4 | 5]     // after step three
front = 1
```
4. move the remaining Array 1 index ahead
```
[ 2 | 3 | 4 | 5 |  ]
                  ^
                 rear
```
5. update the rear and return first element
```
// rear = rear - 1;
[ 2 | 3 | 4 | 5 |  ]
              ^
             rear
```
#### code
```java
public static int remove(){
    if(isEmpty()){
        System.out.println("queue is empty");
        return -1;
    }
    int front = arr[0];
    for(int i = 0; i<rear; i++){
        arr[i] = arr[i+1];
    }
    rear--;
    return front;
}
```

## peek Operation O[1]
**Algorithm**
1. first is Array empty or not
2. if empty print "queue is empty" and return
3. if not return the first element of an array
```java
public static int peek(){
    if(isEmpty()){
        System.out.println("queue is empty");
        return -1;
    }
    return arr[0];
}
```

### Complete code:
```java
public class QueueUsingArray{
    static class Queue{
        static int arr[];
        static int size;
        static int rear = -1;
        
        Queue(int n){
            arr = new int[n];
            this.size = n;
        }

        public static boolean isEmpty(){
            return rear == -1;
        }

        public static void add(int data){
            if(rear == size-1){
                System.out.println("queue is full");
                return;
            }
            rear++;
            arr[rear] = data;
        }

        public static int remove(){
            if(isEmpty()){
                System.out.println("queue is empty");
                return -1;
            }
            int front = arr[0];
            for(int i = 0; i<rear; i++){
                arr[i] = arr[i+1];
            }
            rear--;
            return front;
        }

        public static int peek(){
            if(isEmpty()){
                System.out.println("queue is empty");
                return -1;
            }
            return arr[0];
        }   
    }

    public static void print(Queue q){
        while(!q.isEmpty()){
            System.out.println(q.peek());
            q.remove();
        }
    }
    
    public static void main(String[] args) {
        Queue q = new Queue(5);
        q.add(1);
        q.add(2);
        q.add(3);
        print(q);
    }
}
```

[Go to Top](#content)

---

