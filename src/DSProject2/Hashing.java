package DSProject2;

public class Hashing {

    private static int p;

    public static int intHash(int value){
        // n % p.

        return (value % p);

    }
    public static int stringHash(String str){
        char[] s = str.toCharArray();
        double hashedIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            hashedIndex += getAsciiIndex(s[i]) * Math.pow(31, s.length - 1 - i); // p = 383
        }
        hashedIndex = hashedIndex % p;
        return (int) hashedIndex;
    }


    // AUX:
    public static int getAsciiIndex(char c) {
        return c;
    }
}
