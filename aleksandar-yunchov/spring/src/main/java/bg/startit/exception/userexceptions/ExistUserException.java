package bg.startit.exception.userexceptions;

public class ExistUserException extends RuntimeException
{
   public ExistUserException()
   {
      super();
   }

   public ExistUserException(String message)
   {
      super(message);
   }

   public ExistUserException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public ExistUserException(Throwable cause)
   {
      super(cause);
   }

   protected ExistUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
