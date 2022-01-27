package restaurant.worker;

import restaurant.Order;
import restaurant.client.Client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Worker
{

  private String      workerName;
  private BigDecimal  salary;
  private BigDecimal  tips;
  private List<Order> orders;

  public Worker()
  {
    orders = new ArrayList<>();
    tips = new BigDecimal(0);
  }

  public String getWorkerName()
  {
    return workerName;
  }

  public void setWorkerName(String workerName)
  {
    this.workerName = workerName;
  }

  public BigDecimal getSalary()
  {
    return salary;
  }

  public void setSalary(BigDecimal salary)
  {
    this.salary = salary;
  }

  public BigDecimal getTips()
  {
    return tips;
  }

  public void setTips(BigDecimal tips)
  {
    this.tips = tips;
  }

  public List<Order> getOrders()
  {
    return orders;
  }

  public void setOrders(List<Order> orders)
  {
    this.orders = orders;
  }


  public void sumTips(double tipsFromClient)
  {
    this.setTips(this.getTips().add(BigDecimal.valueOf(tipsFromClient)));
  }

  protected abstract void takeOrder(Scanner scanner, Client client);

  @Override
  public String toString()
  {
    return "Worker{" +
        ", workerName='" + workerName + '\'' +
        ", salary=" + salary +
        ", tips=" + tips +
        ", orders=" + orders +
        '}';
  }
}
