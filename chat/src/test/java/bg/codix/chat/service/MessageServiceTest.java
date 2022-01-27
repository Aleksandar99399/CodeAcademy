//package bg.codix.chat.service;
//
//import bg.codix.chat.exception.UserNotFoundException;
//import bg.codix.chat.model.message.Message;
//import bg.codix.chat.model.message.SendMessageRequest;
//import bg.codix.chat.model.message.SendMessageResponse;
//import bg.codix.chat.model.user.User;
//import bg.codix.chat.repository.MessageRepository;
//import bg.codix.chat.repository.UserRepository;
//import bg.codix.chat.service.impl.MessageServiceImpl;
//import bg.codix.chat.service.impl.UserServiceImpl;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
//import org.springframework.test.context.TestExecutionListeners;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.testng.Assert.*;
//
//@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
//@ExtendWith(MockitoExtension.class)
//public class MessageServiceTest
//{
//
//   @Mock
//   private MessageRepository messageRepository;
//   @Mock
//   private UserRepository userRepository;
//   @Mock
//   private UserService userService;
//   @Mock
//   private ModelMapper modelMapper;
//
//   private MessageService messageService;
//
//   @BeforeMethod
//   public void setUp()
//   {
//      MockitoAnnotations.openMocks(this);
//      this.userService = new UserServiceImpl(userRepository);
//      this.messageService = new MessageServiceImpl(messageRepository,userService,modelMapper);
//
//   }
//
//   private static long idgenerator = 0;
//
//   private Message generateMessage(Long id){
//      return new Message().setMessageFrom(id).setMessageTo(id).setText("messages").setSendDate(LocalDateTime.now());
//   }
//
//
//   @Test
//   public void sendMessage(){
//
//      //created objects
//      SendMessageRequest messageRequest =
//         new SendMessageRequest().setSender(1L).setReceipt(3L).setText("First Mess Test");
//
//      User sender = new User(1L,"first","first", "first");
//      User receipt = new User(3L,"third","third", "third");
//
//      //mock resources
//      Mockito.when(userService.getUserById(Mockito.anyLong())).thenReturn(sender,receipt);
//      Mockito.when(messageRepository.sendMessage(any())).thenReturn(new Message());
//
//      SendMessageResponse savedMessage = messageService.sendMessage(messageRequest.getSender(), messageRequest.getReceipt(), messageRequest.getText());
//
//      // expected results
//      assertEquals("third",savedMessage.getReceipt());
//      assertNotNull(savedMessage);
//   }
//
//   @Test(expectedExceptions = UserNotFoundException.class)
//   public void sendMessageWithInvalidUser(){
//      //create object
//      SendMessageRequest messageRequest =
//         new SendMessageRequest().setSender(1L).setReceipt(3L).setText("First Mess Test");
//
//      //Mock resources
////      Mockito.when(userRepository.getUserById(17L)).thenReturn();
//      Mockito.when(userService.getUserById(Mockito.anyLong())).thenReturn(null);
//      Mockito.when(messageRepository.sendMessage(any())).thenReturn(new Message());
//
//      //invoke method
//      SendMessageResponse savedMessage =
//         messageService.sendMessage(messageRequest.getSender(),messageRequest.getReceipt(),messageRequest.getText());
//   }
//
//   @Test
//   public void getAllMessagesByReceipt(){
//      Message first = generateMessage(1L);
//      Message second = generateMessage(1L);
//      Message third = generateMessage(1L);
//      User sender = new User(1L,"first","first", "first");
//      User receipt = new User(3L,"third","third", "third");
//      List<Message> messages = Arrays.asList(first, second, third);
//
//      Mockito.when(messageRepository.getAllMessagesByReceipt(1L)).thenReturn(messages);
//      Mockito.when(userService.getUserById(Mockito.anyLong())).thenReturn(sender,receipt);
//
//      List<SendMessageResponse> allMessagesByReceipt = messageService.getAllMessagesByReceipt(1L);
//      assertEquals(3,allMessagesByReceipt.size());
//
//   }
//}
