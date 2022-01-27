package bg.startit.comment;


import bg.startit.book.Book;
import bg.startit.user.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Comment
{

   private Long   id;
   private String text;
   private Book   book;
   private User   user;

   public Comment()
   {
   }

   @Id
   @GeneratedValue
   public Long getId()
   {
      return id;
   }

   public Comment setId(Long id)
   {
      this.id = id;
      return this;
   }

   @Length(max = 200, message = "Твърде дълъг коментар")
   public String getText()
   {
      return text;
   }

   public void setText(String body)
   {
      this.text = body;
   }

   @ManyToOne
   @NotNull(message = "Въведете книга за да създадете коментар")
   public Book getBook()
   {
      return book;
   }

   public void setBook(Book book)
   {
      this.book = book;
   }

   @ManyToOne
   @NotNull(message = "Въведете клиент за да създадете коментар")
   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }
}
