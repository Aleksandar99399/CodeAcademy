package bg.codix.chat.dao;

import bg.codix.chat.exception.MessageNotFoundException;
import bg.codix.chat.model.message.Message;
import bg.codix.chat.repository.MessageRepository;
import bg.codix.chat.repository.UserRepository;
import bg.codix.chat.repository.impl.MessageRepositoryImpl;
import bg.codix.chat.repository.impl.UserRepositoryImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

@ContextConfiguration(locations = "classpath:spring-test-config.xml")
public class MessageRepositoryTest
{

   @Mock
   private NamedParameterJdbcOperations namedParameterJdbcOperations;

   private MessageRepository messageRepository;

   private long idgenerator;

   @BeforeMethod
   public void setUp()
   {
      idgenerator = 0;
      MockitoAnnotations.openMocks(this);
      this.messageRepository = new MessageRepositoryImpl(namedParameterJdbcOperations)
      {
      };
   }

   private Message createMessage()
   {
      return new Message()
         .setMessageId(++idgenerator)
         .setMessageFrom(++idgenerator)
         .setMessageTo(++idgenerator)
         .setSendDate(LocalDateTime.now())
         .setText("Test Message " + idgenerator);
   }

   @Test
   public void getMessageById()
   {
      Message message = createMessage();

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.any(),
         (RowMapper<Message>) Mockito.any()))
         .thenReturn(message);

      Message messageById = messageRepository.getMessageById(message.getMessageId());

      assertEquals(Long.valueOf(1), messageById.getMessageId());
   }

   @Test(expectedExceptions = MessageNotFoundException.class)
   public void getMessageByIdHandleException()
   {
      Message message = createMessage();

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.any(),
         (RowMapper<Message>) Mockito.any()))
         .thenThrow(EmptyResultDataAccessException.class);

      Message messageById = messageRepository.getMessageById(message.getMessageId());


      assertEquals(Long.valueOf(1), messageById.getMessageId());
   }
}
