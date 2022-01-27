package homework0607;

import java.util.*;

public class List1a
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    List<Integer> list = getIntegers(scanner);
    System.out.println(list.size());
    for (Integer integer : list) {
      System.out.print(integer + " ");
    }
    Collections.sort(list);
    Collections.reverse(list);

    List<Integer> secondList = getIntegers(scanner);

    if (list.size() != secondList.size()){
      System.out.println("Not equal size for lists");
      return;
    }

    for (int i = 0; i < list.size(); i++) {
      int i1 = secondList.get(i).compareTo(list.get(i));
      if (i1 != 0){
        System.out.printf
            ("Елемент %d от списък 1 е по-малък/по-голям от елемент %d от списък 2.",list.get(i),secondList.get(i));
      }else {
        System.out.println("Their are equals");
      }

    }
  }

  private static List<Integer> getIntegers(Scanner scanner)
  {
    List<Integer> secondList = new LinkedList<>();
    int number = Integer.parseInt(scanner.nextLine());
    for (int i = 0; i < number; i++) {
      secondList.add(Integer.parseInt(scanner.nextLine()));
    }
    return secondList;
  }
}
