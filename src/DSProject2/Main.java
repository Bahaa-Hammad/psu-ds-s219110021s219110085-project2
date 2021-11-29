package DSProject2;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {

        /*
                                 args[0]       args[1]     args[2]      args[3]              args[4]              args[5]        args[6]
            java -jar project2 <input file> <output file> <column> <hash table size n> <collision resolution> <prime number p> <remove keys>
         */

        // Reading:
        ArrayList<Node> inputData = input(new File(args[0]));

        String outputFileName = args[1];
        int hashingCol = Integer.parseInt(args[2]);
        int n = Integer.parseInt(args[3]);
        int p = Integer.parseInt(args[5]);

        if (args[4].equals("1")){ //Probing
            Probing probTable = new Probing(n , p, hashingCol);
            for (int i = 0; i < inputData.size(); i++) {
                probTable.add(inputData.get(i));
            }

            // Deleting:
            while (probTable.delete(args[6]) != null){
                probTable.delete(args[6]);
            }

            // Writing:

            probTable.output(outputFileName);

        }else if (args[3].equals("2")) { // Chaining
            Chaining chainedTable = new Chaining(n , p , hashingCol);

            for (int i = 0; i < inputData.size(); i++) {
                chainedTable.add(inputData.get(i));
            }

            // Deleting:
            while (chainedTable.delete(args[6]) != null){
                chainedTable.delete(args[6]);
            }

            // Writing:

            chainedTable.output(outputFileName);

        }else {
            throw new IllegalArgumentException("Invalid collision resolution");
        }


        /*
                Testing:

        File a = new File("A.CSV");
        File b = new File("B.CSV");
        File c = new File("C.CSV");
        File d = new File("D.CSV");

        Time time = new Time();


            EXP A:

        ArrayList<Node> fileA = input(a);
        //System.out.println("Reading time: " + time.elapsedTime());

        // Chaining:
        Chaining hashChaining = new Chaining(input(a).size() , 7 , 1);
        for (int i = 0; i < input(a).size(); i++) {
            hashChaining.add(fileA.get(i));
        }
        //hashChaining.print();

        //time = new Time();
        //output();
        //System.out.println("Writing time: " + time.elapsedTime());


        // Propping:
        Probing prop = new Probing(input(a).size() , 7 , 1);
        for (int i = 0; i < input(a).size(); i++) {
            prop.add(fileA.get(i));
        }

        time = new Time();
        Node delN = prop.deleteConName("Zimbabwe");
        System.out.println("del time in nano: " + time.elapsedTimeNano());

        System.out.println(delN.toString());
        prop.print();


         */




    }

    public static ArrayList input(File file) throws IOException { // Returns ArrayList of nodes containing file data
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = " ";
        String[] input;
        ArrayList<Node> nodes = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            input = line.split(",");
            Node node = new Node(input[0], input[1], Integer.parseInt(input[2]), Double.parseDouble(input[3]));
            nodes.add(node);
        }
        return nodes;
    }
}
