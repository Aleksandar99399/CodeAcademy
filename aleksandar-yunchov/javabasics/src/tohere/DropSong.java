package tohere;

import java.util.Scanner;

public class DropSong
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num;

    while (true){
      num = Integer.parseInt(scanner.nextLine());

      if (num == 0){
        System.out.println("Пльок Пляс Пльос");
        break;
      }


      if (num % 3 == 0){
        System.out.print("Пльок ");
      }
      if (num % 5 == 0){
        System.out.print("Пляс ");
      }
      if (num % 7 == 0){
        System.out.print("Пльос ");
      }

      if (num % 3 != 0 && num % 5 != 0 && num % 7 != 0){
        System.out.print(num);
      }
      System.out.println();
    }
  }
}
