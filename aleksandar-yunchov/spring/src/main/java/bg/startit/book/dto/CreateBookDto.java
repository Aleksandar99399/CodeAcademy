package bg.startit.book.dto;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class CreateBookDto
{


   private String     isbn;
   private String     title;
   private String     author;
   private BigDecimal price;
   private Integer    pages;
   private Integer    stock;
   private Integer    year;

   public CreateBookDto()
   {
   }

   @Column(unique = true)
   @NotNull(message = "ISBN не може да бъде null")
   @Length(min = 7, message = "ISBN не може да съдържа по-малко от 7 символа")
   @Pattern(regexp = "^[\\d-]+$", message = "Невалиден ISBN")
   public String getIsbn()
   {
      return isbn;
   }

   public CreateBookDto setIsbn(String isbn)
   {
      this.isbn = isbn;
      return this;
   }

   @Length(min = 2, message = "Заглавието на книгата не може да съдържа по-малко от 2 символа")
   @NotNull(message = "Заглавието не може да бъде null")
   public String getTitle()
   {
      return title;
   }

   public CreateBookDto setTitle(String title)
   {
      this.title = title;
      return this;
   }

   @Length(min = 2, message = "Авторът не може да съдържа по-малко от 2 символа")
   @Pattern(regexp = "^[A-z А-я]+$", message = "Невалидно име")
   @NotNull(message = "Авторът не може да буде null")
   public String getAuthor()
   {
      return author;
   }

   public CreateBookDto setAuthor(String author)
   {
      this.author = author;
      return this;
   }

   @DecimalMin(value = "0.1",message = "Невалидна стойност за цена")
   @NotNull(message = "Цената не може да бъде null")
   public BigDecimal getPrice()
   {
      return price;
   }

   public CreateBookDto setPrice(BigDecimal price)
   {
      this.price = price;
      return this;
   }

   @Min(value = 2, message = "Книгата не може да съдържа по-малко от 2 страници")
   @NotNull(message = "Броят страници не може да бъде null")
   public Integer getPages()
   {
      return pages;
   }

   public CreateBookDto setPages(Integer pages)
   {
      this.pages = pages;
      return this;
   }

   @Min(value = 1, message = "Не може да създадете книга с по-малко от 1 копие")
   @NotNull(message = "Броят копия не може да бъде null")
   public Integer getStock()
   {
      return stock;
   }

   public CreateBookDto setStock(Integer stock)
   {
      this.stock = stock;
      return this;
   }

   @Min(value = 1800, message = "Невалидна година")
   @Max(value = 2021,  message = "Невалидна година")
   @NotNull(message = "Годината на издаване на книгата не може да бъде null")
   public Integer getYear()
   {
      return year;
   }

   public CreateBookDto setYear(Integer year)
   {
      this.year = year;
      return this;
   }
}
