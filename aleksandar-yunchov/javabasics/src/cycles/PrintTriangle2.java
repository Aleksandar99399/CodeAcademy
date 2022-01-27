package cycles;

import java.util.Scanner;

public class PrintTriangle2
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());


    for (int j = 1; j <= num; j++) {

      for (int k = num; k >= j; k--) {
        System.out.print("# ");
      }
      System.out.println();

      for (int i = 0; i < j && j!=5; i++) {
        System.out.print("  ");
      }

    }
  }
}
