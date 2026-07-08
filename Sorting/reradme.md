# Content
1. [Introduction](#introduction)
2. [Bubble Sort](#bubble-sort)
3. [Selection sort](#selection-sort)
3. [Quick Sort](#quick-sort)

---
# Introduction

In DSA, sorting means arranging a collection of items (usually numbers or strings) in a specific order—typically ascending or descending.

### Why sorting matters
It helps with:
- Faster searching
- Easier data analysis
- Cleaner data organization
- Preparing input for other algorithms

### Simple illustration

Imagine you have this list:\
`[7, 2, 9, 1, 5]`

After sorting (ascending):\
`[1, 2, 5, 7, 9]`


[Go To Top](#content)

---
# Bubble Sort

Bubble Sort is a simple comparison-based sorting algorithm. It repeatedly compares two **adjacent elements** and swaps them if they are in the wrong order. After each pass, the **largest (heaviest) unsorted element** moves to its correct position at the **end of the array**. This process is repeated until the entire array is sorted.

### Working of Bubble Sort:

1. Compare each pair of adjacent elements.
2. If the left element is greater than the right element, swap them.
3. After the first pass, the largest element reaches the last index.
4. In the second pass, the second largest element reaches the second last index.
5. Continue this process for **`n - 1` passes** (or stop earlier if no swaps occur).
    >if I have n=5 elements and my 4=n-1 elements is sorted then last nth element is already sorted therefore we only repeat this algo n-1 times 
    >
    > Think of it like "if my n-1 elements are sorted then my nth element will already be sorted"
6. At the end, the array becomes sorted in ascending order.

### Example:

Initial Array:
`5 3 8 4 2`

#### now in Pass One

1. compare first two element
    ```
    5 3 8 4 2 
    ^ ^
    n n+1
    ```
    since `5(n) > 3(n+1)` -> swap

    ```
    3 5 8 4 2 
    ^ ^
    n n+1
    ```
2. Go to next pair
    ```
    3 5 8 4 2 
      ^ ^
      n n+1
    ```
    since `5(n) < 8(n+1)` -> no swap
3. Go to next pair
    ```
    3 5 8 4 2 
        ^ ^
        n n+1
    ```
    since `8(n) > 4(n+1)` -> swap

    ```
    3 5 4 8 2 
        ^ ^
    ```
4. Go to next pair
    ```
    3 5 4 8 2 
          ^ ^
          n n+1
    ```
    since `8(n) > 2(n+1)` -> swap

    ```
    3 5 4 2 8
          ^ ^
          n n+1
    ```
as you can see 8 being the heaviest element get push at the end of the list
> You can think of it as heaviest (largest) element get bubble up 

#### similarly in future passes

* Pass 1 → `3 5 4 2 8` (8 reaches the last position)
* Pass 2 → `3 4 2 5 8` (5 reaches the second last position)
* Pass 3 → `3 2 4 5 8` (4 reaches the third last position)
* Pass 4 → `2 3 4 5 8` (Array is sorted)

### Understand one thing
in each pass the respective largest element get push to the last position
- in 1st pass largest element get push last position
- in 2nd pass 2nd largest element get push to 2nd largest element\
...goes on

As you can see 
- after first pass largest element gets to the last index
- since this is his actual position he is not gonna swap with any other element in array (as no element is bigger than him)
- therefore after first pass we can skip the last position for comparison
- same happens with second pass, the 2nd largest element to 2nd last index in array and all the remaining element are smaller 
- therefore after 2nd pass we skip comparison with 2nd last element for next traversal

from above observation we can say that we only perform comparison up to `i-1` last element 

where i = number of pass

example:
- for pass 3 -> `i = 3` -> `i - 1 = 2` -> compare up to `2nd` last element




### Code:
1. declare an array
```java
int arr[] = { 7, 8, 3, 1, 2 };
```
2. run a loop for n-1 passes 
```java
for (int i = 0; i < arr.length - 1; i++) {
    // pass i+1
}
```
3. for each pass traverse up to i-1 element in array  
```java
for (int i = 0; i < arr.length - 1; i++) {
    for (int j = 0; j < arr.length - i - 1; j++) {
        // traversing over each element
    }
}
```
4. check whether current number is greater that that of next
```java
for (int i = 0; i < arr.length - 1; i++) {
    for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
            // current if greater
        }
    }
}
```
5. swap
```java
for (int i = 0; i < arr.length - 1; i++) {
    for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
            int temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
        }
    }
}
```
5. print the array
```java
for(int i = 0; i<arr.length;i++){
    System.out.println(arr[i]);
}
```

### Complete code
```java
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = { 7, 8, 3, 1, 2 };

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for(int i = 0; i<arr.length;i++){
            System.out.print(arr[i]);
        }
    }
}
```
output
```
12378
```


### Time Complexity:

* Best Case: **O(n)** (when the array is already sorted and an optimized version is used)
* Average Case: **O(n²)**
* Worst Case: **O(n²)**

### Space Complexity: O(1) (In-place sorting)

**Key Point to Remember:**

> In Bubble Sort, the **largest (heaviest) unsorted element bubbles up to the end after every pass**. After the first pass, the largest element is fixed at the last index; after the second pass, the second largest element is fixed at the second last index, and so on, until the array is completely sorted.


[Go To Top](#content)

---
# Selection sort
Selection sort is a simple sorting algorithm that repeatedly finds the smallest element from the unsorted part of a list and places it at the beginning.

### Example
For an array:
`[64, 25, 12, 22, 11]`

1. 1st pass:\
find the smallest element (11) and swap it with the first element.
    - `[11, 25, 12, 22, 64]`
2. 2nd pass:\
find the smallest element in the remaining unsorted part (12) and swap it with the second element.
    - `[11, 12, 25, 22, 64]`
3. 3rd pass:\
find the smallest element in the remaining part (22) and swap it.
    - `[11, 12, 22, 25, 64]`
4. Continue until the array is sorted.

### How it work?
1. we start with assuming first element of array smallest element

    ```
    smallest = 64

    64 25 12 22 11
     ^
    ```
2. compare the smallest with next element
    ```
    smallest = 64
    
    64 25 12 22 11
        ^
    ```
    since, 25 < 64 -> smallest = 25
    ```
    smallest = 25
    
    64 25 12 22 11
        ^
    ```
3. compare with next element
    ```
    smallest = 25

    64 25 12 22 11
          ^
    ```
    since, 12 < 25 -> smallest = 12
    ```
    smallest = 12

    64 25 12 22 11
          ^
    ```
4. compare with next element
    ```
    smallest = 12

    64 25 12 22 11
              ^
    ```
    since, 12 < 22 -> smallest = 12 (no change)
5. compare with next element
    ```
    smallest = 12
    
    64 25 12 22 11
                 ^
    ```
    since, 11 < 12 -> smallest = 11
    ```
    smallest = 11
    
    64 25 12 22 11
                 ^
    ```
6. swap smallest with first element
    ```
    smallest = 11
    
    11 25 12 22 64
    ```
now for second iteration skip the first element i.e, 11 and apply the same algorithm from next element i.e, 2nd element

### Using index 
instead of storing the values of smallest element we store its index

example:
- `arr[]` = `[5 3 6]`
- for iteration `i=0`
    - `smallest = i = 0` -> first element at index `0`
    - `j = i + 1 = 1` -> j = with whom we are comparing the smallest
- start with first element
    - `arr[smallest]` = `arr[0]` = `5`
    - `arr[j]` = `arr[1]` = `3`
    - `arr[smallest]` > `arr[j]` -> update 
    - `smallest = j` -> `smallest = 1`
- go to next element `(j++)`
    - `smallest = 1` ; `j = 2` 
    - `arr[smallest]` = `arr[1]` = `3`
    - `arr[j]` = `arr[2]` = `6`
    - `arr[smallest]` < `arr[j]` -> no update 
    - `smallest = 1`
- since we reach the last element -> swap
    - swap(`arr[smallest]`, `arr[i]`)
    - swap(`arr[1]`, `arr[0]`)
    - `arr[]` = `[3 5 6]`
- into the next iteration `i=1`
    - `smallest = i = 1` -> In remaining unsorted array first element is at index `1`
    - `j = i + 1 = 2` -> as first element is consider as smallest we start comparing with next element
- repeat the same steps until array ends

### Code:
1. start with first element as  smallest 
```java
for (int i = 0; i < arr.length - 1; i++) {
    int smallest = i;
}
```
2. traverse over the array
```java
for (int i = 0; i < arr.length - 1; i++) {
    int smallest = i;
    for (int j = i+1; j < arr.length; j++) {
        // arr[j] is use for comparison
    }
}
```
3. compare and update the smallest
```java
for (int i = 0; i < arr.length - 1; i++) {
    int smallest = i;
    for (int j = i+1; j < arr.length; j++) {
        if (arr[smallest] > arr[j]) {
            smallest = j;
        }
    }
}
```
4. swap once the iteration is complete
```java
for (int i = 0; i < arr.length - 1; i++) {
    int smallest = i;
    for (int j = i+1; j < arr.length; j++) {
        if (arr[smallest] > arr[j]) {
            smallest = j;
        }
        int temp = arr[smallest];
        arr[smallest] = arr[i];
        arr[i] = temp;
    }
}
```

### Complete Code
```java
public class SelectionSort {
    public static void main(String[] args) {
        int arr[] = { 7, 8, 3, 1, 2 };

        for (int i = 0; i < arr.length - 1; i++) {
            int smallest = i;
            for (int j = i+1; j < arr.length-1; j++) {
                if (arr[smallest] > arr[j]) {
                    smallest = j;
                }
                int temp = arr[smallest];
                arr[smallest] = arr[i];
                arr[i] = temp;
            }
        }

        for(int i = 0; i<arr.length;i++){
            System.out.print(arr[i]);
        }
    }
}
```
output:
```
13782
```

### Time Complexity:

* Best Case: **O(n)** (when the array is already sorted and an optimized version is used)
* Average Case: **O(n²)**
* Worst Case: **O(n²)**

### Space Complexity: O(1) (In-place sorting)


[Go To Top](#content)

---
# Quick Sort


[Go To Top](#content)

---