package restaurant.menu;

import java.util.List;

public class Menu
{

  public static void showDrinks()
  {
//    for (Drink drink : getDrinksFromClass()) {
//      System.out.println(drink.toString());
//  }
    for (int i = 0; i < getDrinksFromClass().size(); i++) {
      System.out.printf("%2d.%-15s - %.2f лв.%n", getDrinksFromClass().get(i).getId(), getDrinksFromClass().get(i).getName(),
          getDrinksFromClass().get(i).getPrice());
    }
  }


  public static List<Drink> getDrinksFromClass()
  {
    return Drink.getDrinksFromEnum();
  }

  public static List<Meal> getMealsFromClass()
  {
    return Meal.getMealsFromEnum();
  }

  public static void showMeals()
  {
//    for (Meal meal : getMealsFromClass()) {
//      System.out.println(meal.toString());
//    }
    for (int i = 0; i < getMealsFromClass().size(); i++) {
      System.out.printf("%2d.%-15s - %.2f лв.%n", getMealsFromClass().get(i).getId(), getMealsFromClass().get(i).getName(),
          getMealsFromClass().get(i).getPrice());
    }
  }

//  public static void showDrinks()
//  {
//    for (int i = 0; i < getDrinks().size(); i++) {
//      Drink drink = getDrinks().get(i);
//      System.out.println(drink.toString() + "; ");
//    }
//  }
}
