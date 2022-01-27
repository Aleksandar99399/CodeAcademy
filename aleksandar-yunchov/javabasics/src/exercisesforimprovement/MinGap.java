package exercisesforimprovement;

import java.util.Scanner;

public class MinGap
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int count = Integer.parseInt(scanner.nextLine());

    int[] array = new int[count];

    for (int i = 0; i < count; i++) {
      array[i] = Integer.parseInt(scanner.nextLine());
    }

    int sum = computeMinGap(array);
    System.out.println(sum);
  }

  public static int computeMinGap(int[] arr){
    int sum = Integer.MAX_VALUE;

    for (int i = arr.length-1; i >=1 ; i--) {
      int diff = arr[i] - arr[i-1];
      if (sum >diff){
        sum = diff;
      }
    }
    return sum;
  }
}
