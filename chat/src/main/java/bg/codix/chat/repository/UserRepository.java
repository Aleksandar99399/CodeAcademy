package bg.codix.chat.repository;

import bg.codix.chat.model.user.User;
import bg.codix.chat.model.user.UserByGroupResponse;


import java.util.List;

public interface UserRepository
{
   List<UserByGroupResponse> getAllByGroup();

   Long checkSameGroupOfUsers(Long senderId, Long receiptId);

   List<User> getUsersWhoCanSendMessageTo(String username);

   User getUserById(Long id);

   User getUserByUsername(String username);

}
