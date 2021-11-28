package DSProject2;

import java.io.*;

public class Node{
     String conName;
     String conCode;
     int year;
     double value;

     public Node(String conName, String conCode, int year, double value) {
          this.conName = conName;
          this.conCode = conCode;
          this.year = year;
          this.value = value;
     }

     @Override
     public String toString() {
          return "Node{" +
                  "conName='" + conName + '\'' +
                  ", conCode='" + conCode + '\'' +
                  ", year=" + year +
                  ", value=" + value +
                  '}';
     }
}
