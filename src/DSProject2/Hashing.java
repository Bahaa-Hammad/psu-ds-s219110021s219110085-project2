package DSProject2;

public class Hashing {

    public static int intHash(int value, int p){
        // n % p.

        return (value % p);

    }
    public static int stringHash(String str , int p){
        char[] s = str.toCharArray();
        double hashedIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            hashedIndex += getAsciiIndex(s[i]) * Math.pow(31, s.length - 1 - i);
        }
        hashedIndex = hashedIndex % p;
        return (int) hashedIndex;
    }

    public static int hash(Node node, int p, int col){
        if (col == 1){
            return stringHash(node.conName, p);
        }

        if (col == 2){
            return stringHash(node.conCode, p);
        }

        if (col == 3){
            return intHash(node.year, p);
        }
        throw new IllegalArgumentException("Invalid column");
    }


    // AUX:
    public static int getAsciiIndex(char c) {
        return c;
    }
}
