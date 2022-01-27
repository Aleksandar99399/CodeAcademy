package bg.codix.chat.repository;

import bg.codix.chat.model.message.Message;
import bg.codix.chat.model.message.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository
{
   Message sendMessage(Message message);

   List<MessageResponse> getAllMessagesByReceipt(Long receiptId);

   Message readMessage(Long id, LocalDateTime readDate);

   // можем да направим да връщаме само определени полета
   Message getMessageById(Long id);
}
