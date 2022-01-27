package tohere;

import java.util.Scanner;

public class CharFrequency
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    String sentence = scanner.nextLine();
    String symb = scanner.nextLine();
    int counter = 0;

    for (int i = 0; i < sentence.length(); i++) {
      char reserve = symb.charAt(0);
      if (reserve == sentence.charAt(i)){
        counter++;
      }
    }
    System.out.println(counter);
  }
}
