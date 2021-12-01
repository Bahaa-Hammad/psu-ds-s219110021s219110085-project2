package DSProject2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;

public class Probing {

    Node[] probedTable;
    int p;
    int hashCol;

    public Probing(int N, int p,int hashCol) {
        probedTable = new Node[N];
        this.p = p;
        this.hashCol = hashCol;
    }

    public void add(Node element) {
        int hashedIndex = Hashing.hash(element , this.p , this.hashCol);
        int originalHashedIndex = hashedIndex; // To use it in circling around


        // Handling collision: By linear Probing

        if (probedTable[hashedIndex] != null) { // Already Taken

            if (hashedIndex == probedTable.length - 1) { // last index? circle around the array
                hashedIndex = 0;
            } else {
                hashedIndex++;
            }


            // If arr[i] is not free; try arr[i+1 mod N], arr[i+2 mod N] …
            while (probedTable[hashedIndex] != null && originalHashedIndex != hashedIndex) {
                hashedIndex = (hashedIndex + 1) % probedTable.length;
            }
        }

        if (probedTable[hashedIndex] != null && originalHashedIndex == hashedIndex) { // Went through all table and non is empty
            throw new IllegalArgumentException("Probing Hashtable is full");
        }

        probedTable[hashedIndex] = element;
    }


