package toaccesscontroll.book1;

public class Book1Test
{
  public static void main(String[] args)
  {
    Book1 book1 = new Book1();

    printAttributes(book1);

    book1.setAuthor("Iordan Iovkov");
    book1.setIsbn("sada");
    book1.setPages(12);
    book1.setPrice(54.14);
    book1.setPublisher("Prosveta");
    book1.setTitle("The King");

    printAttributes(book1);
  }

  public static void printAttributes(Book1 book1){
    System.out.println(book1.getAuthor());
    System.out.println(book1.getPrice());
    System.out.println(book1.getPages());
    System.out.println(book1.getPublisher());
    System.out.println(book1.getIsbn());
    System.out.println(book1.getTitle());
  }
}
