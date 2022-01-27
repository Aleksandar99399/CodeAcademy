package exercisesforimprovement;

import java.util.Scanner;

public class PrintPatterns
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    String figure = scanner.nextLine();
    int count = Integer.parseInt(scanner.nextLine());

    if (figure.equals("a")) {

      for (int j = 1; j <= count; j += 2) {
        for (int i = j; i <= count; i++) {
          System.out.print("# ");
        }
        System.out.println();
        for (int i = 0; i <= j; i++) {
          System.out.print(" ");
        }
      }
    }
    else if (figure.equals("b")) {
      int space = 1;
      for (int i = 0; i < count; i += 2) {
        for (int j = 0; j < count - space; j++) {
          System.out.print(" ");
        }
        space += 2;
        for (int j = 0; j <= i; j++) {
          System.out.print("# ");
        }
        System.out.println();
      }
    }
    else {
      int space = 1;
      for (int i = 0; i < count; i += 2) {
        for (int j = 0; j < count - space; j++) {
          System.out.print(" ");
        }
        space += 2;
        for (int j = 0; j <= i; j++) {
          System.out.print("# ");
        }
        System.out.println();
      }
      space=2;
      for (int j = 1; j < count; j += 2) {
        for (int i = 1; i <= space; i++) {
          System.out.print(" ");
        }
        for (int i = j; i <= count - 2; i++) {
          System.out.print("# ");
        }
        System.out.println();
        space+=2;

      }
    }
  }
}
