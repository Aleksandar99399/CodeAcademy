package bg.codix.chat.exception;

public class GroupNotSameException extends RuntimeException
{
   public GroupNotSameException()
   {
      super();
   }

   public GroupNotSameException(String message)
   {
      super(message);
   }

   public GroupNotSameException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public GroupNotSameException(Throwable cause)
   {
      super(cause);
   }

   protected GroupNotSameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
