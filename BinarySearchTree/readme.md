# Content

1. [Introduction](#introduction)
2. [Build a BST](#build-a-bst)


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

1. we start with building the node class for our BST which represent the single node
```java
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

### Code:
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