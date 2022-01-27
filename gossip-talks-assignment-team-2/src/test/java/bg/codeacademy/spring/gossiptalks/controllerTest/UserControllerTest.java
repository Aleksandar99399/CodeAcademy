package bg.codeacademy.spring.gossiptalks.controllerTest;


import bg.codeacademy.spring.gossiptalks.model.dto.user.CreateUserRequest;
import bg.codeacademy.spring.gossiptalks.model.entity.User;
import bg.codeacademy.spring.gossiptalks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.*;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({WithSecurityContextTestExecutionListener.class})
@ContextConfiguration(loader = SpringBootContextLoader.class)
public class UserControllerTest extends AbstractTestNGSpringContextTests
{
   @Autowired
   private MockMvc        mockMvc;
   @Autowired
   private UserRepository userRepository;

   private CreateUserRequest newUser(String id)
   {
      return new CreateUserRequest(id + "@gmail.com", id, id + "Name", id, id);
   }


   @Test
//   @WithMockUser(username = "admin")
   public void create_User_correct() throws Exception
   {
      CreateUserRequest user = newUser("newuser");
      mockMvc.perform(multipart("/api/v1/users")
         .param("email", user.getEmail())
         .param("name", user.getName())
         .param("username", user.getUsername())
         .param("password", user.getPassword())
         .param("passwordConfirmation", user.getPasswordConfirmation()))
         .andDo(print())
         .andExpect(status().isOk());

      Optional<User> newuser = userRepository.findByUsername("newuser");
      userRepository.deleteById(newuser.get().getId());

   }

   @Test
   @WithMockUser("admin")
   public void get_current_logged_user() throws Exception
   {
      mockMvc.perform(get("/api/v1/users/me"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.username").value("admin"));

   }

   @Test
   @WithMockUser(username = "admin")
   public void get_all_users_without_params() throws Exception
   {
      mockMvc.perform(get("/api/v1/users"))
         .andExpect(status().isOk());

   }

   @Test
   public void get_all_users_with_name_params_unauthorized() throws Exception
   {

      mockMvc.perform(get("/api/v1/users?name=jorko"))
         .andExpect(status().isUnauthorized());

   }

   @Test
   @WithMockUser("admin")
   public void get_Users_correct() throws Exception
   {
      mockMvc.perform(get("/api/v1/users"))
         .andDo(print())
         .andExpect(status().isOk());
   }
}
