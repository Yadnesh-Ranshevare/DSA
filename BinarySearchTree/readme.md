# Content

1.[Introduction](#introduction)


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