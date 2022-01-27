package bg.startit.spring.first.project.users;


import bg.startit.user.dto.RegisterUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({WithSecurityContextTestExecutionListener.class})
public class UserControllerTest extends AbstractTestNGSpringContextTests
{
  @Autowired
  MockMvc mockMvc;

  @Test
  @WithMockUser(roles = "ADMIN")
  public void getUserById_ReturnsOk_IfRoleUser() throws Exception
  {
    mockMvc.perform(get("/api/v1/users/4"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("admin@startit.bg"))
        .andExpect(jsonPath("$.firstName").value("Ivan"))
        .andExpect(jsonPath("$.lastName").value("Ivanov"));

  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void registerNewUser_Succeeds_IfUserIsValid() throws Exception
  {
     RegisterUserDto dto = new RegisterUserDto()
        .setEmail("sthfhsd@abv.bg")
        .setPassword("Trustno1.")
        .setConfirmPassword("Trustno1.");
    ObjectMapper mapper = new ObjectMapper();
    String jsonData = mapper.writeValueAsString(dto);

    String location = mockMvc.perform(post("/api/v1/users/register").content(jsonData).contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(header().exists(HttpHeaders.LOCATION))
        .andReturn().getResponse().getHeader(HttpHeaders.LOCATION);

    mockMvc.perform(get(location))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("sthfhsd@abv.bg"))
        .andExpect(jsonPath("$.firstName").isEmpty())
        .andExpect(jsonPath("$.password").doesNotExist());

  }
}