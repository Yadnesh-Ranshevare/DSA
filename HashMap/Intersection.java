// given two array return the array of intersection of those two array
// intersection: set( only contain unique element ) of common element

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