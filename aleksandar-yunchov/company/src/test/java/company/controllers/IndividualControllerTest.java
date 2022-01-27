package company.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.model.individual.CreateIndividualRequest;
import company.model.individual.UpdateIndividualRequest;
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

@SpringBootTest
@SqlGroup({
   @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
      statements = {
         "insert into COMPANIES (company_id, name) values (241,'Vendem');",
         "insert into INDIVIDUAL  values (100,'Iordan Iordanov','LEGAL',241);"
      }),
   @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
      statements = {
         "delete from COMPANIES where COMPANY_ID = 241;",
         "delete from INDIVIDUAL where INDIV_ID = 100;"
      })})
public class IndividualControllerTest extends AbstractTransactionalTestNGSpringContextTests
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
   public void getAllIndividuals() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getIndividualByIdCorrect() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/100"))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.name").value("Iordan Iordanov"))
         .andExpect(jsonPath("$.indivId").value(100));
   }

   @Test
   public void getIndividualByIdWithInvalidId() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/-3213"))
         .andDo(print())
         .andExpect(status().isBadRequest());

   }

   @Test
   public void createIndividualOk() throws Exception
   {

      CreateIndividualRequest request =
         new CreateIndividualRequest()
            .setName("Dragan Ivanov")
            .setCompanyId(241L)
            .setTypeEntity("legal");

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);

      mockMvc.perform(post("/api/v1/individual").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.name").value("Dragan Ivanov"));
   }

   @Test
   public void createIndividualWithWrongNameBadRequest() throws Exception
   {

      CreateIndividualRequest request =
         new CreateIndividualRequest()
            .setName(null)
            .setCompanyId(241L)
            .setTypeEntity("legal");

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);

      mockMvc.perform(post("/api/v1/individual").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void createIndividualWithWrongTypeEntityBadRequest() throws Exception
   {

      CreateIndividualRequest request =
         new CreateIndividualRequest()
            .setName("Boris Ivanov")
            .setCompanyId(241L)
            .setTypeEntity("sadasd");

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);

      mockMvc.perform(post("/api/v1/individual").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }
   @Test
   public void createIndividualWithWrongCompanyIdBadRequest() throws Exception
   {

      CreateIndividualRequest request =
         new CreateIndividualRequest()
            .setName("Boris Ivanov")
            .setCompanyId(null)
            .setTypeEntity("sadasd");

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);

      mockMvc.perform(post("/api/v1/individual").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void updateIndividualOk() throws Exception
   {

      UpdateIndividualRequest request =
         new UpdateIndividualRequest()
            .setName("Frederico Varela")
            .setTypeEntity("physical")
            .setCompanyId(241L);

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);

      mockMvc.perform(patch("/api/v1/individual/100").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.name").value("Frederico Varela"));
   }

   @Test
   public void updateIndividualWithWrongNameBadRequest() throws Exception
   {

      UpdateIndividualRequest request =
         new UpdateIndividualRequest()
            .setName("dd")
            .setTypeEntity("physical")
            .setCompanyId(241L);

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);

      mockMvc.perform(patch("/api/v1/individual/100").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void updateIndividualWithWrongTypeEntityBadRequest() throws Exception
   {

      UpdateIndividualRequest request =
         new UpdateIndividualRequest()
            .setName("Bendji")
            .setTypeEntity("sadasds")
            .setCompanyId(241L);

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);

      mockMvc.perform(patch("/api/v1/individual/100").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void updateIndividualWithWrongCompanyIdBadRequest() throws Exception
   {

      UpdateIndividualRequest request =
         new UpdateIndividualRequest()
            .setName("Bendji")
            .setTypeEntity("sadasds")
            .setCompanyId(-323L);

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);

      mockMvc.perform(patch("/api/v1/individual/100").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void updateIndividualWithWrongIndivIdInPathBadRequest() throws Exception
   {

      UpdateIndividualRequest request =
         new UpdateIndividualRequest()
            .setName("Bendji")
            .setTypeEntity("sadasds")
            .setCompanyId(-323L);

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);

      mockMvc.perform(patch("/api/v1/individual/asdas").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void deleteIndividualOk() throws Exception
   {
      mockMvc.perform(delete("/api/v1/individual/100"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void deleteIndividualWithWrongIndivIdInPathBadRequest() throws Exception
   {
      mockMvc.perform(delete("/api/v1/individual/dsad"))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void getIndividualsByCriteriaWithPageAndSize() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/search_criteria")
         .queryParam("page", "2")
         .queryParam("size", "3"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getIndividualsByCriteriaWithoutParameters() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/search_criteria"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getIndividualsByCriteriaWithSortTypeCompanyNameOk() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/search_criteria")
         .param("sort","company_name"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getIndividualsByCriteriaWithSortTypeIssueDateOk() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/search_criteria")
         .param("sort","issue_date"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getIndividualsByCriteriaWithSortTypeIndivNameOk() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/search_criteria")
         .param("sort","indiv_name"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getIndividualsByCriteriaWitInvalidSortTypeBadRequest() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/search_criteria")
         .param("sort","sadsdfs"))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void getIndividualsByCriteriaWithSearchTypeCompanyNameOk() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/search_criteria")
         .param("company_name","ec"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getIndividualsByCriteriaWithSearchTypeIndivNameOk() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/search_criteria")
         .param("indiv_name","fi"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getIndividualsByCriteriaWithInvalidSearchTypeDefaultSortIssueDateOk() throws Exception
   {
      mockMvc.perform(get("/api/v1/individual/search_criteria")
         .param("sad","fdsfd"))
         .andDo(print())
         .andExpect(status().isOk());
   }

}
