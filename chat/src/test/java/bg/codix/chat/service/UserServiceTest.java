//package bg.codix.chat.service;
//
//import bg.codix.chat.exception.UserNotFoundException;
//import bg.codix.chat.model.user.User;
//import bg.codix.chat.repository.UserRepository;
//import bg.codix.chat.repository.impl.UserRepositoryImpl;
//import bg.codix.chat.service.impl.UserServiceImpl;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
//import org.springframework.test.context.TestExecutionListeners;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import static org.testng.Assert.*;
//
//@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest
//{
//   @Mock
//   private UserRepository userRepository;
//
//   private UserService userService;
//
//   @Mock
//   private NamedParameterJdbcOperations namedParameterJdbcTemplate;
//
//   @BeforeMethod
//   public void setUp(){
//      MockitoAnnotations.openMocks(this);
//      this.userService = new UserServiceImpl(userRepository);
//      this.userRepository = new UserRepositoryImpl(namedParameterJdbcTemplate);
//   }
//
//   private static long idgenerator = 0;
//
//   private User createUser(String id){
//      return new User(++idgenerator, id,"first " + id,"last " + id);
//   }
//
//   @Test
//   public void getUserById(){
//      User user = createUser("admin");
//      Mockito.when(userRepository.getUserById(user.getId())).thenReturn(user);
//      User userById = userService.getUserById(user.getId());
//      assertNotNull(userById);
//   }
//
//   @Test(expectedExceptions = UserNotFoundException.class)
//   public void getUserByIdThrowException(){
//      User user = createUser("admin");
//      Mockito.when(userRepository.getUserById(user.getId())).thenReturn(null);
//      User userById = userService.getUserById(user.getId());
//   }
//
//   @Test
//   public void getUserByUsername(){
//      User user = createUser("admin");
//      Mockito.when(userRepository.getUserByUsername(user.getUsername())).thenReturn(user);
//      User userByUsername = userService.getUserByUsername(user.getUsername());
//      assertNotNull(userByUsername);
//   }
//
//   @Test(expectedExceptions = UserNotFoundException.class)
//   public void getUserByUsernameThrowException(){
//      User user = createUser("admin");
//      Mockito.when(userRepository.getUserByUsername(user.getUsername())).thenReturn(null);
//      User userByUsername = userService.getUserByUsername(user.getUsername());
//   }
//
////   @Test
////   public void getAllUserByGroup(){
////      User user = createUser("admin");
////      Mockito.when(userRepository.getAllByGroup()).thenReturn();
////      assertNotNull(userByUsername);
////   }
//}
