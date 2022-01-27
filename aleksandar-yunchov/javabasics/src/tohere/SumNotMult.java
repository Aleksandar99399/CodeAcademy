package tohere;

import java.util.Scanner;

public class SumNotMult
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int x = Integer.parseInt(scanner.nextLine());
    int y = Integer.parseInt(scanner.nextLine());

    int sum = 0;

    for (int i = 0; i < y; i++) {
      sum += x;
    }

    System.out.println(sum);
  }
}
