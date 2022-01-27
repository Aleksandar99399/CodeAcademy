package cycles;

import java.util.Scanner;

public class Factorial
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());

    while (num <= 0){
      num = Integer.parseInt(scanner.nextLine());
    }

    int sum = 1;
    for (int i = 1; i <= num ; i++) {
      sum = i * sum;
    }

    System.out.println(sum);
  }
}
