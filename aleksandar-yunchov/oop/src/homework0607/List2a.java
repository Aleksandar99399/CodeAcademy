package homework0607;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class List2a
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    List<Integer> firstList = getIntegers(scanner);
    List<Integer> secondList = getIntegers(scanner);
    firstList.addAll(secondList);
    System.out.println(firstList);

  }

  private static List<Integer> getIntegers(Scanner scanner)
  {
    List<Integer> secondList = new LinkedList<>();
    int size = Integer.parseInt(scanner.nextLine());

    for (int i = 0; i < size; i++) {
      secondList.add(Integer.parseInt(scanner.nextLine()));
    }
    return secondList;
  }
}
