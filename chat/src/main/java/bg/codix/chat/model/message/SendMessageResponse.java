package bg.codix.chat.model.message;

import java.time.LocalDateTime;

public class SendMessageResponse
{
   private String   sender;
   private String   receipt;
   private String        text;
   private LocalDateTime sendDate;

   public SendMessageResponse()
   {
   }

   public String getSender()
   {
      return sender;
   }

   public SendMessageResponse setSender(String sender)
   {
      this.sender = sender;
      return this;
   }

   public String getReceipt()
   {
      return receipt;
   }

   public SendMessageResponse setReceipt(String receipt)
   {
      this.receipt = receipt;
      return this;
   }

   public String getText()
   {
      return text;
   }

   public SendMessageResponse setText(String text)
   {
      this.text = text;
      return this;
   }

   public LocalDateTime getSendDate()
   {
      return sendDate;
   }

   public SendMessageResponse setSendDate(LocalDateTime sendDate)
   {
      this.sendDate = sendDate;
      return this;
   }
}
