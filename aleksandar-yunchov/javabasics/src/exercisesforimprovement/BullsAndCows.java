package exercisesforimprovement;

import java.util.Scanner;

public class BullsAndCows
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Тайно число: ");
    String secretNum = scanner.nextLine();
    int[] secretArr = new int[4];
    char[] secret = secretNum.toCharArray();



    while (true){
      System.out.print("-> ");
      String choiceNum = scanner.nextLine();
      char[] choice = choiceNum.toCharArray();

      int bulls = 0;
      int cows = 0;

      if (choice.length != 4){
        System.out.println("Невалиден опит!");
        continue;
      }

      if (secretNum.equals(choiceNum)){
        System.out.println("4 бикове, 0 крави");
        break;
      }

      for (int i = 0; i < secret.length; i++) {
        for (int j = 0; j < choice.length; j++) {
          if (secret[i] == choice[j] && i == j){
            bulls++;
          }else if (secret[i] == choice[j]){
            cows++;
          }
        }
      }

      System.out.printf("%d бикове, %d крави%n",bulls, cows);
    }

  }
}
