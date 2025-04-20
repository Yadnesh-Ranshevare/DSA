// given an integer array of size n, find all the element that appear more than n/3 times

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