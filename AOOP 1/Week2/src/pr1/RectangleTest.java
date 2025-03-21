package pr1;

public class RectangleTest {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle();
        Rectangle rectangle2 = new Rectangle(5, 6);

        System.out.println(Rectangle.getColor());

        rectangle1.setWidth(6.3);

        System.out.printf("Area: %s%nPerimeter: %s%n", rectangle1.getArea(), rectangle1.getPerimeter());
        System.out.printf("Area: %s%nPerimeter: %s%n", rectangle2.getArea(), rectangle2.getPerimeter());
    }
}
