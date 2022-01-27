package toaccesscontroll.triangle;

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

    if (aSide <= 0) {
      aSide = validData(scanner);
    }
    else if (bSide <= 0) {
      bSide = validData(scanner);
    }
    else if (gammaAngle <= 0) {
      gammaAngle = validData(scanner);
    }

    Triangle triangle = new Triangle(aSide, bSide, gammaAngle);
    triangle.findSideC();
    triangle.findAlpha();
    triangle.findBeta();
    triangle.perimeterOfTriangle();
    triangle.areaOfTriangle();
    System.out.println(triangle.toString());
  }

  private static double validData(Scanner scanner)
  {
    double data;
    System.out.println("GREATER THAN 0");
    data = Double.parseDouble(scanner.nextLine());
    return data;
  }

}
