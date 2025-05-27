public class WordBreak {

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

    public static boolean  search(String key){
        Node head = root;
        for(int i = 0; i<key.length(); i++){
            int idx = key.charAt(i) - 'a';

            if(head.children[idx] == null){
                return  false;
            }
            if(i == key.length() - 1 ){
                return head.children[idx].eow;
            }
            head = head.children[idx];
        }
        return true;
    }


    public static boolean word(String key){
        if(key.length() == 0){
            return  true;
        }
        for(int i = 1; i<= key.length(); i++){
            String firstPart = key.substring(0,i);
            String secondPArt = key.substring(i);
            if(search(firstPart) && word(secondPArt)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String words[] = {"i", "like", "sam", "samsung", "mobile"};

        for(String word : words){
            insert(word);
        }

        String key = "ilikesamsung";

        System.out.println(word(key));
    }   
}
