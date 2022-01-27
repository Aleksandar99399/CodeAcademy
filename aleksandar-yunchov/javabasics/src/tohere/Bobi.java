package tohere;


import java.util.Scanner;

public class Bobi
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    String sentence = scanner.nextLine();

    while (!sentence.equals(".")) {

      if (sentence.equals("")) {
        System.out.println("Хубаво деее");

      }
      else if (sentence.toUpperCase().equals(sentence)) {

        if (sentence.charAt(sentence.length() - 1) == '?') {
          System.out.println("Спокоооо, знам к'во правя!");

        }
        else {
          System.out.println("Споко бе, ман!");
        }

      }
      else if (!sentence.toUpperCase().equals(sentence) && sentence.charAt(sentence.length() - 1) == '?') {
        System.out.println("Добре.");

      }
      else {
        System.out.println("Ахъ.");
      }

      sentence = scanner.nextLine();
    }
  }
}
