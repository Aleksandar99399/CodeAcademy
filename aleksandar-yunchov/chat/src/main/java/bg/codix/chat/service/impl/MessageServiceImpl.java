package bg.codix.chat.service.impl;

import bg.codix.chat.exception.GroupNotSameException;
import bg.codix.chat.model.message.Message;
import bg.codix.chat.model.message.MessageResponse;
import bg.codix.chat.model.user.User;
import bg.codix.chat.repository.MessageRepository;
import bg.codix.chat.service.MessageService;
import bg.codix.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class MessageServiceImpl implements MessageService
{
   private final MessageRepository messageRepository;
   private final UserService       userService;

   @Autowired
   public MessageServiceImpl(MessageRepository messageRepository, UserService userService)
   {
      this.messageRepository = messageRepository;
      this.userService = userService;
   }

   @Override
   public MessageResponse sendMessage(Long senderId, Long receiptId, String text)
   {
      User sender = userService.getUserById(senderId);
      User receipt = userService.getUserById(receiptId);

      if (userService.checkSameGroupOfUsers(senderId,receiptId) < 1) {
         throw new GroupNotSameException("Receipt is not in group of sender");
      }

      Message message = new Message()
         .setMessageFrom(sender.getId())
         .setMessageTo(receipt.getId())
         .setText(text)
         .setSendDate(LocalDateTime.now());

      Message savedMessage = messageRepository.sendMessage(message);
      return messageResponse(savedMessage, sender, receipt);
   }

   @Override
   public List<MessageResponse> getAllMessagesByReceipt(String username)
   {
      User user = userService.getUserByUsername(username);

      return messageRepository.getAllMessagesByReceipt(user.getId());
   }

   @Override
   public Message readMessage(Long id)
   {
      if (!messageRepository.getMessageById(id).getRead()){
         return messageRepository.readMessage(id, LocalDateTime.now());
      }
      return messageRepository.getMessageById(id);
   }

   private MessageResponse messageResponse(Message message, User sender, User receipt)
   {
      MessageResponse response = new MessageResponse();
      response.setId(message.getMessageId());
      response.setText(message.getText());
      response.setSendDate(message.getSendDate());
      response.setSender(sender.getUsername());
      response.setReceipt(receipt.getUsername());
      return response;
   }
}
