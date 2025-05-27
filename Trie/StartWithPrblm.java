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

