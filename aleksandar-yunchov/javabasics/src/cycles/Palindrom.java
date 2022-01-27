package cycles;

import java.util.Scanner;

public class Palindrom
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    String word = scanner.nextLine();
    word = word.toLowerCase();
    StringBuilder palindrome = new StringBuilder();

    for (int i = word.length() - 1; i >= 0; i--) {
      palindrome.append(word.charAt(i));
    }

    if (palindrome.toString().equals(word)) {
      System.out.println("true");
    }
    else {
      System.out.println("false");
    }

  }
}
