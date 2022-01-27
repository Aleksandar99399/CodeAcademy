package company.exception.company;

public class CompanyInvalidNameException extends RuntimeException
{
   public CompanyInvalidNameException()
   {
      super();
   }

   public CompanyInvalidNameException(String message)
   {
      super(message);
   }

   public CompanyInvalidNameException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public CompanyInvalidNameException(Throwable cause)
   {
      super(cause);
   }

   protected CompanyInvalidNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
