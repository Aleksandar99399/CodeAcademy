package lists;

import java.util.*;

public class Print5th
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    String name = scanner.nextLine();
    int index = 2;
    List<String> list = new ArrayList<>();

    while (!name.equals(".")){
      list.add(name);
      name = scanner.nextLine();
    }

    if (index > list.size()-1){
      System.out.println("Invalid index!");
    }else {
      System.out.println(list.get(index));
    }

    Collections.sort(list);

    System.out.println(list);

  }
}
