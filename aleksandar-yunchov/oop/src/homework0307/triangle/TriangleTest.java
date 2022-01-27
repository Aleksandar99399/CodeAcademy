package homework0307.triangle;

import toaccesscontroll.triangle.Triangle;

import java.util.Scanner;

public class TriangleTest
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter valid data. All greater than 0");
    System.out.print("Enter A side: ");
    double aSide = Double.parseDouble(scanner.nextLine());
    System.out.print("Enter B side: ");
    double bSide = Double.parseDouble(scanner.nextLine());
    System.out.print("Enter Gamma angle: ");
    double gammaAngle = Double.parseDouble(scanner.nextLine());

    validData(scanner, aSide, bSide, gammaAngle);

    toaccesscontroll.triangle.Triangle triangle = new Triangle(aSide, bSide, gammaAngle);
    triangle.findSideC();
    triangle.findAlpha();
    triangle.findBeta();
    triangle.perimeterOfTriangle();
    triangle.areaOfTriangle();
    System.out.println(triangle.toString());
  }


  private static void validData(Scanner scanner, double... validData)
  {

    for (int i = 0; i < validData.length; i++) {
      try {
        if (validData[i] <= 0) {

          throw new IllegalArgumentException("Invalid side or angle.");
        }
      }
      catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
        System.out.print("Enter valid data greater than 0: ");
        validData[i] = Double.parseDouble(scanner.nextLine());
      }
    }
  }

}
