package homework0607;

import java.util.*;

public class Set2b
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    System.out.println("First set: ");
    Set<Integer> setOne = createSet(scanner);
    System.out.println("Second set: ");
    Set<Integer> setTwo = createSet(scanner);

    //obedinenie
    Set<Integer> copy = new HashSet<>();
    copy.addAll(setOne);
    copy.addAll(setTwo);

    System.out.println("Обединение: " + copy);

    //sechenie

    setOne.retainAll(setTwo);
    System.out.println("Сечение: " + setOne);

  }

  private static Set<Integer> createSet(Scanner scanner)
  {
    int n = scanner.nextInt();
    Set<Integer> mySet = new HashSet<>();

    for (int i = 0; i < n; i++) {
      mySet.add(scanner.nextInt());
    }
    return mySet;
  }
}