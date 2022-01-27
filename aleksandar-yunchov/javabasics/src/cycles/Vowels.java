package cycles;

import java.util.Scanner;

public class Vowels
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    String sentence = scanner.nextLine();
    sentence = sentence.toLowerCase();
    int sum = 0;

    for (int i = 0; i < sentence.length(); i++) {
      char symb = sentence.charAt(i);

      if (symb == 'a' || symb == 'e' || symb=='o' || symb == 'y' || symb == 'u' || symb == 'i'){
        sum++;
      }
    }

    System.out.println(sum);
  }
}
