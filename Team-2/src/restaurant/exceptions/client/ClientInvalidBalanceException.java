package restaurant.exceptions.client;

public class ClientInvalidBalanceException extends Exception
{
  public ClientInvalidBalanceException(String message)
  {
    super(message);
  }
}
