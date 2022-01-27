package restaurant.client;

import restaurant.exceptions.NotEnoughMoneyException;
import restaurant.exceptions.NotEnoughQuantityException;
import restaurant.exceptions.ProductNotExistException;
import restaurant.menu.Drink;
import restaurant.menu.enums.DrinkEnum;
import restaurant.menu.Meal;
import restaurant.menu.enums.MealEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Client
{
  private String      name;
  private BigDecimal  balance;
  private List<Meal>  meals;
  private List<Drink> drinks;

  public Client()
  {
    meals = new ArrayList<>();
    drinks = new ArrayList<>();
    balance = new BigDecimal(0);
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public BigDecimal getBalance()
  {
    return balance;
  }

  public void setBalance(BigDecimal balance)
  {
    this.balance = balance;
  }

  public List<Meal> getMeals()
  {
    return meals;
  }

  public void setMeals(List<Meal> meals)
  {
    this.meals = meals;
  }

  public List<Drink> getDrinks()
  {
    return drinks;
  }

  public void setDrinks(List<Drink> drinks)
  {
    this.drinks = drinks;
  }

  public void buyDrinks(String[] wishDrink, List<Drink> drinks)
  {
    if (!wishDrink[0].equals("")) {
      for (String wish : wishDrink) {

        String[] prodAndQuan = wish.split("\\|");
        int productId = 0;
        int quantity = 0;

        if (checkIfExistIdAndQuantity(prodAndQuan)) {
          System.out.println("Невалидно въведени ID и/или количество");
          continue;
        }
        else {
          try {
            int[] arr = getIdAndQuantity(prodAndQuan);
            productId = arr[0];
            quantity = arr[1];
          }
          catch (NumberFormatException nfe) {
            System.out.println("ID-то и количеството не може да съдържа други символи освен цифри");
            continue;
          }
        }

        try {
          Drink drink = drinks.get(productId);

          if (drink == null) {
            throw new ProductNotExistException("Продукт ID: " + productId + " не съществува.");
          }
          if (drink.getQuantity() < quantity) {
            throw new NotEnoughQuantityException();
          }
          if (checkCurrentBalance(drink.getPrice().multiply(BigDecimal.valueOf(quantity)))) {
            throw new NotEnoughMoneyException();
          }
          Drink clientDrink = getTestDrink(quantity, drink);
          this.subtractQuantityInEnumDrinks(productId, quantity);
          this.buyProduct(drink.getPrice().multiply(BigDecimal.valueOf(quantity)));
          this.drinks.add(clientDrink);
//          System.out.println("Успешно добавени към поръчката напитки:");
          System.out.printf("Успешно добавена към поръчката напитка: %2d.%-10s - %3s бр.%n",
              clientDrink.getId(), clientDrink.getName(), clientDrink.getQuantity());
        }
        catch (NotEnoughQuantityException qe) {
          System.out.println("Няма достатъчно количество от този продукт - " + (productId + 1));
        }
        catch (NotEnoughMoneyException nme) {
          System.out.println("Нямате достатъчно пари за този продукт - " + (productId + 1));
        }
        catch (IllegalArgumentException iae) {
          System.out.println("Несъщесвуващ продукт - " + (productId + 1));
        }
        catch (ProductNotExistException pne) {
          System.out.println(pne.getMessage());
        }
        catch (IndexOutOfBoundsException e) {
          System.out.println("Продукт ID: " + (productId + 1) + " не съществува.");
        }
      }
    }
    else {
      System.out.println("Не поръчахте напитка");
    }
  }

  public void buyMeals(String[] wishMeal, List<Meal> meals)
  {
    if (!wishMeal[0].equals("")) {
      for (String wish : wishMeal) {

        String[] prodAndQuan = wish.split("\\|");

        int productId = 0;
        int quantity = 0;
        if (checkIfExistIdAndQuantity(prodAndQuan)) {
          System.out.println("Невалидно въведени ID и/или количество");
          continue;
        }
        else {
          try {
            int[] arr = getIdAndQuantity(prodAndQuan);
            productId = arr[0];
            quantity = arr[1];
          }
          catch (NumberFormatException nfe) {
            System.out.println("ID-то и количеството не може да съдържа други символи освен цифри");
            continue;
          }
        }

        try {
          Meal meal = meals.get(productId);

          if (meal == null) {
            throw new ProductNotExistException("Продукт ID: " + productId + " не съществува.");
          }
          if (meal.getQuantity() < quantity) {
            throw new NotEnoughQuantityException();
          }
          if (checkCurrentBalance(meal.getPrice().multiply(BigDecimal.valueOf(quantity)))) {
            throw new NotEnoughMoneyException();
          }
          Meal clientMeal = getTestMeal(quantity, meal);
          this.subtractQuantityInEnumMeals(productId, quantity);

          this.buyProduct(meal.getPrice().multiply(BigDecimal.valueOf(quantity)));
          this.meals.add(clientMeal);
          System.out.printf("Успешно добавено към поръчката ястие: %2d.%-10s - %3s бр.%n",
              clientMeal.getId(), clientMeal.getName(), clientMeal.getQuantity());
        }
        catch (NotEnoughQuantityException qe) {
          System.out.println("Няма достатъчно количество от този продукт - " + meals.get(productId).getName());
        }
        catch (NotEnoughMoneyException nme) {
          System.out.println("Нямате достатъчно пари за този продукт - " + meals.get(productId).getName());
        }
        catch (ProductNotExistException pne) {
          System.out.println(pne.getMessage());
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException e) {
          System.out.println("Продукт с ID: " + (productId + 1) + " не съществува.");
        }
      }
    }
    else {
      System.out.println("Не поръчахте ястие");
    }
  }

  private boolean checkIfExistIdAndQuantity(String[] prodAndQuan)
  {
    return prodAndQuan.length != 2;
  }

  private int[] getIdAndQuantity(String[] prodAndQuan)
  {
    int[] arr = new int[2];
    int productId = Integer.parseInt(prodAndQuan[0]) - 1;
    int quantity = Integer.parseInt(prodAndQuan[1]);
    arr[0] = productId;
    arr[1] = quantity;
    return arr;
  }

  private void subtractQuantityInEnumMeals(int indexProduct, int quantity)
  {
    MealEnum mealEnum = MealEnum.values()[indexProduct];
    mealEnum.setQuantity(mealEnum.getQuantity() - quantity);
  }


  private void subtractQuantityInEnumDrinks(int indexProduct, int quantity)
  {
    DrinkEnum drinkEnum = DrinkEnum.values()[indexProduct];
    drinkEnum.setQuantity(drinkEnum.getQuantity() - quantity);
  }

  private Drink getTestDrink(int quantity, Drink drink)
  {
    Drink clientDrink = new Drink();
    clientDrink.setId(drink.getId());
    clientDrink.setName(drink.getName());
    clientDrink.setQuantity(quantity);
    clientDrink.setPrice(drink.getPrice().multiply(BigDecimal.valueOf(quantity)));
    return clientDrink;
  }

  private Meal getTestMeal(int quantity, Meal meal)
  {
    Meal clientMeal = new Meal();
    clientMeal.setId(meal.getId());
    clientMeal.setName(meal.getName());
    clientMeal.setQuantity(quantity);
    clientMeal.setPrice(meal.getPrice().multiply(BigDecimal.valueOf(quantity)));
    return clientMeal;
  }

  private boolean checkCurrentBalance(BigDecimal priceOfProduct)
  {
    return this.balance.compareTo(priceOfProduct) < 0;
  }

  private void buyProduct(BigDecimal priceOfProduct)
  {
    this.setBalance(this.getBalance().subtract(priceOfProduct));
  }
}
