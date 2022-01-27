package toaccesscontroll.circle;

import java.util.Random;

public class Circle
{
  private double radius;
  private Color  color;

  public Circle()
  {
    this.radius = 1.0;
    this.color = getRandomColor();
  }

  public Circle(double radius, Color color)
  {
    this.radius = radius;
    this.color = color;
  }

  private Color getRandomColor()
  {
    Random random = new Random();
    int num = random.nextInt(4);

    Color[] values = Color.values();
    Color color = null;
    for (int i = 0; i < values.length; i++) {
      if (i == num) {
        color = values[i];
        break;
      }
    }
    return color;
  }

  public double getArea()
  {
    return Math.PI * (Math.pow(this.radius, 2));
  }

  public double getCircumference()
  {
    return 2 * Math.PI * this.radius;
  }

  public void makeMeMoon()
  {
    this.color = Color.YELLOW;
  }

  private double getDiameter()
  {
    return 2 * this.radius;
  }

  public double getRadius()
  {
    return radius;
  }

  public void setRadius(double radius)
  {
    this.radius = radius;
  }

  public Color getColor()
  {
    return color;
  }

  public void setColor(Color color)
  {
    this.color = color;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Кръг с радиус ").append(this.getRadius()).append(" и диаметър ").append(this.getDiameter());
    builder.append("\nПериметър: ").append(this.getCircumference());
    builder.append("\nЛице: ").append(this.getArea());
    builder.append("\nЦвят: ").append(this.getColor());

    return builder.toString();
  }
}
