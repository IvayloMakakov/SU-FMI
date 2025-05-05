package pr6;

public class HugeIntegerTest {
    public static void main(String[] args) {
        HugeInteger h = new HugeInteger(new int[]{123, 456, 789, 101112});

        System.out.println(h);

        System.out.println(h.isZero());

        System.out.println(h.isEqualTo(new HugeInteger(new int[]{123, 456, 789, 101112})));

        System.out.println(h.isGreaterThan(new HugeInteger(new int[]{123})));

        System.out.println(h.isLessThan(new HugeInteger(new int[]{123})));

        System.out.println(h.isGreaterThanOrEqualTo(new HugeInteger(new int[]{123, 8978797, 545415, 454, 545415, 456514})));
    }
}