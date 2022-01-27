package methods;

import java.util.Scanner;

public class SumNaturalNumbers
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int number = Integer.parseInt(scanner.nextLine());

    System.out.println(naturalNumbers(number));
  }

  public static int naturalNumbers(int num)
  {
    if (num > 0) {
      return num + naturalNumbers(num - 1);
    }
    return 0;

  }
}
