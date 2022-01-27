package homework0607;

import java.util.*;

public class List1b
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    Set<Integer> numbers = getIntegers(scanner);

    System.out.println(numbers.size());
    System.out.println("Unsorted");
    System.out.println(numbers);
    TreeSet<Integer> integers = new TreeSet<>(numbers);
    System.out.println("Sorted");
    System.out.println(integers);

    System.out.println("Try to add element to Set");
    if (!numbers.add(Integer.parseInt(scanner.nextLine()))){
      System.out.println("Такъв запис вече съществуваи не може да бъде добавен повторно.");
    }

    System.out.println(integers.descendingSet());

    Set<Integer> secondNumbers = getIntegers(scanner);

    Iterator<Integer> iterator = numbers.iterator();
    Iterator<Integer> secondIterator = secondNumbers.iterator();

    if(numbers.size() != secondNumbers.size()){
      System.out.println("Not equals count of numbers");
      return;
    }

    for (int i = 0; i < numbers.size(); i++) {
      int firstNum = iterator.next();
      int secondNum = secondIterator.next();

      if (firstNum > secondNum){
        System.out.printf
            ("Елемент %d от списък 1 е по-голям от елемент %d от списък 2.",firstNum,secondNum);
      }else if (firstNum < secondNum){
        System.out.printf("Елемент %d от списък 1 е по-малък от елемент %d от списък 2.",firstNum,secondNum);

      }
    }
  }

  private static Set<Integer> getIntegers(Scanner scanner)
  {
    Set<Integer> numbers = new HashSet<>();
    System.out.println("Enter count of elements");
    int count = Integer.parseInt(scanner.nextLine());

    for (int i = 0; i < count; i++) {
      numbers.add(Integer.parseInt(scanner.nextLine()));
    }
    return numbers;
  }
}
