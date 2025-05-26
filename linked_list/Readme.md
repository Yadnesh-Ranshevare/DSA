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
10. [rotate a LL](#rotate-a-linkedlist)
11. [palindrome LL](#palindrome-linkedlist)
12. [swap adjacent node in LL](#swap-adjacent-node-in-ll)
13. [Add two Numbers](#add-two-numbers)

---

# INTRODUCTION
A linked list is a data structure used in computer science to organize and store data. Unlike arrays, where elements are stored contiguously in memory, a linked list stores elements (called nodes) that are connected to each other in a sequence using pointers or references.

Each node in a linked list contains two parts:
1. **Data:** The value or information stored in the node.
2. **Next:** A reference (or pointer) to the next node in the list.

The linked list structure can be visualized like a chain of nodes, where each node points to the next one. The last node in the list points to `null` (or `None` in Python) to indicate the end of the list.

### Time complexities
- addFirst: O[1]
- addLast: O[n]
- deleteFirst: O[1]
- deleteLast: O[n]

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
- as next represent the next node of the linked list its data type will be Node as well
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


**example:**
1. pointer allocation
```
1 -> 2 -> 3 -> 4 -> 5 -> null
^    ^    ^
prv curr  next
```
2. ` currNode.next = prevNode;`
```
1 <- 2 -> 3 -> 4 -> 5 -> null
^    ^    ^
prv curr  next
```
3. updating the pointer
```
1 <- 2 -> 3 -> 4 -> 5 -> null
     ^    ^    ^
     prv curr  next
```
4. repeat the step 2 & 3 until you reach the end pf LL 

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
        currNode = nextNode; // as currNode.next = prevNode we need to use another Node to keep the track of the nextNode
    }

    head.next = null;
    head = prevNode;
}
```
### Recursive Approach
- `head.next.next:` \
les assume LL `1 -> 2 -> 3 -> null` if 1 is head then 2 is head.next and 3 is head.next.next that is next Node of head.next
- `head.next.next  = head`\
in previous example head.next is 2 and head is 1 therefor hed.next.next = head means next of node 2 is changes to head that is node 1 (head.next.next = head is use to reverse the link of the Node that is if `1 -> 2` will be changes to `1 <-> 2` note that `1 -> 2` link is still exist)  

**algorithm**
1. get the last Node of the LL recursively also keep the track of removed last node during each recursive call 
2. reverse the link of last Node using head.next.next = head
3. remove the link of second last node ( head.next = null )
4. return the last Node as head of reverse LL

**example:**
1. consider a LL
```
1 -> 2 -> 3 -> 4 -> 5 -> null
```

#### first recursive call
1. consider its last node as head also act as current newHead
```
5 -> null
```
2. apply `head.next.next  = head`
```
5 <- null
```
3. apply `head.next = null`
```
null <- 5 
```
#### second recursive call
1. this time second last will de the head which is pointing towards 5 ( last node )
```
 4 -> 5 -> null 
 ^
head
```
2. apply `head.next.next  = head` also 4 is still pointing towards 5
```
4 <-> 5
```
 3. apply `head.next = null`
```
null <- 4 <- 5 
```
#### third recursive call
1. this time third last will de the head which is pointing towards 4 ( second last node )
```
 3 -> 4 <- 5
 ^    |
head  V
     null  
```
2. apply `head.next.next  = head` also 3 is still pointing towards 4
```
3 <-> 4 <- 5
```
 3. apply `head.next = null`
```
null <- 3 <- 4 <- 5 
```
#### continue this cycle until LL set reverse

### code

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

# Rotate a LinkedList
- Given the head of a linked list, rotate the list to the right by n places.
### Approach:
1. make the last Node of the LL head 
2. repeat this step n times recursively with new heads

### Example
1. assume following LL 
```
1 -> 2 -> 3 -> 4 -> 5 -> null
```
2. make 5 ( last node of the LL ) head of the LL and make 4(2nd last node of the LL ) point towards null for first rotation
```
5 -> 1 -> 2 -> 3 -> 4 -> null
```
3. make 4 new head and let 3 point towards null for second rotation
```
4 -> 5 -> 1 -> 2 -> 3 -> null
```
### code
```java
public void rotate(int n){
    // base condition
    if(n == 0){
        return;
    }
    // corner case for single value or empty LL
    if(head.next == null || head == null){
        System.out.println("can't rotate the LL");
        return ;
    }
    Node prevNode = head;  //node who is going to point towards null
    Node currNode = head.next;  //node who is going to be new head
    while(currNode.next != null){
        prevNode = prevNode.next;
        currNode = currNode.next;
    }
    currNode.next = head;
    head = currNode;
    prevNode.next = null;
    rotate(n-1);
}
```
[Go to Top](#content)

--- 

# palindrome LinkedList
### Approach:
split the LL in two half compare the first half of the LL with the reverse of the second half if both are equal then LL is palindrome
#### example:
1. consider a LL
```
1 -> 2 -> 3 -> 3 -> 2 -> 1 ->  null
```
2. split this LL in exactly two half
```
1 -> 2 -> 3 -> null     <!-- first half-->
3 -> 2 -> 1 -> null     <!-- second half-->
``` 
3. reverse the second half and compare it with first half
```
1 -> 2 -> 3 -> null 
1 -> 2 -> 3 -> null     <!-- reverse -->
``` 
### how to find the mid of LL 
- **hare-turtle approach:**\
hare moves with two nodes per round and turtle moves with single node per round when hare reach the end of the LL turtle reach the mid of the LL
```
1 -> 2 -> 3 -> 3 -> 2 -> 1 ->  null
^
hare/
turtle
```
```
1 -> 2 -> 3 -> 3 -> 2 -> 1 ->  null
     ^    ^
  turtle hare 
```
```
1 -> 2 -> 3 -> 3 -> 2 -> 1 ->  null
          ^         ^
          turtle    hare   
```
as hare cant move further hare reach the end while turtle reach the mid( end of the first half of LL ) of the LL
 
### Code:
```java
public  Node findMid(Node head){
    Node hare = head;
    Node turtle = head;
    while(hare.next != null && hare.next.next != null){
        hare = hare.next.next;      //move with two nodes
        turtle = turtle.next;       //move with single node
    }
    return turtle;  //end of first half
}
```
- `hare.next == null:`\
if next node hare is null it reach the end of LL
```
1 -> 2 -> 3 -> 2 -> 1 ->  null
          ^         ^
          turtle    hare   
```

- `hare.next.next != null:`\
as hare move with 2 node pre move if hare don't have a node to jump on it reach the end of LL
```
1 -> 2 -> 3 -> 3 -> 2 -> 1 ->  null
          ^         ^
          turtle    hare    
```

### How to split the LL
1. get the next node of middle node of first half of LL 
2. by considering this node as head reverse the remaining LL
```
1 -> 2 -> 3 -> 3 -> 2 -> 1 ->  null
          ^    ^
         mid  new head     
``` 
```
1 -> 2 -> 3 -> 3 <- 2 <- 1 
               |         ^   
               V      head of second half    
              null
```
3. return the head of the second half
### Code
```java
public  Node reverse(Node h){   //h is mid.next
    Node prev = null;
    Node curr = h;
    while(curr != null){
        Node next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return  prev;
}
``` 
[to learn more about how to reverse a LL](#reverse-the-linkedlist)

### code
```java
public boolean  palindrome(){
    //corner case for single valued and empty LL 
    if(head == null || head.next == null){
        return  true;
    }

    Node mid = findMid(head);   //end of the first half
    Node  secHalf = reverse(mid.next);

    Node firstHalfStart = head;

    while(secHalf != null){
        if(firstHalfStart.data != secHalf.data){
            return  false;
        }
        firstHalfStart = firstHalfStart.next;
        secHalf = secHalf.next;
    }

    return  true;
}
```
[Go to Top](#content)

---

# swap adjacent node in LL 

### approach:
1. consider a LL with 2 pointer one at head and another aat head.next
```
1 -> 2 -> 3 -> 4 -> 2 -> 1 ->  null
^    ^
pt1 pt2  
``` 
2. swap the data between the pointer
```
2 -> 1 -> 3 -> 4 -> 2 -> 1 ->  null
^    ^
pt1 pt2  
``` 
3. update the pointers 
```
2 -> 1 -> 3 -> 4 -> 2 -> 1 ->  null
          ^    ^
          pt1 pt2
``` 
4. repeat the step 2 util you reach the end of the LL

### Code:
```java
public Node swapPairs(Node head) {
    if(head == null || head.next == null) return head;

    Node curr = head;
    Node next = head.next;
    while(true){
        int temp = curr.data;
        curr.data = next.data;
        next.data = temp;
        
        if( next.next != null && next.next.next != null){
            curr = curr.next.next;
            next = next.next.next;
        }else{
            break;
        }   
    }
    return head; 
}
```

#### Note:
**we are checking is curr and next is upgradeable or not if yes the upgrade and if not then terminate the loop this is because**

considering the while loop like
```java
while(next.next != null && next.next.next != null){
    int temp = curr.data;
    curr.data = next.data;
    next.data = temp;
    
    curr = curr.next.next;
    next = next.next.next; 
}
```
it will not swap the last 2 nodes as during last two while loop condition becomes false
```
2 -> 1 -> 3 -> 4 -> 2 -> 1 ->  null
                    ^    ^
                    curr next
```
as you can see here `next.next != null` returns false resulting noe executing the while loop causing last 2 no. not to swap, and if you put or ( || ) operator instead of and ( && ) it will throw `nullPointException` error at `next = next.next.next;` this line as there is no node present at `next.next.next`


[Go to Top](#content)

---
# Add Two Numbers
**You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.**

Example:\
consider following input LinkedLists
```
1 -> 5 -> null
2 -> 7 -> 4 -> null
```
Output LL
```
3 -> 2 -> 5 -> null
```
Explanation:\
51 + 472 = 523


### Approach
1. we will start with only single node LL as our ans LL pointing towards null and its value is 0
2. we will traverse over the both of our given LL
3. calculate the sum at each nodes and store it in ans LL
4. during each iteration we will add a new node in our ans LL(only if next Node exist) with value = 0
3. at some point if sum becomes greater than 9 we will carry the first digit for future addition
4. while updating the pointer of any LL, if one LL ends while another doesn't then we add the new Node with value 0 to smaller LL to make both LL have similar sizes
5. finally we will check if we have any carry value then we add that value into our ans LL as well

### Illustration
consider following input LinkedLists
```
1 -> 5 -> null
2 -> 7 -> 4 -> null
```
**1. initialize ans LL**
```
0 -> null
```
#### for iteration 1
1. sum: 1 + 2 = 3
```
carry = 0
3 -> null
```
2. update ans LL
```
3 -> 0 -> null
```
#### for iteration 2
1. sum: 5 + 6 = 12 as sum > 9 carry = 1
```
carry = 1
3 -> 2 -> null
```
2. LL 1 has ended but LL 2 haven't therefor add new node to LL 1\
therefor new LinkedLists re as follow
```
1 -> 5 -> 0 -> null
2 -> 6 -> 4 -> null
```
3. update ans LL
```
3 -> 2 -> 0 -> null
```
#### for iteration 3
1. sum: 0 + 4 + 1 (carry) = 5
```
carry = 0
3 -> 2 -> 5 -> null
```
2. as both LL has ended and carry = 0 final ans is
```
3 -> 2 -> 5 -> null
```

### Algorithm
1. initiate some basics variables
```java
Node ans = new Node(0);
Node curr = ans;
int carry = 0;
```
**ans:- head of ans LL** \
**curr:- LL in which we are gonna store our sum, also copy of head to upgrade next pointer without loosing the head**

2. traverse over the given LL
```java
while(l1 != null && l2 != null){

} 
```
3. calculate the sum
```java
curr.data = l1.data  + l2.data + carry;
carry = 0;
```
**As we have use the carry reset carry to 0**

4. check for sum > 9
```java
if(curr.data > 9){
    // greater than 9
}
```

5. calculate the actual sum and carry 
```java
if(curr.data > 9){
    carry = curr.data/10;   // last digit
    curr.data = curr.data % 10;     //first digit
}
```

6. case where only one LL is ending
```java
if(l1.next == null && l2.next != null){
    l1.next = new Node(0);  // adding new node with its value equal to 0
}
            
if(l2.next == null && l1.next != null){
    l2.next = new Node(0);  // adding new node with its value equal to 0
}
```

7. updating the ans LL
```java
if(l1.next != null && l2.next != null){
    curr.next = new Node(0);    // adding new node with its value equal to 0
    curr = curr.next;
}
```
8. updating the input LL
```java
l1 = l1.next;
l2 = l2.next;
```
9. once loop ended add carry if it has some valid value
```java
if(carry != 0){ // has some valid value
    curr.next = new Node(0);
    curr = curr.next;
    curr.data = carry;
}
```

### code
```java
public static Node add(Node l1, Node l2){
    Node ans = new Node(0);
    Node curr = ans;
    int carry = 0;
    while(l1 != null && l2 != null){
        // calculating sum
        curr.data = l1.data  + l2.data + carry;
        carry = 0;

        // sum > 9
        if(curr.data > 9){
            carry = curr.data/10;
            curr.data = curr.data % 10;
        }

        // only one LL has ended
        if(l1.next == null && l2.next != null){
            l1.next = new Node(0);
        }
        
        if(l2.next == null && l1.next != null){
            l2.next = new Node(0);
        }

        // update ans LL
        if(l1.next != null && l2.next != null){
            curr.next = new Node(0);
            curr = curr.next;
        }
         
        l1 = l1.next;
        l2 = l2.next;
    }

    if(carry != 0){
        curr.next = new Node(0);
        curr = curr.next;
        curr.data = carry;
    }

    return ans;
}
```
### Complete Code
```java
public class AddTwoNum {
    Node head;

    static class Node{
        int data;
        Node next;
    
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
        
    }

    // add first
    public void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    // print Linked list
    public void printList(){
        Node currNode = head;
        while(currNode != null){
            System.out.print(currNode.data + "->");
            currNode = currNode.next;
        }
        System.out.println("null");
    }

    public static Node add(Node l1, Node l2){
        Node ans = new Node(0);
        Node curr = ans;
        int carry = 0;
        while(l1 != null && l2 != null){
            curr.data = l1.data  + l2.data + carry;
            carry = 0;

            if(curr.data > 9){
                carry = curr.data/10;
                curr.data = curr.data % 10;
            }

            if(l1.next == null && l2.next != null){
                l1.next = new Node(0);
            }
            
            if(l2.next == null && l1.next != null){
                l2.next = new Node(0);
            }

            if(l1.next != null && l2.next != null){
                curr.next = new Node(0);
                curr = curr.next;
            }
         
            l1 = l1.next;
            l2 = l2.next;
        }

        if(carry != 0){
            curr.next = new Node(0);
            curr = curr.next;
            curr.data = carry;
        }

        return ans;
    }

    public static void main(String[] args) {
        AddTwoNum list1 = new AddTwoNum();
        AddTwoNum list2 = new AddTwoNum();
        list1.addFirst(9);
        list1.addFirst(9);
        list1.addFirst(9);
        list1.addFirst(9);
        list1.addFirst(9);
        list1.addFirst(9);
        list1.addFirst(9);

        list2.addFirst(9);
        list2.addFirst(9);
        list2.addFirst(9);
        list2.addFirst(9);

        System.out.println("input LL");
        list1.printList();
        list2.printList();

        AddTwoNum List3 = new AddTwoNum();
        List3.head = add(list1.head, list2.head);

        System.out.println("sum:");
        List3.printList();
    }
}

```
### Output
```
input LL
9->9->9->9->9->9->9->null
9->9->9->9->null
sum:
8->9->9->9->0->0->0->1->null
```
### verification
9999999 + 9999 = 89990001

[Go to Top](#content)

---