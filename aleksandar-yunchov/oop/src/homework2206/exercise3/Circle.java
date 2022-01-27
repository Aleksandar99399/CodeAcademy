package homework2206.exercise3;

public class Circle implements Movable
{
  private double       radius;
  private MovablePoint center;

  public Circle(double radius, MovablePoint center)
  {
    this.radius = radius;
    this.center = center;
  }

  @Override
  public void moveUp()
  {
    this.center.setY(this.center.getY() - this.center.getySpeed());

  }

  @Override
  public void moveDown()
  {
    this.center.setY(this.center.getY() + this.center.getySpeed());
  }

  @Override
  public void moveLeft()
  {
    this.center.setX(this.center.getX() - this.center.getxSpeed());
  }

  @Override
  public void moveRight()
  {
    this.center.setX(this.center.getX() + this.center.getxSpeed());
  }

  public void circle_equation(double xPoint, double yPoint, double radius){
    double a = -2 * xPoint;

    double b = -2 * yPoint;

    double c = (radius * radius) - (xPoint * xPoint) - (yPoint * yPoint);

    // Printing result
    System.out.print("x^2 + (" +a+ " x) + ");
    System.out.print("y^2 + ("+b + " y) = ");
    System.out.println(c +"." );
  }

}
