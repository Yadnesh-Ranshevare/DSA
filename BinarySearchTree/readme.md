# Content

1. [Introduction](#introduction)
2. [Build a BST](#build-a-bst)
3. [Search in BST](#search-in-bst)
4. [Delete node](#delete-node)


# Introduction
- it is a updated version of the tree data structure\
[to learn about binary tree](../BinaryTree/readme.md)

- in normal tree the time complexity of searching any node is `O[n]` where **n is number of nodes** in that tree whereas in **BST** the time complexity of searching the node is `O[H]` where **H is height (always less that n)** of the tree and in `average cases H is equal to log(n)` therefor time complexity of average case is `O[log(n)]` which is quite fast therefor BST usually use in fast lookups 
operations
- Operations in BST:

| Operation | Time Complexity (Average) | Time Complexity (Worst) |
| --------- | ------------------------- | ----------------------- |
| Search    | O(log n)                  | O(n)                    |
| Insert    | O(log n)                  | O(n)                    |
| Delete    | O(log n)                  | O(n)                    |

- properties of BST
    1. Left subtree nodes < root
    2. Right subtree nodes > root
    3. Right and left subtree are also BST 
    4. BST doesn't contain duplicate nodes

**Let’s build a BST with values:** `4, 2, 5, 1, 3, 6`
```
        4
      /   \
     2     5
    / \     \
   1   3     6
```
**Note: By default, a Binary Search Tree (BST) does not allow duplicate nodes. However, if duplicates are allowed, there are common conventions to handle them:**\
**1. Store duplicates in the right subtree**\
**2. Store duplicates in the left subtree**

Example (allowing duplicates on the right):

**Inserting:** `5, 3, 7, 3`
```sql
      5
     / \
    3   7
     \
      3  ← duplicate stored in right subtree of first 3
```

- **Inorder traversal** of BST always gives a **sorted** (increasing) sequence

**Let’s build a BST with values:** `4, 2, 5, 1, 3, 6`
```
        4
      /   \
     2     5
    / \     \
   1   3     6
```
**Inorder Traversal:** `1 2 3 4 5 6`


[Go To Top](#content)

---

# Build a BST


### Approach:
1. we will start with root being null
2. then we start adding values one at a time
3. while adding first value our root is null therefor we make first value as a root of our whole tree
4. while adding further values we compare that value with root 
    - value > root : switch to right subtree
    - value < root : switch to left subtree
5. compare the value with each root of subtree and repeat step 4 util root becomes null
6. once we reach the subtree whose root is null then add that value at that node 


### illustration
**Here's a step-by-step illustration for values:** `5, 1, 3, 4, 2, 7`

**1. Insert 5**\
Tree is empty → insert 5 as root
```
5
```
**2. Insert 1**\
1 < 5 → go left
```
    5
   /
  1
```
**3. Insert 3**\
3 < 5 → go left\
3 > 1 → go right
```
    5
   /
  1
   \
    3
```
**4. Insert 4**\
4 < 5 → left\
4 > 1 → right\
4 > 3 → right
```
    5
   /
  1
   \
    3
     \
      4
```
**5. Insert 2**\
2 < 5 → left\
2 > 1 → right\
2 < 3 → left
```
    5
   /
  1
   \
    3
   / \
  2   4
```
**6. Insert 7**\
7 > 5 → go right
```
    5
   / \
  1   7
   \
    3
   / \
  2   4
```



### Algorithm:

1. we start with building the node class for our BST which represent the single node (this class is same as binary tree class)
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
2. we will start with initializing root to null value \
**Note: this root is the actual root of your whole tree**
```java
Node root = null
```
3. while inserting the value check whether the root is null or not 
```java
if(root == null){
    // root is null
}
```
4. if root is null then assign the current value to root and return that root
```java
if(root == null){
    root = new Node(val);
    return root;
}
```
5. check whether the value is greater than root or less than root
```java
if(root.data > val){
    // value is greater than root
}
if(root.data < val){
    // value is less than root
}
```
6. add the value into its respective subtree
```java
if(root.data > val){
    root.left = insert(root.left, val); // adding value into left subtree
}
if(root.data < val){
    root.right = insert(root.right, val); // adding value into left subtree
}
```
**Note: here `insert(root.left, val)` add the new value into left subtree and create the new subtree while `root.left = insert(root.left, val)` this line make sure that the new subtree attach to old tree**

7. return the root of the BST
```java
return root
```

### Code 
```java
public static Node insert(Node root,int val){
    if(root == null){
        root = new Node(val);
        return root;
    }

    if(root.data > val){
        root.left = insert(root.left, val);
    }
    if(root.data < val){
        root.right = insert(root.right, val);
    }
    return root;
}
```

### Complete Code:
```java
public class BuildBST{
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

    public static Node insert(Node root,int val){
        if(root == null){
            root = new Node(val);
            return root;
        }

        if(root.data > val){
            root.left = insert(root.left, val);
        }
        if(root.data < val){
            root.right = insert(root.right, val);
        }
        return root;
    }

    //  inorder traversal of binary tree
    public static void inOrder(Node root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        int value[] = {5, 1, 3, 4, 2, 7};

        Node root = null;

        for(int i = 0; i< value.length; i++){
            root = insert(root, value[i]);
        }

        inOrder(root);
    }
}
```
### Output:
```
1 2 3 4 5 7 
```

### Step-by-step Flow

**Insert 5:**
```java
insert(null, 5)
  -> root == null → create Node(5)
```
**Insert 1:**
```java
insert(5, 1)
  -> 5 > 1 → go left
    -> insert(null, 1)
         -> root == null → create Node(1)
  -> 5.left = Node(1)
```
**Insert 3:**
```java
insert(5, 3)
  -> 5 > 3 → go left
    -> insert(1, 3)
         -> 1 < 3 → go right
           -> insert(null, 3)
                -> root == null → create Node(3)
         -> 1.right = Node(3)
  -> 5.left updated
```
**Insert 4:**
```java
insert(5, 4)
  -> 5 > 4 → go left
    -> insert(1, 4)
         -> 1 < 4 → go right
           -> insert(3, 4)
                -> 3 < 4 → go right
                   -> insert(null, 4)
                        -> root == null → create Node(4)
                -> 3.right = Node(4)
         -> 1.right updated
  -> 5.left updated
```
**Insert 2:**
```java
insert(5, 2)
  -> 5 > 2 → go left
    -> insert(1, 2)
         -> 1 < 2 → go right
           -> insert(3, 2)
                -> 3 > 2 → go left
                   -> insert(null, 2)
                        -> root == null → create Node(2)
                -> 3.left = Node(2)
         -> 1.right updated
  -> 5.left updated
```
**Insert 7:**
```java
insert(5, 7)
  -> 5 < 7 → go right
    -> insert(null, 7)
         -> root == null → create Node(7)
  -> 5.right = Node(7)
```

### Final BST Structure:
```
       5
     /   \
    1     7
     \
      3
     / \
    2   4
```


[Go To Top](#content)

---

# Search in BST


### Time complexity
- **BST make search efficient, in BST time complexity of search operation is `O[h]` where h is height**

- **Balanced tree:** It is a binary tree where the **height difference between the left and right subtrees of every node is no more than 1**\
example:
```
    2
   / \
  1   3
```

**In a perfectly balanced BST, `h = log₂(n)`, so search is O(log n).** 

**Note: in most of the cases search time complexity of BST is `O[h]` where h is height because having perfectly balanced BST is extremly rear** 

- **skewed tree:** It is a special kind of binary tree where all nodes have only one child, either always on the left or always on the right 

**Right Skewed Tree:** Every node has only a right child:
```
1
 \
  2
   \
    3
     \
      4
       \
        5
```

in skewed tree search time complexity in increases to `O[n]` where n in number of node

### Final summary for time complexity

| Type          | Height | Search Time | Optimization |
| ------------- | ------ | ----------- | ------------ |
| Normal Tree   | h      | O(h)        | Average      |
| Balanced Tree | log(n) | O(log n)    | Best         |
| Skewed Tree   | n      | O(n)        | Worst        |


### Approach for search operation

Suppose you want to search for a value `key` in BST:

1. Start at the root node.

2. Compare key with current node value:

    - If **key == node.value**: 🎯 Found it!

    - If **key < node.value**: 🔽 Go to left subtree

    - If **key > node.value**: 🔼 Go to right subtree

3. Repeat step 2 until:

    - You find the value (success)

    - Or reach a `NULL` (value not present)

### Code:
```java
public static boolean search(Node root, int key){
    if(root == null){   // key not found
        return false;
    }

    if(root.data == key){   // value found
        return true;
    }

    if(root.data > key){    // go to left subtree
        return search(root.left, key);
    }

    if(root.data < key){    // go to right subtree
        return search(root.right, key);
    }

    return false;  // default return     
}
```
### complete code
```java

public class searchBST{
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

    // code to build tree
    public static Node insert(Node root,int val){
        if(root == null){
            root = new Node(val);
            return root;
        }

        if(root.data > val){
            root.left = insert(root.left, val);
        }
        if(root.data < val){
            root.right = insert(root.right, val);
        }
        return root;
    }


    public static boolean search(Node root, int key){
        if(root == null){
            return false;
        }
        if(root.data == key){
            return true;
        }
        if(root.data > key){
            return search(root.left, key);
        }
        if(root.data < key){
            return search(root.right, key);
        }
        return false;     
    }

    public static void main(String[] args) {
        int value[] = {5, 1, 3, 4, 2, 7};

        Node root = null;

        for(int i = 0; i< value.length; i++){
            root = insert(root, value[i]);
        }

        System.out.println(search(root, 4));
    }
}
```
### output:
```
true
```

### Example BST Structure:
```
       5
     /   \
    1     7
     \
      3
     / \
    2   4
```
### Step by step execution
```java
search(5, 4)
  -> 5 > 4 → go left
    -> search(1, 4)
         -> 1 < 4 → go right
           -> search(3, 4)
                -> 3 < 4 → go right
                  -> search(4, 4)
                      -> 4 == 4 → return true ✅
```



[Go To Top](#content)

---

# Delete node

To delete a node in a Binary Search Tree (BST), you need to consider three possible cases:

Example tree:
```
    1         
   / \
  2   3      
 / \   \ 
4   5   6   
```
1. No child (leaf node)
    - Delete node and return null 
    - example: delete 4
```
      1         
     / \
    2   3      
   / \   \
null  5   6   
```
2. single child
    - Delete node and replace it with child node
    - in java garbage collector will automatically free up the space, there is no need to write a extra line to free up the space
    - example: delete 3
```
    1         
   / \
  2   \      
 / \   \ 
4   5   6   
```
3. two child
    - replace the value with its **inorder successor**
    - inorder successor: next node in inorder traversal\
    eg., let say your inorder traversal is `4, 2, 5, 1, 3, 6`\
    here,\
    2 is inorder successor of 4  \
    5 is inorder successor of 2 \
    1 is inorder successor of 5 \
    3 is inorder successor of 1 \
    6 is inorder successor of 3 
    - once you replace the value with **inorder successor** delete that successor
    - while deleting the successor we will always apply case 1 or 2 
    - according to this algorithm node with 2 child can never be a inorder successor (inorder algorithm doesn't allow) and inorder successor will **always lie in right subtree**, if it has left child than according to inorder algorithm that left child comes before the current node as cannot becomes the successor node (this property doesn't apply if node has no child)
    - to find the successor node we just need to find the **left most node of right subtree** which is the smallest node in right subtree
    - to find he left most node in right subtree we continuously traverse in **only** left direction in right subtree until we reach null




### Algorithm to delete the node
1. search the node in given tree with the help of same approach as search algorithm ([to learn more about search operation](#search-in-bst)) :
    - If **key < node.value:** 🔽 Go to left subtree
    - If **key > node.value:** 🔼 Go to right subtree
    - **else:** 🎯 Found the node
2. once we found the node then we proceed with delete operation with all this three cases
    1. **single child:** return null instead of node
    2. **one child:** return the child node
    3. **two child:** change the value with its inorder successor and delete the successor
3. to find the cases:
    1. `root.left == null && root.right == null` : single child
    2. `root.left == null` : has right child
    3. `root.right == null` : ha left child
    4. if all the above cases fails then  that means root has two child's

**Code:**
```java
public static Node delete(Node root, int val){
    if(root.data > val){
        root.left = delete(root.left, val);
    }else if(root.data < val){
        root.right = delete(root.right, val);
    }else{  // root.data == val
        // case 1
        if(root.left == null && root.right == null){
            return  null;
        }

        // case 2
        if(root.left == null){
            return root.right;
        }else if(root.right == null){
            return  root.left;
        }

        // case 3
        Node IS = inorderSuccessor(root.right);     // function that return the inorder successor
        root.data = IS.data;
        root.right = delete(root.right, IS.data);
    }
    return root;
}
```

### Algorithm to find the Inorder successor
1. get the root of right subtree 
2. use while loop which will run until `root.left != null`
3. update the root during each iteration
4. return the root

**Code**
```java
public static Node inorderSuccessor(Node root){
    while(root.left != null){
        root = root.left;
    }
    return root;
}
```

**Note: we are not checking for `root == null` case because in delete node algorithm we are checking for this condition in advance (for single right child)**

### Complete code
```java


public class deleteNode{
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
    public static Node insert(Node root,int val){
        if(root == null){
            root = new Node(val);
            return root;
        }

        if(root.data > val){
            root.left = insert(root.left, val);
        }
        if(root.data < val){
            root.right = insert(root.right, val);
        }
        return root;
    }

    public static Node delete(Node root, int val){
        if(root.data > val){
            root.left = delete(root.left, val);
        }else if(root.data < val){
            root.right = delete(root.right, val);
        }else{  // root.data == val
            // case 1
            if(root.left == null && root.right == null){
                return  null;
            }

            // case 2
            if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return  root.left;
            }

            // case 3
            Node IS = inorderSuccessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right, IS.data);
        }
        return root;
    }

    public static Node inorderSuccessor(Node root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    // function to print inorder traversal
    public static void inOrder(Node root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        int value[] = {8, 5, 3, 1, 4, 6, 10, 11, 14};

        Node root = null;

        for(int i = 0; i< value.length; i++){
            root = insert(root, value[i]);
        }

        System.out.println("original");
        inOrder(root);
        System.out.println("\n \ndelete 4");

        delete(root, 4);
        inOrder(root);
        System.out.println("\n \ndelete 10");

        delete(root, 10);
        inOrder(root);
        System.out.println("\n \ndelete 5");

        delete(root, 5);
        inOrder(root);

    }
}
```
### Output:
```
original
1 3 4 5 6 8 10 11 14

delete 4
1 3 5 6 8 10 11 14

delete 10
1 3 5 6 8 11 14

delete 5
1 3 6 8 11 14
```
### Illustration of tree
**original tree**
```
        8
      /   \
     5     10
    / \      \
   3   6     11
  / \           \
 1   4          14
```
**After deleting 4**
```
        8
      /   \
     5     10
    / \      \
   3   6     11
  /             \
 1              14
```
**After deleting 10**
```
        8
      /   \
     5     11
    / \      \
   3   6     14
  /
 1
```
**After deleting 5**
```
        8
      /   \
     6     11
    /        \
   3         14
  /
 1
```


### step by step execution of the code
**Delete 4**
```java
delete(root, 4)
  -> root = 8
      -> 8 > 4 → go left
        -> delete(5, 4)
            -> 5 > 4 → go left
              -> delete(3, 4)
                  -> 3 < 4 → go right
                    -> delete(4, 4)  // Node to delete found ✅
                        -> 4 == 4 → node to delete found ✅
                        -> Node has no children → return null
              -> return 3 (3.right = null)
        -> return  5 (5.left = 3)
    -> return 8 (8.left = 5)
```
**Delete 10**
```java
delete(root, 10)
  -> root = 8
      -> 8 < 10 → go right
        -> delete(10, 10)  // Node 10 found ✅
            -> 10 == 10 → node to delete found ✅
            -> Node has no left child → return right child (11)
  -> return 8 → 8.right = 11
        
```
**Delete 10**
```java 
delete(root, 5)
  -> root = 8
      -> 8 > 5 → go left
        -> delete(5, 5)  // Node 5 found ✅
            -> 5 == 5 → node to delete found ✅
            -> Node has two children (left child 3, right child 6) → find inorder successor
            -> Inorder Successor: 6
            -> Replace node 5 with 6
            -> delete(6, 6)  // Now delete the inorder successor node (6) ✅
                -> 6 == 6 → node to delete found ✅
                -> Node has no child → return(null)
```



[Go To Top](#content)

---