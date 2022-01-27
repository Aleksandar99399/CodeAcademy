package bg.codeacademy.spring.gossiptalks.service.impl;

import bg.codeacademy.spring.gossiptalks.exception.userexceptions.ExistUserException;
import bg.codeacademy.spring.gossiptalks.exception.userexceptions.FollowUserException;
import bg.codeacademy.spring.gossiptalks.exception.userexceptions.PasswordsNotMatchException;
import bg.codeacademy.spring.gossiptalks.model.dto.gossip.GossipList;
import bg.codeacademy.spring.gossiptalks.model.entity.User;
import bg.codeacademy.spring.gossiptalks.repository.UserRepository;
import bg.codeacademy.spring.gossiptalks.service.GossipService;
import bg.codeacademy.spring.gossiptalks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService
{
   private final UserRepository        userRepository;
   private final GossipService         gossipService;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;


   @Autowired
   public UserServiceImpl(UserRepository userRepository, @Lazy GossipService gossipService,
                          BCryptPasswordEncoder bCryptPasswordEncoder)
   {
      this.userRepository = userRepository;
      this.gossipService = gossipService;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }

   @Override
   public List<User> getAll(boolean f, String name, String username)
   {

      List<User> all;
      User loggedUser = this.getByUsername(username);
      // sanitize parameters
      if (name != null) {
         name = name.trim();
      }
      // if follow is TRUE
      if (f) {
         //if name is not null
         if (name != null) {
            all = userRepository.findAllFriendsWithGivenSubstring(loggedUser.getId(), name);
         }
         else {
            all = userRepository.findAllFriends(loggedUser.getId());
         }
      }
      else {
         if (name != null) {
            all = userRepository.findAllByNameOrUsernameContains(name);
         }
         else {
            all = userRepository.findAll();
         }
      }

      all.remove(loggedUser);
      if (!all.isEmpty()) {
         all = all.stream()
            // sort by number of gossips
            .sorted((a, b) -> b.getGossips().size() - a.getGossips().size())
            .collect(Collectors.toList());
      }
      this.markFollowedAndUnfollowed(all, loggedUser);
      return all;
   }

   @Override
   public User save(User user)
   {
      if (userRepository.findByUsername(user.getUsername()).isPresent()) {
         throw new ExistUserException("User already exist with username: " + user.getUsername());
      }
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      return this.saveUserInDB(user);
   }

   @Override
   public User changePassword(String password, String oldPassword, String username)
   {
      User user = this.getByUsername(username);

      if (!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
         throw new PasswordsNotMatchException("Your current password is wrong");
      }
      user.setPassword(password);
      return this.saveUserInDB(user);
   }

   @Override
   public User getByUsername(String username)
   {
      return userRepository.findByUsername(username)
         .orElseThrow(() -> new ExistUserException("User not exist"));
   }

   @Override
   public User followUser(String username, Boolean follow, String currentLogged)
   {
      User logged = this.getByUsername(currentLogged);
      User userFollow = this.getByUsername(username);

      if (follow == null) {
         throw new FollowUserException("Invalid value for follow");
      }
      else if (logged.getUsername().equals(userFollow.getUsername())) {
         throw new FollowUserException("Cannot follow or unfollow yourself");
      }

      if (follow) {
         logged.getFriends().add(userFollow);
      }
      else {
         logged.getFriends().remove(userFollow);
      }
      this.saveUserInDB(logged);
      userFollow.setFollowing(follow);
      return userFollow;
   }

   private static final Collection<? extends GrantedAuthority> ROLES =
      Collections.singletonList(new SimpleGrantedAuthority("USER"));

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
   {
      User user = this.getByUsername(username);
      return new org.springframework.security.core.userdetails.User(
         user.getUsername(),
         user.getPassword(),
         ROLES);
   }

   @Override
   public User saveUserInDB(User user)
   {
      return userRepository.save(user);
   }

   @Override
   public GossipList getAllByUsernameOrderByDatetime(String username, PageRequest pageRequest)
   {
      return gossipService.getAllByUsernameOrderByDatetime(username, pageRequest);
   }

   private void markFollowedAndUnfollowed(List<User> users, User loggedUser)
   {
      for (int i = 0; i < users.size(); i++) {
         User checkUserFollowed = users.get(i);
         if (loggedUser.getFriends().contains(checkUserFollowed)) {
            checkUserFollowed.setFollowing(true);
         }
         else {
            checkUserFollowed.setFollowing(false);
         }
      }
   }
}
