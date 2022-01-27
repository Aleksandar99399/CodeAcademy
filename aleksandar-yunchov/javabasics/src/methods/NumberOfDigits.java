package methods;

import java.util.Scanner;

public class NumberOfDigits
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());

    int count = countOfDigit(num);
    System.out.println(count);
  }

  public static int countOfDigit(int num)
  {
    int count = 0;

    while (num > 0){
      num /= 10;
      count++;
    }
    return count;
  }
}
