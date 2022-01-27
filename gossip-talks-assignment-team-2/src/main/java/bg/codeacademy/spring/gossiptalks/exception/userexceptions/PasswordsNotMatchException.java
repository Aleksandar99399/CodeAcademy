package bg.codeacademy.spring.gossiptalks.exception.userexceptions;

public class PasswordsNotMatchException extends RuntimeException
{
   public PasswordsNotMatchException()
   {
      super();
   }

   public PasswordsNotMatchException(String message)
   {
      super(message);
   }

   public PasswordsNotMatchException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public PasswordsNotMatchException(Throwable cause)
   {
      super(cause);
   }

   protected PasswordsNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
