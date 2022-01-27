package conditionalchecks;

import java.util.Scanner;

public class BigOne
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    double firstNum = Double.parseDouble(scanner.nextLine());
    double secondNum = Double.parseDouble(scanner.nextLine());

    double max = Math.max(firstNum, secondNum);
    System.out.println(max);

  }
}
