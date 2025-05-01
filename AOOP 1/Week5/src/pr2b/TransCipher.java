package pr2b;

public class TransCipher {
    private String plainText;
    private String cipherKey;

    public TransCipher(String plainText, String cipherKey) {
        setPlainText(plainText);
        setCipherKey(cipherKey);
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public void setCipherKey(String cipherKey) {
        this.cipherKey = cipherKey;
    }

    public String encrypt() {
        //assume cipherKey.length() is greater than 0
        int cipherKeyLength = cipherKey.length();

        char[] plainTextChars = plainText.toCharArray();

        int countRows = plainText.length() / cipherKeyLength;

        char[][] matrix = new char[countRows][cipherKeyLength];

        int idx = 0;
        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < cipherKeyLength; j++) {
                if (idx < plainTextChars.length) {
                    matrix[i][j] = plainTextChars[idx++];
                } else {
                    matrix[i][j] = ' ';
                }
            }
        }

        char[] result = new char[idx];

        idx = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[idx++] = matrix[j][i];
            }
        }

        return new String(result);
    }

    public String decrypt(String encryptedText) {
        int cipherKeyLength = cipherKey.length();
        char[] encryptedTextChars = encryptedText.toCharArray();

        int countRows = encryptedText.length() / cipherKeyLength;

        char[][] matrix = new char[countRows][cipherKeyLength];

        int idx = 0;

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = encryptedTextChars[idx++];
            }
        }

        char[] result = new char[idx];

        idx = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[idx++] = matrix[i][j];
            }
        }

        return new String(result);
    }
}
