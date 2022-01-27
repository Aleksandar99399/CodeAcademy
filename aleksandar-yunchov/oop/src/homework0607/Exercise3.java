package homework0607;

import java.util.*;

public class Exercise3
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    System.out.println("First list:");
    ArrayList<Integer> listOne = createList(scanner);
    System.out.println("Second list:");
    ArrayList<Integer> listTwo = createList(scanner);

    Collections.sort(listOne);
    Collections.sort(listTwo);
    System.out.println("First list: " + listOne);
    System.out.println("Second list: " + listTwo);

    ArrayList<Integer> listThree = new ArrayList<>();
    listThree.addAll(listOne);
    listThree.addAll(listTwo);

    System.out.println("Third list: " + listThree);

    Set<Integer> mySet = new HashSet<>();
    mySet.addAll(listThree);

    System.out.println("Final: " + mySet);

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
