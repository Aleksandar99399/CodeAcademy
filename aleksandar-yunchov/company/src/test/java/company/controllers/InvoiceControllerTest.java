package company.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import company.CompanyApplication;
import company.model.invoice.Invoice;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CompanyApplication.class)
@SqlGroup({
   @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
      statements = {
         "insert into COMPANIES values (54, 'INDIGO')",
         "insert into INDIVIDUAL values (200, 'Ivan Sokachev','LEGAL', 54);",
         "insert into INVOICES values (450,1000,'2021-11-10 08:56:55.879000 +02:00',200)"}),
   @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
      statements = {
         "delete from COMPANIES where COMPANY_ID = 54;",
         "delete from INDIVIDUAL where INDIV_ID = 200;",
         "delete from INVOICES where INVOICE_ID = 450"})
})
public class InvoiceControllerTest extends AbstractTransactionalTestNGSpringContextTests
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
   public void getAllInvoices() throws Exception
   {
      mockMvc.perform(get("/api/v1/invoice"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void getInvoiceByIdOk() throws Exception
   {
      mockMvc.perform(get("/api/v1/invoice/450"))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.invoiceId").value(450));
   }

   @Test
   public void getInvoiceByIdWithWrongIdInPathBadRequest() throws Exception
   {
      mockMvc.perform(get("/api/v1/invoice/-3213"))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void createInvoiceOk() throws Exception
   {
      Invoice request =
         new Invoice()
            .setAmount(BigDecimal.valueOf(1000))
            .setIndivId(200L);

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);
      mockMvc.perform(post("/api/v1/invoice").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.indivId").value(200));

   }

   @Test
   public void createInvoiceWithWrongSumOfAmountBadRequest() throws Exception
   {
      Invoice request =
         new Invoice()
            .setAmount(BigDecimal.valueOf(-2321))
            .setIndivId(200L);

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);
      mockMvc.perform(post("/api/v1/invoice").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());

   }

   @Test
   public void createInvoiceWithWrongIndivIdBadRequest() throws Exception
   {
      Invoice request =
         new Invoice()
            .setAmount(BigDecimal.valueOf(32132))
            .setIndivId(-312L);

      ObjectMapper mapper = new ObjectMapper();
      String jsonData = mapper.writeValueAsString(request);
      mockMvc.perform(post("/api/v1/invoice").content(jsonData).contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isBadRequest());

   }

   @Test
   public void updateInvoiceOk() throws Exception
   {
      mockMvc.perform(patch("/api/v1/invoice/450/decrease_amount")
         .param("amount", "500"))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.amount").value(500));
   }

   @Test
   public void updateInvoiceWrongSumForAmountBadRequest() throws Exception
   {
      mockMvc.perform(patch("/api/v1/invoice/450/decrease_amount")
         .param("amount", "-321"))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void deleteInvoiceOk() throws Exception
   {
      mockMvc.perform(delete("/api/v1/invoice/450"))
         .andDo(print())
         .andExpect(status().isOk());
   }

   @Test
   public void deleteInvoiceWithWrongId() throws Exception
   {
      mockMvc.perform(delete("/api/v1/invoice/sdsad"))
         .andDo(print())
         .andExpect(status().isBadRequest());
   }

   @Test
   public void getAllInvoicesAmountByCompanyOk() throws Exception
   {
      mockMvc.perform(get("/api/v1/invoice/amount_by_company"))
         .andDo(print())
         .andExpect(status().isOk());
   }
}
