package restaurant.worker;

import restaurant.client.Client;

import java.math.BigDecimal;
import java.util.Scanner;

public class Barman extends Worker
{
  private static final BigDecimal SALARY_PER_DAY = BigDecimal.valueOf(30);

  public Barman()
  {
    super.setSalary(SALARY_PER_DAY);
  }

  @Override
  protected void takeOrder(Scanner scanner, Client client)
  {

  }
}
