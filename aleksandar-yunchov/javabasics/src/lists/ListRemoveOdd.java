package lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ListRemoveOdd
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    List<Integer> list = new ArrayList<>(Arrays.asList(1, 4, 6, 2, 10, 5));

    for (int i = list.size()-1; i >= 0; i--) {
      if (i % 2 == 0){
        list.remove(list.get(i));
      }
    }
    System.out.println(list);

  }
}
