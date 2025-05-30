# Content
1. [introduction](#introduction)
2. [implementation](#implementation)
3. [put Operation](#put-operation)
4. [hash function](#hash-function)
5. [Search in LL](#search-in-ll)
6. [Rehashing](#rehashing)
7. [Contains key Function](#contains-key-function)
8. [Get Function](#get-function)
9. [Remove Function](#remove-function)
10. [isEmpty Function](#isempty-function)
11. [keySet Function](#keyset-function)
12. [How To Print The Hashmap](#how-to-print-the-hashmap)
13. [Complete Code](#complete-code)
14. [Using Collection Framework](#using-collection-framework)
15. [HashSet Using Collection Framework](#hashset-using-collection-framework)
16. [Majority Element Question](#majority-element-question)
17. [Union Of Two Array](#union-of-two-array)
18. [Intersection of Two Array](#intersection-of-two-array)
19. [Find Itinerary from Tickets](#find-itinerary-from-ticket)
20. [Count Sub Array](#count-sub-array)


---

# introduction

In Data Structures and Algorithms (DSA), a HashMap (or Hash Table) is a data structure that stores key-value pairs. It allows you to store data in a way that enables fast retrieval, insertion, and deletion operations, typically in constant time, O(1), on average.

### Key-Value Pairs:
- A key is used to identify a value in the map, and each key is associated with a specific value.

- For example, in a HashMap of students and their grades:\
 Key: Student's name (e.g., "Alice")\
 Value: Grade (e.g., 85)

### how hashmap is implemented
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

**step 4:-** create the constructor for HashMap class initialize at step 1 with following data
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
1. `get the bucket index` of the key with the help of special hashFunction which return the bucket index for a key\
[to learn how hash function works](#hash-function)
```java
int bi = HashFunction(key);
```
2. check is key exist at that bucket index in the hashmap or not with the help of searchInLL function which take key and bucket index as an argument and returns the `data index` ( index of Linked list at which the key is present ) if it exist else return -1\
[to learn about search in LL](#search-in-ll)
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
4. check the value of lambda and decide whether we need to perform rehashing or not\
[to learn about rehashing](#rehashing)
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

[Go to Top](#content)

---
# Hash Function

- it takes a key value as an input and return the positive index with respect to that key
- `.hashCode():` in build function of java that hash the value and return the integer value, this integer values range start form negative infinity to positive infinity
- `Math.abs(value):` **returns the modules** of the value i.e, negative becomes positive and positive remains positive

**code:**
```java
private int HashFunction(k key){
    int bi = key.hashCode();
    return Math.abs(bi) % N;
}
```
**Note we always want our bucked index between 0 to N-1 where N is the size of bucket array that why take modulo of the bi with N to keep the number between the 0 and N-1**

[Go to Top](#content)

---
# Search in LL
1. get the key and bucket index as a input to check is that key already present at that bucket index or not 
2. get the LL present at that bucket index within the bucket array ( hashmap )
```java
LinkedList<Node> LL = bucket[bi];
```
3. traverse ove the that LL to check is key exist or not if exist then return the respective bucket index
```java
for(int i = 0; i < LL.size(); i++){
    if(LL.get(i).key == key){
        return i;
    }
}
```
4. if key doesn't exist then return the -1

**code:**
```java
private int searchInLL(k key, int bi){
    LinkedList<Node> LL = bucket[bi];
    for(int i = 0; i < LL.size(); i++){
        if(LL.get(i).key == key){
            return i;
        }
    }
    return  -1;
}
```

[Go to Top](#content)

---

# Rehashing
- It is a concept of creating a new larger array (buckets) and re-inserting all old key-value pairs using new hash values.
- It helps maintain fast performance (O(1)) by reducing collisions.

**Algorithm:**
1. **get the original bucket** to store the element after the resizing
```java
LinkedList<Node> oldBucket[] = bucket;
```
2. as the data of the original bucket is now secure **resize ( increasing the size ) the original bucket** by redeclaring the bucket as array of LL with x2 size as original bucket \
**N :- size of original bucket**
```java
bucket = new LinkedList[N*2];
```

3. initialize the empty LL at each index of the resized bucket array\
**let say N(size of original bucket array) = 2**\
**Therefor N*2(size of new bucket array) = 4** 
```
[ null | null | null | null ]   //before initializing empty LL
[ (empty node) | (empty node) | (empty node) | (empty node) ]   //after initializing empty LL
```
**code:**
```java
for(int i = 0; i<N*2;i++){
    bucket[i] = new LinkedList<>();
}
```
 **As new bucket is ready to store the values we start inserting the value from oldBucket(where we store the values of original bucket before resizing it) to newly resized bucket and to do that we follow the step 4,5,6** 

4. traverse over the oldBucket to get the LL stored at each index
```java
for(int i = 0;  i<oldBucket.length;i++){
    LinkedList<Node> ll = oldBucket[i]; //getting the LL present at each index of oldBucket array

    // ignore this part of the code for now

    // for(int j = 0;j<ll.size();j++){
    //     Node node = ll.get(i);
    //     put(node.key,node.value);
    // }
}
```
5. now for all LL in oldBucket traverse over each LL one by one, and get the each node
```java
for(int i = 0;  i<oldBucket.length;i++){
    LinkedList<Node> ll = oldBucket[i]; //getting the LL present at each index of oldBucket array

    for(int j = 0;j<ll.size();j++){
        Node node = ll.get(i);  // getting the each node from ll linked list
        
        // put(node.key,node.value);  // ignore this line for now
    }
}
```
6. now for each node call the `put()` function (as this time it will put this node into newly resized bucket) and pass the key and value of that node \
[to learn about the put function](#put-operation)
```java
for(int i = 0;  i<oldBucket.length;i++){
    LinkedList<Node> ll = oldBucket[i]; //getting the LL present at each index of oldBucket array

    for(int j = 0;j<ll.size();j++){
        Node node = ll.get(i);  // getting the each node from ll linked list
        put(node.key,node.value); 
    }
}
```

**complete code:**
```java
public void rehash(){
    LinkedList<Node> oldBucket[] = bucket;
    bucket = new LinkedList[N*2];
    for(int i = 0; i<N*2;i++){
        bucket[i] = new LinkedList<>();
    }
    for(int i = 0;  i<oldBucket.length;i++){
        LinkedList<Node> ll = oldBucket[i];
        for(int j = 0;j<ll.size();j++){
            Node node = ll.get(i);
            put(node.key,node.value);
        }
    }
}
```



[Go to Top](#content)

---
# Contains key Function
this function accepts the key as argument and check is that key present in the hashmap or not if it exist then it return the value of that key else it return the null
**Algorithm:**
1. get the bucket index of the key with the help of hash function\
**this bucket index represent the index of bucket array at which the LL with provide key might be present**\
[to learn about hash function](#hash-function)
```java
int bi = HashFunction(key);
```
2. check if that key is present in the LL present at the respective bucket index (bi) or not with the help of search in LL function\
**this function returns the data index which is equal to index within the LL where the key is present else return the -1**\
[to learn about search in LL](#search-in-ll) 
```java
int di = searchInLL(key, bi);
``` 
3. if we get the valid data index (di != -1) then return true else return false
```java
if(di == -1){
    return  false;
}else{
    return  true; 
}
```
**Complete code:**
```java
public boolean  containsKey(k key){
    int bi = HashFunction(key);
    int di = searchInLL(key, bi);
    if(di == -1){
        return  false;
    }else{
        return  true; 
    }
}
```

[Go to Top](#content)

---
# Get Function
this function accepts the key as argument and check is that key present in the hashmap or not if it exist then it return the value of that key else it return the null
**Algorithm:**
1. get the bucket index of the key with the help of hash function\
**this bucket index represent the index of bucket array at which the LL with provide key might be present**\
[to learn about hash function](#hash-function)
```java
int bi = HashFunction(key);
```
2. check if that key is present in the LL present at the respective bucket index (bi) or not with the help of search in LL function\
**this function returns the data index which is equal to index within the LL where the key is present else return the -1**\
[to learn about search in LL](#search-in-ll) 
```java
int di = searchInLL(key, bi);
``` 
3. if we get the valid data index (di != -1) then return the value present at that index from the respective LL else return null
```java
if(di == -1){
    return  null;
}else{
    Node node = bucket[bi].get(di);
    return node.value; 
}
```
**complete code:**
```java
public v get(k key){
    int bi = HashFunction(key);
    int di = searchInLL(key, bi);
    if(di == -1){
        return  null;
    }else{
        Node node = bucket[bi].get(di);
        return node.value; 
    }
}
```

[Go to Top](#content)

---


# Remove Function
this function get the key as an argument and checks is that key present in the hashmap or not if it present then it remove that key value pair and return them as well else return the null\
**Algorithm:**
1. get the bucket index of the key with the help of hash function\
**this bucket index represent the index of bucket array at which the LL with provide key might be present**\
[to learn about hash function](#hash-function)
```java
int bi = HashFunction(key);
```
2. check if that key is present in the LL present at the respective bucket index (bi) or not with the help of search in LL function\
**this function returns the data index which is equal to index within the LL where the key is present else return the -1**\
[to learn about search in LL](#search-in-ll) 
```java
int di = searchInLL(key, bi);
``` 
3. if we get the valid data index (di != -1) then remove and return the value present at that index from the respective LL else return null
```java
if(di == -1){
    return  null;
}else{
    Node node = bucket[bi].remove(di);
    return node.value; 
}
```
4. update the count total number of node present in the hashmap if we successfully remove the node from the hashmap
```java
if(di == -1){
    return  null;
}else{
    Node node = bucket[bi].remove(di);
    n--;
    return node.value; 
}
``` 
**Complete code:**
```java
public v remove(k key){
    int bi = HashFunction(key);
    int di = searchInLL(key, bi);
    if(di == -1){
        return  null;
    }else{
        Node node = bucket[bi].remove(di);
        n--;
        return node.value; 
    }
}
```


[Go to Top](#content)

---
# isEmpty Function
it is a function that return the true if hashmap is empty else it return the false
- in hashmap implementation we declare the variable n which represent the number of node present in the hashmap 
- soo just check the value of n if it equal to 0 then hashmap is empty else in is not
- [check the step 3 of hashmap implementation](#implementation)

**Code:**
```java
public boolean isEmpty(){
    return n==0;
}
```

[Go to Top](#content)

---

# keySet function
it is the function that return the array of keys present into the hashmap

**Algorithm:**
1. create the empty ArrayList to store the keys
```java
ArrayList<k> keys = new ArrayList<>();
```
2. traverse over the while bucket array and get the LL present at each index
```java
for(int i = 0; i<bucket.length; i++){
    LinkedList<Node> ll = bucket[i];  //getting the LL stored at each index of the bucket array

    // ignore this code
    
    // for(int j=0;j<ll.size();j++){
    //     Node node = ll.get(j);
    //     keys.add(node.key);
    // }
}
```
3. traverse over the every single LL stored in bucket array and get their respective node
```java
for(int i = 0; i<bucket.length; i++){
    LinkedList<Node> ll = bucket[i];  //getting the LL stored at each index of the bucket array

    for(int j=0;j<ll.size();j++){
        Node node = ll.get(j);  // getting the each node from ll
        // keys.add(node.key);
    }
}
```
4. add the key of that node into the keys ArrayList
```java
for(int i = 0; i<bucket.length; i++){
    LinkedList<Node> ll = bucket[i];  //getting the LL stored at each index of the bucket array

    for(int j=0;j<ll.size();j++){
        Node node = ll.get(j);  // getting the each node from ll
        keys.add(node.key);
    }
}
```
5. return the keys ArrayList
```java
return keys;
```
**Complete code:**
```java
public ArrayList<k> keySet(){
    ArrayList<k> keys = new ArrayList<>();
    for(int i = 0; i<bucket.length; i++){
        LinkedList<Node> ll = bucket[i];
        for(int j=0;j<ll.size();j++){
            Node node = ll.get(j);
            keys.add(node.key);
        }
    }
    return keys;
}
``` 

[Go to Top](#content)

---

# How to Print The Hashmap
1. first get all the keys of the hashmap with the help of keySet() function\
[to learn about keySet Function](#keyset-function)
```java
ArrayList<k> keys = keySet();
```
2. for every key get its respective value with the help of get function\
[to learn about get Function](#get-function)
```java
for(int i=0; i<keys.size();i++){
    System.out.println(keys.get(i)+" "+get(keys.get(i)));
}
```

**Complete code:**

```java
public void print(){
    ArrayList<k> keys = keySet();
    for(int i=0; i<keys.size();i++){
        System.out.println(keys.get(i)+" "+get(keys.get(i)));
    }
}
```


[Go to Top](#content)

---

# Complete Code
```java
import java.util.*;

public class HashMapClass{

    static class HashMap<k,v>{
        private class Node{
            k key;
            v value;

            public Node(k key,v value){
                this.key = key;
                this.value = value;
            }
        }

        private int n;  //number of nodes
        private int N;  //number of bucket
        private LinkedList<Node> bucket[];

        public HashMap(){
            this.N = 4;
            this.bucket = new LinkedList[4];
            for(int i = 0; i<4;i++){
                this.bucket[i] = new LinkedList<>(); 
            }
        }

        private int HashFunction(k key){
            int bi = key.hashCode();
            return Math.abs(bi) % N;
        }

        private int searchInLL(k key, int bi){
            LinkedList<Node> LL = bucket[bi];
            for(int i = 0; i < LL.size(); i++){
                if(LL.get(i).key == key){
                    return i;
                }
            }
            return  -1;
        }


        public void rehash(){
            LinkedList<Node> oldBucket[] = bucket;
            bucket = new LinkedList[N*2];
            for(int i = 0; i<N*2;i++){
                bucket[i] = new LinkedList<>();
            }
            for(int i = 0;  i<oldBucket.length;i++){
                LinkedList<Node> ll = oldBucket[i];
                for(int j = 0;j<ll.size();j++){
                    Node node = ll.get(i);
                    put(node.key,node.value);
                }
            }
        }

        public void put(k key,v value){
            int bi = HashFunction(key);
            int di = searchInLL(key, bi);
            if(di == -1){  //not exist
                bucket[bi].add(new Node(key,value));
                n++;
            }else{  //exist
                Node node = bucket[bi].get(di);
                node.value = value;
            }
            double lambda = (double)n/N;
            if(lambda > 2.0){
                rehash();
            }
        }

        public v get(k key){
            int bi = HashFunction(key);
            int di = searchInLL(key, bi);
            if(di == -1){  //not exist
                return  null;
            }else{  //exist
                Node node = bucket[bi].get(di);
                return node.value; 
            }
        }
        public boolean  containsKey(k key){
            int bi = HashFunction(key);
            int di = searchInLL(key, bi);
            if(di == -1){  //not exist
                return  false;
            }else{  //exist
                return  true;
            }
        }

        public v remove(k key){
            int bi = HashFunction(key);
            int di = searchInLL(key, bi);
            if(di == -1){  //not exist 
                return  null;
            }else{  //exist
                Node node = bucket[bi].remove(di);
                n--;
                return node.value; 
            }
        }

        public boolean isEmpty(){
            return n==0;
        }

        public ArrayList<k> keySet(){
            ArrayList<k> keys = new ArrayList<>();
            for(int i = 0; i<bucket.length; i++){
                LinkedList<Node> ll = bucket[i];
                for(int j=0;j<ll.size();j++){
                    Node node = ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
        }


        public void print(){
            ArrayList<k> keys = keySet();
            for(int i=0; i<keys.size();i++){
                System.out.println(keys.get(i)+" "+get(keys.get(i)));
            }
        }


    }


    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("india",100);
        map.put("china",200);
        map.put("us",150);

        System.out.println("before removing");
        map.print();

        map.remove("us");

        System.out.println("\nafter removing");
        map.print();
    }
}
```

**Output:**
```bash
before removing
us 150
india 100
china 200

after removing
india 100
china 200
```
[Go to Top](#content)

---
# Using Collection Framework
**Most of the methods are work same**

```java
import java.util.*;


public class HashmapUsingCollectionFramework {

    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<>();

        map.put("india",1000);
        map.put("us",500);
        map.put("china",2000);

        System.out.println(map);
        
        map.put("us",700);      //update
        
        System.out.println(map);

        if(map.containsKey("japan")){
            System.out.println("key is present in the map");
        }else{
            System.out.println("key is not present in the map");
        }


        System.out.println(map.get("india"));   // key exist
        System.out.println(map.get("japan"));   // key doesn't exist

        // iteration
        for(Map.Entry<String,Integer> e : map.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }

        Set<String> keys = map.keySet();
        System.out.println(keys);

        map.remove("china");
        System.out.println(map);
    }
    
}
```
### iteration over hashmap:
in java there is another syntax for iterating over collection
```java
int arr[] = {1,2,3,4};
for (int val : arr){
    System.out.println(val);
}
```
**here we get each value from arr array in val variable during each iteration**

**output:**
```
1
2
3
4
```
**Therefor iteration in hashmap is done as follow**
```java
for(Map.Entry<String,Integer> e : map.entrySet()){
    System.out.println(e.getKey()+" "+e.getValue());
}
```


[Go to Top](#content)

---

# HashSet Using Collection Framework
A HashSet in Java is a collection that `stores unique elements` and uses hashing to manage them. It is part of the Java Collections Framework and provides `fast performance` for basic operations like adding, removing, and checking if an item exists. It `does not maintain the order` of elements.
```java
import java.util.HashSet;
import java.util.Iterator;

public class HashSetUsingCollectionFramework {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);   
        set.add(2);   
        set.add(3);   
        set.add(4);   
        set.add(1);         // will ignore this duplicate  

        System.out.println("size of the set is: "+set.size());

        System.out.println(set);

        if(set.contains(1)){
            System.out.println("set contains 1");
        }
        if(!set.contains(5)){
            System.out.println("set does not contain 5");
        }
        
        set.remove(1);

        if(!set.contains(1)){
            System.out.println("set does not contain 1");
        }

        Iterator it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
```
### Iterator in java
An **Iterator** is an object used to **loop through elements of a collection (like ArrayList, HashSet, etc.) one by one**.

**Has two basic method**
- `.next():` Returns the next element ( also update the Iterator )
- `.hasNext():` Checks if there is a next element and return true or false accordingly
```java
import java.util.HashSet;
import java.util.Iterator;

public class HashSetUsingCollectionFramework {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);   
        set.add(2);   
        set.add(3);   
        set.add(4);   

        Iterator it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
```
**output:**
```
1
2
3
4
```



[Go to Top](#content)

---
# Majority Element Question
**given an integer array of size n, find all the element that appear more than n/3 times**

**Basics Approach:**
1. create the hashmap who has all the number from the question array along with there frequency count
2. for each key in hashmap check whither the value is greater than array size divide by 3 or not
4. if it is greater then add that value into ans array 
5. return the ans array

### NOTE: in hashmap you can store value in two ways

lets assume question array to be:
```
int num[] = {1,3,1,2,1}
```

#### 1. frequency as key and number as value:

in this way your hash table will look like this

key(frequency) | value(number)
---|---
1 | [3,2]
3 | 1

as you can see that for key(frequency) 1 we have two values(3,2) and to store them we need our value data type to be array which is space inefficient also the search time for each number in question is high  

#### 2. number as key and frequency as value
in this way your hash table will look like this

key(number) | value(frequency)
---|---
1 | 3
2 | 1
3 | 1

in this way although the table size is large but we need Integer datatype to store the value which is more space efficient that array and also with this approach we can perform the search operation in less amount of time


**Algorithm:**
1. create the ans `ArrayList<Integer>` and a `HashMap<Integer,Integer>` to store the ans array and question array data(number and its frequency) respectively
```java
ArrayList<Integer> ans = new ArrayList<>();
HashMap<Integer,Integer> map = new HashMap<>();
```
2. traverse over the question array and for every element in question array check whether that number(as key) present in hashmap or not
```java
for(int i=0;i<num.length;i++){      // num is a question array
    if(map.containsKey(num[i])){
        // key exist
    }else{
        // key does not exist
    }
}
```
3. if key exist then increase it's value(frequency) by 1 
```java
for(int i=0;i<num.length;i++){      // num is a question array
    if(map.containsKey(num[i])){
        map.put(num[i] , map.get(num[i])+1);   // updating the frequency 
    }else{
        // key does not exist
    }
}
```
4. if key does not exist the create the new key value pair with value (frequency) as 1
```java
for(int i=0;i<num.length;i++){      // num is a question array
    if(map.containsKey(num[i])){
        map.put(num[i] , map.get(num[i])+1);    
    }else{
        map.put(num[i],1);  // creating the new key value pair
    }
}
```
5. at this point you'll get the hashmap with all the number present inside the question array and there respective frequency
6. get the keyset of the hashmap and traverse over each key
```java
for(int key : map.keySet()){
    
}
```
7. now for each key in key set check is there any value greater that question array divide by 3 or not
```java
for(int key : map.keySet()){
    if(map.get(key) > num.length / 3){
        // if value is greater that num.length / 3
    }
}
```
8. if value is greater that question array divide ny 3 the add that value into the ans ArrayList
```java
for(int key : map.keySet()){
    if(map.get(key) > num.length / 3){
        ans.add(key);
    }
}
```
9. return the ans ArrayList
```java
return ans;
```

**Complete code:**
```java
import java.util.*;

public class MajorityElement{

    public static ArrayList<Integer> majority(int num[]){
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<num.length;i++){
            if(map.containsKey(num[i])){
                map.put(num[i] , map.get(num[i])+1);
            }else{
                map.put(num[i],1);
            }
        }

        
        for(int key : map.keySet()){
            if(map.get(key) > num.length / 3){
                ans.add(key);
            }
        }

        return ans;
    } 

    public static void main(String[] args) {
        int num[] = {1,3,2,5,1,3,1,3,1,3};

        ArrayList<Integer> ans = majority(num);

        System.out.println(ans);
    }
}
```
**output:**
```
[1, 3]
```


[Go to Top](#content)

---

# Union Of Two Array
**Given two array perform the union operation on them and count the number of element present inside the union array**

`UNION (All unique elements together)`: Union operation is the process of combining all elements from two or more sets (or collections) into a single set that contains every distinct element from each set, without duplicates.

NOTE: we can also solve this same problem `using nested loops` who has time complexity of `O[n^2]`
```java
public static int unionArray(int arr1[],int arr2[]){
    int ans[] = new int[arr1.length+arr2.length];
    int k = 0; // for indexing

    for(int i = 0;i<arr1.length;i++){
        boolean existFlag = false;
        for(int j = 0;j<ans.length;j++){
            if(arr1[i] == ans[j]){  // checking is element exist in union array or not
                existFlag = true;
            }
        }
        if(!existFlag){     // if element does not exist
            ans[k++] = arr1[i];
        }
    }
    
    for(int i = 0;i<arr2.length;i++){
        boolean existFlag = false;
        for(int j = 0;j<ans.length;j++){    // checking is element exist in union array or not
            if(arr2[i] == ans[j]){
                existFlag = true;
            }
        }
        if(!existFlag){     // if element does not exist
            ans[k++] = arr2[i];
        }
    }

    // to print the ans array
    for(int i = 0;i<ans.length;i++){
        System.out.print(ans[i]+" ");
    }

    return k;
} 
```
### Solution using HashSet
**By using hashSet we can reduce the time complexity to `O[n]` as HashSet doesn't allow the duplicate element** 

**Algorithm:**
1. create then hashSet to store the ans element
```java
HashSet<Integer> u = new HashSet<>();
```
2. iterate over the first array and perform the add operation during each iteration
```java
for (int i = 0 ; i< arr1.length;i++){
    u.add(arr1[i]);
}
```
**Note: while performing the add operation on HashSet it skip the element that already present inside the HashSet to avoid adding duplicate element**

3. perform the same operation for second array
```java
for(int i = 0;i < arr2.length;i++){
    u.add(arr2[i]);
}
```
4. return the size of the HashSet
```java
return u.size();
```

**Code:**



```java
public static int unionHashSet(int arr1[],int arr2[]){
    HashSet<Integer> u = new HashSet<>();
    for (int i = 0 ; i< arr1.length;i++){
        u.add(arr1[i]);
    }
    for(int i = 0;i < arr2.length;i++){
        u.add(arr2[i]);
    }
    // print the ans HashSet
    System.out.println(u);
    return u.size();
}
```

### Solution Using ArrayList
**we can use same algorithm as HashSet to solve this problem using ArrayList the only difference is, before adding element(within the step 2) we need to check whether the element exist in that ans ArrayList or not with the help of `.contains` method**

`.contains():` returns true if element exist inside the arrayList
```java
public static int unionArraylist(int arr1[],int arr2[]){
    ArrayList<Integer> ans = new ArrayList<>();
    for (int a : arr1){     // iterating over arr1
        if(!ans.contains(a)){
            ans.add(a);
        }
    }
    for (int a:arr2){       // iterating over arr2
        if(!ans.contains(a)){
            ans.add(a);
        }
    }

    //print the ans arrayList
    System.out.println(ans);
    return ans.size();
}
```

**Complete code:**
```java
// given two array create another array by preform the union operation on then return the count of that unio array
// union: combining two array without any delicate value

import java.util.*;

public class UnionOfTwoArray {
    public static int unionHashSet(int arr1[],int arr2[]){
        HashSet<Integer> u = new HashSet<>();
        for (int i = 0 ; i< arr1.length;i++){
            u.add(arr1[i]);
        }
        for(int i = 0;i < arr2.length;i++){
            u.add(arr2[i]);
        }
        System.out.println(u);
        return u.size();
    }

    public static int unionArraylist(int arr1[],int arr2[]){
        ArrayList<Integer> ans = new ArrayList<>();
        for (int a : arr1){
            if(!ans.contains(a)){
                ans.add(a);
            }
        }
        for (int a:arr2){
            if(!ans.contains(a)){
                ans.add(a);
            }
        }
        System.out.println(ans);
        return ans.size();
    }

    public static int unionArray(int arr1[],int arr2[]){
        int ans[] = new int[arr1.length+arr2.length];
        int k = 0;

        for(int i = 0;i<arr1.length;i++){
            boolean existFlag = false;
            for(int j = 0;j<ans.length;j++){
                if(arr1[i] == ans[j]){
                    existFlag = true;
                }
            }
            if(!existFlag){
                ans[k++] = arr1[i];
            }
        }
        for(int i = 0;i<arr2.length;i++){
            boolean existFlag = false;
            for(int j = 0;j<ans.length;j++){
                if(arr2[i] == ans[j]){
                    existFlag = true;
                }
            }
            if(!existFlag){
                ans[k++] = arr2[i];
            }
        }


        for(int i = 0;i<k;i++){
            System.out.print(ans[i]+" ");
        }
        return k;
    } 

    public static void main(String[] args) {
        int arr1[] = {7,3,9};
        int arr2[] = {6,3,9,2,9,4};
        System.out.println(unionHashSet(arr1, arr2));
        System.out.println(unionArraylist(arr1, arr2));
        System.out.println("\n"+unionArray(arr1, arr2));
    }
}
```
**Output:**
```
[2, 3, 4, 6, 7, 9]
6
[7, 3, 9, 6, 2, 4]
6
7 3 9 6 2 4 
6
```


[Go to Top](#content)

---

# Intersection of Two Array
**given two array return the array of intersection for those two array**

**intersection: set( only contain unique element ) of common element**

**Approach:**

1. iterate over any one array and check is the element present into the another array or not
2. if it present into the second array then add that element into the ans array
3. there is no need to perform the iteration on the second array as we have already found all the common element while iterating over the first element
4. to avoid adding the duplicate element into the ans array we use the HashSet
5. we convert the any one array into the HashSet and iterate over the second array and check is current element present in HashSet or not
6. if it present then add that element into the ans array and remove it from HashSet to avoid adding duplicate element

**Note: we can use nested loop to solve this problem and use ArrayList to remove the duplicates but in this way we take `O[n^2]` time which is high**

**Solution Using nested loop**
```java
public static ArrayList<Integer> intersectionLoop(int arr1[],int arr2[]){
    ArrayList<Integer> ans = new ArrayList<>();

    for(int i = 0;i<arr1.length;i++){
        for (int j=0;j<arr2.length;j++){
            if(arr1[i]==arr2[j] && !temp.contains(arr1[i])){        // checking is both the element equal or does that element already present within the ans Array or not 
                temp.add(arr1[i]);
            }
        }
    }

    return ans;
}
```

**Algorithm:**

1. declare the temporary HashSet and a ans ArrayList
```java
HashSet<Integer> temp = new HashSet<>();
ArrayList<Integer> ans = new ArrayList<>();
```
2. convert the any one array into HashSet
```java
for (int i : arr1){     // converting 1st array into HashSet 
    temp.add(i);
}
```
3. iterate over the second array
```java
for (int i : arr2){
    
}
```
4. during each iteration check whether current element present in the temporary HashSet or not
```java
for (int i : arr2){
    if(temp.contains(i)){
        
    }
}
```
5. if it present then add that element into the ans ArrayList and at the end of the loop return the ans ArrayList
```java
for (int i : arr2){
    if(temp.contains(i)){
        ans.add(i);
        temp.remove(i);
    }
}
return  ans;
```
**Solution using HashSet:**
```java
public static ArrayList<Integer> intersection(int arr1[],int arr2[]){
    HashSet<Integer> temp = new HashSet<>();
    ArrayList<Integer> ans = new ArrayList<>();

    for (int i : arr1){
        temp.add(i);
    }

    for (int i : arr2){
        if(temp.contains(i)){
            ans.add(i);
            temp.remove(i);
        }
    }

    return  ans;
}
```
**Comparison between nested approach and HashSet approach**
Criteria | First Code (intersectionLoop) | Second Code (intersection)
---|---|---
Efficiency (Time Complexity) | O(n²) → Two loops inside each other (slow for large arrays) | O(n) → Single loop + HashSet lookup (very fast)
Duplicate Handling | You check manually using contains, which is O(n) again. | HashSet automatically handles uniqueness efficiently.
Cleaner Code | Nested loops, extra conditions | Straightforward and clean
Scalability | Bad for big arrays | Good for big arrays

**Complete code**
```java
import java.util.*;

public class Intersection{

    public static ArrayList<Integer> intersection(int arr1[],int arr2[]){
        HashSet<Integer> temp = new HashSet<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i : arr1){
            temp.add(i);
        }

        for (int i : arr2){
            if(temp.contains(i)){
                ans.add(i);
                temp.remove(i);
            }
        }
        return  ans;
    }

    public static ArrayList<Integer> intersectionLoop(int arr1[],int arr2[]){
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 0;i<arr1.length;i++){
            for (int j=0;j<arr2.length;j++){
                if(arr1[i]==arr2[j] && !temp.contains(arr1[i])){
                    temp.add(arr1[i]);
                }
            }
        }

        return temp;
    }

    public static void main(String[] args) {
        int arr1[] = {7,3,9};
        int arr2[] = {6,3,9,2,9,4};
        System.out.println(intersection(arr1, arr2));
        System.out.println(intersectionLoop(arr1, arr2));
    }
}
```
**Output:**
```
[3, 9]
[3, 9]
```

 

[Go to Top](#content)

---

# find Itinerary from Ticket 
**you are given the data about the tickets for journey from one place to anther such as**\
**"Chennai" -> "Bengaluru"\
"Mumbai" -> "Delhi"\
"Goa" -> "Chennai"\
"Delhi" -> "Goa"**\
**find the path in which the person can travel**

**i.e, your ans is `Mumbai -> Delhi -> Goa -> Chennai -> Bengaluru`**

**Itinerary means a plan or schedule**

**some condition to keep in mind**
1. there can be no loop in the journey
2. person can not travel two places from one place, i.e, there is only one source and only one destination
3. person cannot reach one place fromm two different places

**Approach:**
1. we'll store the given data in the form of Hashmap where our hashMap look like

key (source/from) | value (destination\to)
---|---
Chennai | Bengaluru
Mumbai | Delhi
Goa | Chennai
Delhi | Goa

2. **find the starting point:**
- in my HashMap key represent the places from where we can start our journal
- value column represent the places where we can reach from the particular place
- so if we can reach somewhere from the particular place that place cannot be our starting point therefore all the places mention in the value common cannot be our starting point

- therefor to find the starting  point we just need to find the key which is not present in the value column\
`example: mumbai`

- to find the starting point we use reverse map where we just switch the key value pair of the HashMap i.e, key become value and value become key

**Original map (Source to Destination)**

key (source/from) | value (destination\to)
---|---
Chennai | Bengaluru
Mumbai | Delhi
Goa | Chennai
Delhi | Goa

**reverse map (Destination to source)**

key (destination\to) | value (source/from)
---|---
Bengaluru | Chennai
Delhi | Mumbai
Chennai | Goa
Goa | Delhi


**Note: not all the map can be reverse, we can only reverse the map if value of all the keys in HashMap has a unique value, like in our example according to the given condition all the destinations are unique**

- now as we have both original and reverse map, use `.keySet()` method on original map to iterate over the keys of original map and use `.containsKey()` method on reverse map to check is that key (source) from original map present in the reverse map (destination) or not

- if key present then we are reaching that destination from some other places if it not present then we are not reaching that place from anywhere and that place is our starting point



3. once you find the starting point start travailing from that point and once you reach the destination make that destination your new starting point and repeat this step until you reach the end of your journey \
**Note: you always travel from keys to value**


**Algorithm:**
1. get the HashMap with given data
```java
HashMap<String,String> tickets = new HashMap<>();
```
2. get the starting point of the hashmap\
[to find the starting point](#to-find-the-starting-point)
```java
String startingPoint = getStart(tickets);
```
3. traverse over the key (source) of the Hashmap and check is startingPoint present in that hashmap or not (check in the keys/source)
```java
while(tickets.containsKey(startingPoint)){

}
```
4. if key present then that means you can travel to next location from that place therefor print that startingPoint and travel to next location i.e update the starting point to the next location which is value of key (startingPoint), repeat this until you cannot travel further
```java
while(tickets.containsKey(startingPoint)){
    System.out.print(startingPoint + " -> ");
    startingPoint = tickets.get(startingPoint );
}
```
5. print the last place you have visited which will be your finl starting point
```java
System.out.println(startingPoint);
```
**Code:**
```java
public static void printPath(HashMap<String,String> originalMap){
    String startingPoint = getStart(originalMap);

    while(originalMap.containsKey(startingPoint)){
        System.out.print(startingPoint + " -> ");
        startingPoint = originalMap.get(startingPoint );
    }

    System.out.println(startingPoint);
}
```
#### To Find The Starting Point
1. create the new temporary empty HashMap
```java
HashMap<String,String> revMap = new HashMap<>();
```
2. traverse over the keyset of the original HashMap
```java
for (String key : originalMap.keySet()){
    
}
```
3. get the key-value pair of each key in the keyset of original HashMap and add it into the reverse HashMap with value of original HashMap as key and key of original HashMap as value
```java
for (String key : originalMap.keySet()){
    revMap.put(originalMap.get(key) , key);
}
```
4. again traverse over the keyset of the original HashMap
```java
for (String key : originalMap.keySet()){
    
}
```
5. check is reverse HashMap has the same key or not, if same key doesn't found in the reverse HashMap then return that key  
```java
for (String key : originalMap.keySet()){
    if(!revMap.containsKey(key)){
        return key;
    }
}
```
4. if we doesn't found any key at the end of the loop then return the null
```java
return null
```
**Code:**
```java
public static String getStart(HashMap<String,String> originalMap){
    HashMap<String,String> revMap = new HashMap<>();

    for (String key : originalMap.keySet()){
        revMap.put(originalMap.get(key) , key);
    }

    for (String key : originalMap.keySet()){
        if(!revMap.containsKey(key)){
            return key;
        }
    }
    return  null;
}
```
**Complete Code:**
```java
import java.util.*;

public class Itinerary {

    public static String getStart(HashMap<String,String> originalMap){
        HashMap<String,String> revMap = new HashMap<>();

        for (String key : originalMap.keySet()){
            revMap.put(originalMap.get(key) , key);
        }

        for (String key : originalMap.keySet()){
            if(!revMap.containsKey(key)){
                return key;
            }
        }
        return  null;
    }

    public static void printPath(HashMap<String,String> originalMap){
        String startingPoint = getStart(originalMap);

        while(originalMap.containsKey(startingPoint)){
            System.out.print(startingPoint + " -> ");
            startingPoint = originalMap.get(startingPoint );
        }

        System.out.println(startingPoint);
    }


    public static void main(String[] args) {
        HashMap<String,String> tickets = new HashMap<>();

        tickets.put("Chennai", "Bengaluru");
        tickets.put("Mumbai", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");

        printPath(tickets);

       
    }
}
```
**output:**
```
Mumbai -> Delhi -> Goa -> Chennai -> Bengaluru
```

[Go to Top](#content)

---

#  Count Sub Array
**provided an Array find the maximum number of subArray that can be constructed whose sum is k**

**Prefix Array**\
it is the array that contains the sum at each index up to that index from original array\
example:
```
// original array
[1, 2, 3, 4, 5]

// prefix array
[1, 3, 6, 10, 15]

// explanation
0 + 1 = 1
1 + 2 = 3
3 + 3 = 6
6 + 4 = 10
10 + 5 = 15
```
**Note: prefix Array is always start from 0 which represent the empty array as at the start sum is always zero**
### how to find the subArray?

lets assume k=5 and apply equation `k = sum[i] - sum[j-1]` where **j is the starting index of the subArray and i is the last index**\
example:
```
// original array
[1, 2, 3, 4, 5]

// prefix array
int sum[] = {1, 3, 6, 10, 15};

k = 5

// at j = 1 and i = 2
// sum[i] = 6 and sum[j-1] = sum[0] = 1 
// therefor sum[i] - sum[j-1] = 6 - 1 = 5 == k
// therefor we get subArray from j to i
[2, 3]  

//similarly for j = 4 and i = 4 we get
// sum[i] - sum[j-1] = 15 - 10 = 5 == k
// therefor we get subArray from j to i
[5]
```
**Note: to solve this problem we just need to find the `sum[i]` term and apply updated formula `sum[i] - k = sum[j-1]` to calculate the `sum[j-1] `and then check is `sum[j-1]` already present in the sum array or not if it exist then we find the subArray from j to i**

### How to Handle duplicate sum values?

lest assume k = 0 for the following array
```
// original array
[1, -1, 1, -1, 1, -1]

// prefix array
sum = [1, 0, 1, 0, 1, 0]

k = 0

// for i = 5
// sum[i] = 0
// sum[i] - k = 0 - 0 = 0 = sum[j - 1]
// from the sum array we can say that 
j = 2 or j = 4

// therefor subArray
[1, -1, 1, -1] and [1, -1]
```
**Note: unlike previous example where we have only one unique value of `sum[j-1]` here we have two different values of `sum[j-1]` that says at `i = 5` we can have two different subArray or in general we can say number of subArray at particular index `i` is equal to number of valid `j` index present**

### why to use HashMap?

we use HashMap to store the frequency of the `sum[j-1]` to calculate the valid `j` index from it. That is if the frequency of `sum[j-1]` is 3 that means we can have the 3 unique index of `j` which says 3 unique subArray

by default hasMap contains the 0-1 as a key-value which represent the empty array at start whose sum is zero

your HahMap look like this

key (sum[j-1]) | value (frequency)
---| ---
0 | 1

therefor on every time when we calculate the `sum[j-1]` we check is that value present in the hashMap or not

if `sum[j-1]` is present then that means it already exist in sum array and number of valid `j` index is equal to its frequency

if not exist then add it into the HashMap with frequency as 1


**Algorithm:**
1. create the HashMap to store thr `sum[j-1]` and frequency
```java
HashMap<Integer,Integer> map = new HashMap<>();
```
2. add the default values of HashMap
```java
map.put(0, 1);
```
3. declarer some general variables
```java
int ans = 0;    // represent the number of subArray
int sum = 0;    // calculate the sum at each index of prefix array
```

4. traverse over the input array
```java
for(int i:arr){
            
}
```
5. update the sum
```java
for(int i:arr){
    sum+=i;     // same as sum[i]      
}
```
6. check is `sum[j-1] = sum[i] - k` present in the HashMap or not if it exist then add its frequency into the ans value
```java
for(int i:arr){
    sum+=i;
    if(map.containsKey(sum-k)){
        ans += map.get(sum-k);
    }
}
```
7. check is `sum[i]` present in the Hashmap or not
```java
for(int i:arr){
    sum+=i;
    if(map.containsKey(sum-k)){
        ans += map.get(sum-k);
    }
    if(map.containsKey(sum)){
        // exist
    }else{
       // does not exist 
    }
}
```
8. if it exist the update its frequency by one as in future this value can be use as `sum[j-1]` else create new key-value pair with frequency equal to 1
```java
for(int i:arr){
    sum+=i;
    if(map.containsKey(sum-k)){
        ans += map.get(sum-k);
    }
    if(map.containsKey(sum)){
        map.put(sum, map.get(sum)+1);
    }else{
        map.put(sum, 1);
    }
}
```
9. return the ans value as it represent the number of subArray that can be form
```java
return ans;
```
**Complete code:**
```java
import java.util.*;

public class subArray{

    public static int countSubArray(int arr[],int k){
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        int sum = 0;

        for(int i:arr){
            sum+=i;
            if(map.containsKey(sum-k)){
                ans += map.get(sum-k);
            }
            if(map.containsKey(sum)){
                map.put(sum, map.get(sum)+1);
            }else{
                map.put(sum, 1);
            }
        }
        

        return ans;
    }

    public static void main(String[] args) {
        int arr1[] = {10, 2, -2, -20, 10};
        int arr2[] = {1, -1, 1, -1, 1, -1};
        int count1 = countSubArray(arr1, 10);
        int count2 = countSubArray(arr2, 0);
        System.out.println(count1);
        System.out.println(count2);
    }
}
```
**Output:**
```
3
9
```



[Go to Top](#content)

---