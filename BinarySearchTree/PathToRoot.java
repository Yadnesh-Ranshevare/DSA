
import java.util.ArrayList;



public class PathToRoot{
    static  class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node insert(Node root,int val){
        if(root == null){
            root = new Node(val);
            return root;
        }

        if(root.data > val){
            root.left = insert(root.left, val);
        }
        if(root.data < val){
            root.right = insert(root.right, val);
        }
        return root;
    }


    public static void printPath(ArrayList<Integer> path){
        for (int node : path){
            System.out.print(node + "->");
        }
        System.out.println();
    }

    public static void PrintToRootNode(Node root, ArrayList<Integer> path){

        if(root == null){
            return;
        }
        path.add(root.data);

        if(root.left == null && root.right == null){
            printPath(path);
        }else{
            PrintToRootNode(root.left, path);
            PrintToRootNode(root.right, path);
        }


        path.remove(path.size()-1);
    }
    



    public static void main(String[] args) {
        int value[] = {8, 5, 3, 1, 4, 6, 10, 11, 14};

        Node root = null;

        for(int i = 0; i< value.length; i++){
            root = insert(root, value[i]);
        }

        PrintToRootNode(root, new ArrayList<>());

    }
}
