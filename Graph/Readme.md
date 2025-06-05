# Content
1. [Introduction](#introduction)
2. Storing a graph
    1. [Adjacency List](#adjacency-list)
    2. [Adjacency Matrix](#adjacency-matrix)
    3. [Edge List](#edge-list)
    4. [Implicit Graph](#implicit-graph)


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

<!-- # Storing a graph

there are 4 different ways of storing a graph i.e,
1. [Adjacency List](#adjacency-list)
2. Adjacency Matrix
3. Edge List
4. 2D Matrix (Implicit Graph) -->

# Adjacency List
1. [Implementation for unweighted graph](#implementation-for-unweighted-graph)
2. [How to get neighbor](#how-to-get-the-neighboring-nodes)
3. [implementation for weighted graph](#adjacency-list-for-weighted-graph)

## Implementation for unweighted graph
- this method is also known as List of Lists
- here we give each node a index from 0 to n-1 where n is total number of vertex 
- now for each node we maintain the list which contains two things source and destination with general syntax as follow: `{source, Destination}`
- all the lists of single nodes is again stored in a another list of size n (total number of vertex) at their respective index

Example:
```
0    3
 \  / \
  2 —— 1
```
total number of vertex = 4\
therefor range of index 0 to 3 

Here we have already assign a index to each node, and that index represent that particular node

- for index 0: {0, 2}   -> here 0 is source and 2 is destination, this is same for each list
- for index 1: {{1, 2}, {1, 3}}
- for index 2: {{2, 0}, {2, 1}, {2, 3}}   
- for index 3: {{3, 1}, {3, 2}}

**Now our final Adjacency List will be:**\
**`{{0, 2}, {{1, 2}, {1, 3}}, {{2, 0}, {2, 1}, {2, 3}}, {{3, 1}, {3, 2}}}`**

**Note: in java you can implement the Adjacency list by using `hashMap`, `ArrayList of ArrayList` and `Array of ArrayList`. In above example we have implemented `Array of ArrayList`**


### How to create the Array of ArrayList?
- syntax of declaring Integer Array in java is as follow
```java
int arr[] = new int[n]  // array of int of size n
```
- in above example we have **array of type integer**, therefor to create the array of type ArrayList we just have to **replace int with ArrayList**
- type of Array  tells us what type of data is present in that Array
- **int** means in that Array at **each index a integer value is stored**, but in our example at each index there exist a ArrayLists and for that ArrayList at each index there exist a single **edge**\
**Note: in our code edge is a static class which act as a data type for declaring our ArrayList**  
- Therefor the syntax for declaring the **Array of ArrayList**
```java
ArrayList<Edge> arr[] = new ArrayList[n];   //Array of ArrayList of size n
```
- this Array of ArrayList is known as Graph
- At each index of the array we store the edges of that node
    - `arr[0]` = Edges of node 0
    - `arr[1]` = Edges of node 1
    - `arr[2]` = Edges of node 2 
    - `arr[3]` = Edges of node 3
- therefor the data store at each index of arr is as follow:
    - `arr[0]` = {0, 2}
    - `arr[1]` = {{1, 2}, {1, 3}}
    - `arr[2]` = {{2, 0}, {2, 1}, {2, 3}}
    - `arr[3]` = {{3, 1}, {3, 2}}
- Now this arr Array is our Adjacency list for our graph

### Structure of Edge
**Note: in our example we have unweighted edges, therefor we only store the source and destination for particular edge**

```java
static class Edge{
    int src;
    int dest;

    public Edge(int s, int d){
        this.src = s;
        this.dest = d;
    }
}
```

### How to add edges into the Adjacency list?
- first create our Array of ArrayList
```java
ArrayList<Edge> graph[] = new ArrayList[V];     // V is a total number of vertex
```
- Even though our graph Array is of type ArrayList at first by default before adding any data there exist a null value at each index (we cannot add any data at null)
- therefor create a empty ArrayList of type Edge at each index of that Array, soo that we can start adding data into it
```java
for (int i = 0; i< graph.length; i++){
    graph[i] = new ArrayList<Edge>();
}
```
- now as at each index of graph array there exist a ArrayList we can use `.add()` method to add the edges at each index of our Array to form our Adjacency list
- for node 0
```java
graph[0].add(new Edge(0, 2));
```
- for node 1
```java
graph[1].add(new Edge(1, 2));
graph[1].add(new Edge(1, 3));
```
- for node 2
```java
graph[2].add(new Edge(2, 0));
graph[2].add(new Edge(2, 1));
graph[2].add(new Edge(2, 3));
```
- for node 3
```java
graph[3].add(new Edge(3, 1));
graph[3].add(new Edge(3, 2));
```
**Note: each index of graph array represent a separate node who might have multiple edges**

### Complete Code
```java
import java.util.ArrayList;

public class AdjacencyList{
    static class Edge{
        int src;
        int dest;

        public Edge(int s, int d){
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for (int i = 0; i< graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 2));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 2));
    }
    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
    }
}
```

## How to get the neighboring nodes

**Note: in graph getting the neighboring nodes is most use algorithm, and in adjacency list method time complexity for getting the neighboring node is `O[E]` where E is number of edges. As this time complexity is faster than most of the other implementation and is only implemented in adjacency list we can say that adjacency list implementation of graph is the optimal implementation**

1. as each index in  Adjacency list represent its respective node, to get the neighbors of specific node we go to its specific index in Adjacency list
2. we get the ArrayList present at that index and traverse over it using for loop
```java
// printing the neighbor of node 2
for(int i = 0; i<graph[2].size(); i++){
    
}
```
3. at each index we get one Edge
```java
// printing the neighbor of node 2
for(int i = 0; i<graph[2].size(); i++){
    Edge e = graph[2].get(i);
}
```
3. Edge contains two thing first is source i.e, current node whose neighbor we are trying to find and second is destination that is all of its neighboring nodes
4. As we only want to get the neighboring node we print the destination form each index
```java
// printing the neighbor of node 2
for(int i = 0; i<graph[2].size(); i++){
    Edge e = graph[2].get(i);
    System.out.print(e.dest+" ");
}
```
#### Complete Code
```java
import java.util.ArrayList;

public class AdjacencyList{
    static class Edge{
        int src;
        int dest;

        public Edge(int s, int d){
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for (int i = 0; i< graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 2));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 2));
    }
    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        // printing the neighbor of node 2
        for(int i = 0; i<graph[2].size(); i++){
            Edge e = graph[2].get(i);
            System.out.print(e.dest+" ");
        }
    }
}
```
**Output:**
```
0 1 3
```

## Adjacency List for Weighted graph
- before you learn about this algorithm you must know how to implemented [adjacency list for unweighted graph](#implementation-for-unweighted-graph)

- our whole approach and code remains exactly same we just add an extra field weight into our Edge class

- Therefor the general syntax for list maintain by each node becomes as `{source, Destination, Weight}`

**Complete Code**
```java
import java.util.ArrayList;

public class AdjacencyListForWeightedGraph{
    static class Edge{
        int src;
        int dest;
        int wt;     // extra field weight

        public Edge(int s, int d, int wt){
            this.src = s;
            this.dest = d;
            this.wt = wt;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for (int i = 0; i< graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 2, 2));    // passing weight while adding the age into our graph

        graph[1].add(new Edge(1, 2, 10));
        graph[1].add(new Edge(1, 3, 0));

        graph[2].add(new Edge(2, 0, 2));
        graph[2].add(new Edge(2, 1, 10));
        graph[2].add(new Edge(2, 3, -1));

        graph[3].add(new Edge(3, 1, 0));
        graph[3].add(new Edge(3, 2, -1));
    }
    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        // printing the neighbor of node 2
        for(int i = 0; i<graph[2].size(); i++){
            Edge e = graph[2].get(i);
            System.out.println(e.dest+" , " + e.wt);    // printing wt along with neighbor nodes
        }
    }
}
```
**Output:**
```
0 , 2
1 , 10
3 , -1
```
**Graph use in above example code**
```
0 ——(2)—— 2 ——(-1)—— 3
          \         /
          (10)    (0)
            \     / 
             \   /
               1
```



[Go To Top](#content)

---

# Adjacency Matrix
- An Adjacency Matrix is another way to represent a graph, using a 2D array (or matrix) of size `V x V`, where `V` is the number of vertices in the graph.
- for every `i, j` value:
    1. for unweighted graph
        - `[i, j] = 0`: edge from `i` to `j` does't exist
        - `[i, j] = 1`: edge from `i` to `j` exist
    3. for weighted graph
        - `[i, j] = 0`: edge from `i` to `j` does't exist
        - `[i, j] = n`: edge from `i` to `j` exist, also n is a weight of that edge

## Example for unweighted graph
```
0    3
 \  / \
  2 —— 1
```
From the diagram, the connections (edges) are:
- 0 — 2 ------> [0, 2] & [2, 0] = 1
- 2 — 1 ------> [2, 1] & [1, 2] = 1
- 1 — 3 ------> [1, 3] & [3, 1] = 1
- 2 — 3 ------> [2, 3] & [3, 2] = 1

Therefor Adjacency Matrix:

|  Nodes     | 0 | 1 | 2 | 3 |
| ----- | - | - | - | - |
| **0** | 0 | 0 | 1 | 0 |
| **1** | 0 | 0 | 1 | 1 |
| **2** | 1 | 1 | 0 | 1 |
| **3** | 0 | 1 | 1 | 0 |

## Example for weighted graph
**Now assign some weights (just for illustration):**
| Edge  | Weight |
| ----- | ------ |
| 0 — 2 | 5      |
| 2 — 1 | 3      |
| 1 — 3 | 4      |
| 2 — 3 | 2      |
```
0 ——(5)—— 2 ——(2)——  3
          \         /
          (3)     (4)
            \     / 
             \   /
               1
```

From the diagram, the connections (edges) are:
- 0 — 2 ------> [0, 2] & [2, 0] = 5
- 2 — 1 ------> [2, 1] & [1, 2] = 3
- 1 — 3 ------> [1, 3] & [3, 1] = 4
- 2 — 3 ------> [2, 3] & [3, 2] = 2



**Weighted Adjacency Matrix:**

|     Nodes  | 0 | 1 | 2 | 3 |
| ----- | - | - | - | - |
| **0** | 0 | 0 | 5 | 0 |
| **1** | 0 | 0 | 3 | 4 |
| **2** | 5 | 3 | 0 | 2 |
| **3** | 0 | 4 | 2 | 0 |



## Disadvantage
1. **Wastes Space for Sparse Graphs**

    - Takes `O[V²]` space (V is number of vertex) regardless of the number of edges.
    - Even if the graph has very few edges (sparse), the matrix will still have lots of zeros.
    - For example, a graph with 1000 vertices but only 10 edges will still use a 1000×1000 matrix — most entries are 0.
2. **Slow to List Neighbors**

    - To get all neighbors of a vertex v, you have to scan the entire row, which takes `O[V]` (V is number of vertex) time.
    - In adjacency list, it’s only `O[number of neighbors]` (much faster for sparse graphs).

**Note: because of this two disadvantage using ADjacency matrix in any solution is inefficient therefor we are not covering the code implementation of Adjacency Matrix**


[Go To Top](#content)

---
# Edge List
An Edge List is the simplest way to represent a graph.\
It’s just a list of all edges, where each edge connects two vertices — and can also store weights if needed.

**Format:**\
Each edge is represented as:

- For unweighted graphs: `(u, v)`
- For weighted graphs: `(u, v, weight)`


```
0    3
 \  / \
  2 —— 1
```
Edges from the diagram:

- 0 — 2
- 2 — 1
- 1 — 3
- 2 — 3
- 0 — 3

Therefor **Edge List** = `{[0, 2], [2, 1], [1, 3], [2, 3], [0, 3]}`

**Note: each edge added only once i.e, edge [0, 2] is similar to edge [2, 0]**

[Go To Top](#content)

---

# Implicit Graph

here you have given a 2D matrix and each block of this matrix is a node of a graph also adjacent cells of each block is a neighbors of that block

for n = 3\
therefor our 2D(3 x 3) array/matrix look like:
|  | | |
|---|---|---|
| | | |
| | | |

lets give temporary numbering to each block
| 1 | 2| 3|
|---|---|---|
|4 |5 |6 |
| 7| 8|9 |

here block `1, 2, 3, 4, 5, 6, 7, 8, 9` are each individual Node of a graph also neighbor of each block is as follow
| block | neighbors |
|--- | ---|
| 1 | 2, 4
| 2 | 1, 3, 5
| 3 | 2, 6
| 4 | 1, 5, 7
|5 | 2, 4, 6, 8
| 6 | 3, 5, 9
| 7 | 4, 8
| 8 | 5, 7, 9
| 9 | 6, 8

- In implicit graph each node can have maximum of 4 neighbors
- Lets assume i am at node `[i, j]` in our implicit graph therefor for this node:   
    1. `[i-1, j]`: upper neighbor 
    2. `[i+1, j]`: lower neighbor
    3. `[i, j-1]`: left neighbor
    4. `[i, j+1]`: right neighbor


[Go To Top](#content)

---