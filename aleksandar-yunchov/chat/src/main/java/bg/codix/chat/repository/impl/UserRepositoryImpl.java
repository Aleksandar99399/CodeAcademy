package bg.codix.chat.repository.impl;

import bg.codix.chat.exception.GroupNotSameException;
import bg.codix.chat.exception.UserNotFoundException;
import bg.codix.chat.model.user.User;
import bg.codix.chat.model.user.UserByGroupResponse;
import bg.codix.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository
{
   private final NamedParameterJdbcOperations namedParameterJdbcTemplate;

   @Autowired
   public UserRepositoryImpl(NamedParameterJdbcOperations namedParameterJdbcTemplate)
   {
      this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
   }

   @Override
   public List<UserByGroupResponse> getAllByGroup()
   {

      String sql =
             "SELECT u.USR_ID     as userId,   "
            +"       u.USER_NAME  as userName, "
            +"       u.FIRST_NAME as firstName,"
            +"       u.LAST_NAME  as lastName, "
            +"       g.\"group\"    as groupZ  "
            +" FROM \"USER\" u                 "
            +"  JOIN USER_GROUP uGroup         "
            +"    ON u.USR_ID = uGroup.USR_ID  "
            +"  JOIN \"GROUP\" G               "
            +"    ON G.GRP_ID = uGroup.GRP_ID  "
            +" ORDER BY g.\"group\", u.USR_ID  ";

      try {
         return namedParameterJdbcTemplate.query(sql,
            (rs, row) -> {
               UserByGroupResponse user = new UserByGroupResponse();
               user.setId(rs.getLong("userId"));
               user.setUsername(rs.getString("userName"));
               user.setFirstName(rs.getString("firstName"));
               user.setLastName(rs.getString("lastName"));
               user.setGroup(rs.getString("groupZ"));
               return user;
            });
      }
      catch (EmptyResultDataAccessException ex) {
         throw new UserNotFoundException("Not found users!");
      }

   }

   @Override
   public Long checkSameGroupOfUsers(Long senderId, Long receiptId){
      String sql =
           "SELECT COUNT(USR_ID) as rowCount                        "
          +"  FROM USER_GROUP sen                                   "
          +"WHERE USR_ID = :sender AND GRP_ID IN                    "
          +"                           (SELECT rec.GRP_ID           "
          +"                              FROM USER_GROUP  rec      "
          +"                            WHERE rec.USR_ID = :receipt)";

      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
         .addValue("sender", senderId)
         .addValue("receipt", receiptId);

      try {
         return namedParameterJdbcTemplate.queryForObject(
            sql,
            mapSqlParameterSource,
            (rs, row) -> rs.getLong("rowCount"));
      }
      catch (EmptyResultDataAccessException | NullPointerException ex) {
         throw new GroupNotSameException("Sender and receipt are not in same group");
      }

   }

   @Override
   public List<UserByGroupResponse> getUsersWhoCanSendMessageTo(String username)
   {
      String sql =
          "SELECT u.USR_ID as userId,                                                 "
         +"       u.USER_NAME as userName,                                            "
         +"       u.FIRST_NAME as firstName,                                          "
         +"       u.LAST_NAME as lastName,                                            "
         +"       (SELECT \"group\" FROM \"GROUP\" WHERE GRP_ID = ug.GRP_ID) groupName"
         +"  FROM \"USER\" u                                                          "
         +"  JOIN USER_GROUP UG on u.USR_ID = UG.USR_ID                               "
         +"WHERE ug.GRP_ID IN (SELECT GRP_ID                                          "
         +"                       FROM USER_GROUP                                     "
         +"                    WHERE USR_ID IN (SELECT USR_ID                         "
         +"                                        FROM \"USER\"                      "
         +"                                     WHERE USER_NAME = :username))         ";

      try {
         return namedParameterJdbcTemplate.query(
            sql,
            new MapSqlParameterSource("username", username),
            (rs, row) ->
            {
               UserByGroupResponse user = new UserByGroupResponse();
               user.setId(rs.getLong("userId"));
               user.setUsername(rs.getString("userName"));
               user.setFirstName(rs.getString("firstName"));
               user.setLastName(rs.getString("lastName"));
               user.setGroup(rs.getString("groupName"));
               return user;
            }
         );
      }
      catch (EmptyResultDataAccessException ex) {
         throw new UserNotFoundException("Not users in same group");
      }

   }

   @Override
   public User getUserById(Long id)
   {
      String sql =
         "SELECT * FROM \"USER\" WHERE USR_ID = :id";

      try {
         return namedParameterJdbcTemplate.queryForObject(
            sql,
            new MapSqlParameterSource("id", id),
            userMapper());
      }
      catch (EmptyResultDataAccessException ex) {
         throw new UserNotFoundException("User not found with ID: " + id);
      }
   }

   @Override
   public User getUserByUsername(String username)
   {
      String sql =
         "SELECT * FROM \"USER\" WHERE user_name = :username";

      try {
         return namedParameterJdbcTemplate.queryForObject(
            sql,
            new MapSqlParameterSource("username", username),
            userMapper()
         );
      }
      catch (EmptyResultDataAccessException ex) {
         throw new UserNotFoundException("User not found with username: " + username);
      }
   }

   private RowMapper<User> userMapper()
   {
      return (rs, rowNum) ->
         new User()
            .setId(rs.getLong("usr_id"))
            .setUsername(rs.getString("user_name"))
            .setFirstName(rs.getString("first_name"))
            .setLastName(rs.getString("last_name"));
   }

}
