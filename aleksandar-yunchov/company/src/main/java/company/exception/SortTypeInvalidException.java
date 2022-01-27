package company.exception;

public class SortTypeInvalidException extends RuntimeException
{
   public SortTypeInvalidException()
   {
      super();
   }

   public SortTypeInvalidException(String message)
   {
      super(message);
   }

   public SortTypeInvalidException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public SortTypeInvalidException(Throwable cause)
   {
      super(cause);
   }

   protected SortTypeInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
   {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
