# content
1. [Introduction to Trees](#introduction-to-trees)
2. [Build Tree Pre order](#build-tree-preorder)

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
3. declare the static variable which will represent the index of nodes array at first its value is -1 (it is set to -1 because we want to update this value recursively)
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
6. return null for -1 value of node
```java
if(nodes[idx] == -1){
    return  null;
}
```
7. make the `node[idx]` where `idx = 0 (1st index od nodes array)` root of the tree
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

**Complete code:**
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

