# content
1. [Introduction](#introduction)
2. [Implementation using Array](#implementation-using-array)
3. [Implementation Using Circular Array](#implementation-using-circular-array)

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

# Implementation Using Circular Array

### Circular Array:
**A circular array is a linear data structure where the last element is connected to the first element, forming a circle or loop. This means that when you reach the end of the array, you can continue from the beginning,**

**Example**
1. consider a following array
```
[1 | 2 | 3 | 4 | 5]
 ^               ^
front           rear
```
2. remove 1 and move front from the `0th` index to `1st` index
```
[  | 2 | 3 | 4 | 5]
     ^           ^
    front       rear
```
3. remove 2 and move front from the `1st` index to `2nd` index
```
[  |   | 3 | 4 | 5]
         ^       ^
        front   rear
```
4. add 6 ( to add six we first need to move the rear at the 0th index then we can add the six as in queue we can only add the element from the rear(end) )
```
[ 6 |   | 3 | 4 | 5]
  ^       ^       
 rear    front   
```

### some conditions
#### `rear = ( rear + 1 ) % size` and `front = ( front + 1 ) % size`
this are the condition for updating the rear and front pointer of circular array this works similar to `n = n + 1` in between the array but once we reach at the end of an array this condition update the pointer back to 0th index\
example: 
1. consider the following array fo size 5 with 3 empty slots
```
[  |   | 3 | 4 |   ]
         ^   ^
      front  rear
```
2. to add 5 we update `rear = (rear + 1) % size( rear = (3 + 1) % 5 this implies rear = 4 )`  then add 5
```
[  |   | 3 | 4 | 5 ]
         ^       ^
      front      rear
```
3. to add 6 we again apply `rear = (rear + 1) % size( rear = (4 + 1) % 5 this implies rear = 0 )`  then add 6
```
[ 6 |   | 3 | 4 | 5 ]
  ^       ^       
rear     front      
```

#### `(rear + 1) % size == front`
this condition indicate that the array is full
```
[ 6 | 7 | 3 | 4 | 5 ]
      ^   ^       
    rear front      
```
here `(rear + 1) % size = 3` also `front = 3` therefor array is full

### Initialization
```java
 public static class queue{
    static int arr[];
    static int size;
    static int rear = -1;   //represent the end of the queue 
    static int front = -1;  //represent the start of the queue

    public queue(int n) {
        arr = new int[n];
        this.size = n;
    }
}
```
as at first array is empty both rear and front is start with -1


### ISEmpty operation
```java
public static boolean isEmpty(){
    return rear == -1 && front ==-1;
}
```
as both `rear == -1` and `rear == -1` represent the empty array

### isFull operation
```java
public static boolean  isFull(){
    return (rear+1)%size == front; 
    //this condition indicate that the array is full
}
```

### add operation
**Algorithm**
1. check is array full or not
2. if full print "queue is full" and return
3. if not then update the rear pointer 
4. add the data at rear

**special condition**\
since  front and rear initially set to -1 to represent the empty queue we need to update the front pointer as well as we add the data only for the first time in the queue
```
// before adding the first element
    [  |  |  |  |  ]
  ^         
front\rear = -1

//after adding the first element
[ 1 |  |  |  |  ]
  ^         
front\rear = 0     
```
```java
public static void add(int data){
    if(isFull()){
        System.out.println("queue is full");
        return;
    }
    rear = (rear + 1) % size;
    if(front == -1){    //special condition
        front = 0;
    }
    arr[rear] = data;
}
```
### remove operation
**Algorithm**
1. check is array empty or not
2. if empty them print "queue is empty"
3. take the value store at the front pointer of the queue
4. update the front pointer and return the stored value

**special condition**\
when you first time add the vale both rear and front is set to 0( **the only point where front and rear becomes equal** ) there in this case while performing the remove updating the only  rear is not enough therefor we need to update the front as well
```
[ 1 |  |  |  |  ]
  ^         
rear\rear      
```
```java
if(rear == front){
    rear = front = -1;
}
```
#### code:
```java
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
```
### peek operation
**Algorithm**
1. check is array empty or not 
2. if empty then print "queue is empty"
3. if not then return the element at front
```java
public static int peek(){
    if(isEmpty()){
        System.out.println("queue is empty");
        return -1;
    }
    return  arr[front];
}
```


### Complete code
```java
public class QueueUsingCircularArray {
    public static class queue{
        static int arr[];
        static int size;
        static int rear = -1;   
        static int front = -1;  

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
        //method to print the queue
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
```

[Go to Top](#content)