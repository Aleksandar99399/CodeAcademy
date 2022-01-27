package lists;

import java.util.Arrays;

public class Flag
{
  public static void main(String[] args)
  {

    char [] arr = new char[10];
    Arrays.fill(arr,'\u2588');

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < arr.length; j++) {
        System.out.print(arr[i]);
      }

      System.out.println();
    }

  }
}
