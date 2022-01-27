package bg.codix.chat.service;

import bg.codix.chat.model.message.Message;
import bg.codix.chat.model.message.MessageResponse;

import java.util.List;

public interface MessageService
{

   MessageResponse sendMessage(Long senderId, Long receiptId, String text);

   List<MessageResponse> getAllMessagesByReceipt(String username);

   Message readMessage(Long id);
}
