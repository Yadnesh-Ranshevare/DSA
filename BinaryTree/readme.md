# content
1. [Introduction to Trees](#introduction-to-trees)
2. [Build Tree Pre order](#build-tree-preorder)
3. [Traversal](#traversal)
4. [Level order traversal](#level-order-traversal)
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
(4 is complete therefor get back to previous Node i.e, 2)
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
(5 is complete so go back to previous Node i.e, 2, now as 2 is also complete again go back to previous Node which is 1)

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

**we use recursive approach to solve this problem where we build left side of the tree recursively (we get the left subtree during each recursion call) and once we reach the -1 we return the null and shift to right subtree (we get the right subtree during each recursion call)**

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

