package bg.codix.chat.dao;

import bg.codix.chat.exception.GroupNotSameException;
import bg.codix.chat.exception.UserNotFoundException;
import bg.codix.chat.model.user.User;
import bg.codix.chat.model.user.UserByGroupResponse;
import bg.codix.chat.repository.UserRepository;
import bg.codix.chat.repository.impl.UserRepositoryImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

@ContextConfiguration(locations = "classpath:spring-test-config.xml")
public class UserRepositoryTest
{
   @Autowired
   private UserRepository userRepository;

   @Mock
   private NamedParameterJdbcOperations namedParameterJdbcOperations;

   private final ModelMapper modelMapper = new ModelMapper();
   private long idgenerator;

   @BeforeMethod
   public void setUp()
   {
      idgenerator = 0;
      MockitoAnnotations.openMocks(this);
      this.userRepository = new UserRepositoryImpl(namedParameterJdbcOperations);
   }

   private User createUser(String id)
   {
      return new User(++idgenerator, id, "first " + id, "last " + id);
   }

   @Test
   public void getUserByIdCorrect()
   {
      User admin = createUser("admin");

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.anyObject(),
         (RowMapper<User>) Mockito.any()))
         .thenReturn(admin);
      User userById = userRepository.getUserById(admin.getId());
      Long id = userById.getId();

      assertEquals(Long.valueOf(1), id);
   }

   @Test(expectedExceptions = UserNotFoundException.class)
   public void getUserByIdCorrectHandleException()
   {
      User admin = createUser("admin");

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.anyObject(),
         (RowMapper<User>) Mockito.any()))
         .thenThrow(EmptyResultDataAccessException.class);
      User userById = userRepository.getUserById(admin.getId());
      Long id = userById.getId();

      assertEquals(Long.valueOf(1), id);
   }

   @Test
   public void getUserByUsernameCorrect()
   {
      User admin = createUser("admin");

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.anyObject(),
         (RowMapper<User>) Mockito.any()))
         .thenReturn(admin);
      User userById = userRepository.getUserByUsername(admin.getUsername());

      assertEquals("admin", userById.getUsername());
   }

   @Test(expectedExceptions = UserNotFoundException.class)
   public void getUserByUsernameHandleException()
   {
      User admin = createUser("admin");

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.anyObject(),
         (RowMapper<User>) Mockito.any()))
         .thenThrow(EmptyResultDataAccessException.class);
      User userById = userRepository.getUserByUsername(admin.getUsername());
      Long id = userById.getId();

      assertEquals(Long.valueOf(1), id);
   }

   @Test
   public void getUsersWhoCanSendMessageToCorrect()
   {

      List<User> users = Arrays.asList(createUser("admin"), createUser("user"), createUser("libra"));

      Mockito.when(namedParameterJdbcOperations.query(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.anyObject(),
         (RowMapper<User>) Mockito.any()))
         .thenReturn(users);

      List<User> usersInSameGroup = userRepository.getUsersWhoCanSendMessageTo("iordan");

      assertEquals(3, usersInSameGroup.size());
   }

   @Test(expectedExceptions = UserNotFoundException.class)
   public void getUsersWhoCanSendMessageToHandleException()
   {

      List<User> users = Arrays.asList(createUser("admin"), createUser("user"), createUser("libra"));

      Mockito.when(namedParameterJdbcOperations.query(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.anyObject(),
         (RowMapper<User>) Mockito.any()))
         .thenThrow(EmptyResultDataAccessException.class);

      List<User> usersInSameGroup = userRepository.getUsersWhoCanSendMessageTo("iordan");

      assertEquals(3, usersInSameGroup.size());
   }

   @Test
   public void checkSameGroupOfUsersCorrect()
   {

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.anyObject(),
         (RowMapper<Long>) Mockito.any()))
         .thenReturn(4L);

      Long aLong = userRepository.checkSameGroupOfUsers(12L, 23L);

      assertEquals(Long.valueOf(4), aLong);
   }

   @Test(expectedExceptions = GroupNotSameException.class)
   public void checkSameGroupOfUsersHandleException()
   {

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.anyObject(),
         (RowMapper<Long>) Mockito.any()))
         .thenThrow(EmptyResultDataAccessException.class);

      Long aLong = userRepository.checkSameGroupOfUsers(12L, 23L);

      assertEquals(Long.valueOf(4), aLong);
   }

   @Test
   public void getAllByGroup(){

      User admin = createUser("admin");
      UserByGroupResponse adminResponse = this.modelMapper.map(admin, UserByGroupResponse.class);
      adminResponse.setGroup("FakeGroup");
      List<UserByGroupResponse> userByGroupResponses = Arrays.asList(adminResponse);

      Mockito.when(namedParameterJdbcOperations.query(
         Mockito.anyString(),
         (RowMapper<UserByGroupResponse>) Mockito.any()
      )).thenReturn(userByGroupResponses);

      List<UserByGroupResponse> allByGroup = userRepository.getAllByGroup();

      assertEquals(1, allByGroup.size());
   }

   @Test(expectedExceptions = UserNotFoundException.class)
   public void getAllByGroupHandleException(){

      User admin = createUser("admin");
      UserByGroupResponse adminResponse = this.modelMapper.map(admin, UserByGroupResponse.class);
      adminResponse.setGroup("FakeGroup");
      List<UserByGroupResponse> userByGroupResponses = Arrays.asList(adminResponse);

      Mockito.when(namedParameterJdbcOperations.query(
         Mockito.anyString(),
         (RowMapper<UserByGroupResponse>) Mockito.any()
      )).thenThrow(EmptyResultDataAccessException.class);

      List<UserByGroupResponse> allByGroup = userRepository.getAllByGroup();

      assertEquals(1, allByGroup.size());
   }

}
