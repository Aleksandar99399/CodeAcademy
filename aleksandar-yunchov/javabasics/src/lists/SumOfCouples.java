package lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumOfCouples
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());
    List<Integer> list = new ArrayList<>();

    while (num != 0){
      list.add(num);

      num = Integer.parseInt(scanner.nextLine());
    }

    int sum = Integer.parseInt(scanner.nextLine());

    for (int i = 0; i < list.size(); i++) {
      for (int j = i+1; j < list.size(); j++) {
        if (list.get(i) + list.get(j) == sum){
          System.out.printf("%d %d%n", list.get(i), list.get(j));
        }
      }

      list.remove(list.get(i));
      i--;
    }

  }
}
