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