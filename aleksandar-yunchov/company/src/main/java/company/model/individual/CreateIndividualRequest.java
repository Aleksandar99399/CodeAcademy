package company.model.individual;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateIndividualRequest
{
   private String name;
   private String typeEntity;
   private Long companyId;

   public CreateIndividualRequest()
   {
   }

   @Length(min = 3, max = 100, message = "Too long or too short name")
   @NotBlank(message = "Name cannot be null or empty")
   public String getName()
   {
      return name;
   }

   public CreateIndividualRequest setName(String name)
   {
      this.name = name;
      return this;
   }

   @NotBlank(message = "Type cannot be null or empty")
   public String getTypeEntity()
   {
      return typeEntity;
   }

   public CreateIndividualRequest setTypeEntity(String typeEntity)
   {
      this.typeEntity = typeEntity;
      return this;
   }

   @NotNull(message = "Company ID cannot be null")
   public Long getCompanyId()
   {
      return companyId;
   }

   public CreateIndividualRequest setCompanyId(Long companyId)
   {
      this.companyId = companyId;
      return this;
   }
}
