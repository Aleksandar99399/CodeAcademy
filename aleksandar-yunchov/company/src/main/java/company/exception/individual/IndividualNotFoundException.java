package company.exception.individual;

public class IndividualNotFoundException extends RuntimeException
{

   public IndividualNotFoundException()
   {
      super();
   }

   public IndividualNotFoundException(String message)
   {
      super(message);
   }

   public IndividualNotFoundException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public IndividualNotFoundException(Throwable cause)
   {
      super(cause);
   }

   protected IndividualNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
