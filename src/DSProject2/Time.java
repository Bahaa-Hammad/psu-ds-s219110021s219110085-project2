package DSProject2;

public class Time {

        private final long start = System.currentTimeMillis();

    public double elapsedTime() {
            long now = System.currentTimeMillis();
            return (now - start) / 1000.0; // To convert from millie to seconds
        }


    private final long startNano = System.nanoTime();

    public double elapsedTimeNano() {
        long now = System.nanoTime();
        return (now - startNano) / 1000.0;
    }

}
