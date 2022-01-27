package bg.codix.chat.controller;

import bg.codix.chat.model.user.User;
import bg.codix.chat.service.UserService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
   private MockMvc mockMvc;

   private UserService userService;

   @Test
   public void getAllCorrect() throws Exception
   {
      mockMvc.perform(get("/api/v1/users"))
         .andExpect(status().isOk())
         .andDo(print());

   }

   @Test
   public void getUsersWhoCanSendMessageTo() throws Exception
   {
      mockMvc.perform(get("/api/v1/users/{username}", "niki"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.length()").value(3))
         .andDo(print());
   }

   @Test
   public void getUserMessages() throws Exception
   {
      mockMvc.perform(get("/api/v1/users/{username}/messages", "ivka"))
         .andExpect(status().isOk())
         .andDo(print());
   }



}
