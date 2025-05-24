public class Implementation {

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

    static Node head = new Node();

    public static void main(String[] args) {
        String words[] = {"the", "a", "there", "their", "any"};
        System.out.println("hellow");
    }   
}
