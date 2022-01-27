package bg.codix.chat.repository.impl;

import bg.codix.chat.exception.MessageNotFoundException;
import bg.codix.chat.model.message.Message;
import bg.codix.chat.model.message.MessageResponse;
import bg.codix.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class MessageRepositoryImpl implements MessageRepository
{
   private final NamedParameterJdbcOperations namedParameterJdbcTemplate;
   private final KeyHolder keyHolder;

   @Autowired
   public MessageRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcTemplate, KeyHolder keyHolder)
   {
      this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
      this.keyHolder = keyHolder;
   }

   @Override
   public Message sendMessage(Message message)
   {
      String sql =
         "INSERT INTO MESSAGE                            "
        +" (MSG_FROM, MSG_TO, TEXT, IS_READ, SEND_DATE)"
        +"VALUES (:sender, :receipt, :text ,0, :sendDate)";

      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
         .addValue("sender", message.getMessageFrom())
         .addValue("receipt", message.getMessageTo())
         .addValue("text", message.getText())
         .addValue("sendDate", message.getSendDate());

      namedParameterJdbcTemplate.update(sql, mapSqlParameterSource, keyHolder, new String[]{"msg_id"});
      return getMessageById(Objects.requireNonNull(keyHolder.getKey()).longValue());
   }

   @Override
   public List<MessageResponse> getAllMessagesByReceipt(Long receiptId)
   {

      String sql =
           "SELECT                                                                   "
          +"       (SELECT USER_NAME FROM \"USER\" WHERE USR_ID = m.MSG_FROM) sender,"
          +"       (SELECT USER_NAME FROM \"USER\" WHERE USR_ID = m.MSG_TO) receipt, "
          +"       m.TEXT messageZ,                                                  "
          +"       m.SEND_DATE sendDate,                                             "
          +"       m.MSG_ID messageId                                                "
          +"  FROM MESSAGE m                                                         "
          +" WHERE m.msg_to = :receiptId                                             "
          +" ORDER BY m.SEND_DATE DESC                                               ";

      return namedParameterJdbcTemplate.query(
         sql,
         new MapSqlParameterSource("receiptId", receiptId),
         (rs, row) ->
         {
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setId(rs.getLong("messageId"));
            messageResponse.setSender(rs.getString("sender"));
            messageResponse.setReceipt(rs.getString("receipt"));
            messageResponse.setText(rs.getString("messageZ"));
            messageResponse.setSendDate(rs.getObject("sendDate", LocalDateTime.class));
            return messageResponse;
         }
      );
   }

   @Override
   public Message readMessage(Long id, LocalDateTime readDate)
   {
      String sql =
             "UPDATE MESSAGE                              "
            +"  SET IS_READ = 1, READ_DATE = :readDate    "
            +"WHERE msg_id = :id                          ";

      int updatedRow = namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource()
            .addValue("id", id)
            .addValue("readDate", readDate),
         keyHolder,
         new String[]{"msg_id"}
      );

      if (updatedRow > 0) {
         return getMessageById(Objects.requireNonNull(keyHolder.getKey()).longValue());
      }
      else {
         throw new MessageNotFoundException("The message not found");
      }
   }

   @Override
   public Message getMessageById(Long id)
   {
      String sql =
             "SELECT MSG_ID, MSG_TO,    "
            +"       MSG_FROM, TEXT,    "
            +"       IS_READ, SEND_DATE,"
            +"       READ_DATE          "
            +"  FROM MESSAGE            "
            +"WHERE msg_id = :id        ";

      MapSqlParameterSource map = new MapSqlParameterSource().addValue("id", id);

      try {
         return namedParameterJdbcTemplate.queryForObject
            (sql, map,
               (rs, row) -> new Message()
                  .setMessageId(rs.getLong("msg_id"))
                  .setMessageFrom(rs.getLong("msg_from"))
                  .setMessageTo(rs.getLong("msg_to"))
                  .setText(rs.getString("text"))
                  .setRead(rs.getBoolean("is_read"))
                  .setSendDate(rs.getObject("send_Date", LocalDateTime.class))
                  .setReadDate(rs.getObject("read_date", LocalDateTime.class))
            );
      }
      catch (EmptyResultDataAccessException ex) {
         throw new MessageNotFoundException("Message not found with ID: " + id);
      }
   }
}
