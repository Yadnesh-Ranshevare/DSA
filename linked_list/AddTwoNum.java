public class AddTwoNum {
    Node head;

    static class Node{
        int data;
        Node next;
    
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
        
    }

    // add first
    public void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    // print Linked list
    public void printList(){
        Node currNode = head;
        while(currNode != null){
            System.out.print(currNode.data + "->");
            currNode = currNode.next;
        }
        System.out.println("null");
    }

    public static Node add(Node l1, Node l2){
        Node ans = new Node(0);
        Node curr = ans;
        int carry = 0;
        while(l1 != null && l2 != null){
            curr.data = l1.data  + l2.data + carry;
            carry = 0;

            if(curr.data > 9){
                carry = curr.data/10;
                curr.data = curr.data % 10;
            }

            if(l1.next == null && l2.next != null){
                l1.next = new Node(0);
            }
            
            if(l2.next == null && l1.next != null){
                l2.next = new Node(0);
            }

            if(l1.next != null && l2.next != null){
                curr.next = new Node(0);
                curr = curr.next;
            }
         
            l1 = l1.next;
            l2 = l2.next;
        }

        if(carry != 0){
            curr.next = new Node(0);
            curr = curr.next;
            curr.data = carry;
        }

        return ans;
    }

    public static void main(String[] args) {
        AddTwoNum list1 = new AddTwoNum();
        AddTwoNum list2 = new AddTwoNum();
        list1.addFirst(9);
        list1.addFirst(9);
        list1.addFirst(9);
        list1.addFirst(9);
        list1.addFirst(9);
        list1.addFirst(9);
        list1.addFirst(9);

        list2.addFirst(9);
        list2.addFirst(9);
        list2.addFirst(9);
        list2.addFirst(9);

        System.out.println("input LL");
        list1.printList();
        list2.printList();

        System.out.println("sum:");
        AddTwoNum List3 = new AddTwoNum();
        List3.head = add(list1.head, list2.head);

        List3.printList();
    }
}
