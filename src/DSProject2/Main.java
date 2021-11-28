package DSProject2;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        File a = new File("A.CSV");
        File b = new File("B.CSV");
        File c = new File("C.CSV");
        File d = new File("D.CSV");
        ArrayList<Node> fileA = input(a);
        //ArrayList<Node> fileB =input(b);
        //ArrayList<Node> fileC =input(c);
        //ArrayList<Node> fileD =input(d);

        Chaining hashChaining = new Chaining(input(a).size());
        for (int i = 0; i < input(a).size(); i++) {
        hashChaining.addConCodeHash(fileA.get(i));
        }
        hashChaining.print();
        output(hashChaining);
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


    public static void output(Chaining table) throws FileNotFoundException {
        File outCSV = new File("outCSV.csv");
        PrintWriter pw = new PrintWriter(outCSV);

        for (int i = 0; i < table.chainedTable.length; i++) {
            Iterator iter = table.chainedTable[i].iterator();
            while (iter.hasNext()){
                Node node = (Node) iter.next();
                pw.printf("%s, %s ,%d, %f" , node.conName , node.conCode , node.year , node.value);
                pw.println();
            }
        }
        pw.close();
    }
}
