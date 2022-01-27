package bg.startit.historyrecord;


import bg.startit.book.Book;
import bg.startit.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class HistoryRecord
{

   private Long id;
   private Book book;
   private User user;
   private Date dateOfLending;
   private Date dateOfReturn;

   public HistoryRecord()
   {
   }

   @Id
   @GeneratedValue
   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   @ManyToOne
   @NotNull(message = "Въведете книга, която да бъде наета")
   public Book getBook()
   {
      return book;
   }

   public void setBook(Book book)
   {
      this.book = book;
   }

   @ManyToOne
   @NotNull(message = "Въведете клиент, който наема книга")
   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   @Temporal(TemporalType.DATE)
   @Column(nullable = false)
   public Date getDateOfLending()
   {
      return dateOfLending;
   }

   public void setDateOfLending(Date dateOfLending)
   {
      this.dateOfLending = dateOfLending;
   }

   @Temporal(TemporalType.DATE)
   public Date getDateOfReturn()
   {
      return dateOfReturn;
   }

   public void setDateOfReturn(Date dateOfReturn)
   {
      this.dateOfReturn = dateOfReturn;
   }
}
