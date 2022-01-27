package tocompositionandinheritance1206.shapes;

public class TestShapes {

    public static void main(String[] args) {

        Rectangle rectangle = new Rectangle(2, 4);
        Square square = new Square(5);

        System.out.println(rectangle.getArea());
        System.out.println(rectangle.getPerimeter());

        System.out.println(square.getArea());
        System.out.println(square.getPerimeter());
    }
}
