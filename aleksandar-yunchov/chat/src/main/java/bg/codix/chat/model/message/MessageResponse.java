package bg.codix.chat.model.message;

import java.time.LocalDateTime;

public class MessageResponse
{
   private Long          id;
   private String        sender;
   private String        receipt;
   private String        text;
   private LocalDateTime sendDate;

   public MessageResponse()
   {
   }

   public Long getId()
   {
      return id;
   }

   public MessageResponse setId(Long id)
   {
      this.id = id;
      return this;
   }

   public String getSender()
   {
      return sender;
   }

   public MessageResponse setSender(String sender)
   {
      this.sender = sender;
      return this;
   }

   public String getReceipt()
   {
      return receipt;
   }

   public MessageResponse setReceipt(String receipt)
   {
      this.receipt = receipt;
      return this;
   }

   public String getText()
   {
      return text;
   }

   public MessageResponse setText(String text)
   {
      this.text = text;
      return this;
   }

   public LocalDateTime getSendDate()
   {
      return sendDate;
   }

   public MessageResponse setSendDate(LocalDateTime sendDate)
   {
      this.sendDate = sendDate;
      return this;
   }
}
