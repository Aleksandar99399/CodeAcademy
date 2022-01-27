package bg.codix.chat.controller;

import bg.codix.chat.model.user.User;
import bg.codix.chat.model.user.UserByGroupResponse;
import bg.codix.chat.model.message.SendMessageResponse;
import bg.codix.chat.service.MessageService;
import bg.codix.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
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
      List<UserByGroupResponse> allUsers = userService.getAllByGroup();
      return ResponseEntity.ok(allUsers);
   }

   @GetMapping("/{username}")
   public ResponseEntity<List<User>> getUsersWhoCanSendMessageTo(@PathVariable("username") String username){
      List<User> users = userService.getUsersWhoCanSendMessageTo(username);

      return ResponseEntity.ok(users);
   }

   @GetMapping("/{username}/messages")
   public ResponseEntity<List<SendMessageResponse>> getUserMessages(@PathVariable("username") String username){
      User user = userService.getUserByUsername(username);
      List<SendMessageResponse> allMessagesByReceipt = messageService.getAllMessagesByReceipt(user.getId());

      return ResponseEntity.ok(allMessagesByReceipt);
   }
}
