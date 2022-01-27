package tohere;

import java.util.Scanner;

public class PrimeNumber
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());
    boolean prime = false;

    for (int i = 2; i <= num / 2; i++) {

      if (num % i == 0) {
        prime = true;
        break;
      }
    }

    if (!prime) {
      System.out.println("true");
    }else {
      System.out.println("false");
    }
  }
}
