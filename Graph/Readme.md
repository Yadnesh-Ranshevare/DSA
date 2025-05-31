# Content
1. [Introduction](#introduction)


# Introduction
A **graph** in Data Structures and Algorithms (DSA) is a **non-linear data structure** that represents connections (relationships) between a set of nodes (also called vertices).

- Simple Definitions:
    - A graph is a collection of nodes connected by edges.
    - Graph is a network of nodes


- **Components of a Graph:**
    1. **Vertex:** A node in the graph. 
    2. **Edge:** A connection between two nodes.   

                                        


- **Type of edges**

| Edge | Meaning|
|--- | ---|
| **Directed Edge**   | Edges have direction (A ➝ B is not the same as B ➝ A).                  |
| **Undirected Edge** | Edges have no direction (A—B means A is connected to B and vice versa). |
| **Weighted Edge**   | Edges have values (like distance or cost).                              |
| **Unweighted Edge** | Edges don't carry any value.                                            |

-  **Visual Example:**

Undirected Graph
```
A —— B
|    |
D —— C
```
Directed Graph
```
A → B
↓   ↑
D → C
```
Here Node A, B, C, D are the `vertex` and line/arrow connecting those Nodes are `Edge`

- **Types of Graphs:**

| Type of Graph                        | Description                                                                 | Example Illustration        |
| ------------------------------------ | --------------------------------------------------------------------------- | --------------------------- |
| **1. Directed Graph**                | Edges have direction (A ➝ B is not the same as B ➝ A).                      | A → B ← C                   |
| **2. Undirected Graph**              | Edges have no direction (A—B means both ways).                              | A — B — C                   |
| **3. Weighted Graph**                | Each edge has a weight (e.g., distance, cost).                              | A —5— B —2— C               |
| **4. Unweighted Graph**              | Edges have no weight.                                                       | A — B — C                   |
| **5. Cyclic Graph**                  | Contains at least one cycle (closed loop).                                  | A → B → C → A               |
| **6. Acyclic Graph**                 | No cycles exist in the graph.                                               | A → B → C                   |

**Note: single graph may come under the multiple graph type at once**\
**Example:**
Directed Unweighted Acyclic Graph 
```
A → B
↓   ↑
D → C
```

- **Real-life Examples:**

| Graph Type         | Example                                      |
| ------------------ | -------------------------------------------- |
| **Social network** | Users are nodes, friendships are edges       |
| **Map**            | Cities are nodes, roads are edges            |
| **Web**            | Webpages as nodes, hyperlinks as edges       |
| **Network**        | Routers/computers are nodes, cables as edges |


[Go To Top](#content)

---

# Storing a graph

there are 4 different ways of storing a graph i.e,
1. Adjacency List
2. Adjacency Matrix
3. Edge List
4. 2D Matrix (Implicit Graph)

### 1. Adjacency List
- this method is also known as List of Lists
- here we give each node a index from 0 to n-1 where n is total number of vertex 
- now for each node we maintain the list which contains two things source and destination with general syntax as follow: `{source, Destination}`
- all the lists of single nodes is again stored in a another list of size n at their respective index

Example:
```
0    3
 \  / \
  2 —— 1
```
total number of vertex = 4\
therefor range of index 0 to 3 

Here we have already assign a index to each node 

- for index 0: {0, 2}   -> here 0 is source and 2 is destination, this is same for each list
- for index 1: {{1, 2}, {1, 3}}
- for index 2: {{2, 0}, {2, 1}, {2, 3}}   
- for index 3: {{3, 1}, {3, 2}}

**Now our final Adjacency List will be:**\
**`{{0, 2}, {{1, 2}, {1, 3}}, {{2, 0}, {2, 1}, {2, 3}}, {{3, 1}, {3, 2}}}`**

**Note: in java you can implement the Adjacency list by using `hashMap`, `ArrayList of ArrayList` and `Array of ArrayList`. In above example we have implemented `Array of ArrayList`**



[Go To Top](#content)

---