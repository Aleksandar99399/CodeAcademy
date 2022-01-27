package interfaces1906.exercise3;

public class Circle extends Shape
{
  private double r;

  public Circle(double r)
  {
    this.r = r;
  }

  public double getR()
  {
    return r;
  }

  public void setR(double r)
  {
    this.r = r;
  }

  @Override
  public double getArea()
  {
    return Math.PI * Math.pow(this.r,2);
  }

  @Override
  public double getPerimeter()
  {
    return 2 * Math.PI * this.r;
  }
}
