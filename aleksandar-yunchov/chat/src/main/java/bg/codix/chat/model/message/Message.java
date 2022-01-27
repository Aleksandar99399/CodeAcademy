package bg.codix.chat.model.message;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class Message
{
   private Long          messageId;
   private Long          messageFrom;
   private Long          messageTo;
   private String        text;
   private Boolean       isRead;
   private LocalDateTime sendDate;
   private LocalDateTime readDate;

   public Message()
   {
   }

   public Long getMessageId()
   {
      return messageId;
   }

   public Message setMessageId(Long messageId)
   {
      this.messageId = messageId;
      return this;
   }

   public Long getMessageFrom()
   {
      return messageFrom;
   }

   public Message setMessageFrom(Long messageFrom)
   {
      this.messageFrom = messageFrom;
      return this;
   }

   public Long getMessageTo()
   {
      return messageTo;
   }

   public Message setMessageTo(Long messageTo)
   {
      this.messageTo = messageTo;
      return this;
   }

   @NotBlank(message = "Text cannot be null or 0 symbols")
   @Length(max = 200, message = "Message is too long")
   public String getText()
   {
      return text;
   }

   public Message setText(String text)
   {
      this.text = text;
      return this;
   }

   public Boolean getRead()
   {
      return isRead;
   }

   public Message setRead(Boolean read)
   {
      isRead = read;
      return this;
   }

   public LocalDateTime getSendDate()
   {
      return sendDate;
   }

   public Message setSendDate(LocalDateTime sendDate)
   {
      this.sendDate = sendDate;
      return this;
   }

   public LocalDateTime getReadDate()
   {
      return readDate;
   }

   public Message setReadDate(LocalDateTime readDate)
   {
      this.readDate = readDate;
      return this;
   }
}
