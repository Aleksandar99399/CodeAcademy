package lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListSum
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());

    List<Integer> list = new ArrayList<>();

    while (num != 0 ){
      list.add(num);
      num = Integer.parseInt(scanner.nextLine());
    }

    int sum = 0;

    for (Integer aDouble : list) {
      sum+=aDouble;
    }

    System.out.println(sum);

  }
}
