package DSProject2;

public class Probing {

    Node[] probedTable;

    public Probing(int N) {
        probedTable = new Node[N];
    }

    public void addConName(Node element) {
        int hashedIndex = Hashing.stringHash(element.conName);
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
        int hashedIndex = Hashing.stringHash(key);

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

        while (probedTable[hashedIndex] != null && (probedTable[hashedIndex].equals(key) == false)
                && hashedIndex != originalHashedIndex){

            hashedIndex = (hashedIndex+1) % probedTable.length;

        }

        // Check the found hashedIndex:

        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].equals(key)){
            return probedTable[hashedIndex];
        }
        else {
            return null; // Element not found
        }
    }


    public Node deleteConName(String key){

        // Find the hashedIndex:

        int hashedIndex = Hashing.stringHash(key);

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


        while (probedTable[hashedIndex] != null && (probedTable[hashedIndex].equals(key) == false)
                && hashedIndex != originalHashedIndex){

            hashedIndex = (hashedIndex+1) % probedTable.length;

        }

        // Check the found hashedIndex:

        if (probedTable[hashedIndex] != null && probedTable[hashedIndex].equals(key)){
            Node del = probedTable[hashedIndex];
            probedTable[hashedIndex] = new Node("<DEL>");
            return del;
        }
        else {
            return null; // Element not found
        }
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
