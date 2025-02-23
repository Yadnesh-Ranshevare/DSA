# content
1. [introduction](#introduction)
2. [implementation using LinkedList](#implementation-using-linked-list)
3. [implementation using ArrayList](#implementation-using-arraylist)
4. [stack using collection framework](#stack-using-collection-framework)
5. [Reverse A Stack](#reverse-a-stack)
6. [infix to postfix](#infix-to-postfix)


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

# implementation using ArrayList
### ArrayList
An ArrayList in Java is a resizable array that belongs to the Java Collection Framework. It allows you to store a dynamic list of elements, where the size of the list can grow or shrink as needed. Unlike arrays, which have a fixed size, an ArrayList automatically adjusts its size when elements are added or removed. It provides methods for adding, removing, accessing, and manipulating elements in the list.

### Key Points:
- **Resizable:** Can grow or shrink in size.
- **Indexed:** Elements can be accessed by their index.
- **Type-Safe:** Works with specific types of elements using Java Generics (e.g., `ArrayList<String>`, `ArrayList<Integer>`).
- **Ordered:** Maintains the order of elements as they are added.

### some basics method of arrayList
1.  `.size():-` returns the total length of an arrayList
2. `.add(i):-` add the value `i` at the end of the list
3. `.get(i):-` returns the element present at index `i`
4. `.remove(i):-` remove the element at index `i`

## code:-
```java
import java.util.ArrayList;
```
```java
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
```
**Note:- the first vacant place will act as a top of stack**

[Go to Top](#content)

---

# Stack using collection framework
### Code:
```java
import java.util.Stack;
public class StackUsingCollectionFramework {

    public static void main(String[] args) {

        Stack<Integer> s = new Stack<>();

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);

        while(!s.isEmpty()){
            System.out.println(s.peek());
            s.pop();
        }
    }
}
```

[Go to Top](#content)

---

# Reverse A Stack
### Approach:
1. using the recursion remove all the element of the stack in a sequence
2. once the stack gets empty fill the stack in opposite order that is the element which is remove the last will get fill first
3. as you fill the stack try to push the element at the bottom of the stack

### Code:
```java
public static void reverse(Stack<Integer> s){
    if(s.isEmpty()){
        return;
    }
    int top = s.pop();
    reverse(s);
    pushAtBottom(top,s);
}
```

### how to push data at the bottom:
1. consider a empty stack:
```
top >  []  < bottom
```
2. enter the 5 at the bottom as stack is initially empty it will be normal push operation
```
[5]
```
3. now to enter 4 at the bottom first remove the 5 and make stack empty
```
top = 5
[]
```
4. now push the 4 into stack followed by 5
```
[5,4]
```
5. for 3 remove the 5,4 from the stack recursively\
```
<!--first recursion call -->
top = 5
[4]


<!--second recursion call -->
top = 4
[]


<!--third recursion call -->
[3]     //as stack is empty push 3 into it and return


<!-- return to second recursion call -->
[4,3]   //push 4 ( top ) into it and return


<!-- return to first recursion call -->
[5,4,3]   //push 5 ( top ) into it and return
```

### Code:
```java
public static void pushAtBottom(int data,Stack<Integer> s){
    if(s.isEmpty()){
        s.push(data);
        return;
    }
    int top = s.pop();
    pushAtBottom(data, s);
    s.push(top);
}
```

### complete Code:
```java
import java.util.Stack;
public class Questions {

    public static void pushAtBottom(int data,Stack<Integer> s){
        if(s.isEmpty()){
            s.push(data);
            return;
        }
        int top = s.pop();
        pushAtBottom(data, s);
        s.push(top);
    }

    public static void reverse(Stack<Integer> s){
        if(s.isEmpty()){
            return;
        }
        int top = s.pop();
        reverse(s);
        pushAtBottom(top,s);
    }


    public static void Print(Stack<Integer> s){
        while(!s.isEmpty()){
            System.out.print(s.peek()+" | ");
            s.pop();
        }
    }
    

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        reverse(s);
        Print(s);
    }
}
```

[Go to Top](#content)

---

# Infix to Postfix
**infix:**\
normal representation of mathematical expression\
example:- `a + b`

**postfix:**\
 A postfix expression (also known as Reverse Polish Notation or RPN) is a mathematical notation in which operands comes first and then come the operator\
 example:- `a b +`

### Operator Precedence (from highest to lowest):
- `( )` (brackets) has the most highest precedence.
- `^` (exponentiation) has the highest precedence.
- `* and /` (multiplication and division) have the next higher precedence.
- `+ ans  -` (addition ans subtraction) has the lowest precedence.

**Note:**
1. **while considering each level of stack operator with low precedence cannot sit on top of the operator with high precedence that is `+ & -` cannot be at top if there exist `* & /` at the bottom level of the stack** 
2. **also no two operator with same precedence sit on one another that is `+` cannot be at the top of there exist `-` on bottom level of the stack**
3. **that said only operator with high precedence can allow to sit on top of the operator with low precedence only that is `^` can be at top if `* , +, - , /` is at the bottom levels**
4. **in case of `()` once the `(` (opening bracket) comes we need to check the precedence of operator from this bracket only (that is `bottom [ * ( + / ] top` this is possible) but once the `)` (closing bracket) comes we need to pop the element from the stack up the `(` (opening bracket) (that is `bottom [ * ( + / ] top` in this example if next character is `)` then both `+ and /` well get removed and the final stack will be `bottom [ * ] top`)**
4. **therefor to remove the character from stack as new character `c` comes and want to push itself into the stack it must satisfied this precedence condition `precedence(s.peek()) >= precedence(c) `**
5. **if you remove the element form the stack it will be added into the ans string and at the end if their is any element remaining in the stack then stack must be empty out**

### Algorithm
1. declare a Stack and empty ans string
```java
Stack<Character> s = new Stack<>();
StringBuilder ans = new StringBuilder();
```
2. iterate over the whole input string( mathematical expression )
3. if its operand then append it to the ans string
```java 
if (Character.isLetterOrDigit(c)) {
    ans.append(c);
}
```
4. if its `(` then push it into the stack
```java
else if (c == '(') {
    s.push(c);
}
```
5. if its `)` then pop out all the element from the stack util we reach the `(` ( Also remove `(` ) or until the stack gets empty
```java
else if (c == ')') {
    while (!s.isEmpty() && s.peek() != '(') {
        ans.append(s.pop());
    }
    s.pop();  // Pop the '('
}
```
6. if its another operators then check their precedence
```java
public static int precedence(char c) {
    switch (c) {
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        case '^':
            return 3;
        default:
            return -1;
    }
}
```
7. insert them into the stack according to their precedence
```java
while (!s.isEmpty() && precedence(s.peek()) >= precedence(c) && s.peek() != '(') {
    ans.append(s.pop());
}
s.push(c);  // Push the current operator onto the stack
```
`precedence(s.peek()) >= precedence(c)` condition to make sure no low precedence operator can sit on top of the high precedence operator

8. empty out the remaining stack
```java
while (!s.isEmpty()) {
    ans.append(s.pop());
}
```
9. return the ans in string format
```java
return ans.toString();
 ```


### Code:
```java
import java.util.ArrayList;
import java.util.Stack;

public class Postfix {

    public static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    public static String Postfix(String str) {
        Stack<Character> s = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                ans.append(c);
            }

            else if (c == '(') {
                s.push(c);
            }

            else if (c == ')') {
                while (!s.isEmpty() && s.peek() != '(') {
                    ans.append(s.pop());
                }
                s.pop();  
            }
            
            else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                while (!s.isEmpty() && precedence(s.peek()) >= precedence(c) && s.peek() != '(') {
                    ans.append(s.pop());
                }
                s.push(c);  
            }
        }

        while (!s.isEmpty()) {
            ans.append(s.pop());
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        String str = "a+b/c^d+e*(f+j)";
        String ans = Postfix(str);
        System.out.println(ans);  
    }
}
```




[Go to Top](#content)

---