    public Node searchConName(String key){
        // Get the hashedKey:
        int hashedIndex = Hashing.stringHash(key, this.p);

        // Check Assuming No collisions:
        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].conName.equals(key)){ // index not empty and node matches the given key
            return probedTable[hashedIndex]; // Found
        }

            /*
                Assuming Collision Exists:
                Linear probing: handles collisions by placing colliding item in next
                (circularly) available table cell
                if A[j] is already occupied; try A[j+1 mod N]; then A[j+2 mod N] … until free space
            */

        int originalHashedIndex = hashedIndex; // To use it in circling around

        if (hashedIndex == probedTable.length-1){ // last index? circle around the array
            hashedIndex = 0;
        }
        else{
            hashedIndex++;
        }

        /*
            search for the node in the next available indices given that it was inserted using probing:
            To find the hashedIndex: 1) index must be not null
                                     2) The key in Node must Match the given key
                                     3) We must not circle back to the original index, if we did, then it does not exist
         */

        while (probedTable[hashedIndex] != null && (probedTable[hashedIndex].conName.equals(key) == false)
                && hashedIndex != originalHashedIndex){

            hashedIndex = (hashedIndex+1) % probedTable.length;

        }

        // Check the found hashedIndex:

        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].conName.equals(key)){
            return probedTable[hashedIndex];
        }
        else {
            return null; // Element not found
        }
    }


    // Search Using country Code:

    public Node searchConCode(String key){
        // Get the hashedKey:
        int hashedIndex = Hashing.stringHash(key, this.p);

        // Check Assuming No collisions:
        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].conCode.equals(key)){ // index not empty and node matches the given key
            return probedTable[hashedIndex]; // Found
        }


        int originalHashedIndex = hashedIndex; // To use it in circling around

        if (hashedIndex == probedTable.length-1){ // last index? circle around the array
            hashedIndex = 0;
        }
        else{
            hashedIndex++;
        }

        while (probedTable[hashedIndex] != null && (probedTable[hashedIndex].conCode.equals(key) == false)
                && hashedIndex != originalHashedIndex){

            hashedIndex = (hashedIndex+1) % probedTable.length;

        }

        // Check the found hashedIndex:

        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].conCode.equals(key)){
            return probedTable[hashedIndex];
        }
        else {
            return null; // Element not found
        }
    }


    //Search by the year
    public Node searchYear(int key){
        // Get the hashedKey:
        int hashedIndex = Hashing.intHash(key, this.p);

        // Check Assuming No collisions:
        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].year == key){ // index not empty and node matches the given key
            return probedTable[hashedIndex]; // Found
        }


        int originalHashedIndex = hashedIndex; // To use it in circling around

        if (hashedIndex == probedTable.length-1){ // last index? circle around the array
            hashedIndex = 0;
        }
        else{
            hashedIndex++;
        }

        while (probedTable[hashedIndex] != null && (probedTable[hashedIndex].year != key)
                && hashedIndex != originalHashedIndex){

            hashedIndex = (hashedIndex+1) % probedTable.length;

        }

        // Check the found hashedIndex:

        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].year == key){
            return probedTable[hashedIndex];
        }
        else {
            return null; // Element not found
        }
    }

    public Node search(String key){
        if (hashCol == 1){
            return searchConName(key);
        }

        if (hashCol == 2){
            return searchConCode(key);
        }
        else {
            return searchYear(Integer.parseInt(key));
        }
    }


    public Node deleteConName(String key){

        // Find the hashedIndex:

        int hashedIndex = Hashing.stringHash(key , this.p);

        //  Assuming No collisions:
        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].conName.equals(key)){ // index not empty and node matches the given key
            Node del = probedTable[hashedIndex];
            probedTable[hashedIndex] = new Node("<DEL>");
            return del;
        }

        // Collisions Occurred:

        int originalHashedIndex = hashedIndex; // To use it in circling around

        if (hashedIndex == probedTable.length-1){ // last index? circle around the array
            hashedIndex = 0;
        }
        else{
            hashedIndex++;
        }


        while (probedTable[hashedIndex] != null && (probedTable[hashedIndex].conName.equals(key) == false)
                && hashedIndex != originalHashedIndex){

            hashedIndex = (hashedIndex+1) % probedTable.length;

        }

        // Check the found hashedIndex:

        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].conName.equals(key)){
            Node del = probedTable[hashedIndex];
            probedTable[hashedIndex] = new Node("<DEL>");
            return del;
        }
        else {
            return null; // Element not found
        }
    }


    public Node deleteConCode(String key){

        // Find the hashedIndex:

        int hashedIndex = Hashing.stringHash(key , this.p);

        //  Assuming No collisions:
        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].conCode.equals(key)){ // index not empty and node matches the given key
            Node del = probedTable[hashedIndex];
            probedTable[hashedIndex] = new Node("<DEL>");
            return del;
        }

        // Collisions Occurred:

        int originalHashedIndex = hashedIndex; // To use it in circling around

        if (hashedIndex == probedTable.length-1){ // last index? circle around the array
            hashedIndex = 0;
        }
        else{
            hashedIndex++;
        }


        while (probedTable[hashedIndex] != null && (probedTable[hashedIndex].conCode.equals(key) == false)
                && hashedIndex != originalHashedIndex){

            hashedIndex = (hashedIndex+1) % probedTable.length;

        }

        // Check the found hashedIndex:

        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].conCode.equals(key)){
            Node del = probedTable[hashedIndex];
            probedTable[hashedIndex] = new Node("<DEL>");
            return del;
        }
        else {
            return null; // Element not found
        }
    }


    public Node deleteYear(int key){

        // Find the hashedIndex:

        int hashedIndex = Hashing.intHash(key , this.p);

        //  Assuming No collisions:
        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].year == key){ // index not empty and node matches the given key
            Node del = probedTable[hashedIndex];
            probedTable[hashedIndex] = new Node("<DEL>");
            return del;
        }

        // Collisions Occurred:

        int originalHashedIndex = hashedIndex; // To use it in circling around

        if (hashedIndex == probedTable.length-1){ // last index? circle around the array
            hashedIndex = 0;
        }
        else{
            hashedIndex++;
        }


        while (probedTable[hashedIndex] != null && (probedTable[hashedIndex].year != key)
                && hashedIndex != originalHashedIndex){

            hashedIndex = (hashedIndex+1) % probedTable.length;

        }

        // Check the found hashedIndex:

        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].year == key){
            Node del = probedTable[hashedIndex];
            probedTable[hashedIndex] = new Node("<DEL>");
            return del;
        }
        else {
            return null; // Element not found
        }
    }


    public Node delete(String key){
        if (hashCol == 1){
            return deleteConName(key);
        }

        if (hashCol == 2){
            return deleteConCode(key);
        }
        else {
            return deleteYear(Integer.parseInt(key));
        }
    }



    public  void output(String fileName) throws FileNotFoundException {
        File outCSV = new File(fileName);
        PrintWriter pw = new PrintWriter(outCSV);

        for (int i = 0; i < probedTable.length; i++) {
            Node node = probedTable[i];
            if (node == null)
                continue;
            pw.printf("%s, %s, %d" , node.conName , node.conCode , node.year);

            for (int j = 0; j < node.value.length; j++) {
                pw.print(", " + node.value[j]);
            }
            pw.println();
        }
        pw.close();
    }

    // AUX:

    public void print() {

        for (int i = 0; i < probedTable.length; i++) {
            if (probedTable[i] == null) {
                System.out.println("Node At Index " + i +  " EMPTY");
            }
            else {
                System.out.println("Node At Index" + i + " ->"  + probedTable[i].toString());
            }
        }
    }
}
