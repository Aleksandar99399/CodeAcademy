package bg.codix.chat.service.impl;

import bg.codix.chat.model.user.User;
import bg.codix.chat.model.user.UserByGroupResponse;
import bg.codix.chat.repository.UserRepository;
import bg.codix.chat.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
   private final UserRepository userRepository;

   public UserServiceImpl(UserRepository userRepository)
   {
      this.userRepository = userRepository;
   }

   @Override
   public List<UserByGroupResponse> getAllByGroup()
   {
      return userRepository.getAllByGroup();
   }

   @Override
   public Long checkSameGroupOfUsers(Long userId, Long receiptId){
      return userRepository.checkSameGroupOfUsers(userId, receiptId);
   }

   @Transactional
   @Override
   public List<User> getUsersWhoCanSendMessageTo(String username)
   {
      User user = getUserByUsername(username);
      return userRepository.getUsersWhoCanSendMessageTo(user.getUsername());
   }

   @Override
   public User getUserById(Long id)
   {
      return userRepository.getUserById(id);
   }

   @Override
   public User getUserByUsername(String username)
   {
      return userRepository.getUserByUsername(username);
   }
}
