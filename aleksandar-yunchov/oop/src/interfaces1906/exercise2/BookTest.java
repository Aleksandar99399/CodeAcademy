package interfaces1906.exercise2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookTest
{
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter 1 to create book or 0 to exit");
    int comm = Integer.parseInt(scanner.nextLine());
    List<MyBook> books = new ArrayList<>();

    while (comm != 0){
      System.out.print("Enter title: ");
      String title = scanner.nextLine();
      System.out.print("Enter author: ");
      String author = scanner.nextLine();
      System.out.print("Enter price: ");
      double price = Double.parseDouble(scanner.nextLine());

      MyBook myBook = new MyBook(title,author,price);
      books.add(myBook);

      System.out.println("Enter 1 to create book or 0 to exit");
      comm = Integer.parseInt(scanner.nextLine());
    }
    for (MyBook book : books) {
      book.display();
    }

  }

}
