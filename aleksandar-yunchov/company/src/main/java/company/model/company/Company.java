package company.model.company;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class Company
{
   private Long companyId;
   private String name;

   public Company()
   {
   }

   public Long getCompanyId()
   {
      return companyId;
   }

   public Company setCompanyId(Long companyId)
   {
      this.companyId = companyId;
      return this;
   }

   @Length(max = 60, message = "Too long name for company name")
   @NotBlank(message = "Company name cannot be null or empty field")
   public String getName()
   {
      return name;
   }

   public Company setName(String name)
   {
      this.name = name;
      return this;
   }
}
