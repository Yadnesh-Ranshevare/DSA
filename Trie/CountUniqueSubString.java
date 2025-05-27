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
