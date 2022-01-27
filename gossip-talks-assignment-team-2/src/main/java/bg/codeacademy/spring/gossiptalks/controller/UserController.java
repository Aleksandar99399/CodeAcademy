package bg.codeacademy.spring.gossiptalks.controller;


import bg.codeacademy.spring.gossiptalks.model.dto.gossip.GossipList;
import bg.codeacademy.spring.gossiptalks.model.dto.user.ChangePasswordRequest;
import bg.codeacademy.spring.gossiptalks.model.dto.user.CreateUserRequest;
import bg.codeacademy.spring.gossiptalks.model.dto.user.UserResponse;
import bg.codeacademy.spring.gossiptalks.model.entity.User;
import bg.codeacademy.spring.gossiptalks.service.UserService;
import bg.codeacademy.spring.gossiptalks.validation.group.ValidationSequence;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@Validated(ValidationSequence.class)
public class UserController
{
   private final UserService userService;
   private final ModelMapper modelMapper;

   @Autowired
   public UserController(UserService userService, ModelMapper modelMapper)
   {
      this.userService = userService;
      this.modelMapper = modelMapper;
   }

   @GetMapping
   public ResponseEntity<List<UserResponse>> getUsers(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) boolean f,
      Principal principal)
   {

      List<UserResponse> all = userService.getAll(f, name, principal.getName())
         .stream().map(u -> modelMapper.map(u, UserResponse.class))
         .collect(Collectors.toList());

      return ResponseEntity.ok(all);
   }

   @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ResponseEntity<UserResponse> createUser(@Valid CreateUserRequest createUserRequest,
                                                  BindingResult bindingResult)
   {
      User userRequest = this.modelMapper.map(createUserRequest, User.class);
      User save = userService.save(userRequest);
      UserResponse userResponse = this.modelMapper.map(save, UserResponse.class);
      return ResponseEntity.ok(userResponse);
   }

   @GetMapping("/me")
   public ResponseEntity<UserResponse> getCurrentLoggedUser(Principal principal)
   {
      User currentLoggedUser = userService.getByUsername(principal.getName());
      UserResponse userResponse = modelMapper.map(currentLoggedUser, UserResponse.class);
      return ResponseEntity.ok(userResponse);
   }

   @RequestMapping(path = "/me", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ResponseEntity<UserResponse> changeCurrentUserPassword(@Valid ChangePasswordRequest passwordRequest,
                                                                 BindingResult bindingResult,
                                                                 Principal principal
   )
   {
      User user = userService.changePassword(passwordRequest.getPassword(), passwordRequest.getOldPassword(), principal.getName());
      UserResponse userResponse = modelMapper.map(user, UserResponse.class);
      return ResponseEntity.ok(userResponse);
   }

   @RequestMapping(path = "/{username}/follow", method = RequestMethod.POST,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ResponseEntity<UserResponse> userFollow(
      @PathVariable("username") String username,
      @RequestParam Boolean follow,
      Principal principal
   )
   {
      User user = userService.followUser(username, follow, principal.getName());
      UserResponse userResponse = modelMapper.map(user, UserResponse.class);
      return ResponseEntity.ok(userResponse);
   }

   @GetMapping("/{username}/gossips")
   public ResponseEntity<GossipList> getUserGossips(@Valid
                                                    @Pattern(regexp = "^[a-z0-9.\\-]+$",
                                                       message = "Username is not valid! (a-z0-9.-)")
                                                    @PathVariable("username") String username,
                                                    @Min(value = 0) @RequestParam(defaultValue = "0") Integer pageNo,
                                                    @Min(value = 1) @Max(50) @RequestParam(defaultValue = "20")
                                                       Integer pageSize)
   {
      PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
      GossipList allGossipsByUser = userService.getAllByUsernameOrderByDatetime(username, pageRequest);
      return ResponseEntity.ok(allGossipsByUser);
   }

}
