package DSProject2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Chaining {
    LinkedList<Node>[] chainedTable;  // Array Of linkedLists
    int p;
    int hashCol;

    // Constructor:
    public Chaining(int n , int p , int hashCol){
        // Creating array
        chainedTable = new LinkedList[n];

        // Creating LinkedList at each index in an array:
        for (int i = 0; i < n; i++) {
            chainedTable[i] = new LinkedList<Node>();
        }

        this.p = p;
        this.hashCol = hashCol;
    }

    // Adding Method:

    public void add(Node element){
        // Get index of hashing
        int hashedInx = Hashing.hash(element, this.p , this.hashCol);
        // Adding the element node in a LinkedList at the hashed index.
        chainedTable[hashedInx].add(element);
    }

    // Search Methods:

    public Node searchConName(String key){
        // Get the hashedKey for the given input -> so we avoid searching the whole array.

        int hashedKey = Hashing.stringHash(key , this.p);
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

        int hashedKey = Hashing.stringHash(key, this.p);
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

        int hashedKey = Hashing.intHash(key , this.p);
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

    public Node deleteConName(String key) {

        int hashedKey = Hashing.stringHash(key , this.p); // Get index of the list by hashing the given key
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


    public Node deleteConCode(String key) {

        int hashedKey = Hashing.stringHash(key , this.p); // Get index of the list by hashing the given key
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


    public Node deleteYear(int key) {

        int hashedKey = Hashing.intHash(key , this.p); // Get index of the list by hashing the given key
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


    public Node delete(String key){
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



    public void print() {

        for (int i = 0; i < chainedTable.length; i++) {
            if (chainedTable[i].isEmpty() == true) {
                System.out.println("Index: " + i + " -> EMPTY!");
            }
            else {
                System.out.print("Index " + i + ": ");
                Iterator<Node> iterator = chainedTable[i].iterator();
                while (iterator.hasNext()) {
                    System.out.print(iterator.next().toString());
                    System.out.print("->");
                }
            }
        }
    }


    public  void output(String fileName) throws FileNotFoundException {
        File outCSV = new File(fileName);
        PrintWriter pw = new PrintWriter(outCSV);

        for (int i = 0; i < chainedTable.length; i++) {

            Iterator iter = chainedTable[i].iterator();
            while (iter.hasNext()){
                Node node = (Node) iter.next();
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
