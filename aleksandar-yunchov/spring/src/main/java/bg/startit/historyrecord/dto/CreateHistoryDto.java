package bg.startit.historyrecord.dto;


import javax.validation.constraints.NotNull;

public class CreateHistoryDto
{
   private Long book;
   private Long user;

   public CreateHistoryDto()
   {
   }

   @NotNull(message = "Въведете книга, която да бъде наета")
   public Long getBook()
   {
      return book;
   }

   public CreateHistoryDto setBook(Long book)
   {
      this.book = book;
      return this;
   }

   @NotNull(message = "Въведете клиент, който наема книга")
   public Long getUser()
   {
      return user;
   }

   public CreateHistoryDto setUser(Long user)
   {
      this.user = user;
      return this;
   }
}
