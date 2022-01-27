package interfaces1906.exercise3;

public class ShapeTEst
{
  public static void main(String[] args)
  {
    Shape circle = new Circle(4);
    Shape square = new Square(5);

    System.out.println("Circle");
    System.out.println(circle.getArea());
    System.out.println(circle.getPerimeter());

    System.out.println("Square");
    System.out.println(square.getArea());
    System.out.println(square.getPerimeter());
  }
}
