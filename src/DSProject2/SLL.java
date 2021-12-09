package DSProject2;

import java.util.NoSuchElementException;

public class SLL<T> {
     SNode<T> head; //First Element in the list
     SNode<T> tail; //Last Element in the list
     int size; //Number of nodes in the list
    //Methods:

    //Insert At Head:
    public void insertAtHead(T val){
        // Create a new Node: Because we use nodes, and the node class attributes in linkedList.
        SNode<T> tmp = new SNode<>(val);

        tmp.setNext(head);//Assign the current head to "The new head next field"
        head = tmp; //Assign head to its new value (tmp)

        //Edge Case: Empty List
        if (size == 0){
            tail = head; //tmp will be both head and tail
        }
        size++;
    }

    //Insert At Tail:
    public void insertAtTail(T val){
        // Create a new Node
        SNode<T> tmp = new SNode<>(val);

        tmp.setNext(null); // In SLL: tail's next field always == null

        //Edge Case: Empty List
        if (size == 0){
            head = tmp; // tmp will be both head and tail
        }
        else{
            tail.setNext(tmp);
        }
        tail = tmp;
        size++;
    }

    // Insert At Pos i:
    public void insertAtPos(T val, int pos){
        //Check if pos is valid:
        if (pos > size-1 || size == 0){
            throw new IndexOutOfBoundsException("Invalid pos");
        }

        //Create new node:
        SNode<T> tmp = new SNode<>(val);

        // Shortcut cases:
        if (pos == 0){
            insertAtHead(val);
            return;
        }
        if (pos == (size-1) ){
            insertAtTail(val);
            return;
        }

        //To add a node at a pos, must find the node that is before that pos:
        SNode<T> prev = head;  // Because we want to traverse the array starting from the head.
        for (int i = 0; i < pos-1; i++){ // "pos-1" Because we want the node before the needed pos
            prev = prev.getNext();
        }
        /*
            After the loop: prev --> the node before the needed position:

            List: Head --> "Node" --> prev --> "Node == prev.getNext()"
                                            \ tmp /
                                                  1) tmp.setNext = prev.getNext()
                        2) prev.setNext(tmp);
         */
        tmp.setNext(prev.getNext());
        prev.setNext(tmp);
        size++;
    }

                            //------Slides week2-2------\\
    //Deleting A node:

    //Delete At Head:
    public T deleteAtHead() {
        //Edge Cases:
        if (size == 0) {
            throw new NoSuchElementException("List is Empty!");
        }
        T val = head.getItem(); // to return it to the user in case it's needed. (Optional)
        head = head.getNext(); // set the new head
        size--;

        //Edge Case: List is empty after deleting the head:
        if (size == 0) {
            tail = null;
        }
        return val; // The deleted head node. (Optional)
    }


    //Delete At Tail:
    public T deleteAtTail(){
        if (size == 0) {
            throw new NoSuchElementException("List is Empty!");
        }

        T val = tail.getItem();    // to return it to the user in case it's needed. (Optional)

        /*
           - Why not set tail to null directly?
            Because it would not be deleted, previous node will be still pointing to it.

           - Find the node before the tail:
            ->so we could make it the new tail and delete the current tail.
         */

        //Edge Case:
        if (size == 1){
            head = tail = null;
        }
        else{

            SNode<T> prev = head;
            for(int i = 0; i < size - 2; i++){ // "Size - 2" == The node before the tail
                prev = prev.getNext();
            }
            prev.setNext(null);  // Node before the tail is no longer pointing to the current tail.
            tail = prev;  // New Tail
        }
        size --;
        return val; // to return it to the user in case it's needed. (Optional)
    }


    //Delete At Pos:
    public T deletePos(int pos){
        //Check if pos is valid:
        if (pos > size-1 || size < 0){
            throw new IndexOutOfBoundsException("Invalid pos");
        }
        // Shortcut cases:
        if (pos == 0){
            return deleteAtHead();
        }
        if (pos == size-1){
            return deleteAtTail();
        }

        //Find the node before pos i (pos -1):
        SNode<T> prev = head;
        for(int i = 0; i < pos - 1; i++){
            prev = prev.getNext();
        }
        T val = prev.getNext().getItem(); // to return it to the user in case it's needed. (Optional)

        // Deleting the node and connecting the other nodes in the SLL:
        SNode<T> newNext = prev.getNext().getNext(); // Node at pos i next's field = newNext
        prev.setNext(newNext); // prev is now connected to the node that is after the Node at (pos).
        size--;
        return val; // to return it to the user in case it's needed.
    }

    /*
        Finding Nodes, then returning:
        Method 1 - The Node's object instance (item)
        Method 2 - The index of the node.
     */

    public T findElementPos(int pos){
        //Check if pos is valid:
        if (pos < 0 || pos > size-1){
            throw new IndexOutOfBoundsException("Pos is out of range");
        }
        if (size == 0){
            throw new NoSuchElementException("The list is empty!");
        }
        //Find the node at pos:
        SNode<T> cursor = head;
        for (int i = 0; i < pos; i++){
            cursor = cursor.getNext();
        }
        return cursor.getItem();  // We are interested in returning the object (item), Not the Node (Object and address for the next object)
    }

    // Find pos index for an element:
    public int posOfElement(T val){
        int i = 0;
        SNode<T> cursor = head;

        while(cursor != null){ // if it's null, then it reached the tail and the object (item) DNE
            if (cursor.getItem().equals(val)) { // using OOP to make equals work, we are interested in the Node, we are given an object (item/val).
                return i;
            }
            cursor = cursor.getNext();
            i++;
        }
        return -1; // Val DNE
    }
    public void printList(){
        System.out.println("List Head: "+head.getItem()); // head is the node, We want to print the object instance inside the node (item)
        System.out.println("List Tail: " + tail.getItem());
        System.out.println("List size: " + this.size);
        System.out.println("=======================");
        SNode<T> current = head;
        for (int i = 0; i < size-1; i++) {
            System.out.print(current.getItem() + "->");
            current = current.getNext();
        }
        System.out.println(tail.getItem() + "->Null");
    }

    public boolean isEmpty(){

        if (size == 0){
            return true;
        }
        return false;
    }
}
