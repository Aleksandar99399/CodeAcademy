package homework2206.exercise2;

public class Main
{
  public static void main(String[] args)
  {
    GeometricObject circle = new Circle(3.5);
    GeometricObject rectangle = new Rectangle(2.5,4);

    System.out.printf("%.2f%n",circle.getArea());
    System.out.printf("%.2f%n",circle.getPerimeter());
    System.out.printf("%.2f%n",rectangle.getArea());
    System.out.printf("%.2f%n",rectangle.getPerimeter());

    ResizableCircle circle1 = new ResizableCircle(5);

    circle1.resize(20);
    System.out.println("New radius: " + circle1.getRadius());
  }
}
