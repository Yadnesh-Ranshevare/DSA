# Content
1. [Introduction](#introduction)
2. [Initialization of node class](#initialization-of-node-class)
3. [Insert](#insert)


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

static Node head = new Node();
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
1. traverse over the given word
```java
for(int i = 0; i< word.length(); i++){

}
```
**Note: the `O[L]`  time complexity of insert operation is because of this for loop**

2. get the index of the current character
```java
int idx = word.charAt(i)-'a';
```
**Note: to get the sequence number of any character we simply subtract that characters ASCII value with the ASCII value of `'a'`**\
**example:**\
**'a' - 'a' = 0**\
**'b' - 'a' = 1**\
**'c' - 'a' = 2**

3. check for `children[idx] == null` 
```java
if(root.children[idx] == null){
    // create new node
}
```
4. if `children[idx] == null` then create the new node at `root.children[idx]`
```java
if(root.children[idx] == null){
    root.children[idx] = new Node();
}
```
5. check for eow flag
```java
if(i == word.length() - 1){
    root.children[idx].eow = true;
}
``` 
**Note: as we are not yet updated the root, we get the access of current node at `root.children[idx]` where root is previous/parent node**

6. update the level of tree (switch to child node form parent node)
```java
root = root.children[idx];
```

#### Code:
```java
public static void insert(String word){
    for(int i = 0; i< word.length(); i++){
        int idx = word.charAt(i)-'a';

        if(root.children[idx] == null){
            root.children[idx] = new Node();
        }

        if(i == word.length() - 1){
            root.children[idx].eow = true;
        }

        root = root.children[idx];
    }
}
```


[Go To Top](#content)

---
