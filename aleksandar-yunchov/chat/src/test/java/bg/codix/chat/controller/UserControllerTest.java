package bg.codix.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(loader = SpringBootContextLoader.class)
public class UserControllerTest extends AbstractTestNGSpringContextTests
{

   @Autowired
   private MockMvc mockMvc;

   @Test
   public void getAllCorrect() throws Exception
   {
      mockMvc.perform(get("/api/v1/users"))
         .andExpect(status().isOk())
         .andDo(print());

   }

   @Test
   public void getUsersWhoCanSendMessageToStatusOkAndCorrectResponse() throws Exception
   {
      mockMvc.perform(get("/api/v1/users/{username}", "niki"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.length()").isNotEmpty())
         .andDo(print());
   }
   @Test
   public void getUsersWhoCanSendMessageThrowException() throws Exception
   {
      mockMvc.perform(get("/api/v1/users/{username}", "231"))
         .andExpect(status().isBadRequest())
         .andDo(print());
   }

   @Test
   public void getUsersWhoCanSendMessageToStatusOk() throws Exception
   {
      mockMvc.perform(get("/api/v1/users/{username}", "niki"))
         .andExpect(status().isOk())
         .andDo(print());
   }

   @Test
   public void getUserMessages() throws Exception
   {
      mockMvc.perform(get("/api/v1/users/{username}/messages", "ivka"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.length()").isNotEmpty())
         .andDo(print());
   }

   @Test
   public void getUserMessagesThrowException() throws Exception
   {
      mockMvc.perform(get("/api/v1/users/{username}/messages", "321"))
         .andExpect(status().isBadRequest())
         .andDo(print());
   }



}
