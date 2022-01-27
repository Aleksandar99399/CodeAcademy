package tohere;

import java.util.Scanner;

public class FibonacciSeries
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());
    int first = 0;
    int second = 1;

    System.out.print(first + " " + second);
    for (int i = 2; i < num ; i++) {
      int sum = first + second;
      System.out.printf(" %d",sum);
      first = second;
      second = sum;
    }
  }
}
