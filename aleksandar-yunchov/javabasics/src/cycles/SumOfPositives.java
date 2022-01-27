package cycles;

import java.util.Scanner;

public class SumOfPositives
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);


    double num;
    double sum = 0;
    double min = Integer.MAX_VALUE;
    double max = Integer.MIN_VALUE;

    while (true) {
      System.out.print("Въведете положително число: ");
      num = scanner.nextDouble();
      if (num <= 0) {
          break;
      }
      sum += num;
      if (num < min) {
        min = num;
      }

      if (num > max) {
        max = num;
      }


    }
    System.out.printf("%.2f \n", sum);
    System.out.println(max);
    System.out.println(min);
  }
}
