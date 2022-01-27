package restaurant.exceptions;

public class RestaurantFullException extends Exception
{
  public RestaurantFullException(String message)
  {
    super(message);
  }
}
