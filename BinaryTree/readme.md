# content
1. [Introduction to Trees](#introduction-to-trees)
2. [Build Tree Pre order](#build-tree-preorder)
3. [Traversal](#traversal)
4. [Level order traversal](#level-order-traversal)
5. [Count number of Nodes](#count-number-of-nodes)
6. [Sum of Nodes](#sum-of-nodes)
7. [Height of the Tree](#height-of-the-tree)
8. [calculate Diameter of the tree](#calculate-diameter-of-the-tree)
9. [Is subtree exist](#is-subtree-exist)
# Introduction to Trees
**A tree is a hierarchical data structure** where elements (nodes) are organized across different levels, with each node connected to its children below it, starting from a single root node at the top.

**Binary tree:**\
A binary tree is a hierarchical data structure where each node has at most two children, called the left and right child.
```
        1         <- Root
       / \
      2   3      <- 2 and 3 are children of 1
     / \
    4   5       <- 4 and 5 are children of 2
```

from above example we can say that 
- **left child:** child node present at the left of parent node
    - 2 is a left child of parent 1
    - 4 is a left child of parent 2
- **Right child:** child node present at the right of parent node
    - 3 is a right child of parent 1
    - 5 is a right child of parent 2
- **sibling Nodes:** two chile node having same parent Node  
    - as 2 & 3 have same parent node 2-3 are sibling nodes
    - as 4 & 5 have same parent node 4-5 are sibling nodes
- **Ancestor Nodes:** all the parent node up to root node
    - ancestors of node 4 is 2, 1
    - ancestors of node 3 is 1
    - ancestors of node 2 is 1


### Root Node vs Leaf Node vs Branches

- **Root Node**
    - The topmost node in a tree.

    - It has no parent.

    - Example: In a tree, if 1 is at the top, 1 is the root.

- **Leaf Node**

    - A node that has no children.

    - It is at the bottom of the tree.

    - Example: Nodes like 4, 5 (with no further nodes under them) are leaf nodes.

- **Branches**

    - The connections (edges) between nodes (parent → child).

    - Example: The line connecting 1 → 2 is a branch.

**Quick Illustration:**
```markdown
        1        <- Root Node
       / \
     2     3    
    / \
   4   5         <- Leaf Nodes
```
- 1 is the root.

- 3, 4 and 5 are leaf nodes.

- Lines between nodes (like 1→2, 2→4) are branches.

### level vs depth vs subtree
**Quick Illustration:**
```
        1         
       / \
      2   3      
     / \
    4   5      
```
- **Level**
    - Level of a node = How far the node is from the root.

    - Root is always at Level 1 or you can also say as level 0.

    - Every child node is one level more than its parent.
    - in our example 
        - node 1 is at level 1
        - node 2 & 3 at level 2
        - node 4 & 5 at level 3
-  **Depth**
    - Depth of a node = Same as Level.

    - It’s the number of edges from the root to that node.
    - in our example
        - node 1 is at depth 1
        - node 2 & 3 is at depth 2
        - node 4 & 5 is at depth 3
- **Subtree**
    - A subtree = Any node and all its descendants.

    - Treat any node as a small tree itself.
    - in our example
        - node 1 has two subtree 1st one is `2-4,5` where 2 is the root of subtree and 2nd one is `3-null,null` with 3 is the root of subtree
        - 3 has zero subtree




[Go To Top](#content)

---

# Build Tree PreOrder

given preOrder:
`1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1`

**Note:**

- Preorder = Root → Left → Right

- -1 means NULL (no node)

**Let’s build step-by-step:**

### Step 1: 1
Create node 1 (root).
```
        1
```

### Step 2: 2
Create node 2 as left child of 1.
```
        1
       / 
     2     
```

### Step 3: 4
Create node 4 as left child of 2.
```
        1
       / 
     2    
    /      
   4 
```

### Step 4: -1
Left child of 4 is NULL.
```
        1
       / 
     2    
    /      
   4  
  /
null  
```

### Step 5: -1
Right child of 4 is NULL.
```
          1
         / 
       2     
      /     
     4      
    / \
 null  null
```
(4 is complete therefor backtrack to previous Node i.e, 2)
### Step 6: 5
Create node 5 as right child of 2.
```
          1
         / 
       2     
      / \    
     4   5 
```


### Step 7: -1
Left child of 5 is NULL.

```
          1
         / 
       2     
      / \    
     4   5 
        /
      null 
```

### Step 8: -1
Right child of 5 is NULL.
```
          1
         / 
       2     
      / \    
     4   5 
        / \
      null null
```
(5 is complete so backtrack to previous Node i.e, 2, now 2 is also complete again backtrack to previous Node which is 1)

### Step 9: 3
Create node 3 as right child of 1.
```
        1
       / \
     2     3
    / \     
   4   5     
```

### Step 10: -1
Left child of 3 is NULL.
```
        1
       / \
     2     3
    / \   /
   4   5 null   

```

### Step 11: 6
Create node 6 as right child of 3.
```
        1
       / \
     2    3
    / \    \
   4   5    6
```

### Step 12: -1
Left child of 6 is NULL.
```
        1
       / \
     2    3
    / \    \
   4   5    6
            /
          null  
```
### Step 13: -1
right child of 6 is NULL.
```
        1
       / \
     2    3
    / \    \
   4   5    6
           / \
        null null 
```

### Final Tree:
```
        1
       / \
     2     3
    / \     \
   4   5     6
```

### coding Approach:

**we use recursive backtracking approach to solve this problem where we build left side of the tree recursively once we we reach the null or assign 2 child node we backtrack to parent node**

1. create the new Node class to represent the each node of tree
```java
static  class Node{
    int data; // actual data store at each node
    Node left;  // left child
    Node right; // right child

    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
``` 
2. now create the binary tree class which will be our actual tree
```java
static class BinaryTree{
    
}
```
3. declare the static variable which will represent the index of nodes array which contains the data of tree in the form of array, at first its value is -1 (it is set to -1 because we want to update this value recursively)
```java
static int idx = -1;
```
4. create the build tree function which will build the tree from preOrder array
```java
public static Node buildTree(int nodes[]){
            
}
```
5. update the value of idx (from -1 to 0) to access the first element for the nodes array
```java
idx++
```
6. return null for -1 value of node (base case)
```java
if(nodes[idx] == -1){
    return  null;
}
```
7. make the `node[idx]` where `idx = 0 (1st index of nodes array)` root of the tree
```java
Node newNode = new Node(nodes[idx]);  // newNode is the actual root of the tree
````
8. recursively add the left subtree 
```java
newNode.left = buildTree(nodes);  // return the root of left subtree
```
9. recursively add the right subtree 
```java
newNode.right = buildTree(nodes); // return the root of right subtree
```
10. return the actual root of the tree
```java
return newNode;
```

### Complete code:
```java
public class BuildPreOrder{

    static  class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return  null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
    }
}
```

[Go To Top](#content)

---


# Traversal
Traversal in binary trees means visiting all the nodes of the tree in a specific order. There are mainly three common types of binary tree traversals:

**Note: all the traversal algorithm has time complexity of `O[n]`**

1. **Preorder Traversal (Root → Left → Right)**
- Visit the root node

- Visit the left subtree

- Visit the right subtree

**example**
```
    1
   / \
  2   3
```
**Preorder output:** `1 2 3`

**Code:**
```java
public static void preOrder(Node root){
    if(root == null){
        return;
    }

    System.out.print(root.data+" ");    // first root

    preOrder(root.left);    // then left subtree
    preOrder(root.right);   // then right subtree
}
```

2. **Inorder Traversal (Left → Root → Right)**
- Visit the left subtree

- Visit the root node

- Visit the right subtree
```
    1
   / \
  2   3
```
**Inorder output:** `2 1 3`

**Code:**
```java
 public static void inOrder(Node root){
    if(root == null){
        return;
    }

    inOrder(root.left);     // first left subtree

    System.out.print(root.data+" ");    // then root

    inOrder(root.right);    // then right subtree
}
```
3. **Postorder Traversal (Left → Right → Root)**
- Visit the left subtree

- Visit the right subtree

- Visit the root node
```
    1
   / \
  2   3
```
**Postorder output:** `2 3 1`

**Code:**
```java
public static void postOrder(Node root){
    if(root == null){
        return;
    }

    postOrder(root.left);   // first left subtree
    postOrder(root.right);  // then right subtree

    System.out.print(root.data+" ");    // then root
}
```
### Illustration of All Three:
```
      1
     / \
    2   3
   / \
  4   5
```
| Traversal Type | Output Sequence     |
|----------------|---------------------|
| Inorder        | 4 2 5 1 3           |
| Preorder       | 1 2 4 5 3           |
| Postorder      | 4 5 2 3 1           |

### Complete Code:
```java
public class BuildPreOrder{

    static  class Node{     // represent the each node of tree
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{    // function to build tree from preorder
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return  null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static void preOrder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);

    }

    public static void inOrder(Node root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    public static void postOrder(Node root){
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }


    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        System.out.println("preorder:");
        preOrder(root);

        System.out.println("\n");
        System.out.println("inorder:");
        inOrder(root);

        System.out.println("\n");
        System.out.println("postorder");
        postOrder(root);
    }
}
```
**actual tree in above code example**
```
      1
     / \
    2   3
   / \   \
  4   5   6
```

**Output:**
```
preorder:
1 2 4 5 3 6

inorder:
4 2 5 1 3 6

postorder
4 5 2 6 3 1
```

**Note:** 
- **All this 3 traversal algorithm is also known as DFS (Depth-First Search) visit**
- **DFS (Depth-First Search) is a tree/graph traversal strategy where you go as deep as possible down one path before backtracking and exploring other paths.** 

[Go To Top](#content)

---
# Level order traversal
- Level Order Traversal is a way of traversing a binary tree **level by level** from top to bottom and left to right

- also known as Breadth-First Search (BFS) for trees.Level Order Traversal is called **Breadth-First Search (BFS)** in trees because it follows the BFS strategy

- The BFS (Breadth-First Search) strategy is a method of exploring a graph or tree by **visiting all nodes at the current level** before moving on to the next level.

- Time complexity of level order traversal is `O[n]`

**Example:**

```
      1
     / \
    2   3
   / \   \
  4   5   6
```
**Level Order traversal:**
```
1
23
456
```

unlike normal traversal where we use recursion to get the whole subtree here we **use iteration** and we also gonna **use Queue** to make the use of its FIFO property\
[Click here to learn about Queue](../Queue//Readme.md)

**Approach:**
- once we visit the root node we store it in queue 
- once we pop any node from the queue store its left and right child into the queue
- to print the next line we store null in queue which indicate the new line
- once we pop out the null we reinsert it into the queue to maintain the level

**illustration:**

consider the following tree
```
      1
     / \
    2   3
   / \   \
  4   5   6 
```

- **initializing the queue**\
we start by adding root of the tree followed by null into the queue
```
[1, null]
```
- **first pop operation :- pop 1 store 2, 3**\
perform the pop operation on queue and store the left and right child of the pop out node

**Queue:**
```
[null, 2, 3]
```
**Output:**
```
1
```
- **second pop operation :- pop null**\
as this time we pop out null therefor switch to new line and reinsert the null back in queue

**Queue:**
```
[2, 3, null]
```
**Output:**
```
1
        //switch to new line
```
- **third pop operation :- pop 2 store 4, 5**\
perform the pop operation on queue and store the left and right child of the pop out node

**Queue:**
```
[3, null, 4, 5]
```
**Output:**
```
1
2
```

- **fourth pop operation :- pop 3 store 6**\
perform the pop operation on queue and store the left and right child of the pop out node

**Queue:**
```
[null, 4, 5, 6]
```
**Output:**
```
1
2 3
```

- **fifth pop operation :- pop null**\
as this time we pop out null therefor switch to new line and reinsert the null back in queue

**Queue:**
```
[4, 5, 6, null]
```
**Output:**
```
1
2 3
        //switch to new line
```
- **similar after sixth, seventh, eighth pop operation we get our ans**\
sixth:- pop 4 (no child)\
seventh:- pop 5 (no child)\
eighth:- pop 6 (no child)

**Queue:**
```
[null]
```
**Output:**
```
1
2 3
4 5 6
```
- **ninth pop operation**\
after ninth pop operation our queue becomes empty which indicate the end of our solution

**Coding Algorithm:**
1. condition for empty tree
```java
if(root == null){
    return;
}
```
2. create the queue using linked List and add the root and null value to initialize the queue
```java
Queue<Node> q = new LinkedList<>();
q.add(root);
q.add(null);
```
3. start the loop which will continuously run till the queue gets empty
```java
while(!q.isEmpty()){

}
```

4. perform the pop operation
```java
Node currNode = q.remove();
```
5. check whether the current node is null or not
```java
if(currNode == null){
    // currNode is null
}else{
    // currNode is not null
}
```
6. if it is null then go to next line and check whether the q in empty or not after removing the null
```java
if(currNode == null){
    System.out.println();   // switching to next line
    if(q.isEmpty()){
        // after removal queue is empty
    }else{
        // after removal queue is not empty
    }
}else{
    // currNode is not null
}
```
7. if after removal queue becomes empty then terminate the loop else reinsert null into queue
```java
if(currNode == null){
    System.out.println();
    if(q.isEmpty()){
        break;      // terminating the loop
    }else{
        q.add(null);    // adding null into the queue
    }
}else{
    // currNode is not null
}
```

8. if current node value is not null then print that value 
```java
if(currNode == null){
    System.out.println();
    if(q.isEmpty()){
        break;     
    }else{
        q.add(null);    
    }
}else{
    System.out.print(currNode.data+" ");    // printing the  value of currNode
}
```
9. check whether current node has a left/right child or not
 ```java
if(currNode == null){
    System.out.println();
    if(q.isEmpty()){
        break;     
    }else{
        q.add(null);    
    }
}else{
    System.out.print(currNode.data+" ");    

    if(currNode.left != null){
       // has left child
    }
    if(currNode.right != null){
       // has right child
    }
}
```
10. if any child exist the add that child into the queue
 ```java
if(currNode == null){
    System.out.println();
    if(q.isEmpty()){
        break;     
    }else{
        q.add(null);    
    }
}else{
    System.out.print(currNode.data+" ");    

    if(currNode.left != null){
        q.add(currNode.left);   // adding the child into the queue
    }
    if(currNode.right != null){
        q.add(currNode.right);  // adding the child into the queue
    }
}
```

**Complete Code:**
```java
import java.util.LinkedList;
import java.util.Queue;

public class levelOrder{

    static  class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return  null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static void levelOrder(Node root){
        if(root == null){
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()){
            Node currNode = q.remove();
            if(currNode == null){
                System.out.println();
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            }else{
                System.out.print(currNode.data+" ");

                if(currNode.left != null){
                    q.add(currNode.left);
                }
                if(currNode.right != null){
                    q.add(currNode.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        levelOrder(root);
        
    }
}
```
**actual tree in above code example**
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
**Output:**
```
1 
2 3
4 5 6
```




[Go To Top](#content)

---

# Count number of Nodes
**Given a tree count the total number of nodes for that tree**

**Approach:**
1. by using recursion count the total number of nodes present in left subtree and in right subtree
2. now as you have total count of nodes of left & right subtree add the root (+1) and return that sum


**Algorithm:**
1. base case, says there is no node present
```java
if(root == null){
    return 0;
}
```
2. count the number of node present in left subtree
```java
int leftNodes = count(root.left);   // will return the count of node present in left subtree
```
3. count the number of node present in right subtree
```java
int rightNode = count(root.right);   // will return the count of node present in right subtree
```
4. return the total sum
```java
return leftNodes + rightNode + 1; // +1 is for root
```
**Code:**
```java
public class countNode{

    static  class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return  null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static int count(Node root){

        if(root == null){
            return 0;
        }
        int leftNodes = count(root.left);
        int rightNode = count(root.right);

        return leftNodes + rightNode + 1;
    }

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        System.out.println(count(root));
    }
}
```
**Output:**
```
6
```



**Example Tree:**
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
**How the code runs step-by-step:**
```java
count(1)
  -> count(2)   
       -> count(4)
            -> count(null) = 0
            -> count(null) = 0
            -> return 0 + 0 + 1 = 1
       -> count(5)
            -> count(null) = 0
            -> count(null) = 0
            -> return 0 + 0 + 1 = 1
       -> return 1 + 1 + 1 = 3
  -> count(3)
       -> count(null) = 0
       -> count(6)
            -> count(null) = 0
            -> count(null) = 0
            -> return 0 + 0 + 1 = 1
       -> return 0 + 1 + 1 = 2
  -> return 3 + 2 + 1 = 6
```
**Note: as we are visiting each node single time the time complexity of this solution will be `O[n]`**


[Go To Top](#content)

---
# Sum of Nodes
**given a tree of integer return the sum of all Nodes present int that tree**

**Approach:**
1. by using recursion find the total sum of nodes present in left subtree and in right subtree
2. now as you have total sum of left & right subtree add the root and return that sum


**Algorithm:**
1. base case, says there is no node present
```java
if(root == null){
    return 0;
}
```
2. find the sum of node present in left subtree
```java
int leftSum = sum(root.left);   // will return the sum of node present in left subtree
```
3. count the number of node present in right subtree
```java
int rightSum = sum(root.right);   // will return the sum of node present in right subtree
```
4. return the total sum
```java
return leftSum + rightSum + root.data; 
```

**Code:**
```java
public class sumNode {
    static  class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return  null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static int sum(Node root){
        if(root == null){
            return 0;
        }

        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        return  leftSum + rightSum + root.data;

    }

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        System.out.println(sum(root));

    }
}
```
**Output:**
```
21
```

**Example Tree:**
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
**How the code runs step-by-step:**
```java
sum(1)
  -> sum(2)
       -> sum(4)
            -> sum(null) = 0
            -> sum(null) = 0
            -> return 0 + 0 + 4 = 4
       -> sum(5)
            -> sum(null) = 0
            -> sum(null) = 0
            -> return 0 + 0 + 5 = 5
       -> return 4 + 5 + 2 = 11
  -> sum(3)
       -> sum(null) = 0
       -> sum(6)
            -> sum(null) = 0
            -> sum(null) = 0
            -> return 0 + 0 + 6 = 6
       -> return 0 + 6 + 3 = 9
  -> return 11 + 9 + 1 = 21
```
**Note: as we are visiting each node single time the time complexity of this solution will be `O[n]`**




[Go To Top](#content)

---

# Height of the Tree
**Given tree find its height (deepest level)**

**Approach:**
1. by using recursion find the height of left subtree and right subtree
2. now as you have the height of both left & right subtree check who has large height and add the root height (+1) and return that final height


**Algorithm:**
1. base case, says there is no node present therefor height is zero
```java
if(root == null){
    return 0;
}
```
2. find the height of left subtree
```java
int leftHeight = height(root.left);   // will return the height of node present in left subtree
```
3. count the height of right subtree
```java
int rightHeight = height(root.right);   // will return the height of node present in right subtree
```
4. get the largest height it can be the height of the left subtree or can be the height of the right subtree with the help of `Math.max(a,b)`\
`Math.max(a,b):` return the greatest value among a and b
```java
 Math.max(leftHeight, rightHeight)
``` 
5. return the total height
```java
int myHeight = Math.max(leftHeight, rightHeight) + 1;   // +1 represent the root 
return myHeight; 
```

**Code:**
```java
public class Height {
    static  class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return  null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }


    public static int height(Node root){
        if(root == null){
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int myHeight = Math.max(leftHeight, rightHeight) + 1;
        return myHeight; 
    }

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        System.out.println(height(root));


    }
}
```
**Output:**
```
3
```
**Example Tree:**
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
**How the code runs step-by-step:**
```java
height(1)
  -> height(2)
       -> height(4)
            -> height(null) = 0
            -> height(null) = 0
            -> return max(0, 0) + 1 = 1
       -> height(5)
            -> height(null) = 0
            -> height(null) = 0
            -> return max(0, 0) + 1 = 1
       -> return max(1, 1) + 1 = 2
  -> height(3)
       -> height(null) = 0
       -> height(6)
            -> height(null) = 0
            -> height(null) = 0
            -> return max(0, 0) + 1 = 1
       -> return max(0, 1) + 1 = 2
  -> return max(2, 2) + 1 = 3
```
**Note: as we are visiting each node single time the time complexity of this solution will be `O[n]`**





[Go To Top](#content)

---
# calculate Diameter of the tree
**given a tree find the largest diameter of the tree**

there are tree different possibility in which you can find the largest diameter of the tree
1. trough root
```
    1
   / \
  2   3
 /     \
4       5
```
**here largest diameter is 5:** `4-2-1-3-5`

2. in left subtree
```
      1
     / 
    2   
   / \    
  3   4
 /     \  
5       6
```
**here largest diameter is 5:** `5-3-2-4-6`

2. in right subtree
```
      1
       \
        2
       / \    
      3   4
     /     \  
    5       6
```
**here largest diameter is 5:** `5-3-2-4-6`


- therefor in general we find all three diameter and return the greatest one among this three as the greatest diameter of the tree



### 1. to find the demeter passing through root 

- we just calculate the height of the left subtree and right subtree and add the root (+1) and return the overall sum as a diameter\
[to learn about how to find the height](#height-of-the-tree)
```
    1
   / \
  2   3
 /     \
4       5
```
- here height of the left subtree is 2
- here height of the right subtree is 2
- add the root i.e `height of left subtree + height of right subtree + 1`
- after adding the root we get\
final sum = 2 + 2 + 1 = `5`
- return the 5 as a diameter trough root

**Notes:**\
**1. this algorithm fails if the diameter is not passing trough root**\
**2. it has time complexity of `O[n]` as we are visiting each node single time**

### 2. To find the diameter not passing trough root
- if diameter is not passing through the root at that time we recursively calculate he diameter of the left/right subtree and also calculate the diameter when passing through root
- [to learn about hoe to calculate the diameter passing through root ](#1-to-find-the-demeter-passing-through-root)
- and we return the largest diameter among them as a final diameter 
```
      1
     / 
    2   
   / \    
  3   4
 /     \  
5       6
```
#### 1. **recursively we reach the last node (let say 5) of the left subtree**
```
    5
   / \
null null 
```
As null represent there is no node present we can say that `diameter of null subtree is 0`

therefor dimeter of both **left and right `subtree = 0`**

since there is only one node present we can say that `diameter of single node (passing through root) is 1`
passing through root| left subtree (null) | right subtree (null)
---| ---| ---
1 (greatest) | 0 | 0

therefor `return 1`

#### 2.  **recursive call for node 3**
```
  3   
 / \      
5  null     
```
- as 5 have return the 1 as his diameter, we can say that `dimeter of left subtree is 1`

passing through root| left subtree (5) | right subtree (null)
---| ---| ---
2 (greatest) | 1 | 0

- therefor return 2
#### 3. recursive call for 2
```  
    2   
   / \    
  3   4
 /     \  
5       6
```
- the same way we calculate the diameter of the left subtree, we will calculate the diameter of the right subtree
- Link we will ask node 4 to give his diameter, 4 will as his right child i.e 6 to give his diameter
- `6 returns 1 to 4 (by following step 1), 4 return 2 as his diameter (by following step 2)`

passing through root| left subtree (3) | right subtree (4)
---| ---| ---
5 (greatest) | 2 | 2

- therefor return 5
#### recursive call for 1
```
      1
     / \
    2  null 
   / \    
  3   4
 /     \  
5       6
```
- as 2 have return the 5 as his diameter we can say that `dimeter of left subtree is 5`

passing through root| left subtree (2) | right subtree (null)
---| ---| ---
4  | 5 (greatest) | 0

return the 5

**Note:**\
**1. as this algorithm is also applicable for diameter passing trough root we generally use this algorithm**\
**2. time complexity of this algorithm is `o[n^2]` as we are traversing over tree twice (1st time to calculate the diameter of left/right subtree, 2nd time to calculate the hight for diameter passing through root)** 


### code:
```java
public class Diameter {
    static  class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // function to build tree
    static class BinaryTree{
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return  null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }


    // function to calculate the height of the tree
    public static int height(Node root){
        if(root == null){
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int myHeight = Math.max(leftHeight, rightHeight) + 1;
        return myHeight; 
    }


    public static int diameter(Node root){
        if(root == null){
            return  0;
        }
        int dim1 = diameter(root.left);     // diameter of left subtree   
        int dim2 = diameter(root.right);    // diameter of right subtree
        int dim3 = height(root.left) + height(root.right) + 1;  // diameter when passing through root

        return Math.max(dim3, Math.max(dim1, dim2));
    }

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        System.out.println(diameter(root));


    }
}
````
### output
```
5
```
### Example Tree:
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
### How the code runs step-by-step:
```java
diameter(1)
  -> diameter(2)
       -> diameter(4)
            -> diameter(null) = 0
            -> diameter(null) = 0
            -> dim3 = height(null) + height(null) + 1 = 0 + 0 + 1 = 1
            -> return max(1, max(0, 0)) = 1
       -> diameter(5)
            -> diameter(null) = 0
            -> diameter(null) = 0
            -> dim3 = height(null) + height(null) + 1 = 0 + 0 + 1 = 1
            -> return max(1, max(0, 0)) = 1
       -> dim3 = height(4) + height(5) + 1 = 1 + 1 + 1 = 3
       -> return max(3, max(1, 1)) = 3
  -> diameter(3)
       -> diameter(null) = 0
       -> diameter(6)
            -> diameter(null) = 0
            -> diameter(null) = 0
            -> dim3 = height(null) + height(null) + 1 = 0 + 0 + 1 = 1
            -> return max(1, max(0, 0)) = 1
       -> dim3 = height(null) + height(6) + 1 = 0 + 1 + 1 = 2
       -> return max(2, max(0, 1)) = 2
  -> dim3 = height(2) + height(3) + 1 = 2 + 2 + 1 = 5
  -> return max(5, max(3, 2)) = 5
```

#### In above solution we can see that its time complexity is `O[n^2]` which is not optimize therefor to optimize this solution we use another approach who has time complexity of `O[n]`
- the main reason why above solution taking `O[n^2]` of time is because we are **calculating the height with the help of the another recursive calls**\
[to learn about how to find the height](#height-of-the-tree)
```java
int dim3 = height(root.left) + height(root.right) + 1;  // calling the height function which calculate the height recursively
```
- therefor to reduce the time to `O[n]` we just need to avoid the another recursive call for calculating the height
- to avoid this unnessery recursive call we use class object in which we store the diameter and height of the each node 
```java
public static class TreeInfo{
    int ht;
    int dim;
    public TreeInfo(int ht,int dim) {
        this.ht = ht;
        this.dim = dim;
    }  
}
```
- we use similar approach as previous but this time with help of this class we get info about the height and the diameter of the subtrees with only single recursive call  

### lets take an example
```
      1
     / 
    2   
   / \    
  3   4
 /     \  
5       6
```
#### 1. you recursively reach the node 5
```
    5
   / \
null null   
```
- we make recursive call for `left subtree which is null` therefor it return the class object with `ht = 0 & dim = 0` to node 5
- similarly we make recursive call for `right subtree which is also null` therefor it return the same class object with `ht = 0 & dim = 0` to node 5
- therefor we have following info at node 5

subtree   | ht | dim
--- | --- | ---
left subtree (null) | 0 | 0
right subtree (null) | 0 | 0

from above table we can calculate the height and the diameter of the current node in same way you did in pervious approach

therefor we can say that:-
- `height of node 5` = **Math.max(ht of left subtree, ht of right subtree) + 1 = 0 + 1 = 1**
- `diameter passing through root` = **ht of left subtree + ht of right subtree + 1  = 0 + 0 + 1 = 1 (greatest)**
- `diameter of left subtree` = **dim of left subtree = 0** 
- `diameter of right subtree` = **dim of right subtree = 0**  

from this calculation for node 5 we can construct the treeInfo object as follow:-
ht | dim
--- | ---
1 | 1

we return this object to its parent node which is 3

#### recursive call for 3
```    
  3   
 / \      
5  null     
```
- from node 5 we get the info as `ht = 1 & dim = 1` which is the info left subtree of node 3
- as for right subtree as it is null it return the `ht = 0 & dim = 0` to node 3
- therefor we have following info at node 3

subtree   | ht | dim
--- | --- | ---
left subtree (5) | 1 | 1
right subtree (null)| 0 | 0

therefor we can say that:-
- `height of node 3` = **Math.max(ht of left subtree, ht of right subtree) + 1 = 1 + 1 = 2**
- `diameter passing through root` = **ht of left subtree + ht of right subtree + 1 = 1 + 0 + 1 = 2 (greatest)**
- `diameter of left subtree` = **dim of left subtree = 1** 
- `diameter of right subtree` = **dim of right subtree = 0** 

from this calculation for node 3 we can construct the treeInfo object as follow:-
ht | dim
--- | ---
2 | 2

we return this object to its parent node which is 3
#### recursive call for node 2
```     
    2   
   / \    
  3   4
 /     \  
5       6
```
- for node `6` we get info `ht = 1 & dim = 1` **(follow step 1)** which return to node 4
- for node `4` we get info `ht = 2 & dim = 2` **(follow step 2)** which return to node 2
- therefor we have following info at node 2

subtree   | ht | dim
--- | --- | ---
left subtree (3) | 2 | 2
right subtree (4)| 2 | 2

therefor we can say that:-
- `height of node 2` = **Math.max(ht of left subtree, ht of right subtree) + 1 =  2 + 1 = 3**
- `diameter passing through root` = **ht of left subtree + ht of right subtree + 1 = 2 + 2 + 1 = 5 (greatest)**
- `diameter of left subtree` = **dim of left subtree = 2** 
- `diameter of right subtree` = **dim of right subtree = 2** 

from this calculation for node 2 we can construct the treeInfo object as follow:-
ht | dim
--- | ---
3 | 5

#### recursive call for node 1
```
      1
     / \
    2  null 
   / \    
  3   4
 /     \  
5       6
```
- from node 2 we get the info as `ht = 3 & dim = 5`which is the info of left subtree whose root in 2
- from right subtree of node 1 we get `ht = 0 & dim = 0` as right subtree is null
- therefor we have following info at node 2

subtree   | ht | dim
--- | --- | ---
left subtree (2) | 3 | 5
right subtree (null)| 0 | 0

therefor we can say that:-
- `height of node 1` = **Math.max(ht of left subtree, ht of right subtree) + 1 =  3 + 1 = 4**
- `diameter passing through root` = **ht of left subtree + ht of right subtree + 1 = 3 + 0 + 1 = 4**
- `diameter of left subtree` = **dim of left subtree = 5 (greatest)** 
- `diameter of right subtree` = **dim of right subtree = 0** 

from this calculation for node 2 we can construct the treeInfo object as follow:-
ht | dim
--- | ---
4 | 5

#### therefor the final ans is height = 4 and dimeter = 5 we get this answer in `O[n]` time which is more optimize than pervious approach who has time complexity of `O[n^2]`

### Code:
```java
public class Diameter {
    static  class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return  null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static class TreeInfo{
        int ht;
        int dim;

        public TreeInfo(int ht,int dim) {
            this.ht = ht;
            this.dim = dim;
        }
        
    }

    public static TreeInfo diameter(Node root){
        if(root == null){
            return new TreeInfo(0,0);
        }

        TreeInfo left = diameter(root.left);  // getting the treInfo of left subtree
        TreeInfo right = diameter(root.right);  // getting the treInfo of right subtree

        int myHeight = Math.max(left.ht, right.ht) + 1;  // calculating the height

        int dim1 = left.dim;      // diameter of left subtree
        int dim2 = right.dim;     // diameter of right subtree  
        int dim3 = left.ht + right.ht + 1;  // diameter through root

        int myDim = Math.max(dim3,Math.max(dim1,dim2));     // final diameter

        TreeInfo myInfo = new TreeInfo(myHeight,myDim);     // current node treeInfo
        return myInfo;
    }

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        System.out.println(diameter(root).dim);
    }
}
```
### output
```
5
```
### Example Tree:
```
      1
     / \
    2   3
   / \   \
  4   5   6
```
### How the code runs step-by-step:
```java
diameter(1)
  -> diameter(2)
       -> diameter(4)
            -> diameter(null) = TreeInfo(height=0, diameter=0)
            -> diameter(null) = TreeInfo(height=0, diameter=0)
            -> dim3 = 0 + 0 + 1 = 1
            -> myHeight = max(0, 0) + 1 = 1
            -> myDiameter = max(1, max(0, 0)) = 1
            -> return TreeInfo(height=1, diameter=1)
       -> diameter(5)
            -> diameter(null) = TreeInfo(height=0, diameter=0)
            -> diameter(null) = TreeInfo(height=0, diameter=0)
            -> dim3 = 0 + 0 + 1 = 1
            -> myHeight = max(0, 0) + 1 = 1
            -> myDiameter = max(1, max(0, 0)) = 1
            -> return TreeInfo(height=1, diameter=1)
       -> dim3 = 1 + 1 + 1 = 3
       -> myHeight = max(1, 1) + 1 = 2
       -> myDiameter = max(3, max(1, 1)) = 3
       -> return TreeInfo(height=2, diameter=3)
  -> diameter(3)
       -> diameter(null) = TreeInfo(height=0, diameter=0)
       -> diameter(6)
            -> diameter(null) = TreeInfo(height=0, diameter=0)
            -> diameter(null) = TreeInfo(height=0, diameter=0)
            -> dim3 = 0 + 0 + 1 = 1
            -> myHeight = max(0, 0) + 1 = 1
            -> myDiameter = max(1, max(0, 0)) = 1
            -> return TreeInfo(height=1, diameter=1)
       -> dim3 = 0 + 1 + 1 = 2
       -> myHeight = max(0, 1) + 1 = 2
       -> myDiameter = max(2, max(0, 1)) = 2
       -> return TreeInfo(height=2, diameter=2)
  -> dim3 = 2 + 2 + 1 = 5
  -> myHeight = max(2, 2) + 1 = 3
  -> myDiameter = max(5, max(3, 2)) = 5
  -> return TreeInfo(height=3, diameter=5)
```

[Go To Top](#content)

---

# Is subtree exist

**given two trees a big/main one and a small/subtree one verify whether the small tree is a subtree of bigger tree or not?**\
**example:**

big tree:
```
      1
     / \
    2   3
   / \
  4   5
```
small tree
```
   2
  / \
 4   5
```
**Output:**
Then the answer is `true` because this exact sub/small tree exists inside the big/main tree

### Approach
- we compare the root of subtree with each node of main tree 
- if we find the match then we simultaneously switch over the left & right subtree of the main/big & small tree 
- and then we compare those subtree

**Illustration**

big tree:
```
      1
     / \
    2   3
   / \
  4   5
```
small tree
```
   2
  / \
 4   5
```

1. first we check for 2 (root) from small tree into the big tree by traversing only over the big tree
    - at first we get 1 (root of big tree) != 2 (root of small tree)
    - in big tree shift to right subtree
    - we get 3 != 2 (root of small tree)
    - as there is no child of node 3 we backtrack and shift over the left subtree i.e, at node 1 (root of the gib tree)
    - we get 2 == 2 (root of small tree)
2. as we find the match we shift to right subtree in both big and small tree and then compare 
    - we get 5 (big tree) == 5 (small tree)
    - as there is no further nodes available we backtrack to left subtree of both small and big tree
    - we get 4 (big tree) == 4 (small tree)
3. as we traverse over the whole small tree and we have verify that all the nodes form the small tree is present in big tree w can say that small tre is a subtree of big tree
4. if we find the identical root but not identical subtrees then shift the pointers to previous nodes again i.e, back to root and again check of the match like you did in step 1 

### Algorithm:
1. check for null trees
    1. small tree = null :- null tree is present in all the trees therefor return true
    2. small tree = null & big tree = null :- both the trees are equal therefor return true
    3. small tree != null & big tree = null :- return the false
```java
if(subRoot == null){    // also applicable to case when both big & small tree is equal to null 
    return  true;
}
if(root == null){   // hidden condition subRoot != null as we are checking for this condition in above if block
    return  false;
}
```
2. if we find the match then check whether the subTree is identical or not and return true `only` if they are identical
```java
if(root.data == subRoot.data){
    if(isIdentical(root, subRoot)){
        return true;
    }
}
```
3. if they are not identical perform the same step 1 and 2 for there left and right subtree of big tree 
```java
return  subtree(root.left, subRoot) || subtree(root.right, subRoot);
```

**Note:**\
**1. this statement uses or (||) operator which says search for small tree in left subtree as well as in right subtree and will return true if `any one` subtree contain the given small tree else return false**\
**2. this line also make sure that you shift back to pervious nodes in case you find identical node but not identical subtree**

### Algorithm to check of identical subtree

**we use this algorithm to check is small tree identical to subtree of big tree or not in step 2**

1. check for null roots
    1. both the roots are null:- return true
    2. only one root is null:- return false
```java
 if(root == null && subRoot == null){   // both the root are null
    return true;
}
// as above condition is false there must be a root with not null value
if(root == null || subRoot == null){    // only one root is null 
    return  false;
}
```

2. if both the root have not null value then check is data of both the root are equal or not
```java
if(root.data == subRoot.data){  // root:- big tree, subRoot:- small tree
            
}
```
3. if data is equal then check for right and left subtrees in both of the trees
```java
if(root.data == subRoot.data){
    return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
}
```
- `isIdentical(root.left, subRoot.left):` will recursively traverse over the left of the subtrees and return true if data is match over every node else return false
- `isIdentical(root.right, subRoot.right):` will recursively traverse over the right of the subtrees and return true if data is match over every node else return false
- `&&:` and operator make sure that both the subtree return the true value, if one return true and other one return the false then the operator return false as final output
4. if data doesn't match then return false
```java
return false
```
### Code
```java

public class subtree {
    static  class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return  null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static boolean isIdentical(Node root, Node subRoot){
        if(root == null && subRoot == null){
            return true;
        }
        if(root == null || subRoot == null){
            return  false;
        }

        if(root.data == subRoot.data){
            return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
        }
        return false;
    }


   public static  boolean subtree(Node root, Node subRoot){
        if(subRoot == null){
            return  true;
        }
        if(root == null){
            return  false;
        }

        if(root.data == subRoot.data){
            if(isIdentical(root, subRoot)){
                return true;
            }
        }
        return  subtree(root.left, subRoot) || subtree(root.right, subRoot);
   }

    public static void main(String[] args) {
        int nodes[] = {1, 2, -1, -1, 3, 4, -1, -1, 5, -1, -1 };
        int subtreeNodes[] = {3, 4, -1, -1, 5, -1, -1 };

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        BinaryTree.idx = -1; 
        Node subRoot = tree.buildTree(subtreeNodes);
        
        System.out.println(subtree(root, subRoot));
    }
}
```
### OutPut:
```
true
```
### Tree Structures:
**Main Tree (root):**
```
      1
     / \
    2   3
       / \
      4   5
```

**Subtree (subRoot):**
```
    3
   / \
  4   5
```

 ### How the code runs step-by-step:
```java
subtree(1, 3)
  -> root.data != subRoot.data (1 != 3)
  -> subtree(2, 3) || subtree(3, 3)   
       -> subtree(2, 3)
            -> root.data != subRoot.data (2 != 3)
            -> subtree(null, 3) || subtree(null, 3)
                 -> subtree(null, 3) = false
                 -> subtree(null, 3) = false
                 -> return false
       -> subtree(3, 3)
            -> root.data == subRoot.data (3 == 3)
            -> isIdentical(3, 3)
                 -> 3 == 3
                 -> isIdentical(4, 4) && isIdentical(5, 5)   
                      -> isIdentical(4, 4)
                           -> 4 == 4
                           -> isIdentical(null, null) = true
                           -> isIdentical(null, null) = true
                           -> return true
                      -> isIdentical(5, 5)
                           -> 5 == 5
                           -> isIdentical(null, null) = true
                           -> isIdentical(null, null) = true
                           -> return true
                      -> return true && true = true
                 -> return true
            -> return true
       -> return true || false = true
-> return true
```

[Go To Top](#content)

---