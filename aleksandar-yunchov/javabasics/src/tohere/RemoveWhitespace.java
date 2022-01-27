package tohere;

import java.util.Scanner;

public class RemoveWhitespace
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);


    String sentence = scanner.nextLine();
    sentence = sentence.replaceAll("\t", "").trim();
    sentence = sentence.replaceAll(" ", "").trim();

    System.out.println(sentence);

  }
}
