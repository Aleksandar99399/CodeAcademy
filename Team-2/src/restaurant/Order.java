package restaurant;

import restaurant.client.Client;
import restaurant.menu.Drink;
import restaurant.menu.Meal;
import restaurant.worker.Worker;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Order
{
  private static int instanceCounter=0;
  private int        ordersCounter;
  private Client     client;
  private Worker     waiter;
  private BigDecimal amount;
  private boolean    completed;

  public Order(Client client, Worker waiter)
  {
    instanceCounter++;
    ordersCounter=instanceCounter;
    this.client = client;
    this.waiter = waiter;
    this.amount = new BigDecimal(0);


  }

  public int getOrdersCounter()
  {
    return ordersCounter;
  }

  public void setOrdersCounter(int ordersCounter)
  {
    this.ordersCounter = ordersCounter;
  }

  public Client getClient()
  {
    return client;
  }

  public void setClient(Client client)
  {
    this.client = client;
  }

  public Worker getWaiter()
  {
    return waiter;
  }

  public void setWaiter(Worker waiter)
  {
    this.waiter = waiter;
  }

  public BigDecimal getAmount()
  {
    return amount;
  }

  public void setAmount(BigDecimal amount)
  {
    this.amount = amount;
  }

  public boolean isCompleted()
  {
    return completed;
  }

  public void setCompleted(boolean completed)
  {
    this.completed = completed;
  }

  private void considerAmount()
  {
    BigDecimal addAmount = this.getAmount();
    for (Drink drink : this.client.getDrinks()) {
      addAmount = addAmount.add(drink.getPrice());
    }
    for (Meal meal : this.client.getMeals()) {
      addAmount = addAmount.add(meal.getPrice());
    }

    this.setAmount(addAmount);
  }

  public String giveReceipt()
  {
    this.considerAmount();

    StringBuilder builder = new StringBuilder();
    System.out.println();
    System.out.println("---------------------------");
    builder.append("КАСОВА БЕЛЕЖКА №: " ).append(this.ordersCounter).append("\n");
    builder.append("Вас ви обслужи: ").append(this.waiter.getWorkerName()).append("\n");

    if (this.client.getDrinks().size() != 0) {
      builder.append("\n").append("Закупените от Вас напитки: ").append("\n");
      for (Drink drink : this.client.getDrinks()) {
        builder.append(drink.toString()).append("\n");
      }
    }
    if (this.client.getMeals().size() != 0) {
      builder.append("\n").append("Закупените от Вас ястия: ").append("\n");
      for (Meal meal : this.client.getMeals()) {
        builder.append(meal.toString()).append("\n");
      }
    }
    builder.append("\n").append(String.format("Стойност /тотал/ с ДДС: %s лв.", this.amount.setScale(2, RoundingMode.HALF_UP))).append("\n");

    builder.append("Ще ви очакваме отново ").append(this.client.getName());

    return builder.toString();
  }


}
