package homework2206.exercise2;

public class ResizableCircle extends Circle implements Resizable
{
  public ResizableCircle(double r)
  {
    super(r);
  }

  @Override
  public void resize(int percentage)
  {
    super.setRadius(super.getRadius() + getRadius() * (percentage/100.0));
  }
}
