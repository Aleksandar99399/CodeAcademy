package cycles;

import java.util.Scanner;

public class PrintTriangle1
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());

    for (int i = num; i >= 1 ; i--) {
      for (int j = 1; j <= i; j++) {
        System.out.print(j + " ");

      }
      System.out.println();
    }
  }
}
