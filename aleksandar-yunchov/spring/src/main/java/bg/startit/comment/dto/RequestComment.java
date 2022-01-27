package bg.startit.comment.dto;


import bg.startit.book.Book;
import bg.startit.user.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

// Трябва да да получава само ID
public class RequestComment
{
   private String text;
   private Book   book;
   private User   user;

   public RequestComment()
   {
   }

   @Length(max = 200, message = "Твърде дълъг коментар")
   public String getText()
   {
      return text;
   }

   public RequestComment setText(String text)
   {
      this.text = text;
      return this;
   }

   @NotNull
   public Book getBook()
   {
      return book;
   }

   public RequestComment setBook(Book book)
   {
      this.book = book;
      return this;
   }

   @NotNull
   public User getUser()
   {
      return user;
   }

   public RequestComment setUser(User user)
   {
      this.user = user;
      return this;
   }
}
