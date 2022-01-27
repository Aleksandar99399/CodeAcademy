package inputoutputdata;

import java.util.Scanner;

public class OddOrEven
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    double num = Double.parseDouble(scanner.nextLine());

    String evenOrOdd = num % 2 == 0 ? "even" : "odd";

    System.out.println(evenOrOdd);


  }
}
