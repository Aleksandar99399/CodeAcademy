package bg.codeacademy.spring.gossiptalks.exception.userexceptions;

public class FollowUserException extends RuntimeException
{
   public FollowUserException()
   {
      super();
   }

   public FollowUserException(String message)
   {
      super(message);
   }

   public FollowUserException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public FollowUserException(Throwable cause)
   {
      super(cause);
   }

   protected FollowUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
