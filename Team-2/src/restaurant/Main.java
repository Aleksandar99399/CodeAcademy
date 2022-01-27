package restaurant;

import restaurant.client.Client;
import restaurant.exceptions.RestaurantFullException;
import restaurant.exceptions.client.ClientInvalidBalanceException;
import restaurant.exceptions.client.ClientInvalidNameException;
import restaurant.util.Randomizator;
import restaurant.worker.Waiter;
import restaurant.worker.Worker;


import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    Restaurant restaurant = new Restaurant();
    restaurant.printWelcomeMessage();

    int countClients = Randomizator.random(1, restaurant.getCapacity() + 2);
    int countWorkers = Randomizator.random(5, 8);

    countClients = checkEnoughCapacity(restaurant.getCapacity(), countClients);

    List<Worker> workersList = restaurant.createWorkers(scanner, countWorkers);

    for (int i = 1; i <= countClients; i++) {
      Random random = new Random();
      int randomWorker = random.nextInt(workersList.size());

      Waiter worker = (Waiter) workersList.get(randomWorker);

      worker.takeOrder(scanner, createClient(scanner));
    }
    restaurant.sumBalancePerDay();
    restaurant.getWorkersWithTips();
    restaurant.printStock();
    restaurant.mostSellDrinks();
    restaurant.mostSellMeals();
    scanner.close();
  }

  private static Integer checkEnoughCapacity(Integer capacity, Integer random)
  {
    int countClients = random;
    try {
      if (capacity < countClients) {
        throw new RestaurantFullException("Ресторантът е пълен. Ще бъдат обслужени клиенти само до капацитета.");
      }

    }
    catch (RestaurantFullException rfe) {
      System.out.println(rfe.getMessage());
      countClients = capacity;
    }

    return countClients;
  }

  private static Client createClient(Scanner scanner)
  {
    Client client = new Client();
    boolean isSuccess = false;
    while (!isSuccess) {
      try {
        System.out.println("---------------------------");
        System.out.print("Въведете име на клиента (най-малко 3 символа): ");
        String name = scanner.nextLine().trim();
        if (name.length() < 2) {
          throw new ClientInvalidNameException("Името на клиента е твърде късо");
        }
        if (!validateName(name)) {
          throw new ClientInvalidNameException("Името не може да съдържа знаци и цифри");
        }

        client.setName(name);
        System.out.print("Въведете наличната сума на клиента: ");
        try {
          double v = Double.parseDouble(scanner.nextLine());
          if (v <= 0) {
            throw new ClientInvalidBalanceException("Невалиден въведен баланс");
          }
          client.setBalance(BigDecimal.valueOf(v));
          isSuccess = true;
        }
        catch (NumberFormatException nfe) {
          throw new ClientInvalidBalanceException("Невалидна сума");
        }
      }
      catch (ClientInvalidNameException | ClientInvalidBalanceException ex) {
        System.out.println(ex.getMessage());
        System.out.println("Въведете клиента отново");
      }
    }

    return client;
  }

  private static boolean validateName(String name)
  {
    String reg = "^[a-zA-Z]+$";
    Pattern pattern = Pattern.compile(reg);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }
}

