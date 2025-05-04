package pr11;

public class IntBits {
    private int variable;

    public IntBits() {
        this(0);
    }

    public IntBits(int number) {
        this.variable = number;
    }

    public boolean get(int idx) {
        return ((this.variable >> idx) & 1) == 1;
    }

    public void set(int idx, boolean condition) {
        int mask = 1 << idx;
        if (condition) {
            variable |= mask;
        } else {
            variable &= ~mask;
        }
    }

    @Override
    public String toString() {
        return String.format("%32s", Integer.toBinaryString(variable)).replace(' ', '0');
    }
}