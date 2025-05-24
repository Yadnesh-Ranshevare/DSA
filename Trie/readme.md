# Content
1. [Introduction](#introduction)


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
