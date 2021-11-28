package DSProject2;

import java.util.Iterator;
import java.util.LinkedList;

public class Chaining {
    LinkedList<Node>[] chainedTable;

    // Constructor:
    public Chaining(int N){
        // Creating array
        chainedTable = new LinkedList[N];

        // Creating LinkedList at each index in an array:
        for (int i = 0; i < N; i++) {
            chainedTable[i] = new LinkedList<Node>();
        }
    }

    // Adding Method:

    public void addConNameHash(Node element){
        // Get index of hashing
        int hashedInx = Hashing.stringHash(element.conName);
        // Adding the element node in a LinkedList at the hashed index.
        chainedTable[hashedInx].add(element);
    }

    public void addConCodeHash(Node element){
        // Get index of hashing
        int hashedInx = Hashing.stringHash(element.conCode);
        // Adding the element node in a LinkedList at the hashed index.
        chainedTable[hashedInx].add(element);
    }


    public void addYearHash(Node element){
        // Get index of hashing
        int hashedInx = Hashing.intHash(element.year);
        // Adding the element node in a LinkedList at the hashed index.
        chainedTable[hashedInx].add(element);
    }

    // Search Methods:

    public Node searchConName(String key){
        // Get the hashedKey for the given input -> so we avoid searching the whole array.

        int hashedKey = Hashing.stringHash(key);
        //Create iterator for the list at the hashed Key:
        Iterator iter = chainedTable[hashedKey].iterator();
        while (iter.hasNext()){
            Node node = (Node) iter.next(); // Save it to return if it matches the key
            if (node.conName.equals(key)){ // compare based on the countryName
                return node;
            }
        }
        return null;
    }


    public Node searchConCode(String key){
        // Get the hashedKey for the given input -> so we avoid searching the whole array.

        int hashedKey = Hashing.stringHash(key);
        //Create iterator for the list at the hashed Key:
        Iterator iter = chainedTable[hashedKey].iterator();
        while (iter.hasNext()){
            Node node = (Node) iter.next(); // Save it to return if it matches the key
            if (node.conCode.equals(key)){ // compare based on the countryCode
                return node;
            }
        }
        return null;
    }


    public Node searchYear(int key){
        // Get the hashedKey for the given input -> so we avoid searching the whole array.

        int hashedKey = Hashing.intHash(key);
        //Create iterator for the list at the hashed Key:
        Iterator iter = chainedTable[hashedKey].iterator();
        while (iter.hasNext()){
            Node node = (Node) iter.next(); // Save it to return if it matches the key
            if (node.year == key){ // compare based on the year
                return node;
            }
        }
        return null;
    }

    public Node delConName(String key) {

        int hashedKey = Hashing.stringHash(key); // Get index of the list by hashing the given key
        int pos = -1; // To keep track of the element pos in the linkedList

        // Search for the element in the list at the hashedKey:
        Iterator iter = chainedTable[hashedKey].iterator();
        while (iter.hasNext()) {
            Node node = (Node) iter.next();
            pos++;
            if (node.conName.equals(key)) { // compare based on the countryName
                chainedTable[hashedKey].remove(pos);
                return node;
            }
        }
        return null; // Not found
    }


    public Node delConCode(String key) {

        int hashedKey = Hashing.stringHash(key); // Get index of the list by hashing the given key
        int pos = -1; // To keep track of the element pos in the linkedList

        // Search for the element in the list at the hashedKey:
        Iterator iter = chainedTable[hashedKey].iterator();
        while (iter.hasNext()) {
            Node node = (Node) iter.next();
            pos++;
            if (node.conCode.equals(key)) { // compare based on the countryName
                chainedTable[hashedKey].remove(pos);
                return node;
            }
        }
        return null; // Not found
    }


    public Node delYear(int key) {

        int hashedKey = Hashing.intHash(key); // Get index of the list by hashing the given key
        int pos = -1; // To keep track of the element pos in the linkedList

        // Search for the element in the list at the hashedKey:
        Iterator iter = chainedTable[hashedKey].iterator();
        while (iter.hasNext()) {
            Node node = (Node) iter.next();
            pos++;
            if (node.year == key) { // compare based on the countryName
                chainedTable[hashedKey].remove(pos);
                return node;
            }
        }
        return null; // Not found
    }
}
