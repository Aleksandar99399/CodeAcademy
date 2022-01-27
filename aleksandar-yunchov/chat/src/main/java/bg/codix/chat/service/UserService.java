package bg.codix.chat.service;

import bg.codix.chat.model.user.User;
import bg.codix.chat.model.user.UserByGroupResponse;

import java.util.List;

public interface UserService
{
   List<UserByGroupResponse> getAllByGroup();

   Long checkSameGroupOfUsers(Long userId, Long receiptId);

   List<UserByGroupResponse> getUsersWhoCanSendMessageTo(String username);

   User getUserById(Long id);

   User getUserByUsername(String username);
}
