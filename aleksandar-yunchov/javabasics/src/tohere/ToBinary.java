package tohere;

import java.util.Scanner;

public class ToBinary
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());

    String toBinary = Integer.toBinaryString(num);

    StringBuilder builder = new StringBuilder(toBinary);
    builder = builder.reverse();

    System.out.println(builder);
  }
}
