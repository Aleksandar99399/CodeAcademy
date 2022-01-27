package bg.codix.chat.model.message;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class SendMessageRequest
{

   private String text;
   private Long sender;
   private Long receipt;

   public SendMessageRequest()
   {
   }

   @NotBlank(message = "Text cannot be null or 0 symbols")
   @Length(max = 200, message = "Message is too long")
   public String getText()
   {
      return text;
   }

   public SendMessageRequest setText(String text)
   {
      this.text = text;
      return this;
   }

   public Long getSender()
   {
      return sender;
   }

   public SendMessageRequest setSender(Long sender)
   {
      this.sender = sender;
      return this;
   }

   public Long getReceipt()
   {
      return receipt;
   }

   public SendMessageRequest setReceipt(Long receipt)
   {
      this.receipt = receipt;
      return this;
   }
}
