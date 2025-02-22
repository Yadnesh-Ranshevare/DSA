# content
1. [introduction](#introduction)
2. [implementation using LinkedList](#implementation-using-linked-list)
# introduction
stack is a linear data structure that follows the Last In, **First Out (LIFO)** principle. This means that the last element added to the stack is the first one to be removed.

Think of a stack like a stack of plates: you add plates to the top of the stack, and when you need one, you take the top plate off.

### Key Operations of a Stack:
1. Push: Add an element to the top of the stack.
2. Pop: Remove the top element from the stack.
3. Peek (or Top): View the top element of the stack without removing it.
4. IsEmpty: Check if the stack is empty.
5. Size: Get the number of elements in the stack.
### Common Use Cases:
- Function Call Management: Stacks are used in managing function calls in recursion.
- Undo Operations: Many applications use stacks to keep track of the previous state for undo operations.
- Expression Evaluation: Stacks are used in parsing and evaluating expressions, such as infix, prefix, and postfix notations.
### implementation 
stack can be implemented ny using:-
1. Array:\
Because of the fix size of array stack stack will also become fix size due to which we can not add more data in stack. Therefor implementation of stack using array is haptic process 
2. ArrayList:\
first vacant place will act as top of the stack
3. LinkedList:\
head will act as top therefor newly added element will act as head that is the new element will be add at first


[Go to Top](#content)

---
# implementation using Linked List
in Linked List implementation of stack head will be act as a top therefor to push the data in Linked list we need to perform addFirst operation of Linked list and to pop the the data we need to perform deleteFirst operation of linked list 
## code:
```java
 static class Stack{
    public static Node head;
    static int size = 0;

    // to check for empty stack
    public static boolean  isEmpty(){
        return  head == null;
    }

    // Linked list add first operation
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

    // Linked list delete first operation
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
```


[Go to Top](#content)


---