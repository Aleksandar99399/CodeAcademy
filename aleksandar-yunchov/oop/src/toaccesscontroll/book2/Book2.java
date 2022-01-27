package toaccesscontroll.book2;

public class Book2
{
  public static int COUNT_BOOKS = 0;
  private String title;
  private String author;
  private String isbn;
  private long pages;
  private String publisher;
  private double price;

  public Book2(String title, String author, String isbn, long pages, String publisher, double price)
  {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.pages = pages;
    this.publisher = publisher;
    this.price = price;
    COUNT_BOOKS+=1;

  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getAuthor()
  {
    return author;
  }

  public void setAuthor(String author)
  {
    this.author = author;
  }

  public String getIsbn()
  {
    return isbn;
  }

  public void setIsbn(String isbn)
  {
    this.isbn = isbn;
  }

  public long getPages()
  {
    return pages;
  }

  public void setPages(long pages)
  {
    this.pages = pages;
  }

  public String getPublisher()
  {
    return publisher;
  }

  public void setPublisher(String publisher)
  {
    this.publisher = publisher;
  }

  public double getPrice()
  {
    return price;
  }

  public void setPrice(double price)
  {
    this.price = price;
  }

  @Override
  public String toString()
  {
    return "Book - " +
        "title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", isbn='" + isbn + '\'' +
        ", pages=" + pages +
        ", publisher='" + publisher + '\'' +
        ", price=" + price;
  }
}
