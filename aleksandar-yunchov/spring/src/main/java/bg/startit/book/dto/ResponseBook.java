package bg.startit.book.dto;

import java.math.BigDecimal;

public class ResponseBook
{
   private String     title;
   private String     author;
   private BigDecimal price;
   private Integer    year;

   public ResponseBook()
   {
   }

   public String getTitle()
   {
      return title;
   }

   public ResponseBook setTitle(String title)
   {
      this.title = title;
      return this;
   }

   public String getAuthor()
   {
      return author;
   }

   public ResponseBook setAuthor(String author)
   {
      this.author = author;
      return this;
   }

   public BigDecimal getPrice()
   {
      return price;
   }

   public ResponseBook setPrice(BigDecimal price)
   {
      this.price = price;
      return this;
   }

   public Integer getYear()
   {
      return year;
   }

   public ResponseBook setYear(Integer year)
   {
      this.year = year;
      return this;
   }
}
