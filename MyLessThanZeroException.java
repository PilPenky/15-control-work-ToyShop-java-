public class MyLessThanZeroException extends IllegalArgumentException {
    public MyLessThanZeroException(String s, Double weight) {
        super(s + weight);
    }
}