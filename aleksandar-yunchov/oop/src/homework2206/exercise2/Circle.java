package homework2206.exercise2;

public class Circle implements GeometricObject
{
  private double radius;

  public Circle(double r){
    this.radius = r;
  }

  @Override
  public String toString()
  {
    return "Circle{" +
        "radius=" + this.radius +
        '}';
  }

  @Override
  public double getArea()
  {
    return Math.PI * this.radius * this.radius;
  }

  @Override
  public double getPerimeter()
  {
    return 2 * Math.PI * this.radius;
  }

  public double getRadius()
  {
    return radius;
  }

  public void setRadius(double radius)
  {
    this.radius = radius;
  }
}
