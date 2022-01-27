package homework0607;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise4
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      numbers.add(Integer.parseInt(scanner.nextLine()));
    }

    for (int i = 0; i < numbers.size() - 1; i++) {
      for (int j = i+1; j < numbers.size(); j++) {
        int max = Integer.MIN_VALUE;
        for (int k = i; k <= j ; k++) {
          if (max <= numbers.get(k)){
            max = numbers.get(k);
          }
        }
        if (i < j){
          if (numbers.get(i) * numbers.get(j) <= max){
            System.out.println((i+1) + " " + (j+1));
          }

        }
      }
    }

  }
}
