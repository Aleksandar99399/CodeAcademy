package company.exception.reference;

public class ReferenceNotFoundException extends RuntimeException
{
   public ReferenceNotFoundException()
   {
      super();
   }

   public ReferenceNotFoundException(String message)
   {
      super(message);
   }

   public ReferenceNotFoundException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public ReferenceNotFoundException(Throwable cause)
   {
      super(cause);
   }

   protected ReferenceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
