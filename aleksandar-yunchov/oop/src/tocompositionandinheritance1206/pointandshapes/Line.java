package tocompositionandinheritance1206.pointandshapes;

public class Line
{
  private Point firstPoint;
  private Point secondPoint;

  public Line()
  {
  }

  public int getLine(Point x, Point y){
//    int a = y.getSecond() - x.getSecond();
//    int b = x.getFirst() - y.getFirst();
//    int c = a * (x.getFirst()) + b * (x.getSecond());
    return 0;
  }

  public Point getFirstPoint()
  {
    return firstPoint;
  }

  public void setFirstPoint(Point firstPoint)
  {
    this.firstPoint = firstPoint;
  }

  public Point getSecondPoint()
  {
    return secondPoint;
  }

  public void setSecondPoint(Point secondPoint)
  {
    this.secondPoint = secondPoint;
  }
}
