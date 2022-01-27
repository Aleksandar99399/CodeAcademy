package company.model.invoice;

import java.math.BigDecimal;

public class InvoiceEachCompanyResponse
{
   private BigDecimal bigDecimal = new BigDecimal(0);
   private String company;

   public InvoiceEachCompanyResponse()
   {
   }

   public BigDecimal getBigDecimal()
   {
      return bigDecimal;
   }

   public InvoiceEachCompanyResponse setBigDecimal(BigDecimal bigDecimal)
   {
      this.bigDecimal = bigDecimal;
      return this;
   }

   public String getCompany()
   {
      return company;
   }

   public InvoiceEachCompanyResponse setCompany(String company)
   {
      this.company = company;
      return this;
   }
}
