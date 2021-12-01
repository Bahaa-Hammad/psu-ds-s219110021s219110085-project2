package DSProject2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {

        /*
                                 args[0]       args[1]     args[2]      args[3]              args[4]              args[5]        args[6]
            java -jar project2 <input file> <output file> <column> <hash table size n> <collision resolution> <prime number p> <remove keys>
         */


        /*
        // Reading:
        Time stopwatch = new Time();
        ArrayList<Node> inputData = input(new File(args[0]));
        System.out.println("Reading: " + stopwatch.elapsedTime());

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

            // Deleting:
            stopwatch = new Time();
            // Loop to delete all nodes with the given key
            while (probTable.delete(args[6]) != null){
                probTable.delete(args[6]);
            }
            System.out.println("Deleting: " + stopwatch.elapsedTime());

            // Writing:
            stopwatch = new Time();
            probTable.output(outputFileName);
            System.out.println("Writing: " + stopwatch.elapsedTime());


        }else if (args[4].equals("2")) { // Chaining
            Chaining chainedTable = new Chaining(n , p , hashingCol);

            for (int i = 0; i < inputData.size(); i++) {
                chainedTable.add(inputData.get(i));
            }

            // Deleting:
             stopwatch = new Time();
            while (chainedTable.delete(args[6]) != null){
                chainedTable.delete(args[6]);
            }
            System.out.println("Deleting: " + stopwatch.elapsedTime());

            // Writing:
            stopwatch = new Time();
            chainedTable.output(outputFileName);
            System.out.println("Writing: " + stopwatch.elapsedTime());


        }else {
            throw new IllegalArgumentException("Invalid collision resolution");
        }

         */





        //Testing:

        File a = new File("A.CSV");
        File b = new File("B.CSV");
        File c = new File("C.CSV");
        File d = new File("D.CSV");

        Time time = new Time();


        //EXP A:

        ArrayList<Node> file = input(d);
        //System.out.println("Reading time: " + time.elapsedTime());

        // Chaining:
        //Chaining hashChaining = new Chaining(input(a).size() , 263 , 1);
        //for (int i = 0; i < input(a).size(); i++) {
        //    hashChaining.add(fileA.get(i));
        // }
        //hashChaining.print();

        //time = new Time();
        // System.out.println("Writing time: " + time.elapsedTime());



        //Propping:
        Probing prop = new Probing(file.size() , 7 , 1);
        for (int i = 0; i < file.size(); i++) {
            prop.add(file.get(i));
        }

        time = new Time();
        Node delN = prop.deleteConName("Zimbabwe");
        System.out.println("del time in nano: " + time.elapsedTimeNano());

        System.out.println(delN.toString());
        prop.print();
        prop.output("fileDTest.csv");
    }



    public static ArrayList input(File file) throws IOException { // Returns ArrayList of nodes containing file data
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
