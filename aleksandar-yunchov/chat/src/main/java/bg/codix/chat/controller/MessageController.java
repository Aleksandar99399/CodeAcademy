package bg.codix.chat.controller;

import bg.codix.chat.model.message.Message;
import bg.codix.chat.model.message.MessageResponse;
import bg.codix.chat.model.message.SendMessageRequest;
import bg.codix.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/v1/messages")
@Validated
public class MessageController
{
   private final MessageService messageService;

   @Autowired
   public MessageController(MessageService messageService)
   {
      this.messageService = messageService;
   }

   @GetMapping("/{message_id}")
   public ResponseEntity<Message> readMessage(@NumberFormat @PathVariable("message_id") Long id)
   {
      return ResponseEntity.ok(messageService.readMessage(id));
   }

   @PostMapping
   public ResponseEntity<MessageResponse> sendMessage(@Valid @RequestBody SendMessageRequest sendMessageRequest)
   {
      return ResponseEntity.ok(
         messageService.sendMessage
         (
            sendMessageRequest.getSender(),
            sendMessageRequest.getReceipt(),
            sendMessageRequest.getText()
         ));
   }


}
