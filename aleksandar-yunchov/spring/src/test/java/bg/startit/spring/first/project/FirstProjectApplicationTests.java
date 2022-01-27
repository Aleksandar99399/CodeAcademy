package bg.startit.spring.first.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({WithSecurityContextTestExecutionListener.class})
public class FirstProjectApplicationTests extends AbstractTestNGSpringContextTests
{

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void given_unauthorized_access_When_getting_user_Then_status_unauthorized() throws Exception
	{
		mockMvc.perform(get("/api/v1/users/{id}", 0))
				.andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(roles = "USER")
	public void given_librarian_access_When_getting_user_Then_status_ok() throws Exception
	{
		mockMvc.perform(get("/api/v1/users/{id}", 4).with(user("admin@startit.bg")))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
