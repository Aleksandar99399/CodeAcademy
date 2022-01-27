package bg.codix.chat.service;

import bg.codix.chat.model.message.Message;
import bg.codix.chat.model.message.SendMessageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService
{

   SendMessageResponse sendMessage(Long senderId, Long receiptId, String text);

   List<SendMessageResponse> getAllMessagesByReceipt(Long receiptId);

   Message readMessage(Long id);
}
