package bg.codix.chat.repository;

import bg.codix.chat.exception.MessageNotFoundException;
import bg.codix.chat.model.message.Message;
import bg.codix.chat.model.message.MessageResponse;
import bg.codix.chat.repository.impl.MessageRepositoryImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@ContextConfiguration(locations = "classpath:spring-test-config.xml")
public class MessageRepositoryTest
{

   @Mock
   private NamedParameterJdbcOperations namedParameterJdbcOperations;

   @Mock
   private KeyHolder keyHolder;

   private MessageRepository messageRepository;

   private long idgenerator;

   @BeforeMethod
   public void setUp()
   {
      idgenerator = 0;
      MockitoAnnotations.openMocks(this);
      this.messageRepository = new MessageRepositoryImpl(namedParameterJdbcOperations, keyHolder);
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

   @Test
   public void readMessage()
   {
      Message message = createMessage();

      Mockito.when(keyHolder.getKey()).thenReturn(1L);

      Mockito.when(namedParameterJdbcOperations.update(
         Mockito.anyString(),
         Mockito.any(SqlParameterSource.class),
         Mockito.any(KeyHolder.class),
         Mockito.any(String[].class)))
         .thenReturn(2);

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.any(),
         (RowMapper<Message>) Mockito.any()))
         .thenReturn(message);


      Message testMessage = messageRepository.readMessage(message.getMessageId(), LocalDateTime.now());

      assertNotNull(testMessage);
      assertEquals(Long.valueOf(1), testMessage.getMessageId());
   }

   @Test(expectedExceptions = MessageNotFoundException.class)
   public void readMessageHanelException()
   {
      Message message = createMessage();

      Mockito.when(keyHolder.getKey()).thenReturn(1L);

      Mockito.when(namedParameterJdbcOperations.update(
         Mockito.anyString(),
         Mockito.any(SqlParameterSource.class),
         Mockito.any(KeyHolder.class),
         Mockito.any(String[].class)))
         .thenReturn(0);

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.any(),
         (RowMapper<Message>) Mockito.any()))
         .thenReturn(message);


      Message testMessage = messageRepository.readMessage(message.getMessageId(), LocalDateTime.now());

      assertNotNull(testMessage);
      assertEquals(Long.valueOf(1), testMessage.getMessageId());
   }

   @Test
   public void getAllMessagesByReceipt(){
      List<Message> messages = Arrays.asList(createMessage(), createMessage(), createMessage());

      Mockito.when(namedParameterJdbcOperations.query(
         Mockito.anyString(),
         (SqlParameterSource) Mockito.any(),
         (RowMapper<Message>) Mockito.any()
      )).thenReturn(messages);

      List<MessageResponse> allMessagesByReceipt = messageRepository.getAllMessagesByReceipt(3L);
      assertEquals(3,allMessagesByReceipt.size());
   }

   @Test
   public void sendMessage(){
      Message message = createMessage();

      Mockito.when(keyHolder.getKey()).thenReturn(1L);

      Mockito.when(namedParameterJdbcOperations.update(
         Mockito.anyString(),
         Mockito.any(SqlParameterSource.class),
         Mockito.any(KeyHolder.class),
         Mockito.any(String[].class)
      )).thenReturn(2);

      Mockito.when(namedParameterJdbcOperations.queryForObject(
         Mockito.anyString(),
         Mockito.any(SqlParameterSource.class),
         (RowMapper<Message>)  Mockito.any()))
         .thenReturn(message);

      Message testMessage = messageRepository.sendMessage(message);

      assertNotNull(testMessage);
   }
}
