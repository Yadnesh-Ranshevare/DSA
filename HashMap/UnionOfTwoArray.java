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


        for(int i = 0;i<ans.length;i++){
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
