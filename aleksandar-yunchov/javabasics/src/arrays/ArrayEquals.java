package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayEquals
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int[] arr = { 1, 10, 15 };
    int[] compare = new int[3];
    for (int i = 0; i < 3; i++) {
      compare[i]=Integer.parseInt(scanner.nextLine());
    }

    Arrays.sort(compare);
    Arrays.sort(arr);

    if (Arrays.equals(compare, arr)){
      System.out.println("true");
    }else {
      System.out.println("false");
    }
  }
}
