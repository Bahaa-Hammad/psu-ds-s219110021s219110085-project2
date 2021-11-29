package DSProject2;

public class Time {

        private final long start = System.currentTimeMillis();

        public double elapsedTime() {
            long now = System.currentTimeMillis();
            return (now - start) / 1000.0;
        }

}
