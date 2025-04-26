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

---

# introduction

In Data Structures and Algorithms (DSA), a HashMap (or Hash Table) is a data structure that stores key-value pairs. It allows you to store data in a way that enables fast retrieval, insertion, and deletion operations, typically in constant time, O(1), on average.

### Key-Value Pairs:
- A key is used to identify a value in the map, and each key is associated with a specific value.

- For example, in a HashMap of students and their grades:\
 Key: Student's name (e.g., "Alice")\
 Value: Grade (e.g., 85)

### how hashmap is implementation
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
1. create the ans `ArrayList<Integer>` and a `HashMap<Integer,Integer>` to store the ans array and question array respectively
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