package cycles;

import java.util.Scanner;

public class DrawSquare
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());

    for (int i = 0; i < num; i++) {
      for (int j = 0; j < num; j++) {
        System.out.print("# ");
      }
      System.out.println();
    }
  }
}
