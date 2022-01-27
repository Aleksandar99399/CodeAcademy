package restaurant.worker;

import restaurant.Order;
import restaurant.client.Client;
import restaurant.exceptions.NotEnoughMoneyException;
import restaurant.menu.Menu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Waiter extends Worker
{
  private static final BigDecimal SALARY_PER_DAY = BigDecimal.valueOf(25);

  public Waiter()
  {
    super.setSalary(SALARY_PER_DAY);
  }

  private String[] getProducts(Scanner scanner)
  {
    System.out.println("Направете Вашия избор като въвеждате по двойки (Продукт №|Количество),");
    System.out.println("като ги разделяте със запетая ИЛИ Enter за пропускане:");
    String[] products = scanner.nextLine().split(",\\s+");
    return products;
  }

  @Override
  public void takeOrder(Scanner scanner, Client client)
  {
    Order order = new Order(client, this);
    int createOrExit = 0;
    boolean invalidChoice = true;
    while (invalidChoice) {
      try {
        System.out.print("Въведете 1 за да създадете поръчка или 0 за приключване: ");
        createOrExit = Integer.parseInt(scanner.nextLine());
        if (createOrExit == 0 || createOrExit == 1) {
          invalidChoice = false;
        }
        else {
          System.out.println("\tНевалидна опция!");
        }
      }
      catch (NumberFormatException nfe) {
        System.out.println("\tНевалидна опция!");
      }
    }

    while (createOrExit != 0) {
      System.out.printf("%n###  Наличността на клиента е: %s лв.  ###%n", client.getBalance().setScale(2, RoundingMode.HALF_UP));
      System.out.println();
      System.out.println("ЦЕНОВА ЛИСТА НАПИТКИ: ");
      Menu.showDrinks();
      String[] drinks = getProducts(scanner);
      client.buyDrinks(drinks, Menu.getDrinksFromClass());

      System.out.println();

      System.out.println("ЦЕНОВА ЛИСТА ЯСТИЯ: ");
      Menu.showMeals();
      String[] meals = getProducts(scanner);
      client.buyMeals(meals, Menu.getMealsFromClass());
      System.out.println();

      boolean invalidOption = true;
      do {
        System.out.print("Ако желаете да добавите още към поръчката натиснете 1 за завършване натиснете 0: ");
        try {
          createOrExit = Integer.parseInt(scanner.nextLine());
          if (createOrExit == 1) {
            invalidOption = false;
          }
          else if (createOrExit == 0) {
            break;
          }
          else {
            System.out.println("\tНевалидна опция!");
          }
        }
        catch (NumberFormatException nfe) {
          System.out.println("\tНевалидна опция!");
        }
      }
      while (invalidOption);
    }
    if (client.getDrinks().size() > 0 || client.getMeals().size() > 0) {
      System.out.println();
      boolean invalidTip = true;
      do {
        System.out.print("Бакшиш от клиент (Да - натиснете: 1, Не - натиснете: 0): ");
        try {
          int tipChoice = Integer.parseInt(scanner.nextLine());
          if (tipChoice == 1) {
            System.out.print("Въведете желаната сума за бакшиш: ");

            double tipAmount = Double.parseDouble(scanner.nextLine());
            if (client.getBalance().compareTo(BigDecimal.valueOf(tipAmount)) < 0) {
              throw new NotEnoughMoneyException();
            }
            super.sumTips(tipAmount);

            invalidTip = false;
          }
          else if (tipChoice == 0) {
            break;
          }
          else {
            System.out.println("\tНевалидна опция!");
          }
        }
        catch (NotEnoughMoneyException e) {
          System.out.println("\tНямате достатъчно пари за такъв бакшиш");
        }
        catch (NumberFormatException nfe) {
          System.out.println("\tНе e въведено число!");
        }
      }
      while (invalidTip);
    }
    System.out.println(order.giveReceipt());
    super.getOrders().add(order);
  }
}