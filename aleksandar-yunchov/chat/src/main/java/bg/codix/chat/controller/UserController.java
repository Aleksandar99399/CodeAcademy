package bg.codix.chat.controller;

import bg.codix.chat.model.message.MessageResponse;
import bg.codix.chat.model.user.UserByGroupResponse;
import bg.codix.chat.service.MessageService;
import bg.codix.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController
{
   private final UserService    userService;
   private final MessageService messageService;

   @Autowired
   public UserController(UserService userService, MessageService messageService)
   {
      this.userService = userService;
      this.messageService = messageService;
   }

   @GetMapping
   public ResponseEntity<List<UserByGroupResponse>> getAll(){
      return ResponseEntity.ok(userService.getAllByGroup());
   }

   @GetMapping("/{username}")
   public ResponseEntity<List<UserByGroupResponse>> getUsersWhoCanSendMessageTo(@PathVariable("username") String username){
      return ResponseEntity.ok(userService.getUsersWhoCanSendMessageTo(username));
   }

   @GetMapping("/{username}/messages")
   public ResponseEntity<List<MessageResponse>> getUserMessages(@PathVariable("username") String username){
      return ResponseEntity.ok(messageService.getAllMessagesByReceipt(username));
   }
}
