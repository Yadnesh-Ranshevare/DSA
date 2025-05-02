
public class subtree {
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

    static class BinaryTree{
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return  null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static boolean isIdentical(Node root, Node subRoot){
        if(root == null && subRoot == null){
            return true;
        }
        if(root == null || subRoot == null){
            return  false;
        }

        if(root.data == subRoot.data){
            return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
        }
        return false;
    }


   public static  boolean subtree(Node root, Node subRoot){
        if(subRoot == null){
            return  true;
        }
        if(root == null){
            return  false;
        }

        if(root.data == subRoot.data){
            if(isIdentical(root, subRoot)){
                return true;
            }
        }
        return  subtree(root.left, subRoot) || subtree(root.right, subRoot);
   }

    public static void main(String[] args) {
        int nodes[] = {1, 2, -1, -1, 3, 4, -1, -1, 5, -1, -1 };
        int subtreeNodes[] = {3, 4, -1, -1, 5, -1, -1 };

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        BinaryTree.idx = -1; 
        Node subRoot = tree.buildTree(subtreeNodes);
        
        System.out.println(subtree(root, subRoot));


    }
}
