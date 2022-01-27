package homework0607;

import java.util.ArrayList;
import java.util.Scanner;

public class List2b
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    System.out.println("First list:");
    ArrayList<Integer> listOne = createList(scanner);
    System.out.println("Second list:");
    ArrayList<Integer> listTwo = createList(scanner);

    ArrayList<Integer> result = new ArrayList<>();
    //sechenie
    for (int i = 0; i < listOne.size(); i++) {
      if (listOne.get(i) == listTwo.get(i)) {
        result.add(listOne.get(i));
      }
    }
    //obedinenie
    for (int i = 0; i < listOne.size(); i++) {
      for (int j = listTwo.size() - 1; j >= 0; j--) {
        if (listOne.get(i) == listTwo.get(j)) {
          listTwo.remove(j);
        }
      }
    }
    listOne.addAll(listTwo);

    System.out.println("Обединение: " + listOne);
    System.out.println("Сечение: " + result);

  }

  private static ArrayList<Integer> createList(Scanner scanner)
  {
    int n = scanner.nextInt();
    ArrayList<Integer> list = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      list.add(scanner.nextInt());
    }
    return list;
  }
}
