package bg.codix.chat.service.impl;

import bg.codix.chat.exception.GroupNotSameException;
import bg.codix.chat.exception.MessageNotFoundException;
import bg.codix.chat.model.message.Message;
import bg.codix.chat.model.message.SendMessageResponse;
import bg.codix.chat.model.user.User;
import bg.codix.chat.repository.MessageRepository;
import bg.codix.chat.service.MessageService;
import bg.codix.chat.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

   @Transactional
   @Override
   public SendMessageResponse sendMessage(Long senderId, Long receiptId, String text)
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


   @Transactional
   @Override
   public List<SendMessageResponse> getAllMessagesByReceipt(Long receiptId)
   {
      List<SendMessageResponse> messages = messageRepository.getAllMessagesByReceipt(receiptId)
         .stream().map(m -> {
            User sender = userService.getUserById(m.getMessageFrom());
            User receipt = userService.getUserById(m.getMessageTo());

            SendMessageResponse response = messageResponse(m, sender, receipt);
            return response;
         })
         .collect(Collectors.toList());

      return messages;
   }

   @Transactional
   @Override
   public Message readMessage(Long id)
   {
      return messageRepository.readMessage(id, LocalDateTime.now());
   }

   private SendMessageResponse messageResponse(Message message, User sender, User receipt)
   {
      SendMessageResponse response = new SendMessageResponse();
      response.setText(message.getText());
      response.setSendDate(message.getSendDate());
      response.setSender(sender.getUsername());
      response.setReceipt(receipt.getUsername());
      return response;
   }
}
