package DSProject2;

public class Hashing {

    public static int intHash(int value){
        // n % p.

        return (value % 383);

    }
    public static int stringHash(String str){
        char[] s = str.toCharArray();
        double hashedIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            hashedIndex += getAsciiIndex(s[i]) * Math.pow(31, s.length - 1 - i); // p = 383
        }
        hashedIndex = hashedIndex % 383;
        return (int) hashedIndex;
    }


    // AUX:
    public static int getAsciiIndex(char c) {
        return c;
    }
}
