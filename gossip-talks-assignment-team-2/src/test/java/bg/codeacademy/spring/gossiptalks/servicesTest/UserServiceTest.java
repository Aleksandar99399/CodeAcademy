package bg.codeacademy.spring.gossiptalks.servicesTest;

import bg.codeacademy.spring.gossiptalks.exception.userexceptions.ExistUserException;
import bg.codeacademy.spring.gossiptalks.exception.userexceptions.PasswordsNotMatchException;

import bg.codeacademy.spring.gossiptalks.model.entity.User;
import bg.codeacademy.spring.gossiptalks.repository.UserRepository;

import bg.codeacademy.spring.gossiptalks.service.GossipService;
import bg.codeacademy.spring.gossiptalks.service.impl.UserServiceImpl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestExecutionListeners;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{

   @Mock
   private UserRepository userRepository;
   @Mock
   private GossipService  gossipService;

   private       UserServiceImpl       userService;
   private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//  private User user;

   @BeforeMethod
   public void setUp()
   {
      MockitoAnnotations.initMocks(this);
      this.userService = new UserServiceImpl(userRepository, gossipService, bCryptPasswordEncoder);
//    this.user = new User(1L, "admin", "admin", "admin@gmail.com", "Admin Admin");
   }

   private static long idgenerator = 0;

   private User newUser(String id)
   {
      return new User(++idgenerator, id, bCryptPasswordEncoder.encode(id), id + "@gmail.com", id + "Name");
   }

   @Test
   public void try_to_create_user_save()
   {
      User user = newUser("admin");
      Mockito.when(userRepository.save(user)).thenReturn(user);
      User userToReturn = userService.save(user);
      assertNotNull(userToReturn);
   }

   @Test
   public void try_to_create_user_with_existing_username_save()
   {
      User user = newUser("admin");
      User user2 = new User(2L, "admin", "admin", "admin@gmail.com", "Admin Admin");

      Mockito.when(userRepository.save(user)).thenReturn(user);
      userService.save(user);
      User user3 = userService.save(user2);

      assertNull(user3);
   }

   @Test(expectedExceptions = ExistUserException.class)
   public void try_to_change_password_with_nonexisting_user_changePassword()
   {
      User user = newUser("admin");

      Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
      User userToReturn = userService.changePassword("12345", user.getPassword(), "admin123");

      assertThrows(ExistUserException.class, () -> userService.changePassword("12345", user.getPassword(), "admin123"));
      //assertEquals(userToReturn.getPassword(), "bg.codeacademy.spring.gossiptalks.exception.userexceptions.ExistUserException: User not exist");
   }

   @Test
   public void try_to_get_by_username_correct_getByUsername()
   {
      User user = newUser("admin");

      Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
      User userToReturn = userService.getByUsername(user.getUsername());

      assertNotNull(userToReturn);
   }

   @Test(expectedExceptions = ExistUserException.class)
   public void try_to_get_by_nonexisting_username_getByUsername()
   {
      User user = newUser("admin");

      Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
      userService.getByUsername("invalidName");
   }

   @Test(expectedExceptions = PasswordsNotMatchException.class)
   public void try_to_change_password_with_noPasswordMatch_changePassword()
   {
      User user = newUser("admin");

      Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
      userService.changePassword("newPassword", "wrongPassword", user.getUsername());
   }

   @Test
   public void try_to_change_password_with_right_password_and_existing_username_changePassword()
   {
      User user = newUser("admin");

      Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
      Mockito.when(userRepository.save(user)).thenReturn(user);
      User userToReturn = userService.changePassword("newPassword", "admin", "admin");
      assertEquals(userToReturn.getPassword(), "newPassword");
   }

   @Test
   public void try_to_get_current_logged_user_by_username_getCurrentLoggedUser()
   {
      User user = newUser("admin");

      Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
      User userToReturn = userService.getByUsername(user.getUsername());
      assertNotNull(userToReturn);
   }

   @Test(expectedExceptions = ExistUserException.class)
   public void try_to_get_current_logged_user_by_not_exist_username()
   {
      User user = newUser("foncho");
      Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.empty());
      userService.getByUsername(user.getUsername());
   }

   @Test
   public void try_to_get_all_users_by_SubString_getAll()
   {
      List<User> returnedUser = Arrays.asList(newUser("adrian"), newUser("simeonan"));
      Mockito.when(userRepository.findAllByNameOrUsernameContains("an")).thenReturn(returnedUser);
      Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(newUser("admin")));

      userService.getAll(false, "an", "admin");
      assertEquals(2, returnedUser.size());
   }
}
