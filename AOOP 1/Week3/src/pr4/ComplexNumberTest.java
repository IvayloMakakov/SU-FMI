package pr4;

public class ComplexNumberTest {
    public static void main(String[] args) {
        //Done without JavaFX

        ComplexNumber complexNumber = new ComplexNumber(1.0, 2.0);
        System.out.println("Real part of sum: " + complexNumber.add(new ComplexNumber(1.0, 2.0)).getRealPart());
    }
}
