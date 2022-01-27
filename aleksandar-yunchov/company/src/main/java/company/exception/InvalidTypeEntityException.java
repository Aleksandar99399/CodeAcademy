package company.exception;

public class InvalidTypeEntityException extends RuntimeException
{
   public InvalidTypeEntityException()
   {
      super();
   }

   public InvalidTypeEntityException(String message)
   {
      super(message);
   }

   public InvalidTypeEntityException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public InvalidTypeEntityException(Throwable cause)
   {
      super(cause);
   }

   protected InvalidTypeEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
