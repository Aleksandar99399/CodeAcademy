package bg.startit.historyrecord.dto;

import bg.startit.book.dto.ResponseBook;
import bg.startit.user.dto.ResponseUser;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ResponseHistory
{
   private ResponseBook book;
   private ResponseUser user;
   private Date         dateOfLending;
   private Date         dateOfReturn;

   public ResponseHistory()
   {
   }

   public ResponseBook getBook()
   {
      return book;
   }

   public ResponseHistory setBook(ResponseBook book)
   {
      this.book = book;
      return this;
   }

   public ResponseUser getUser()
   {
      return user;
   }

   public ResponseHistory setUser(ResponseUser user)
   {
      this.user = user;
      return this;
   }

   @JsonFormat(pattern = "dd-MM-yyyy")
   public Date getDateOfLending()
   {
      return dateOfLending;
   }

   public ResponseHistory setDateOfLending(Date dateOfLending)
   {
      this.dateOfLending = dateOfLending;
      return this;
   }

   @JsonFormat(pattern = "dd-MM-yyyy")
   public Date getDateOfReturn()
   {
      return dateOfReturn;
   }

   public ResponseHistory setDateOfReturn(Date dateOfReturn)
   {
      this.dateOfReturn = dateOfReturn;
      return this;
   }
}
