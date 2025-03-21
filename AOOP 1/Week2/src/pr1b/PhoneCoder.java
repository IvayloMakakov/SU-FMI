package pr1b;

public class PhoneCoder {
    private int key;

    public PhoneCoder() {
        this(1);//def value
    }

    public PhoneCoder(int key) {
        setKey(key);
    }

    public double getKey() {
        return key;
    }

    public void setKey(int key) {
        if (key < 1 || key > 9)
            System.out.println("Invalid key value, must be [1,9]");
        else this.key = key;
    }

    public String encode(String phoneNumber) {
        int digit1 = 0, digit2 = 0, digit3 = 0, digit4 = 0;

        if (phoneNumber.length() == 3)
            phoneNumber = "0" + phoneNumber;
        else if (phoneNumber.length() == 1)
            phoneNumber += "000";

        digit1 = phoneNumber.charAt(0) - '0';
        digit2 = phoneNumber.charAt(1) - '0';
        digit3 = phoneNumber.charAt(2) - '0';
        digit4 = phoneNumber.charAt(3) - '0';

        digit1 = (digit1 + this.key) % 10;
        digit2 = (digit2 + this.key) % 10;
        digit3 = (digit3 + this.key) % 10;
        digit4 = (digit4 + this.key) % 10;

        digit1 ^= digit3;
        digit3 ^= digit1;
        digit1 ^= digit3;

        digit2 ^= digit4;
        digit4 ^= digit2;
        digit2 ^= digit4;

        return String.format("%s%s%s%s", digit1, digit2, digit3, digit4);
    }

    public String decode(String codedPhoneNumber) {
        int digit1 = 0, digit2 = 0, digit3 = 0, digit4 = 0;

        if (codedPhoneNumber.length() == 3)
            codedPhoneNumber = "0" + codedPhoneNumber;
        else if (codedPhoneNumber.length() == 1)
            codedPhoneNumber += "000";

        digit1 = codedPhoneNumber.charAt(0) - '0';
        digit2 = codedPhoneNumber.charAt(1) - '0';
        digit3 = codedPhoneNumber.charAt(2) - '0';
        digit4 = codedPhoneNumber.charAt(3) - '0';

        digit1 = (digit1 >= key) ? (digit1 - key) : (digit1 - key + 10);
        digit2 = (digit2 >= key) ? (digit2 - key) : (digit2 - key + 10);
        digit3 = (digit3 >= key) ? (digit3 - key) : (digit3 - key + 10);
        digit4 = (digit4 >= key) ? (digit4 - key) : (digit4 - key + 10);

        digit1 ^= digit3;
        digit3 ^= digit1;
        digit1 ^= digit3;

        digit2 ^= digit4;
        digit4 ^= digit2;
        digit2 ^= digit4;

        return String.format("%s%s%s%s", digit1, digit2, digit3, digit4);
    }

    @Override
    public String toString() {
        return "PhoneCoder : " + key;
    }
}
