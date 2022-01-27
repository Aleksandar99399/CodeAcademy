package homework2206.exercise2;

public class Rectangle implements GeometricObject
{
  private double length;
  private double width;

  public Rectangle(double length, double width){
    this.length = length;
    this.width = width;
  }

  @Override
  public double getArea()
  {
    return this.length * this.width;
  }

  @Override
  public double getPerimeter()
  {
    return 2 * this.length + 2 * this.width;
  }

  @Override
  public String toString()
  {
    return "Rectangle{" +
        "length=" + length +
        ", width=" + width +
        '}';
  }
}
