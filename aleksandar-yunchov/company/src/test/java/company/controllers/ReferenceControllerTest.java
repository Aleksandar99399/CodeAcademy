package company.controllers;

import company.CompanyApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
@SqlGroup({
   @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
      statements = {"insert into COMPANIES values (54, 'INDIGO')",
         "insert into INDIVIDUAL values (200, 'Ivan Sokachev','LEGAL', 54);",
         "insert into REFERENCES (EXT_REF, INDIV_ID) values (241, 200);"}),
   @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
      statements = "delete from REFERENCES where EXT_REF = 241;")})
public class ReferenceControllerTest extends AbstractTransactionalTestNGSpringContextTests
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
   public void getAllReferences() throws Exception
   {
      mockMvc.perform(get("/api/v1/reference"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getReferenceByIdCorrect() throws Exception
   {
      mockMvc.perform(get("/api/v1/reference/241"))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.externalRef").value(241));

   }

   @Test
   public void getReferenceByIdWithInvalidId() throws Exception
   {
      mockMvc.perform(get("/api/v1/reference/-231"))
         .andDo(print())
         .andExpect(status().isBadRequest());

   }

   @Test
   public void createReference() throws Exception
   {
      mockMvc.perform(post("/api/v1/reference")
         .queryParam("indivId", "200"))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.indivId").value(200));

   }

   @Test
   public void createReferenceWithInvalidIndividualId() throws Exception
   {
      mockMvc.perform(post("/api/v1/reference")
         .queryParam("indivId", "-321"))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void updateReferenceWithInvalidIndivId() throws Exception
   {
      mockMvc.perform(patch("/api/v1/reference/241")
         .queryParam("indivId", "-321"))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void updateReferenceWithInvalidExternalId() throws Exception
   {
      mockMvc.perform(patch("/api/v1/reference/-231231")
         .queryParam("indivId", "-321"))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void updateReferenceCorrect() throws Exception
   {
      mockMvc.perform(patch("/api/v1/reference/241")
         .queryParam("indivId", ""))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   //TODO DELETE
   @Test
   public void deleteReferenceOk() throws Exception
   {
      mockMvc.perform(delete("/api/v1/reference/241"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void deleteReferenceWithWrongId() throws Exception
   {
      mockMvc.perform(delete("/api/v1/reference/-2313"))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }




}
