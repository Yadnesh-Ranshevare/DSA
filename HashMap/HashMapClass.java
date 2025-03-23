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
                    put(node key,node value);
                }
            }
        }

        public void put(k key,v value){
            int bi = HashFunction(key);
            int di = searchInLL(key, bi);
            if(di == -1){
                bucket[bi].add(new Node);
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
    }


    public static void main(String[] args) {
        System.out.println("hello");
    }
}