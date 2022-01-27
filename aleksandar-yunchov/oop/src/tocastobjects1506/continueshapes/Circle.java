package tocastobjects1506.continueshapes;

public class Circle extends Shape
{
  private double r;

  public Circle(double r)
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

  private double getDiameter(){
    return 2 * this.r;
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
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Кръг с радиус: ").append(this.r);
    builder.append( "и диаметър: ").append(this.getDiameter());
    return builder.toString();
  }
}
