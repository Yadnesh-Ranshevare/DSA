# Content
1. [Introduction](#introduction)
2. [Initialization of node class](#initialization-of-node-class)
3. [Insert](#insert)
4. [Search Operation](#search-operation)
5. [Word Break Problem](#word-break-problem)
6. [start with problem](#starts-with-problem)
7. [Count unique Substring](#count-unique-substring)


# Introduction
- A **Trie** (also called Prefix Tree) is a **tree-like data structure** used to store a set of strings, where each node represents a character of a string.
- The root is empty and each node represents one character.
- its search time complexity is `O[L]` where L is length of string
- trie is **not a binary tree** that is each root node can have **multiple child (0 to infinity)** therefor it is also called as **K-ary tree**
- `K-ary tree:` A K-ary tree (or k-way tree) is a generalized tree data structure where each node can have at most K children.

## How trie tree constructed
lets assume the array of string as follow\
`words[] = "the", "a", "there", "their", "any"` 

### 1. Insert `"the"`
- **Root → create `'t'`**
- **`'t'` → create `'h'`**
- **`'h'` → create `'e'` and mark as end of word (`e*`)**

**Tree so far:**
```
    (root)
      /  
     t
    /
   h
  /
 e*
```

### 2. Insert `"a"`
- **Root → create `'a'`**
- **`'a'` → mark as end of word (`a*`)**

**Tree now:**
```
        (root)
         /  \ 
        t    a*
       /
      h
     /
    e*
```

### 3. Insert `"there"`
- **`'t'` → already exists**
- **`'h'` → already exists**
- **`'e'` → already exists**
- **`'e'` → create `'r'` → create `'e'` → mark as end of word (`e*`)**

**Tree now:**

```
        (root)
         / \ 
        t   a*
       /
      h
     /
    e*
   /
  r
 /
e* 
```


### 4. Insert `"their"`
- **`'t' → h → e'` already exists**
- **`'e'` → create `'i'` → create `'r'` → mark as end of word (`r*`)**

**Tree now:**

```
        (root)
         / \ 
        t   a*
       /
      h
     /
    e*
   / \
  r   i
 /     \
e*      r*
```


### 5. Insert `"any"`
- **`'a'` already exists**
- **`'a'` → create `'n'` → create `'y'` → mark as end of word (`y*`)**

**Final Tree:**
```
        (root)
         / \ 
        t   a*
       /     \
      h       n
     /         \ 
    e*          y*
   / \
  r   i
 /     \
e*      r*
```
##  Advantages of Trie:
- Fast search time: Searching takes O(L) time, where L is the length of the word, independent of the number of words stored.
- Prefix-based search: Tries are perfect to find all words that start with a given prefix.
- No hash collisions: Unlike hash tables, tries avoid collisions and handle prefix queries naturally.
##  Common Use Cases:
- Autocomplete in search engines or text editors
- Spell checkers
- IP routing (Longest prefix matching)
- Storing a dictionary of words
- Word games (like Boggle or Scrabble helpers)
- Quickly search words or prefixes
- Autocomplete words as you type
- Check if a string exists in a large set of strings
- Spell checking
- Pattern matching


[Go To Top](#content)

---


# Initialization of Node class

the node class structure of trie class is different from the class structure of tree class, unlike binary tree here we don't have any right or left child as trie can have multiple child's. In trie class we have array of Nodes containing all the child nodes
```java
class Node(
    Node[] children;
    boolean endOfWord;
)
```
- **children:** array of node where each node represent a single child
- **endOfWord:** boolean flag to specify the end of word
- In this class we are not storing any value as root of tries tree in empty
- Each node has a array containing the info about there child nodes that tells us which characters present at the next level 
- size of children array changes depending upon what kind of strings you wanna store in this tree
    - for string containing char in range
        - `a-z` size = 26 (0 - 25) alphabets
        - `A-Z` size = 26 (0 - 25) alphabets
        - `a-z + A-Z + all special char like @, #, &, etc` size = 256
- each index of array represent its respective character is sequence\
example:
    - index 0 -> a
    - index 1 -> b
    - index 2 -> c
    - index 25 -> z
- from above example we can say that if there exist a character at certain index we can find out which character is it just by looking at there index\
example:\
if `children[3] != null` then `children[3]` must be equal to `d`\
if `children[4] == null` then there in no `e` child

### code:

```java
static class Node{
    Node[] children;
    boolean eow;    // endOfWord

    public Node(){
        children = new Node[26];    // a-z
        for(int i=0; i<26; i++){
            children[i] = null;
        }
        eow = false;
    }
}

static Node root = new Node();
```
- in constructor we are first **initializing the children array** for characters in range `a-z` and at first we are **storing null** at each index using for loop to show that there is **no child present** at first, also default value of `eow` if `false`
- creating the **head** of our trie tree with an **empty children array** and **eow set to false**


[Go To Top](#content)

---
# Insert
time complexity to insert a word in a tries tree ie equal to `O[L]` where L is length of word

### Approach

1. separate out the first character of the word
2. check whether that character is present in the children array or not
3. if character is present then do nothing and switch to next level
    - go to respective child node 
    - go to second character in word 
4. if doesn't exist then create the new node at that index in children array
5. if the character is last character then set the eow flag as true
6. repeat the step 2 to 5 util whole word is inserted

### Step by step illustration
example string: `"the"`

1. **creating head with empty children array**
```
(root)
```
2. **Insert 't'**
    - `'t'`  → index 19 in children
    - at root `children[19]` is `null`, so we create a new Node.
    - Move to this new node.
```
 (root)
  /
 t
```

3. **Insert 'h'**
    - `'h'` → index 7 in children
    - at node `t` `children[7]` is `null`, so we create a new TrieNode.
    - Move to this new node.
```
   (root)
    /
   t
  /
 h
```

4. **Insert 'e'**
    - `'e'` → index 4 in children
    - at node `h` `children[4]` is `null`, so we create a new TrieNode.
    - set end of word as true
    - Move to this new node.

```
       (root)
        /
       t
      /
     h
    /
   e*  
```
**Now inserting `"there"` into this tree**

1. **Inserting `'t'`**
    - `'t'` is already in the `root’s` children → switch to next level
    - Move to the `'t'` node.

2. **Insert `'h'`**
    - `'h'` is already in `'t'` children → switch to next level
    - Move to the `'h'` node.

3. **Insert `'e'`**
    - `'e'` is already in `'h'` children → switch to next level
    - Move to the `'e'` node.
    - Note: This `'e'` node already has `eow = true` (from previous word `"the"`)
4. **Insert `'r'`**
    - `'r'`  → index 17 in children
    - at `'e'` `children[17]` is `null`, so we create a new Node.
    - Move to this new node.
```
       (root)
        /
       t
      /
     h
    /
   e*
  /
 r  
```
4. **Insert `'e'`**
    - `'e'`  → index 4 in children
    - at `'r'` `children[4]` is `null`, so we create a new Node.
    - set end of word as true
    - Move to this new node.
```
        (root)
         /
        t
       /
      h
     /
    e*
   /
  r  
 /
e*
```

### Algorithm
1. get the root of tree
```java
Node head = root;
```
**Note: in our implementation code we have declare root as single static variable, which is use by all the other operations. Therefor changing the root directly may cause some inconsistency in our code, hence we are using the copy of root**


2. traverse over the given word
```java
for(int i = 0; i< word.length(); i++){

}
```
**Note: the `O[L]`  time complexity of insert operation is because of this for loop**

3. get the index of the current character
```java
int idx = word.charAt(i)-'a';
```
**Note: to get the sequence number of any character we simply subtract that characters ASCII value with the ASCII value of `'a'`**\
**example:**\
**'a' - 'a' = 0**\
**'b' - 'a' = 1**\
**'c' - 'a' = 2**

4. check for `children[idx] == null` 
```java
if(head.children[idx] == null){
    // create new node
}
```
5. if `children[idx] == null` then create the new node at `head.children[idx]`
```java
if(head.children[idx] == null){
    head.children[idx] = new Node();
}
```
6. check for eow flag
```java
if(i == word.length() - 1){
    root.children[idx].eow = true;
}
``` 
**Note: as we are not yet updated the root, we get the access of current node at `head.children[idx]` where root is previous/parent node**

7. update the level of tree (switch to child node form parent node)
```java
head = head.children[idx];
```

#### Code:
```java
public static void insert(String word){
    Node head = root;

    for(int i = 0; i< word.length(); i++){
        int idx = word.charAt(i)-'a';

        if(head.children[idx] == null){
            head.children[idx] = new Node();
        }

        if(i == word.length() - 1){
            head.children[idx].eow = true;
        }

        head = head.children[idx];
    }
}
```
#### complete code
```java
public class Implementation {

    static class Node{
        Node[] children;
        boolean eow;

        public Node(){
            children = new Node[26];
            for(int i=0; i<26; i++){
                children[i] = null;
            }
            eow = false;
        }
    }

    static Node root = new Node();

    public static void insert(String word){

        Node head = root;
        for(int i = 0; i< word.length(); i++){
            int idx = word.charAt(i)-'a';

            if(head.children[idx] == null){
                head.children[idx] = new Node();
            }

            if(i == word.length() - 1){
                head.children[idx].eow = true;
            }


            head = head.children[idx];
        }
    }

    public static void main(String[] args) {
        String words[] = {"the", "a", "there", "their", "any"};

        for(String word : words){
            insert(word);
        }
    }   
}

```

[Go To Top](#content)

---

# Search Operation
- **time complexity** of search operation in a tries tree ie equal to `O[L]` where L is length of word
- search operation of trie tree is **similar to insert operation**\
[chick here to learn about insert operation](#insert)
- the only difference is we just **check** for each character in given word **sequentially** whether it is present in the children array or not
- if it present then we move on the next level & if it dose not present then that word does not exist in our tree
- if all **characters are present** in the tree then we can say that our word exist in the tree only if last character has its **endOfWord** flag set to **true** 

### Illustration

consider a tree as follow:
```
        (root)
         / \ 
        t   a*
       /     \
      h       n
     /         \ 
    e*          y*
   / \
  r   i
 /     \
e*      r*
```

#### in the above tree search for word `"the"`
 1. **search for `t`**
    - `'t'`  → index 19 in children
    - at `'root'` `children[19]` is ` not null`, that is `t` exist
    - Move to this node.
 2. **search for `h`**
    - `'h'`  → index 7 in children
    - at `'t'` `children[7]` is ` not null`, that is `h` exist
    - Move to this node.
 2. **search for `e`**
    - `'e'`  → index 4 in children
    - at `'h'` `children[4]` is ` not null`, that is `e` exist
    - at `e` `endOfWord` flag is `true`
    - given word `exist`

#### in same tree search for `thor`
 1. **search for `t`**
    - `'t'`  → index 19 in children
    - at `'root'` `children[19]` is ` not null`, that is `t` exist
    - Move to this node.
 2. **search for `h`**
    - `'h'`  → index 7 in children
    - at `'t'` `children[7]` is ` not null`, that is `h` exist
    - Move to this node.
 2. **search for `o`**
    - `'o'`  → index 14 in children
    - at `'h'` `children[14]` is ` null`, that is `o` dose not exist
    - as `o` dose not exist at this level, we can say that word `thor` also `not exist` in our trie tree

#### in same tree search for `an`
 1. **search for `a`**
    - `'a'`  → index o in children
    - at `'root'` `children[0]` is ` not null`, that is `a` exist
    - Move to this node.
 2. **search for `n`**
    - `'n'`  → index 13 in children
    - at `'n'` `children[13]` is ` not null`, that is `n` exist
    - at `n` `endOfWord` flag is `false`, not a valid stored word (although word `an` exist in our tree its not a valid word)
    - given word does `not exist`


### Algorithm
1. get the root of tree
```java
Node head = root;
```
**Note: in our implementation code we have declare root as single static variable, which is use by all the other operations. Therefor changing the root directly may cause some inconsistency in our code, hence we are using the copy of root**


2. traverse over the given word
```java
for(int i = 0; i< word.length(); i++){

}
```
**Note: the `O[L]`  time complexity of insert operation is because of this for loop**

3. get the index of the current character
```java
int idx = word.charAt(i)-'a';
```
**Note: to get the sequence number of any character we simply subtract that characters ASCII value with the ASCII value of `'a'`**\
**example:**\
**'a' - 'a' = 0**\
**'b' - 'a' = 1**\
**'c' - 'a' = 2**

4. check for `children[idx] == null` return false if true
```java
if(head.children[idx] == null){ // character does not exist
    return false;
}
```
5. check whether we are at the last character or not
```java
if(i == key.length() - 1 ){
    // at last character
}
```
6. return endOfWord flag
```java
if(i == key.length() - 1 ){
    return head.children[idx].eow;
}
```
 **Note :**\
**If `endOfWord = true` then it is a `valid stored word`**\
**If `endOfWord = false` then it is `not a valid stored word`**


7. update the head
```java
head = head.children[idx];
```

### Code:
```java
public static boolean  search(String key){
    Node head = root;
    for(int i = 0; i<key.length(); i++){
        int idx = key.charAt(i) - 'a';

        if(head.children[idx] == null){
            return  false;
        }

        if(i == key.length() - 1 ){
            return head.children[idx].eow;
        }

        head = head.children[idx];
    }
    return true;    // to avoid 'missing return statement' error
}
```
### complete code
```java
public class Implementation {

    static class Node{
        Node[] children;
        boolean eow;

        public Node(){
            children = new Node[26];
            for(int i=0; i<26; i++){
                children[i] = null;
            }
            eow = false;
        }
    }

    static Node root = new Node();

    public static void insert(String word){

        Node head = root;
        for(int i = 0; i< word.length(); i++){
            int idx = word.charAt(i)-'a';

            if(head.children[idx] == null){
                head.children[idx] = new Node();
            }

            if(i == word.length() - 1){
                head.children[idx].eow = true;
            }


            head = head.children[idx];
        }
    }

    public static boolean  search(String key){
        Node head = root;
        for(int i = 0; i<key.length(); i++){
            int idx = key.charAt(i) - 'a';

            if(head.children[idx] == null){
                return  false;
            }
            if(i == key.length() - 1 ){
                return head.children[idx].eow;
            }
            head = head.children[idx];
        }
        return true;
    }

    public static void main(String[] args) {
        String words[] = {"the", "a", "there", "their", "any"};

        for(String word : words){
            insert(word);
        }

        System.out.println(search("the"));
        System.out.println(search("thor"));
        System.out.println(search("an"));
    }   
}
```
### output:
```
true
false
false
```

[Go To Top](#content)

---

# Word Break Problem
**given an input String and a dictionary of word, find out if the input String can be broken into a space-separated of dictionary words**

**example:**\
**words[] = {'i', 'like', 'sam', 'samsung', 'mobile'}**\
**key = 'ilikesamsumg'**\
**output: true**

**explanation:**\
**key: 'ilikesamsung' can break into 'i like samsung' where this all three word ('i', 'like', 'samsung') are present in words[]**


### Approach:
1. break the key letter by letter into two parts
2. first break the key into two part by separating the first letter
3. search for the first part in the tree, if it does not present then go back and separate out the first two letter and perform search again
4. continue step 3 until you find the search for first part and keep on increase the number of letters in first part during each new attempt
5. once you find the search for first part then recursively perform the same steps (1, 2, 3, 4) for the second part
6. if we doesn't find any search for our entire second part that means the two partition we have created are not valid therefor we go back to step 3 make new partitions by adding one more letter in first part and performing the search again i.e, repeat  from step 3
7. if we doesn't find any valid combination then return false


### Illustration
example 1:\
words[] = {'i', 'like', 'sam', 'samsung', 'mobile'}\
key = 'ilikesamsung'

**Step 1: word = ilikesamsung**
- **first break**: `i` -> present 
- search for second part: `'likesamsung'`

**Step 2: word = likesamsung**
- **first break**: `l` -> not present
- **second break**: `li` -> not present
- **third break**: `lik` -> not present
- **fourth break**: `like` -> present
- search for second part: `'samsung'`

**Step 3: word = samsung**
- **first break**: `s` -> not present
- **second break**: `sa` -> not present
- **third break**: `sam` -> present 
- search for second part: `'sung'`

**Step 4: word = sung**
- **first break**: `s` -> not present
- **second break**: `su` -> not present
- **third break**: `sun` -> not present
- **fourth break**: `sung` -> not present
- as this part of the partition in not present in the tree the partition we make in previous step (step 3) is invalid therefor go back to step 3

**Step 5: word = samsung (continue step 3)**
- previous steps:
    - **first break**: `s` -> not present
    - **second break**: `sa` -> not present
    - **third break**: `sam` -> present but not a valid for partitioning
- **fourth break**: `sams` -> not present
- **fifth break**: `samsu` -> not present
- **sixth break**: `samsun` -> not present
- **seventh break**: `samsung` ->  present
- since there is no further partitioning is possible we can say that our key present

example 2:\
words[] = {'i', 'like', 'sam', 'samsung', 'mobile'}\
key = 'ilikesung'

**Step 1: word = ilikesung**
- **first break**: `i` -> present 
- search for second part: `'likesung'`

**Step 2: word = likesung**
- **first break**: `l` -> not present
- **second break**: `li` -> not present
- **third break**: `lik` -> not present
- **fourth break**: `like` -> present
- search for second part: `'sung'`

**Step 3: word = sung**
- **first break**: `s` -> not present
- **second break**: `su` -> not present
- **third break**: `sun` -> not present
- **fourth break**: `sung` -> not present
- as this part of the partition in not present in the tree the partition we make in previous step (step 2) is invalid therefor go back to step 2

**Step 4: word = likesung (continue step 2)**
- previous steps
    - **first break**: `l` -> not present
    - **second break**: `li` -> not present
    - **third break**: `lik` -> not present
    - **fourth break**: `like` -> present but not valid for partitioning
- **fifth break**: `likes` -> not present
- **sixth break**: `likesu` -> not present
- **seventh break**: `likesun` -> not present
- **eight break**: `likesung` -> not present
- as this part of the partition in not present in the tree the partition we make in previous step (step 1) is invalid therefor go back to step 1

**Step 5: word = ilikesung (continue step 1)**
- previous steps
    - **first break**: `i` -> present but not valid for partitioning
- **second break**: `il` -> not present
- **third break**: `ili` -> not present
- **fourth break**: `ilik` -> not present
- **fifth break**: `ilike` -> not present
- **sixth break**: `ilikes` -> not present
- **seventh break**: `ilikesu` -> not present
- **eight break**: `ilikesun` -> not present
- **ninth break**: `ilikesung` -> not present
- since there is not match found we can say that key is not present


#### Note: return true if both first and second part of the key is present, if any of the missing then return false

### Algorithm:
1. traverse over the complete kye
```java
for(int i = 1; i<= key.length(); i++){
         
}
```
2. get the first part of the key
```java
String firstPart = key.substring(0,i);
```
**Note: in `.substring(0,i)` `i` is excluded therefor to get first letter by using `.substring()` method we need range form `0-1` where `i = 1` therefor we initiate `i = 1` in for loop in step 1**

3. get the second part of the key
```java
String secondPArt = key.substring(i);
```
4. check for  first and second part of key
```java
if(search(firstPart) && word(secondPArt)){
    return true;
}
```
**here `word(secondPArt)` tells us whether or not second part of the key exist or not**

5. if we complete our traversal over the key and doesn't find any math then return false
```java
return false;
```
6. base case: as empty string is present in every tree
```java
if(key.length() == 0){
    return  true;
}
```

### code
```java
public static boolean word(String key){
    if(key.length() == 0){
        return  true;
    }
    for(int i = 1; i<= key.length(); i++){
        String firstPart = key.substring(0,i);
        String secondPArt = key.substring(i);
        if(search(firstPart) && word(secondPArt)){
            return true;
        }
    }
    return false;
}
```
### complete code
```java
public class WordBreak {

    static class Node{
        Node[] children;
        boolean eow;

        public Node(){
            children = new Node[26];
            for(int i=0; i<26; i++){
                children[i] = null;
            }
            eow = false;
        }
    }

    static Node root = new Node();

    public static void insert(String word){

        Node head = root;
        for(int i = 0; i< word.length(); i++){
            int idx = word.charAt(i)-'a';

            if(head.children[idx] == null){
                head.children[idx] = new Node();
            }

            if(i == word.length() - 1){
                head.children[idx].eow = true;
            }

            head = head.children[idx];
        }
    }

    public static boolean  search(String key){
        Node head = root;
        for(int i = 0; i<key.length(); i++){
            int idx = key.charAt(i) - 'a';

            if(head.children[idx] == null){
                return  false;
            }
            if(i == key.length() - 1 ){
                return head.children[idx].eow;
            }
            head = head.children[idx];
        }
        return true;
    }


    public static boolean word(String key){
        if(key.length() == 0){
            return  true;
        }
        for(int i = 1; i<= key.length(); i++){
            String firstPart = key.substring(0,i);
            String secondPArt = key.substring(i);
            if(search(firstPart) && word(secondPArt)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String words[] = {"i", "like", "sam", "samsung", "mobile"};

        for(String word : words){
            insert(word);
        }

        String key = "ilikesamsung";

        System.out.println(word(key));
    }   
}
```
### output:
```
true
```



[Go To Top](#content)

---
# Starts With Problem
**Create a function boolean startWith(String prefix) for a trie. return true if there is a previously inserted string word that has the prefix, and false otherwise**

**Example:**\
**words = {'apple', 'app', 'mango', 'man', 'women'}**\
**Prefix = "app"    output = tree (apple and app has a prefix 'app')**\
**Prefix = "moon"    output = false**

- the approach and code to solve ths problem is **exactly** same as search operation\
[to learn about search operation](#search-operation)
- the only difference here is we ignore the endOfWord flag

### Algorithm
1. get the root of tree
```java
Node head = root;
```
**Note: in our implementation code we have declare root as single static variable, which is use by all the other operations. Therefor changing the root directly may cause some inconsistency in our code, hence we are using the copy of root**


2. traverse over the given prefix
```java
for(int i = 0; i< prefix.length(); i++){

}
```

3. get the index of the current character
```java
int idx = prefix.charAt(i)-'a';
```
**Note: to get the sequence number of any character we simply subtract that characters ASCII value with the ASCII value of `'a'`**\
**example:**\
**'a' - 'a' = 0**\
**'b' - 'a' = 1**\
**'c' - 'a' = 2**

4. check for `children[idx] == null` return false as given prefix doesn't exist in our tree
```java
if(head.children[idx] == null){ // character does not exist
    return false;
}
```
5. update the head
```java
head = head.children[idx];
```
6. if all the character exist in our tree then return tree aas there exist a word with given prefix
```java
return true
```
### code
```java
public static boolean startWith(String prefix){
    Node head = root;
    for(int i = 0; i<prefix.length(); i++){
        int idx = prefix.charAt(i) - 'a';

        if(head.children[idx] == null){
            return  false;
        }

        head = head.children[idx];
    }
    return true;
}
```
### Complete code
```java
public class StartWithPrblm {

    static class Node{
        Node[] children;
        boolean eow;

        public Node(){
            children = new Node[26];
            for(int i=0; i<26; i++){
                children[i] = null;
            }
            eow = false;
        }
    }

    static Node root = new Node();

    public static void insert(String word){

        Node head = root;
        for(int i = 0; i< word.length(); i++){
            int idx = word.charAt(i)-'a';

            if(head.children[idx] == null){
                head.children[idx] = new Node();
            }

            if(i == word.length() - 1){
                head.children[idx].eow = true;
            }


            head = head.children[idx];
        }
    }

    public static boolean startWith(String prefix){
        Node head = root;
        for(int i = 0; i<prefix.length(); i++){
            int idx = prefix.charAt(i) - 'a';

            if(head.children[idx] == null){
                return  false;
            }

            head = head.children[idx];
        }
        return true;
    }

    public static void main(String[] args) {
        String words[] = {"apple", "app", "mango", "man", "woman"};

        for(String word : words){
            insert(word);
        }

        System.out.println(startWith("app"));
        System.out.println(startWith("moon"));
    }   
}
```

[Go To Top](#content)

---
# Count unique Substring
**Given a string of length n of lowercase alphabet characters, we need to count total number of distinct substring of this string**

**Example:**\
**Str = 'ababa'**\
**ans = 10**\
**all the possible substrings: 'a', 'ab', 'aba', 'abab', 'ababa', 'ba', 'bab', 'baba', 'b', ' '**


### prefix of all the suffix
- **Prefix:** A prefix is something added at the beginning of a string

- **suffix:** A suffix is something added at the end of a string

- first find all the suffix of given string then for each suffix we find there prefix

| suffix | all possible prefix |
| --- | ---|
|ababa | a, ab, aba, abab, ababa|
| baba | b, ba, bab, baba |
| aba | a, ab, aba |
| ba | b, ba |
| a | a |
| ' '(empty string) | ' ' (empty string) |

- combine all the unique prefix\
form above table: `'a', 'ab', 'aba', 'abab', 'ababa', 'b', 'ba', 'bab', 'baba', ''`\
this uniquely combined prefix are our unique substrings which is the actual ans of our question

### Why use this Approach

By using trie tree if we store all the suffix into a tree then number of nodes will be our count of subString

from above table we get:\
**words[] = {'ababa, 'baba', 'aba', 'ba', 'a', ' '}**

```
        (root)
         / \
        a*  b
       /     \
      b       a
     /         \
    a*          b
   /             \
  b               a*  
 /
a*
```
As you can see from in above tree number of nodes is `10`

each node from the root represent the unique prefix
sr | path | prefix
--- | --- | ---
1 | (root) | " " 
2 | (root) -> a* | a
3 | (root) -> a* -> b | ab
4 | (root) -> a* -> b -> a* | aba
5 | (root) -> a* -> b -> a* -> b | abab
6 | (root) -> a* -> b -> a* -> b -> a* | ababa
7 | (root) -> b | b
8 | (root) -> b -> a | ba
9 | (root) -> b -> a -> b | bab
10 | (root) -> b -> a -> b -> a* | baba



### How this work
In trie tree we uniquely store the prefix that is if two word having same prefix we store that prefix only once

example:\
consider two words `'there'` and `'their'` who have same prefix `'the'`
```
  (root)
    |
    t
    |
    h
    |
    e
   / \
  r   i
 /     \ 
e       r
```
from above tree we can say that our prefix `'the'` has only store single time

**Therefor from above example we can say that by using trie data structure we can store duplicate prefix of all the suffix single time**


### How to count the number of nodes
- The approach to count the number of node in trie tree is similar to the count algorithm of binary tree\
[to learn about how to count number of node in binary tree](../BinaryTree/readme.md#count-number-of-nodes)
- the only difference is that we recursively get the count of left and right subtree only but in trie tree we get the count of all the subtree (can be greater that 2) present
- use loop to traverse over a children array and if at any index of we get a valid node then that means at that index there exist a new subtree

### Approach
1. find all the suffix of given string
2. create the trie tree from the suffix
3. count the number of node in tree


### Algorithm
1. get all the suffix of input string and insert it into the trie tree
```java
for(int i = 0; i< str.length(); i++){
    String suf = str.substring(i);
    insert(suf);
}
```
2. initiate count variable
```java
int count = 0;
```
3. traverse over the children array
```java
for(int i = 0; i < 26; i++){
    
}
```
4. check for `children[i] != null` which says at index `i` there exist a valid subtree
```java
for(int i = 0; i < 26; i++){
    if(root.children[i] != null){
        // subtree exist
    }
}
```
5. make the recursive call to get the count for that subtree and add it  into the count
```java
for(int i = 0; i < 26; i++){
    if(root.children[i] != null){
        count += countNode(root.children[i]);
    }
}
```
6. if there is no further node present the return the `count + 1`
```java 
return count + 1
```
count: previous count of the node\
+1 : add the count of root into previous count

7. base case for empty tree (no nodes are present)
```java
if(root == null){
    return 0;
}
```
### Code
```java
public static int countSubString(String str){
    for(int i = 0; i< str.length(); i++){
        String suf = str.substring(i);
        insert(suf);
    }
    return  countNode(root);
}

public static int countNode(Node root){
    if(root == null){
        return 0;
    }
    int count = 0;
    for(int i = 0; i < 26; i++){
        if(root.children[i] != null){
            count += countNode(root.children[i]);
        }
    }
    return count + 1;
}
```
### Complete code
```java
public class CountUniqueSubString {

    static class Node{
        Node[] children;
        boolean eow;

        public Node(){
            children = new Node[26];
            for(int i=0; i<26; i++){
                children[i] = null;
            }
            eow = false;
        }
    }

    static Node root = new Node();

    public static void insert(String word){

        Node head = root;
        for(int i = 0; i< word.length(); i++){
            int idx = word.charAt(i)-'a';

            if(head.children[idx] == null){
                head.children[idx] = new Node();
            }

            if(i == word.length() - 1){
                head.children[idx].eow = true;
            }

            head = head.children[idx];
        }
    }

    public static int countSubString(String str){
        for(int i = 0; i< str.length(); i++){
            String suf = str.substring(i);
            insert(suf);
        }
        return  countNode(root);
    }

    public static int countNode(Node root){
        if(root == null){
            return 0;
        }
        int count = 0;
        for(int i = 0; i < 26; i++){
            if(root.children[i] != null){
                count += countNode(root.children[i]);
            }
        }
        return count + 1;
    }

    public static void main(String[] args) {
        String str = "ababa";
        System.out.println(countSubString(str));
    }   
}
```
### Output
```
10
```


[Go To Top](#content)

---
