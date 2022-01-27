package lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FindFirstElement
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    int num = Integer.parseInt(scanner.nextLine());
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 4, 6, 2, 10, 5));



    System.out.println(list.indexOf(num));


  }
}
