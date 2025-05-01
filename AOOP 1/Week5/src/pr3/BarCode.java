package pr3;

public class BarCode {
    //switched last row to be the first
    private static final byte[][] code =
            {
                    {1, 1, 0, 0, 0},
                    {0, 0, 0, 1, 1},
                    {0, 0, 1, 0, 1},
                    {0, 0, 1, 1, 0},
                    {0, 1, 0, 0, 1},
                    {0, 1, 0, 1, 0},
                    {0, 1, 1, 0, 0},
                    {1, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0},
                    {1, 0, 1, 0, 0},
            };

    public String decode(short zipCode) {
        String result = "";

        String zipCodeStr = String.format("%03d", zipCode);

        for (char ch : zipCodeStr.toCharArray()) {
            byte currDigit = (byte) (ch - '0');
            for (byte num : code[currDigit]) {
                result += (num == 0) ? ':' : '|';
            }
        }

        return result;
    }
}
