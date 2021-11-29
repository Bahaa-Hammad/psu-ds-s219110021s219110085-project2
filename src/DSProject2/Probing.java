package DSProject2;

public class Probing {

    Node[] probedTable;

    public Probing(int N) {
        probedTable = new Node[N];
    }

    public void addConName(Node element){
        int hashedIndex = Hashing.stringHash(element.conName);

        // Handling collision: By linear Probing

        if (probedTable[hashedIndex] != null){ // Already Taken

            int originalHashedIndex = hashedIndex; // To use it in circling around

            if (hashedIndex == probedTable.length-1){ // last index? circle around the array
                hashedIndex = 0;
            }
            else{
                hashedIndex++;
            }

            // If arr[i] is not free; try arr[i+1 mod N], arr[i+2 mod N] …
            while (probedTable[hashedIndex] != null && originalHashedIndex != hashedIndex){
                hashedIndex = (hashedIndex+1) % probedTable.length;
            }

            if(probedTable[hashedIndex] != null && originalHashedIndex == hashedIndex){ // Went through all table and non is empty
                throw new IllegalArgumentException("Probing Hashtable is full");
            }

            probedTable[hashedIndex] = element;
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
