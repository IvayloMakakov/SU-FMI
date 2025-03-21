package pr1;

public class Rectangle {
    private double width;
    private double height;
    private static String color = "yellow";

    public Rectangle() {
        this(1, 1);
    }

    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width <= 0)
            this.width = 1;
        else this.width = width;
    }

    public static String getColor() {
        return color;
    }

    public static void setColor(String color) {
        if (color != null && !color.equals(""))
            Rectangle.color = color;
        else Rectangle.color = "yellow";
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0)
            this.height = 1;
        else this.height = height;
    }

    public double getArea() {
        return (height * width);
    }

    public double getPerimeter() {
        return 2 * (height + width);
    }
}
