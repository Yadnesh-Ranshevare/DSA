# CONTENT
1. [Introduction](#introduction)
2. [initialize](#how-to-initialize-linked-list)
3. [Add First](#add-first)
4. [print linked list](#print)
5. [Add last](#add-last)
6. [delete first](#delete-first)
7. [delete last](#delete-last)
8. [Linked List package](#linkedlist-using-package)
9. [reverse the LinkedList](#reverse-the-linkedlist)

---

# INTRODUCTION
A linked list is a data structure used in computer science to organize and store data. Unlike arrays, where elements are stored contiguously in memory, a linked list stores elements (called nodes) that are connected to each other in a sequence using pointers or references.

Each node in a linked list contains two parts:
1. **Data:** The value or information stored in the node.
2. **Next:** A reference (or pointer) to the next node in the list.

The linked list structure can be visualized like a chain of nodes, where each node points to the next one. The last node in the list points to `null` (or `None` in Python) to indicate the end of the list.

### Types of Linked Lists:
1. **Singly Linked List:** Each node points to the next node in the list. It’s a one-way link, meaning you can only traverse the list in one direction.

2. **Doubly Linked List:** Each node contains two references: one pointing to the next node and another pointing to the previous node. This allows traversal in both directions.

3. **Circular Linked List:** The last node points back to the first node, forming a loop.

### Advantages of Linked Lists:
- **Dynamic size:** You can easily add or remove elements without worrying about the size of the data structure ahead of time, unlike arrays which have a fixed size.
- **Efficient insertions and deletions:** Especially in cases where you need to insert or delete nodes in the middle of the list.

### Disadvantages:
- **More memory usage:** Each node requires extra memory to store the pointer/reference to the next (and possibly previous) node.
- **Access time:** To access a specific element, you must traverse the list from the start, which can be slower compared to arrays where you can directly access an index.

[Go to Top](#content)

---

# HOW TO INITIALIZE LINKED LIST  

```java
public class Linked_list {
    Node head;
    class Node{
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    public static void main(String[] args) {
        Linked_list list = new Linked_list();
    }
}
```
- as next represent the next node of the linked list its data will be Node as well
- whenever you create the object for current class you initialize a new empty Linked list
- head represent the starting point of the linked list

[Go to Top](#content)

---

# ADD FIRST

**algorithm:**
1. create the new node with data
2. check is there any linked list present or not
3. if no linked list present the make current node head and return
4. if linked present then current node.next becomes head (fist node of linked list) and  make the current node head 
```java
 public void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }
```

[Go to Top](#content)

---

# PRINT 
**algorithm**
1. find the head of the LL
2. traverse over the LL form head to tail(end) i.e util we gen null node
3. print Node.data during each iteration  
```java
public void printList(){
        Node currNode = head;
        while(currNode != null){
            System.out.println(currNode.data);
            currNode = currNode.next;
        }
        System.out.println("null");
    }
```

[Go to Top](#content)

---

# add last
**algorithm**
1. create the new Node with the data
2. check is there any linked list present or not
3. if no linked list present the make current node head and return 
4. if LL present the traverse over the LL till we find the last Node 
5. set the next pointer of last node to newNode
 ```java
 public void  addLast(int data){
        Node newNode = new Node(data); 
        if (head == null){
            newNode = head;
            return;
        }
        Node currNode = head;
        while(currNode.next != null){
            currNode = currNode.next;
        }
        currNode.next = newNode;
    }
```

[Go to Top](#content)

---
# Delete first
**algorithm**
1. check if head == null then list is empty
2. else shift the head to its next Node
```java
public void deleteFirst(){
        if(head==null){
            System.out.println("list is empty");
            return;
        }
        head = head.next;
    }
```

[Go to Top](#content)

---

# Delete last

 
**algorithm**
1. check if head == null then list is empty
2. if head.next == null that means that there is only one Node present in LL which should be deleted
3. initiate secondLastNode with first Node(head) value and lastNode with second Node(head.next) of the LL
3. traverse over the LL and keep track of the secondLast Node(to shift its .next value to null) with the help of lastNode
4. during eac iterate update the secondLastNode and lastNode to its respective next Node
5. when lastNode.next == null then lastNode has successfully reach the lastNode in original LL and secondLastNode is at the second last node
6. shift the secondLastNode.next pointer to null 

```java
public void deleteLast(){
        if(head==null){
            System.out.println("list is empty");
            return;
        }
        if(head.next==null){
            head = null;
            return;
        }

        Node secondLastNode = head;
        Node lastNode = head.next;
        while (lastNode.next != null){
            lastNode = lastNode.next;
            secondLastNode = secondLastNode.next;
        }
        secondLastNode.next = null;
}
```
[Go to Top](#content)

---


# LinkedList Using Package
```java
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
```

- **`.add():`** to add last
- **`.addFirst():`** to add first
- **`.get(i):`** to get the element at index i
- **`.removeFirst():`** to remove the first element
- **`.removeLast():`** to remove the last element
- **`.remove(i):`** to remove the element at i'th index

[for more info of LinkedList methods visit](https://www.geeksforgeeks.org/linked-list-in-java/)

[Go to Top](#content)

---

# Reverse the LinkedList
### iterative approach
using three pointer approach
1. **`prevNode:`** represent the previous Node of LL at first it will be a head of LL
2. **`currNode:`** represent the current Node of the LL at first it will be the 2nd node(head.next) of the LL
3. **`nextNode:`** represent the next node of the currNode

**algorithm**
1. traverse over the LL and change the next pointer of currNode to prevNode while keeping the track of the nextNode
2. update prevNode to currNode and CurrNode to nextNode 
3. change the position of head

```java
public void reverseIterate(){
    // corner case for empty or single valued LL
    if(head == null || head.next == null){
        return;
    }

    Node prevNode = head;
    Node currNode = head.next;
    
    while(currNode != null){
        Node nextNode = currNode.next; // as we Don't need nextNode outside this loop we declare it inside the loop
        currNode.next = prevNode;
        prevNode = currNode;
        currNode = nextNode; // as currNode.next = prevNode we need to use another Node to keep the trace of the nextNode
    }

    head.next = null;
    head = prevNode;
}
```
### Recursive Approach
- `head.next.next:` \
les assume LL `1 -> 2 -> 3 -> null` if 1 is head then 2 is head.next and 3 is head.next.next that is next Node of head.next
- `head.next.next  = head`\
in previous example head.next is 2 and head is 1 therefor hed.next.next = head means next of node 2 is changes to head that is node 1 (head.next.next = head is use to reverse the link of the Node that is if `1 -> 2` will be changes to `1 <- 2`)  

**algorithm**
1. get the last Node of the LL 
2. reverse the link of last Node using head.next.next = head
3. remove the link of second last node ( head.next = null )
4. return the last Node as head of reverse LL
```java
public Node reverseRecursive(Node head){
    
    // corner case and base case
    if (head == null || head.next == null){
        return  head;
    }

    Node newHead = reverseRecursive(head.next);
    head.next.next  = head;
    head.next = null;
    return newHead;
}
```


[Go to Top](#content)

---