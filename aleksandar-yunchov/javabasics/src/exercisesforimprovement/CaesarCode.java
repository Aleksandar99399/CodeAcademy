package exercisesforimprovement;

import java.util.Scanner;

public class CaesarCode
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Въведете криптиране (1) или декриптиране (2): ");
    int cryptOrDecrypt = Integer.parseInt(scanner.nextLine());
    System.out.print("Въведете код (n): ");
    int n = Integer.parseInt(scanner.nextLine());
    if (cryptOrDecrypt == 1) {
      System.out.print("Въведете текст за криптиране: ");
      String text = scanner.nextLine();
      String cryptText = crypt(text, n);
      System.out.println(cryptText);
    }else {
      System.out.print("Въведете текст за декриптиране: ");
      String text = scanner.nextLine();
      String decryptText = deCrypt(text, n);
      System.out.println(decryptText);
    }


  }
  public static String  deCrypt(String text, int n){
    StringBuilder builder = new StringBuilder();
    char[] arr = text.toUpperCase().toCharArray();

    for(char symb : arr){
      if (symb<= 'Z' && symb >= 'A'){
        symb = (char) (symb - n);
        if (symb < 'A'){
          symb = (char) (91 - ('A' - symb));
        }
      }
      builder.append(symb);
    }
    return builder.toString();
  }
  public static String  crypt(String text, int n){
    StringBuilder builder = new StringBuilder();
    char[] arr = text.toUpperCase().toCharArray();

    for(char symb : arr){
      if (symb<= 'Z' && symb >= 'A'){
        symb = (char) (symb + n);
        if (symb > 'Z'){
          symb = (char) (64 + (symb - 'Z'));
        }
      }
      builder.append(symb);
    }
    return builder.toString();
  }
}
