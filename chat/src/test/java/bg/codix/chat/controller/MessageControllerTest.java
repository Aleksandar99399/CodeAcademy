package bg.codix.chat.controller;
import bg.codix.chat.model.message.SendMessageRequest;
import bg.codix.chat.model.message.SendMessageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({WithSecurityContextTestExecutionListener.class})
@ContextConfiguration(loader = SpringBootContextLoader.class)
public class MessageControllerTest extends AbstractTestNGSpringContextTests
{

   @Autowired
   private MockMvc mockMvc;

   @Test
   public void readMessageWithValidId() throws Exception
   {
      mockMvc.perform(get("/api/v1/messages/{message_id}",34))
         .andExpect(status().isOk())
         .andDo(print());
   }

   @Test
   public void readMessageWithWrongId() throws Exception
   {
      mockMvc.perform(get("/api/v1/messages/{message_id}","fadsa"))
         .andExpect(status().isBadRequest())
         .andDo(print());
   }

   @Test
   public void sendMessageOk() throws Exception
   {
      SendMessageRequest request = new SendMessageRequest();
      request.setSender(1L);
      request.setReceipt(3L);
      request.setText("Text From Test");

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);


      mockMvc.perform(post("/api/v1/messages").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.sender").value("niki"))
         .andDo(print());
   }

   @Test
   public void sendMessageBadRequest() throws Exception
   {
      SendMessageRequest request = new SendMessageRequest();
      request.setSender(2L);
      request.setReceipt(3L);
      request.setText("Text From Test");

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);


      mockMvc.perform(post("/api/v1/messages").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isBadRequest())
         .andDo(print());
   }
}
