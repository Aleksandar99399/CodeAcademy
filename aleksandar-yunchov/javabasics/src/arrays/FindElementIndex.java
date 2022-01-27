package arrays;

import java.util.Scanner;

public class FindElementIndex
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());
    int[] myArray = { 1, 4, 6, 2, 10, 5 };
    int index = -1;


    for (int i = 0; i < myArray.length; i++) {
      if (myArray[i] == num){
        index = i;
      }
    }

    System.out.println(index);

  }
}
