package lists;

import java.util.*;

public class SortNames
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    String name1 = scanner.nextLine();
    String name2 = scanner.nextLine();
    String name3 = scanner.nextLine();

    List<String> names = new ArrayList<>(Arrays.asList(name1,name2,name3));
    Collections.sort(names);
    System.out.println(names);

  }
}
