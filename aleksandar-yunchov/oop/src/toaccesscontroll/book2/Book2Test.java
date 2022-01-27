package toaccesscontroll.book2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book2Test
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    String reg = "[0-9]{3}-[0-9]{3}-[0-9]{4}-[0-9]{2}";

    System.out.print("Enter 1 to create book or 2 to exit: ");
    int choice = Integer.parseInt(scanner.nextLine());
    List<Book2> listOfBooks = new ArrayList<>();

    while (choice != 2) {
      try {
        System.out.print("Author must be least 3 symbols: ");
        String author = scanner.nextLine();

        if (author.length() < 3) {
          throw new Exception("Incorrect count symbols for Author!");
        }
        System.out.print("Enter correct ISBN. Model: 978-954-7992-12");
        String isbn = scanner.nextLine();

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(isbn);
        if (!matcher.matches()) {
          throw new Exception("Incorrect count symbols for ISBN!");
        }
        System.out.print("Page must be least 120 pages: ");
        long pages = Long.parseLong(scanner.nextLine());

        if (pages < 120) {
          throw new Exception("Incorrect count pages!");
        }
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Publisher must be least 3 symbols: ");
        String publisher = scanner.nextLine();
        if (publisher.length()<3 ){
          throw new Exception("Incorrect count symbols for publisher!");
        }
        System.out.print("Title must be least 3 symbols: ");
        String title = scanner.nextLine();

        if (title.length()<3 ){
          throw new Exception("Incorrect count symbols for title!");
        }

        Book2 book2 = new Book2(title, author, isbn, pages, publisher, price);
        listOfBooks.add(book2);
      }
      catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Try again!!!");
      }


      System.out.print("Enter 1 to create book or 2 to exit: ");
      choice = Integer.parseInt(scanner.nextLine());
    }

    System.out.println("Count of all books: "+Book2.COUNT_BOOKS);

    for (Book2 listOfBook : listOfBooks) {
      System.out.println(listOfBook.toString());
    }

  }

}
