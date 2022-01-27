package lists;

import java.util.*;

public class ListEquals
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    List<Integer> compare = new ArrayList<>();

    for (int i = 0; i < 3; i++) {
      compare.add(Integer.parseInt(scanner.nextLine()));
    }

    List<Integer> list = new ArrayList<>(Arrays.asList(1, 10, 15));

    Collections.sort(list);
    Collections.sort(compare);

    if (list.equals(compare)){
      System.out.println("true");
    }else {
      System.out.println("false");
    }
  }
}
