# Content
1. [Introduction](#introduction)
2. [Storing a graph](#storing-a-graph)
    1. [Adjacency List](#adjacency-list)


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
1. [Adjacency List](#adjacency-list)
2. Adjacency Matrix
3. Edge List
4. 2D Matrix (Implicit Graph)

# Adjacency List
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


## How to create the Array of ArrayList?
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
- therefor the data store at each index of arr
    - `arr[0]` = {0, 2}
    - `arr[1]` = {{1, 2}, {1, 3}}
    - `arr[2]` = {{2, 0}, {2, 1}, {2, 3}}
    - `arr[3]` = {{3, 1}, {3, 2}}
- Now this arr Array is our Adjacency list for our graph

## Structure of Edge
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

## How to add edges into the Adjacency list?
- first create our Array of ArrayList
```java
ArrayList<Edge> graph[] = new ArrayList[V];     // V is a total number of vertex
```
- Even though our graph Array is of type ArrayList at first by default before adding any data there exist a null value at each index (we cannot add any data at null)
- therefor create a empty ArrayList of type Edge at each index of the Array, soo that we can start adding data into it
```java
for (int i = 0; i< graph.length; i++){
    graph[i] = new ArrayList<Edge>();
}
```
- now we use `.add()` method to add the edges in our Adjacency list
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

## Complete Code
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

[Go To Top](#content)

---