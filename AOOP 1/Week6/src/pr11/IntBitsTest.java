package pr11;

public class IntBitsTest {
    public static void main(String[] args) {
        int adapted = 63;
        IntBits bits = new IntBits(adapted);
        boolean peek = bits.get(6); // retrieve boolean at index 6
        bits.set(0, true); // set the bit at index 0 to true
        bits.set(31, false); // set the bit at index

        System.out.println(bits);
    }
}
