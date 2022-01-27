package company.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.CompanyApplication;
import company.model.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CompanyApplication.class)
//@AutoConfigureMockMvc
@SqlGroup({
   @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
      statements = "insert into COMPANIES (company_id, name) values (241,'Vendem');"),
   @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
      statements = "delete from COMPANIES where COMPANY_ID = 241;") })
public class CompanyControllerTest extends AbstractTransactionalTestNGSpringContextTests
{

   private MockMvc mockMvc;

   @Autowired
   private WebApplicationContext webApplicationContext;

   @BeforeClass
   public void setup()
   {
      mockMvc = MockMvcBuilders
         .webAppContextSetup(webApplicationContext)
//         .apply(springSecurity()) // only if you test security as well
         .build();
   }

   @Test
   public void getAllCompaniesCorrect() throws Exception
   {
      mockMvc.perform(get("/api/v1/company"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getCompanyByIdCorrect() throws Exception
   {

      mockMvc.perform(get("/api/v1/company/241"))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.name").value("Vendem"));

   }

   @Test
   public void getCompanyByIdIncorrect() throws Exception
   {
      mockMvc.perform(get("/api/v1/company/009999"))
         .andDo(print())
         .andExpect(status().isBadRequest());

   }
   @Test
   public void createCompanyCorrect() throws Exception
   {
      mockMvc.perform(post("/api/v1/company")
         .param("companyName", "Fake"))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.name").value("Fake"));

   }

   @Test
   public void createCompanyIncorrect() throws Exception
   {
      mockMvc.perform(post("/api/v1/company")
         .param("companyName", (String) null))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void updateCompanyCorrect() throws Exception
   {

      Company company = new Company()
         .setName("Dasko");

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(company);

      mockMvc.perform(put("/api/v1/company/241").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.name").value("Dasko"));
   }

   @Test
   public void updateCompanyWithIncorrectId() throws Exception
   {
      Company company = new Company()
         .setName("DASKO");

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(company);

      mockMvc.perform(put("/api/v1/company/00321").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());

   }

   @Test
   public void updateCompanyWithIncorrectCompanyName() throws Exception
   {
      Company company = new Company()
         .setName(null);

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(company);

      mockMvc.perform(put("/api/v1/company/00321").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());

   }

   @Test
   public void deleteCompanyCorrect() throws Exception
   {
      mockMvc.perform(delete("/api/v1/company/241"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void deleteCompanyIncorrect() throws Exception
   {
      mockMvc.perform(delete("/api/v1/company/-1223"))
         .andDo(print())
         .andExpect(status().isBadRequest());

   }

}
