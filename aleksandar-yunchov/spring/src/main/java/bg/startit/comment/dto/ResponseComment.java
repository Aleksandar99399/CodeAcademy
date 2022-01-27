package bg.startit.comment.dto;


import bg.startit.book.dto.ResponseBook;
import bg.startit.user.dto.ResponseUser;

public class ResponseComment
{
   private String       text;
   private ResponseBook book;
   private ResponseUser user;

   public ResponseComment()
   {
   }

   public String getText()
   {
      return text;
   }

   public ResponseComment setText(String text)
   {
      this.text = text;
      return this;
   }

   public ResponseBook getBook()
   {
      return book;
   }

   public ResponseComment setBook(ResponseBook book)
   {
      this.book = book;
      return this;
   }

   public ResponseUser getUser()
   {
      return user;
   }

   public ResponseComment setUser(ResponseUser user)
   {
      this.user = user;
      return this;
   }
}
