package bg.startit.exception.userexceptions;

public class InvalidUserRoleException extends RuntimeException
{
   public InvalidUserRoleException()
   {
      super();
   }

   public InvalidUserRoleException(String message)
   {
      super(message);
   }

   public InvalidUserRoleException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public InvalidUserRoleException(Throwable cause)
   {
      super(cause);
   }

   protected InvalidUserRoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
