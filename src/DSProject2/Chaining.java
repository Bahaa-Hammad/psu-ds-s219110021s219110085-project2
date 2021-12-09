package DSProject2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Chaining {
    SLL<Node>[] chainedTable;  // Array Of linkedLists
    int p;
    int hashCol;
    double occupiedCells;
    int hashtableSize;

    // Constructor:
    public Chaining(int hashtableSize , int p , int hashCol){
        this.hashtableSize = hashtableSize;
        // Creating array
        chainedTable = new SLL[hashtableSize];

        // Creating LinkedList at each index in an array:
        for (int i = 0; i < hashtableSize; i++) {
            chainedTable[i] = new SLL<Node>();
        }

        this.p = p;
        this.hashCol = hashCol;
    }

    // Adding Method:

    public void add(Node element){
        // Get index of hashing
        int hashedInx = Hashing.hash(element, this.p , this.hashCol);
        // Adding the element node in a LinkedList at the hashed index.
        chainedTable[hashedInx].insertAtHead(element);
    }

    // Search Methods:

    public Node searchConName(String key){
        // Get the hashedKey for the given input -> so we avoid searching the whole array.
        int hashedKey = Hashing.stringHash(key , this.p);

        if (chainedTable[hashedKey] == null){
            return null;
        }

        SNode<Node> current = chainedTable[hashedKey].head;

        while (current.getNext() != null){

            Node node = current.getItem(); // Save it to return if it matches the key
            if (node.conName.equals(key)){ // compare based on the countryName
                return node;
            }
            current = current.getNext();
        }
        return null;
    }


    public Node searchConCode(String key){
        // Get the hashedKey for the given input -> so we avoid searching the whole array.

        int hashedKey = Hashing.stringHash(key, this.p);

        if (chainedTable[hashedKey] == null){
            return null;
        }

        SNode<Node> current = chainedTable[hashedKey].head;
        while (current.getNext() != null){

            Node node = current.getItem(); // Save it to return if it matches the key
            if (node.conCode.equals(key)){ // compare based on the countryName
                return node;
            }
            current = current.getNext();
        }
        return null;
    }


    public Node searchYear(int key){
        // Get the hashedKey for the given input -> so we avoid searching the whole array.

        int hashedKey = Hashing.intHash(key , this.p);
        //Create iterator for the list at the hashed Key:

        if (chainedTable[hashedKey] == null){
            return null;
        }


        SNode<Node> current = chainedTable[hashedKey].head;
        while (current.getNext() != null){

            Node node = current.getItem(); // Save it to return if it matches the key
            if (node.year == key){ // compare based on the countryName
                return node;
            }
            current = current.getNext();
        }
        return null;
    }


    public Node search(String key){
        if (hashCol == 1){
            return searchConName(key);
        }

        if (hashCol == 2){
            return searchConCode(key);
        }
        else {
            return searchYear(Integer.parseInt(key.trim()));
        }
    }

    public SLL deleteConName(String key) {

        int hashedKey = Hashing.stringHash(key , this.p); // Get index of the list by hashing the given key
        // Search for the element in the list at the hashedKey:
        SNode<Node> current = chainedTable[hashedKey].head;

        if (current.getItem().conName.equals(key)){
            SLL del = chainedTable[hashedKey];
            chainedTable[hashedKey] = null;
            return del;
        }
        return null; // Not found
    }


    public SLL deleteConCode(String key) {

        int hashedKey = Hashing.stringHash(key , this.p); // Get index of the list by hashing the given key

        // Search for the element in the list at the hashedKey:
        SNode<Node> current = chainedTable[hashedKey].head;

        if (current.getItem().conCode.equals(key)){
            SLL del = chainedTable[hashedKey];
            chainedTable[hashedKey] = null;
            return del;
        }

        return null; // Not found
    }


    public SLL deleteYear(int key) {

        int hashedKey = Hashing.intHash(key , this.p); // Get index of the list by hashing the given key

        // Search for the element in the list at the hashedKey:
        SNode<Node> current = chainedTable[hashedKey].head;

        if (current.getItem().year == key){
            SLL del = chainedTable[hashedKey];
            chainedTable[hashedKey] = null;
            return del;
        }
        return null; // Not found
    }


    public SLL delete(String key){
        if (hashCol == 1){
            return deleteConName(key);
        }

        if (hashCol == 2){
            return deleteConCode(key);
        }
        else {
            return deleteYear(Integer.parseInt(key.trim()));
        }
    }


    /*
      Load-factor: If n is the total number of buckets and k is the number of buckets that have data;
      then Load-factor is k/n.
       Example; if n is 10 and k is 7, then load factor is 0.7.
   */
    public double loadFactor(){

        for (int i = 0; i < hashtableSize; i++) {
            if (chainedTable[i].head != null){
                occupiedCells++;
            }
        }

        return (this.occupiedCells / this.hashtableSize);
    }



    public void print() {

        for (int i = 0; i < chainedTable.length; i++) {
            if (chainedTable[i].isEmpty() == true) {
                System.out.println("Index: " + i + " -> EMPTY!");
            }
            else {
                System.out.print("Index " + i + ": ");
                chainedTable[i].printList();
            }
        }
    }


    public  void output(String fileName) throws FileNotFoundException {
        File outCSV = new File(fileName);
        PrintWriter pw = new PrintWriter(outCSV);

        for (int i = 0; i < chainedTable.length; i++) {

            SLL currentList = chainedTable[i];
            if (currentList == null){
                continue;
            }

            for(int l = 0; l < currentList.size; l++){
                Node node = (Node) currentList.findElementPos(l);
                pw.printf("%s, %s, %d" , node.conName , node.conCode , node.year);

                for (int j = 0; j < node.value.length; j++) {
                    pw.print(", " + node.value[j]);
                }

                pw.println();
            }
        }
        pw.close();
    }
}
