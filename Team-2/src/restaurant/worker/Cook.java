package restaurant.worker;

import restaurant.client.Client;

import java.math.BigDecimal;
import java.util.Scanner;

public class Cook extends Worker
{
  public Cook()
  {
    setWorkerName("Penka");
    setSalary(new BigDecimal(25));
    setTips(new BigDecimal(15));
  }

  @Override
  protected void takeOrder(Scanner scanner, Client client)
  {

  }
}
