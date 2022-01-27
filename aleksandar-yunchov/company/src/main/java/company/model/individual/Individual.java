package company.model.individual;

import company.model.TypeEntity;

public class Individual
{
   private Long       indivId;
   private String     name;
   private TypeEntity typeEntity;
   private Long    companyId;

   public Individual()
   {
   }

   public Long getIndivId()
   {
      return indivId;
   }

   public Individual setIndivId(Long indivId)
   {
      this.indivId = indivId;
      return this;
   }

   public String getName()
   {
      return name;
   }

   public Individual setName(String name)
   {
      this.name = name;
      return this;
   }

   public TypeEntity getTypeEntity()
   {
      return typeEntity;
   }

   public Individual setTypeEntity(TypeEntity typeEntity)
   {
      this.typeEntity = typeEntity;
      return this;
   }

   public Long getCompanyId()
   {
      return companyId;
   }

   public Individual setCompanyId(Long companyId)
   {
      this.companyId = companyId;
      return this;
   }
}
