interface Drawable {
    void draw();
}

abstract class Shape {
    String color;

    Shape(String color) {
        this.color = color;
    }

    abstract double calculateArea();

    void showColor() {
        System.out.println("The shape color is " + color);
    }
}

class Circle extends Shape implements Drawable {
    double radius;

    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle...");
    }

    @Override
    double calculateArea() {
        return 3.14 * radius * radius;
    }
}

public class Abstraction {
    public static void main(String[] args) {
        Circle myCircle = new Circle("Red", 5.0);
        myCircle.showColor();
        myCircle.draw();
        System.out.println("Area: " + myCircle.calculateArea());
    }
}