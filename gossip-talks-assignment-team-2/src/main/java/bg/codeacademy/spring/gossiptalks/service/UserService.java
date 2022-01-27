package bg.codeacademy.spring.gossiptalks.service;

import bg.codeacademy.spring.gossiptalks.model.dto.gossip.GossipList;
import bg.codeacademy.spring.gossiptalks.model.entity.User;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService
{
   List<User> getAll(boolean f, String name, String username);

   User save(User userRequest);

   User changePassword(String password, String oldPassword, String username);

   User getByUsername(String username);

   User followUser(String username, Boolean follow, String currentLogged);

   User saveUserInDB(User user);

   GossipList getAllByUsernameOrderByDatetime(String username, PageRequest pageRequest);
}
