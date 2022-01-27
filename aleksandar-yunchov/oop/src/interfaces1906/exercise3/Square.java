package interfaces1906.exercise3;

public class Square extends Shape
{
  private double side;

  public Square(double side)
  {
    this.side = side;
  }

  public double getSide()
  {
    return side;
  }

  public void setSide(double side)
  {
    this.side = side;
  }

  @Override
  public double getArea()
  {
    return Math.pow(this.side,2);
  }

  @Override
  public double getPerimeter()
  {
    return 4 * this.side;
  }
}
