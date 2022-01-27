package conditionalchecks;

import java.util.Scanner;

public class NumberOfWords
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    String sentence = scanner.nextLine();

    if (sentence.split(" ").length == 1){
      System.out.println("една");
    }else if (sentence.split(" ").length == 2){
      System.out.println("две");
    }else if (sentence.split(" ").length == 3){
      System.out.println("три");
    }else if (sentence.split(" ").length == 4){
      System.out.println("четири");
    }else if (sentence.split(" ").length == 5){
      System.out.println("пет");
    }else if (sentence.split(" ").length == 6){
      System.out.println("шест");
    }else if (sentence.split(" ").length == 7){
      System.out.println("седем");
    }else if (sentence.split(" ").length == 8){
      System.out.println("осем");
    }else if (sentence.split(" ").length == 9){
      System.out.println("девет");
    }else if (sentence.split(" ").length == 10){
      System.out.println("десет");
    }else {
      System.out.println("твърде дълго изречение");
    }
  }
}
