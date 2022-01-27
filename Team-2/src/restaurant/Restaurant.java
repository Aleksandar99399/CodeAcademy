package restaurant;

import restaurant.exceptions.worker.WorkerInvalidNameException;
import restaurant.menu.Drink;
import restaurant.menu.enums.DrinkEnum;
import restaurant.menu.Meal;
import restaurant.menu.enums.MealEnum;
import restaurant.worker.Waiter;
import restaurant.worker.Worker;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Restaurant
{
  private String       name;
  private String       address;
  private int          capacity;
  private BigDecimal   balance;
  private List<Worker> workers;

  public Restaurant()
  {
    name = "Fantastic Team 2";
    address = "CodeAcademy, 2021";
    capacity = 5;
    balance = new BigDecimal(1000);
    workers = new ArrayList<>();
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public int getCapacity()
  {
    return capacity;
  }

  public void setCapacity(int capacity)
  {
    this.capacity = capacity;
  }

  public BigDecimal getBalance()
  {
    return balance;
  }

  public void setBalance(BigDecimal balance)
  {
    this.balance = balance;
  }

  public List<Worker> getWorkers()
  {
    return workers;
  }

  public void setWorkers(List<Worker> workers)
  {
    this.workers = workers;
  }

  public void getWorkersWithTips()
  {
    System.out.println("---------------------------");
    System.out.println("СПРАВКА БАКШИШИ ПО СЛУЖИТЕЛИ:");
    for (Worker worker : this.workers) {
      if (worker.getTips().compareTo(BigDecimal.valueOf(0)) > 0) {
        System.out.printf("%-15s:  %.2f лв.%n", worker.getWorkerName(), worker.getTips());
      }
    }
  }

  public void sumBalancePerDay()
  {
    BigDecimal turnover = new BigDecimal(0);
    BigDecimal salaries = new BigDecimal(0);
    for (Worker worker : this.workers) {
      for (Order order : worker.getOrders()) {
        turnover = turnover.add(order.getAmount());
      }
      salaries = salaries.add(worker.getSalary());
    }
    System.out.println("---------------------------");
    System.out.println("ФИНАНСОВИ РЕЗУЛТАТИ ЗА ДЕНЯ:");
    System.out.println("---------------------------");
    System.out.printf("%-15s: %.2f лв.%n", "НАЧАЛНО САЛДО", this.getBalance());
    System.out.printf("%-15s: %.2f лв.%n", "ОБОРОТ: ", turnover);
    System.out.printf("%-15s: %.2f лв.%n", "ЗАПЛАТИ: ", salaries);
    System.out.printf("%-15s: %.2f лв.%n", "КРАЙНО САЛДО: ", this.getBalance().add(turnover).subtract(salaries));
  }

  public List<Worker> createWorkers(Scanner scanner, int countWorkers)
  {

    List<Worker> workers = new ArrayList<>();
    int count = 1;

    for (int i = 0; i < countWorkers; i++) {
      Worker waiter = new Waiter();
      System.out.printf("Моля въведете името (най-малко 3 символа) на служител  %d: ", count);
      try {
        String workerName = scanner.nextLine().trim();
        if (workerName.length() < 3) {
          throw new WorkerInvalidNameException("Невалидно въведено име");
        }
        if (!validateName(workerName)) {
          throw new WorkerInvalidNameException("Името не може да съдържа знаци и цифри");
        }

        waiter.setWorkerName(workerName);
        workers.add(waiter);
        count++;
      }
      catch (WorkerInvalidNameException ex) {
        System.out.println(ex.getMessage());
        System.out.println("Опитайте отново. Въведете коректно име");
        i--;
      }
    }
    this.getWorkers().addAll(workers);
    return workers;
  }

  private boolean validateName(String name)
  {
    String reg = "^[a-zA-Z]+$";
    Pattern pattern = Pattern.compile(reg);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }

  public void printStock()
  {
    System.out.println("---------------------------");
    System.out.printf("%20s%n", "СПРАВКА НАЛИЧНОСТИ");
    System.out.printf("%15s%n", "ЯСТИЯ:");
    for (int i = 0; i < MealEnum.values().length; i++) {
      System.out.printf("%-15s: %3s бр.%n", MealEnum.values()[i].getName(), MealEnum.values()[i].getQuantity());
    }
    System.out.printf("%15s%n", "НАПИТКИ:");
    for (int i = 0; i < DrinkEnum.values().length; i++) {
      System.out.printf("%-15s: %3s бр.%n", DrinkEnum.values()[i].getName(), DrinkEnum.values()[i].getQuantity());
    }
  }

  public void printWelcomeMessage()
  {
    System.out.printf("==============================================%n");
    System.out.printf("= Добре дошли в Ресторант \"%s\" =%n", getName());
    System.out.printf("=\t\t\t\t\tАдрес: \"%s\"\t\t\t\t =%n", getAddress());
    System.out.printf("==============================================%n%n");
  }

  public void mostSellMeals()
  {
    Map<String, Integer> mostSell = new TreeMap<>();

    for (Worker worker : workers) {
      for (Order order : worker.getOrders()) {
        for (Meal meal : order.getClient().getMeals()) {
          if (!mostSell.containsKey(meal.getName())) {
            mostSell.put(meal.getName(), meal.getQuantity());
          }
          else {
            mostSell.put(meal.getName(), mostSell.get(meal.getName()) + meal.getQuantity());
          }
        }
      }
    }
    if (!mostSell.isEmpty()) {
      System.out.printf("%15s%n", "ЯСТИЯ:");
      sortSellProducts(mostSell);
    }
  }

  public void mostSellDrinks()
  {
    Map<String, Integer> mostSell = new TreeMap<>();

    for (Worker worker : workers) {
      for (Order order : worker.getOrders()) {
        for (Drink drink : order.getClient().getDrinks()) {
          if (!mostSell.containsKey(drink.getName())) {
            mostSell.put(drink.getName(), drink.getQuantity());
          }
          else {
            mostSell.put(drink.getName(), mostSell.get(drink.getName()) + drink.getQuantity());
          }
        }
      }
    }
    if (!mostSell.isEmpty()) {
      System.out.println("---------------------------");
      System.out.printf("%23s%n", "СПРАВКА НАЙ-ПРОДАВАНИ");
      System.out.printf("%15s%n", "НАПИТКИ:");
      sortSellProducts(mostSell);
    }
  }

  private void sortSellProducts(Map<String, Integer> mostSell)
  {
    mostSell.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).forEach(e -> {
      int count = 1;
      while (count == 1) {
        System.out.printf("%-15s: %3s бр.%n",e.getKey(),e.getValue());
        count++;
      }
    });
  }
}
