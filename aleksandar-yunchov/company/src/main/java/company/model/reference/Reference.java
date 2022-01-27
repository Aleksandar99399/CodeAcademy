package company.model.reference;

public class Reference
{
   private Long indivId;
   private Long externalRef;

   public Reference()
   {
   }

   public Long getIndivId()
   {
      return indivId;
   }

   public Reference setIndivId(Long indivId)
   {
      this.indivId = indivId;
      return this;
   }

   public Long getExternalRef()
   {
      return externalRef;
   }

   public Reference setExternalRef(Long externalRef)
   {
      this.externalRef = externalRef;
      return this;
   }
}
