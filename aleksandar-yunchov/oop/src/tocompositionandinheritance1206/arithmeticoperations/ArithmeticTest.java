package tocompositionandinheritance1206.arithmeticoperations;

import java.util.Random;
import java.util.Scanner;

public class ArithmeticTest
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    Arithmetic arithmetic = new Adder();
    Random random = new Random();
    int n = random.nextInt(20 - 1) + 2;

    int[] numbers = new int[n];
    for (int i = 0; i < n; i++) {
      int num = Integer.parseInt(scanner.nextLine());
      numbers[i] = num;
    }
    System.out.println(arithmetic.add(numbers));

  }
}
