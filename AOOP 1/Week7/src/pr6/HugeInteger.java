package pr6;

import java.util.Arrays;

public class HugeInteger {
    private int[] integer;

    public HugeInteger(int[] integer) {
        this.integer = integer;
    }

    public HugeInteger() {
        this(new int[40]);
    }

    public HugeInteger(HugeInteger hugeInteger) {
        this(hugeInteger.getInteger());
    }

    public int[] getInteger() {
        int[] result = new int[this.integer.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = this.integer[i];
        }

        return result;
    }

    public void setInteger(int[] integer) {
        this.integer = new int[40];

        if (integer != null) {
            for (int i = 0; i < integer.length; i++) {
                this.integer[i] = integer[i];
            }
        }
    }

    public boolean isEqualTo(HugeInteger hugeInteger) {
        return this.toString().equals(hugeInteger.toString());
    }

    public boolean isNotEqualTo(HugeInteger hugeInteger) {
        return !isEqualTo(hugeInteger);
    }

    public boolean isGreaterThan(HugeInteger hugeInteger) {
        return this.toString().compareTo(hugeInteger.toString()) > 0;
    }

    public boolean isLessThan(HugeInteger hugeInteger) {
        return this.toString().compareTo(hugeInteger.toString()) < 0;
    }

    public boolean isGreaterThanOrEqualTo(HugeInteger hugeInteger) {
        return this.toString().compareTo(hugeInteger.toString()) >= 0;
    }

    public boolean isLessThanOrEqualTo(HugeInteger hugeInteger) {
        return this.toString().compareTo(hugeInteger.toString()) <= 0;
    }

    public boolean isZero() {
        return !this.toString().contains("0");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(var num: this.integer) {
            sb.append(num);
        }

        return sb.toString();
    }
}