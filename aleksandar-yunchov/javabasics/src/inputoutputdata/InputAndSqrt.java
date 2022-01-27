package inputoutputdata;

import java.util.Scanner;

public class InputAndSqrt
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    double num = Double.parseDouble(scanner.nextLine());

    num = Math.sqrt(num);

    System.out.printf("%.5f", num);
  }
}
