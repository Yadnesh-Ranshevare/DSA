# Content
1. [introduction](#introduction)
2. [implementation](#implementation)
3. [put Operation](#put-operation)



---

# introduction

In Data Structures and Algorithms (DSA), a HashMap (or Hash Table) is a data structure that stores key-value pairs. It allows you to store data in a way that enables fast retrieval, insertion, and deletion operations, typically in constant time, O(1), on average.

### Key-Value Pairs:
- A key is used to identify a value in the map, and each key is associated with a specific value.

- For example, in a HashMap of students and their grades:\
 Key: Student's name (e.g., "Alice")\
 Value: Grade (e.g., 85)

### implementation
hashmap in implemented as a array of linked list where each index of array contains the linked list
```
[ (node -> node) | (node -> node) | (node -> node) | (node -> node) ]
```
- bucket index is index of array
- n is number of node and N is size of array
- lambda = n/N represent exactly how full is your hashmap
- at each index of array there will be only two node of linked list so while performing the search operation the key dose not found at the first index then it will be present at second otherwise it will will be absent 
- if lambda become grater that a specific threshold value ( as there will be no more space for inserting the key value pair ) then we perform the rehashing to increase the size of the array
- we use special hash function to get the index of array where we store the key value pair we also use same hash function to find the index of array to search that key-value pair we are looking for 

### Operations
1. **put():** insert the key and data
2. **get():** give the data of respective key
3. **containskey():** returns true if hashmap contain the key otherwise return the false
4. **remove():** remove the respective key value pair
5. **size():** return the size of hashmap
6. **keyset():** return the set of key

[Go to Top](#content)

---

# implementation
**step 1:-** 
initialize the generic class:
```java
static class HashMap<k,v>{
    //hashmap code will be written here
} 
```
- `<k, v>:` This syntax defines two `generic type` parameters for the class.\
k represents the type of the key in the map.\
v represents the type of the value in the map.
- A `generic` parameter in Java refers to a `placeholder for a type` that can be specified when creating or using a class, interface, or method. It allows you to write more flexible, reusable, and type-safe code by enabling you to define classes, interfaces, and methods with types that can vary depending on the needs of the program.

**step 2:-** define the node class of LinkedList

- each node will have the key and value 
```java
private class Node{
    k key;
    v value;

    public Node(k key,v value){
        this.key = key;
        this.value = value;
    }
}
```
**this will be the private class as we don't need it outside the class**

**step 3:-** define some General variable
- n :- number of nodes 
- N :- number of buckets( size of array )
- bucket :- array of type linked list this the actual hashmap where we store our key-value pair
```java
private int n;  
private int N;  
private LinkedList<Node> bucket[];  //similar to private int arr[] just replace int with LinkedList of type node
```

**step 3:-** create the constructor for HashMap class initialize at step 1 with following data
- initialize the size of bucket declare in step 2
```java
this.N = 4;     //size can be change
```  
- initialize the bucket array declare in step 2
```java
this.bucket = new LinkedList[4];    //size will be same as N
```
- initialize an empty LinkedList at each index of the bucket
```
[ null | null | null | null ]   //before initializing empty LL
[ (empty node) | (empty node) | (empty node) | (empty node) ]   //after initializing empty LL
```
```java
for(int i = 0; i<4;i++){
    this.bucket[i] = new LinkedList<>(); 
}
```
- code
```java
public HashMap(){
    this.N = 4;
    this.bucket = new LinkedList[4];
    for(int i = 0; i<4;i++){
        this.bucket[i] = new LinkedList<>(); 
    }
}
``` 
[Go to Top](#content)

---

# put Operation
**Algorithm:**
1. `get the bucket index` of the key with the help of special hashFunction which return the bucket index for a key
```java
int bi = HashFunction(key);
```
2. check is key exist in the hashmap or not with the help of searchInLL function which returns the `data index` ( index of Linked list at each bucket index ) if it exist else return -1
```java
int di = searchInLL(key, bi);
```
3. if key doesn't exist the add the new node at the bucket index ad increase the n by 1 as n represent the number of node else change the value of that key
```java
if(di == -1){   //key doesn't exist
    bucket[bi].add(new Node(key,value));
    n++;
}else{  //key exist
    Node node = bucket[bi].get(di);
    node.value = value;
}
```
4. check the value of lambda and decide whether we need to perform rehashing or not
```java
double lambda = (double)n/N;
if(lambda > k){     //k :- can be any threshold value
    rehash();       //function that perform the rehashing
}
```

### code
```java
public void put(k key,v value){
    int bi = HashFunction(key);
    int di = searchInLL(key, bi);
    if(di == -1){
        bucket[bi].add(new Node(key,value));
        n++;
    }else{
        Node node = bucket[bi].get(di);
        node.value = value;
    }
    double lambda = (double)n/N;
    if(lambda > 2.0){
        rehash();
    }
}
```