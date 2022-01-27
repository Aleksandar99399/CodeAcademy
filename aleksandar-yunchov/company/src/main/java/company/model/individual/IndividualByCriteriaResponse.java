package company.model.individual;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class IndividualByCriteriaResponse
{
   private String        individualName;
   private String        individualType;
   private Long          externalId;
   private BigDecimal    amount = new BigDecimal(0);
   private ZonedDateTime issueDate;
   private String        companyName;

   public IndividualByCriteriaResponse()
   {
   }

   public String getIndividualName()
   {
      return individualName;
   }

   public IndividualByCriteriaResponse setIndividualName(String individualName)
   {
      this.individualName = individualName;
      return this;
   }

   public String getIndividualType()
   {
      return individualType;
   }

   public IndividualByCriteriaResponse setIndividualType(String individualType)
   {
      this.individualType = individualType;
      return this;
   }

   public Long getExternalId()
   {
      return externalId;
   }

   public IndividualByCriteriaResponse setExternalId(Long externalId)
   {
      this.externalId = externalId;
      return this;
   }

   public BigDecimal getAmount()
   {
      return amount;
   }

   public IndividualByCriteriaResponse setAmount(BigDecimal amount)
   {
      this.amount = amount;
      return this;
   }

   public ZonedDateTime getIssueDate()
   {
      return issueDate;
   }

   public IndividualByCriteriaResponse setIssueDate(ZonedDateTime issueDate)
   {
      this.issueDate = issueDate;
      return this;
   }

   public String getCompanyName()
   {
      return companyName;
   }

   public IndividualByCriteriaResponse setCompanyName(String companyName)
   {
      this.companyName = companyName;
      return this;
   }
}
