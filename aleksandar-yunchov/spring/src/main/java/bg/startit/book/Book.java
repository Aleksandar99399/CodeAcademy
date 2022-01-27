package bg.startit.book;


import bg.startit.comment.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Book
{
   private Long          id;
   private String        isbn;
   private String        title;
   private String        author;
   private BigDecimal    price;
   private Integer       pages;
   private Integer       stock;
   private Integer       year;
   private List<Comment> comments = new ArrayList<>();

   public Book()
   {
   }

   public Book(String s, String godfather, String puzo, BigDecimal valueOf, int i, int i1)
   {

   }

   @Id
   @GeneratedValue
   public Long getId()
   {
      return id;
   }

   public Book setId(Long id)
   {
      this.id = id;
      return this;
   }

   @Column(unique = true)
   @NotNull(message = "ISBN не може да бъде null")
   @Length(min = 7, message = "ISBN не може да съдържа по-малко от 7 символа")
   @Pattern(regexp = "^[\\d-]+$", message = "Невалиден ISBN")
   public String getIsbn()
   {
      return isbn;
   }

   public void setIsbn(String isbn)
   {
      this.isbn = isbn;
   }

   @DecimalMin(value = "0.1", message = "Невалидна стойност за цена")
   @NotNull(message = "Цената не може да бъде null")
   public BigDecimal getPrice()
   {
      return price;
   }

   public void setPrice(BigDecimal price)
   {
      this.price = price;
   }

   public void setPages(Integer pages)
   {
      this.pages = pages;
   }

   @Min(value = 1, message = "Не може да създадете книга с по-малко от 1 копие")
   @NotNull(message = "Броят копия не може да бъде null")
   public Integer getStock()
   {
      return stock;
   }

   public void setStock(Integer stock)
   {
      this.stock = stock;
   }

   public void setYear(Integer year)
   {
      this.year = year;
   }

   @Length(min = 2, message = "Заглавието на книгата не може да съдържа по-малко от 2 символа")
   @NotNull(message = "Заглавието не може да бъде null")
   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   @Length(min = 2, message = "Авторът не може да съдържа по-малко от 2 символа")
   @Pattern(regexp = "^[A-z А-я]+$", message = "Невалидно име")
   @NotNull(message = "Авторът не може да буде null")
   public String getAuthor()
   {
      return author;
   }

   public void setAuthor(String author)
   {
      this.author = author;
   }

   @Min(value = 2, message = "Книгата не може да съдържа по-малко от 2 страници")
   @NotNull(message = "Броят страници не може да бъде null")
   public Integer getPages()
   {
      return pages;
   }

   @Min(value = 1800, message = "Невалидна година")
   @Max(value = 2021, message = "Невалидна година")
   @NotNull(message = "Годината на издаване на книгата не може да бъде null")
   public Integer getYear()
   {
      return year;
   }

   @OneToMany
   public List<Comment> getComments()
   {
      return comments;
   }

   public Book setComments(List<Comment> comments)
   {
      this.comments = comments;
      return this;
   }

   @Override
   public String toString()
   {
      final StringBuilder sb = new StringBuilder("Book{");
      sb.append("id=").append(id);
      sb.append(", isbn='").append(isbn).append('\'');
      sb.append(", title='").append(title).append('\'');
      sb.append(", author='").append(author).append('\'');
      sb.append(", price=").append(price);
      sb.append(", pages=").append(pages);
      sb.append(", stock=").append(stock);
      sb.append(", year=").append(year);
      sb.append('}');
      return sb.toString();
   }
}
