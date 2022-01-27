package tocompositionandinheritance1206.pointandshapes;

import java.util.Scanner;

public class TestLine
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    
    Line firstLine = new Line();
    int x = scanner.nextInt();
    int y = scanner.nextInt();
    int x1 = scanner.nextInt();
    int y1 = scanner.nextInt();
    Point firstPoint = new Point(x, y);
    Point secondPoint = new Point(x1, y1);
    double a = secondPoint.getY() - firstPoint.getY();
    double b = firstPoint.getX() - secondPoint.getX();
    double c = a * (firstPoint.getX()) + b * (firstPoint.getY());
    System.out.println(c);
    firstLine.setFirstPoint(firstPoint);
    firstLine.setSecondPoint(secondPoint);
    
    Line secondLine = new Line();
    int x2 = scanner.nextInt();
    int y2 = scanner.nextInt();
    int x3 = scanner.nextInt();
    int y3 = scanner.nextInt();
    Point thirdPoint = new Point(x2, y2);
    Point fourthPoint = new Point(x3, y3);
    double a1 = fourthPoint.getY() - thirdPoint.getY();
    double b1 = thirdPoint.getX() - fourthPoint.getX();
    double c1 = a1 * (firstPoint.getX()) + b1 * (firstPoint.getY());
    System.out.println(c1);
    secondLine.setFirstPoint(thirdPoint);
    secondLine.setSecondPoint(fourthPoint);
    
    if (checkOrtho(firstPoint.getX(), firstPoint.getY(), secondPoint.getX(), secondPoint.getY(),
        thirdPoint.getX(), thirdPoint.getY(), fourthPoint.getX(), fourthPoint.getY())) {
      System.out.println("This is orthogonal.");
    }
    else {
      System.out.println("It is not orthogonal.");
    }
    if (doIntersect(firstPoint, secondPoint, thirdPoint, fourthPoint)) {
      System.out.println("Lines are intersecting.");
    }
    else {
      System.out.println("Lines aren't intersecting.");
    }
    if (parallel(a, a1, b, b1)) {
      System.out.println("Lines are parallel.");
    }
    else {
      System.out.println("Lines aren't parallel.");
    }
    if (areCoincident(a1, c, b1, c1)) {
      System.out.println("Lines are coincident.");
    }
    else {
      System.out.println("Lines aren't coincident.");
    }
  }

  static boolean areCoincident(double a, double c, double a1, double c1)
  {
    return c / a == c1 / a1;
  }

  static boolean onSegment(Point p, Point q, Point r)
  {
    if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
        q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())) {
      return true;
    }
    return false;
  }

  static int orientation(Point p, Point q, Point r)
  {
    int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
        (q.getX() - p.getX()) * (r.getY() - q.getY());
    if (val == 0) {
      return 0;
    }
    return (val > 0) ? 1 : 2;
  }

  static boolean doIntersect(Point p1, Point q1, Point p2, Point q2)
  {
    int o1 = orientation(p1, q1, p2);
    int o2 = orientation(p1, q1, q2);
    int o3 = orientation(p2, q2, p1);
    int o4 = orientation(p2, q2, q1);
    // General case
    if (o1 != o2 && o3 != o4) {
      return true;
    }
    if (o1 == 0 && onSegment(p1, p2, q1)) {
      return true;
    }
    if (o2 == 0 && onSegment(p1, q2, q1)) {
      return true;
    }
    if (o3 == 0 && onSegment(p2, p1, q2)) {
      return true;
    }
    if (o4 == 0 && onSegment(p2, q1, q2)) {
      return true;
    }
    return false;
  }

  static boolean parallel(double a1, double b1, double a2, double b2)
  {
    return (-(a1 / b1)) == (-(a2 / b2));
  }

  static boolean checkOrtho(double x1, double y1, double x2, double y2,
                            double x3, double y3, double x4, double y4)
  {
    double m1, m2;
    // Both lines have infinite slope
    if (x2 - x1 == 0 && x4 - x3 == 0) {
      return false;
    }
    // Only line 1 has infinite slope
    else if (x2 - x1 == 0) {
      m2 = (y4 - y3) / (x4 - x3);
      if (m2 == 0) {
        return true;
      }
      else {
        return false;
      }
    }
    // Only line 2 has infinite slope
    else if (x4 - x3 == 0) {
      m1 = (y2 - y1) / (x2 - x1);
      if (m1 == 0) {
        return true;
      }
      else {
        return false;
      }
    }
    else {
      // Find slopes of the lines
      m1 = (y2 - y1) / (x2 - x1);
      m2 = (y4 - y3) / (x4 - x3);
      // Check if their product is -1
      if (m1 * m2 == -1) {
        return true;
      }
      else {
        return false;
      }
    }
  }
}