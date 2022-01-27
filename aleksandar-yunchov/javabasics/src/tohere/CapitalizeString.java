package tohere;

import java.util.Scanner;

public class CapitalizeString
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    StringBuilder sentence = new StringBuilder(scanner.nextLine());

    char symb = sentence.charAt(0);
    char lastSymb = sentence.charAt(sentence.length()-1);

    if (symb >= 97 && symb <= 122){
      sentence = sentence.replace(0,1,String.valueOf(symb).toUpperCase());
    }
    if (lastSymb != '!' && lastSymb != '?' && lastSymb != '.'){
      sentence.append(".");
    }
    System.out.println(sentence);
  }
}
