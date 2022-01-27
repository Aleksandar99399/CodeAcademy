package cycles;

import java.util.Scanner;

public class ReverseNumber
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());
    
    num = Math.abs(num);
    String reserve = String.valueOf(num);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < reserve.length(); i++) {
      int digit = num % 10;
      builder.append(digit);
      num /= 10;
    }

    System.out.println(builder.toString());
  }
}
