package company.model.invoice;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Invoice
{
   private Long          invoiceId;
   private Long          indivId;
   private BigDecimal    amount = BigDecimal.valueOf(0);
   private ZonedDateTime issueDate;

   public Invoice()
   {
   }

   public Long getInvoiceId()
   {
      return invoiceId;
   }

   public Invoice setInvoiceId(Long invoiceId)
   {
      this.invoiceId = invoiceId;
      return this;
   }

   public Long getIndivId()
   {
      return indivId;
   }

   public Invoice setIndivId(Long indivId)
   {
      this.indivId = indivId;
      return this;
   }

   @DecimalMin(value = "0", message = "Amount cannot be <= 0")
   public BigDecimal getAmount()
   {
      return amount;
   }

   public Invoice setAmount(BigDecimal amount)
   {
      this.amount = amount;
      return this;
   }

   public ZonedDateTime getIssueDate()
   {
      return issueDate;
   }

   public Invoice setIssueDate(ZonedDateTime issueDate)
   {
      this.issueDate = issueDate;
      return this;
   }
}
