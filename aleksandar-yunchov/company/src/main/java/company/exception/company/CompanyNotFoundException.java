package company.exception.company;

public class CompanyNotFoundException extends RuntimeException
{
   public CompanyNotFoundException()
   {
      super();
   }

   public CompanyNotFoundException(String message)
   {
      super(message);
   }

   public CompanyNotFoundException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public CompanyNotFoundException(Throwable cause)
   {
      super(cause);
   }

   protected CompanyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
