package DSProject2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        /*
                                 args[0]       args[1]     args[2]      args[3]              args[4]              args[5]        args[6]
            java -jar project2 <input file> <output file> <column> <hash table size n> <collision resolution> <prime number p> <remove keys>


         */


        // Handling Input:
        int numCellInput = args.length - 6;   // Number of cells that contains remove keys
        String cells = "";

        for (int i = 0; i < numCellInput; i++) {
            String key = args[6 + i].concat(" ");
             cells = cells.concat(key);
        }

        cells = cells.trim(); // To get rid of extra spaces
        String[] removeKeys = cells.split(",");
        System.out.println("Remove Keys: "+ Arrays.toString(removeKeys));


        File inFile = new File(args[0]);
        // Reading:
        System.out.println("Reading data...");
        Time stopwatch = new Time();
        ArrayList<Node> inputData = new ArrayList<>();
        try {
            inputData = input(inFile);
        }catch (Exception FileNotFoundException){
            throw new FileNotFoundException("Enter The Full Path For The Input CSV File or Change Directory To Where The File Exists");
        }




        String outputFileName = args[1];
        int hashingCol = Integer.parseInt(args[2]);
        int n = Integer.parseInt(args[3]);
        int p = Integer.parseInt(args[5]);

        if (args[4].equals("1")){ //Probing

            Probing probTable = new Probing(n , p, hashingCol);
            // Adding input into PropHashTable
            for (int i = 0; i < inputData.size(); i++) {
                probTable.add(inputData.get(i));
            }

            System.out.println("Total time to read data: " + stopwatch.elapsedTime() + " seconds");

            // Deleting:
            System.out.println("Searching and removing "+ cells +"...");
            stopwatch = new Time();
            // Loop to delete all nodes with the given key
            for (int i = 0; i < removeKeys.length; i++) {

                while (probTable.delete(removeKeys[i]) != null){
                    probTable.delete(removeKeys[i]);
                }
            }
            System.out.println("Total time to remove data: " + stopwatch.elapsedTime() + " seconds");

            /*for (int i = 0; i < removeKeys.length; i++) { // For Testing Purposes.
                System.out.println("Prop: " +probTable.search(removeKeys[i]));
            }*/

            // Writing:
            System.out.println("Writing data to output file...");
            stopwatch = new Time();
            probTable.output(outputFileName);
            System.out.println("Total time to write data: " + stopwatch.elapsedTime() + " seconds");


            //System.out.println("Propping Load Factor: " + probTable.loadFactor());
            //System.out.println("Occupied In Probing: " + probTable.occupiedCells);

        }else if (args[4].equals("2")) { // Chaining
            Chaining chainedTable = new Chaining(n , p , hashingCol);

            for (int i = 0; i < inputData.size(); i++) {
                chainedTable.add(inputData.get(i));
            }

            System.out.println("Total time to write data: " + stopwatch.elapsedTime() + " seconds");

            // Deleting:
            System.out.println("Searching and removing "+ cells +"...");
            stopwatch = new Time();
            for (int i = 0; i < removeKeys.length; i++) {
                chainedTable.delete(removeKeys[i]);
            }
            System.out.println("Total time to remove data: " + stopwatch.elapsedTime() + " seconds");

            for (int i = 0; i < removeKeys.length; i++) {   //Used For Testing Purposes
                System.out.println("Chain: " + chainedTable.search(removeKeys[i]));
            }


            // Writing:
            stopwatch = new Time();
            chainedTable.output(outputFileName);
            System.out.println("Total time to write data: " + stopwatch.elapsedTime() + " seconds");


            //System.out.println("Chaining Load Factor: " + chainedTable.loadFactor());
            //System.out.println("Occupied in ChainTable "+chainedTable.occupiedCells);


        }else {
            throw new IllegalArgumentException("Invalid collision resolution");
        }
    }



    public static ArrayList<Node> input(File file) throws IOException { // Returns ArrayList of nodes containing file data
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = " ";
        String[] input;
        ArrayList<Node> nodes = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            input = line.split(","); // CSV
            Node node = new Node(input);
            nodes.add(node);
        }
        return nodes;
    }

}
