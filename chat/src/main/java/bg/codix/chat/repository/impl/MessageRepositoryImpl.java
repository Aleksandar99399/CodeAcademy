package bg.codix.chat.repository.impl;

import bg.codix.chat.exception.MessageNotFoundException;
import bg.codix.chat.model.message.Message;
import bg.codix.chat.repository.MessageRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository
{
   private final NamedParameterJdbcOperations namedParameterJdbcTemplate;

   public MessageRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcTemplate)
   {
      this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
   }

   @Override
   public Message sendMessage(Message message)
   {
      String sql = "INSERT INTO MESSAGE (MSG_FROM, MSG_TO, MESSAGE,IS_READ, SEND_DATE) " +
         "VALUES (:sender, :receipt, :text ,0, :sendDate)";

      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
         .addValue("sender", message.getMessageFrom())
         .addValue("receipt", message.getMessageTo())
         .addValue("text", message.getText())
         .addValue("sendDate", message.getSendDate());

      KeyHolder keyHolder = new GeneratedKeyHolder();

      namedParameterJdbcTemplate.update(sql, mapSqlParameterSource, keyHolder, new String[]{"msg_id"});

      Message messageById = getMessageById(keyHolder.getKey().longValue());
      return messageById;
   }

   @Override
   public List<Message> getAllMessagesByReceipt(Long receiptId)
   {

      String sql =
         "SELECT * FROM MESSAGE WHERE msg_to = :receiptId ORDER BY SEND_DATE DESC ";

      List<Message> messages = namedParameterJdbcTemplate.query(
         sql,
         new MapSqlParameterSource("receiptId", receiptId),
         messageMapper()
      );

      return messages;
   }

   @Override
   public Message readMessage(Long id, LocalDateTime readDate)
   {
      String sql =
             "UPDATE MESSAGE                              "
            +"  SET IS_READ = 1, READ_DATE = :readDate    "
            +"WHERE MSG_ID = :id                          ";

      KeyHolder keyHolder = new GeneratedKeyHolder();
      int updatedRows = namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource()
            .addValue("id", id)
            .addValue("readDate", readDate),
         keyHolder,
         new String[]{"msg_id"}
      );

      if (updatedRows > 0) {
         return getMessageById(keyHolder.getKey().longValue());
      }
      else {
         throw new MessageNotFoundException("The message not found");
      }
   }

   @Override
   public Message getMessageById(Long id)
   {
      String sql =
             "SELECT *           "
            +"  FROM MESSAGE     "
            +"WHERE msg_id = :id ";

      MapSqlParameterSource map = new MapSqlParameterSource().addValue("id", id);

      try {
         return namedParameterJdbcTemplate.queryForObject(sql, map, messageMapper());
      }
      catch (EmptyResultDataAccessException ex) {
         throw new MessageNotFoundException("Message not found with ID: " + id);
      }

   }

   private RowMapper<Message> messageMapper()
   {
      return (rs,row) ->
         new Message()
            .setMessageId(rs.getLong("msg_id"))
            .setMessageFrom(rs.getLong("msg_from"))
            .setMessageTo(rs.getLong("msg_to"))
            .setText(rs.getString("message"))
            .setRead(rs.getBoolean("is_read"))
            .setSendDate(rs.getObject("send_Date",LocalDateTime.class))
            .setReadDate(rs.getObject("read_date", LocalDateTime.class));
   }

}
