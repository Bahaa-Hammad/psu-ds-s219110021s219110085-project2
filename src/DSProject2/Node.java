package DSProject2;

import java.util.Arrays;

public class Node{
     String conName;
     String conCode;
     int year;
     double[] value;

     public Node(String conName, String conCode, int year, double[] value) {
          this.conName = conName;
          this.conCode = conCode;
          this.year = year;
          this.value = value;
     }

     public Node(String str){
          if (str.equals("<DEL>")){
               this.conName = "<DEL>";
               this.conCode = "<DEL>";
               this.year = 0;
               this.value = new double[6];

               for (int i = 0; i < 6; i++) {
                    this.value[i] = 0;
               }
          }
          else{
               throw new IllegalArgumentException("Argument Not Accepted");
          }
     }


     public Node(String[] input) {
          this.conName = input[0];
          this.conCode = input[1];
          this.year = Integer.parseInt(input[2]);
          this.value = new double[input.length - 3];

          int d = 3;
          for (int i = 0; i < input.length - 3 ; i++) {
               this.value[i] = Double.parseDouble(input[d]);
               d++;
          }
     }

     @Override
     public String toString() {
          return "Node{" +
                  "conName='" + conName + '\'' +
                  ", conCode='" + conCode + '\'' +
                  ", year=" + year +
                  ", value=" + Arrays.toString(this.value) +
                  '}';
     }
}
