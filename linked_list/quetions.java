




public class quetions {
    int size=0;

    Node head;

    class Node{
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
            size++;
            return;
        }

        newNode.next = head;
        head = newNode;
        size++;
    }


    // add last
    public void  addLast(int data){
        Node newNode = new Node(data); 
        if (head == null){
            newNode = head;
            size++;
            return;
        }
        Node currNode = head;
        while(currNode.next != null){
            currNode = currNode.next;
        }
        currNode.next = newNode;
        size++;

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


    // delete first
    public void deleteFirst(){
        if(head==null){
            System.out.println("list is empty");
            return;
        }
        head = head.next;
        size--;
    }

    // delete last
    public void deleteLast(){
        if(head==null){
            System.out.println("list is empty");
            return;
        }
        if(head.next==null){
            head = null;
            return;
        }

        Node secondLastNode = head;
        Node lastNode = head.next;
        while (lastNode.next != null){
            lastNode = lastNode.next;
            secondLastNode = secondLastNode.next;
        }
        secondLastNode.next = null;
        size--;
    }

    public int size(){
        System.out.println(size);
        return size;
    }

// Given the head of a linked list, remove the nth node from the end of the list and return its head.
    public void remove(int n){
        Node currNode = head;

        int prevNodeIdx = size-n-1;
        if(prevNodeIdx == -1){
            head = head.next;
        }
        for(int i=0; i<prevNodeIdx;i++){
            
            currNode = currNode.next;
        }
        currNode.next = currNode.next.next;
    }

    // Given the head of a linked list, rotate the list to the right by k places.
    public void rotate(int n){
            if(n == 0){
                return;
            }
            if(head.next == null || head == null){
                System.out.println("can't rotate the LL");
                return ;
            }
            Node prevNode = head;
            Node currNode = head.next;
            while(currNode.next != null){
                prevNode = prevNode.next;
                currNode = currNode.next;
            }
            currNode.next = head;
            head = currNode;
            prevNode.next = null;
            rotate(n-1);

    }


    // remove the duplicate value form the sorted LL
    public void deleteDuplicates() {

        if(head == null || head.next == null){
            return ;
        }
        // System.out.println(head == head.next);
        if(head.data == head.next.data){
            System.out.println("kkk");
            while(head.data == head.next.data){
                head = head.next;
            }
            head = head.next;
        }
        if( head.next == null){
            return ;
        }
        Node currNode = head.next;
        Node prevNode = head;
        while(currNode.next != null){
            if(currNode.data == currNode.next.data){
                while(currNode.data == currNode.next.data){
                    System.out.println(currNode.data);
                    currNode = currNode.next;
                }
                prevNode.next = currNode.next;
                currNode = currNode.next;
            }else{
                currNode = currNode.next;
                prevNode = prevNode.next;
            } 
        }
        
    }


    public  Node reverse(Node h){
        Node prev = null;
        Node curr = h;
        while(curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return  prev;
    }


    public  Node findMid(Node head){
        Node hare = head;
        Node turtle = head;
        while(hare.next != null && hare.next.next != null){
            hare = hare.next.next;
            turtle = turtle.next;
        }
        return turtle;
    }

    // check is LL palindrome or not 
    public boolean  palindrome(){
        if(head == null || head.next == null){
            return  true;
        }
        Node mid = findMid(head);   //end of the first half
        Node  secHalf = reverse(mid.next);

        Node firstHalfStart = head;
        while(secHalf != null){
            if(firstHalfStart.data != secHalf.data){
                return  false;
            }
            firstHalfStart = firstHalfStart.next;
            secHalf = secHalf.next;
        }
        return  true;
    }



    public Node mergeTwoLists(Node list1, Node list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        Node dummy = new Node(0);
        Node curr = dummy;

        while (list1 != null ) {
            curr.next = list1;
            list1 = list1.next;
            curr = curr.next; // Move to the next node
        }
        while (list2 != null ) {
            curr.next = list2;
            list2 = list2.next;
            curr = curr.next; // Move to the next node
        }

        curr = dummy.next;
        while(curr != null){
            Node next = curr;
            while(next != null){
                if(curr.data >= next.data){
                    System.out.println(curr.data);
                    int temp = curr.data;
                    curr.data = next.data;
                    next.data = temp;
                }
                next = next.next;
            }
            curr = curr.next;
        }
        
        return  dummy.next;    
    }

    

    public Node swapPairs(Node head) {
        if(head == null || head.next == null) return head;

        Node curr = head;
        Node next = head.next;
        while(true){
            int temp = curr.data;
            curr.data = next.data;
            next.data = temp;
            
            if( next.next != null && next.next.next != null){
                curr = curr.next.next;
                next = next.next.next;
                System.out.println(next.data);
            }else{
                break;
            }
            
        }
        return head; 
    }
    


    public static void main(String[] args) {
        quetions list1 = new quetions();
        quetions list2 = new quetions();

        list1.addFirst(1);
        list1.addLast(2);
        list1.addLast(4);
        list1.addLast(5);
        list1.addLast(6);
        list1.addLast(8);
        // list1.addLast(9);

        // list2.addFirst(1);
        // list2.addLast(3);
        // list2.addLast(2);
        // quetions List3 = new quetions();
        // List3.head = List3.mergeTwoLists(list1.head, list2.head);
        // list1.printList();
        // list2.printList();
        
        list1.printList();
        list1.head = list1.swapPairs(list1.head);
        list1.printList();
        
        
        // list.printList();
    }
}
