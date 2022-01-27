package restaurant.menu;

import restaurant.menu.enums.MealEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Meal
{
  private int        id;
  private String     name;
  private int        quantity;
  private BigDecimal price;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }

  public BigDecimal getPrice()
  {
    return price;
  }

  public void setPrice(BigDecimal price)
  {
    this.price = price;
  }

  public static List<Meal> getMealsFromEnum()
  {
    List<Meal> mealList = new ArrayList<>();
    MealEnum[] values = MealEnum.values();
    for (int i = 0; i < values.length; i++) {
      MealEnum value = values[i];
      Meal meal = new Meal();
      meal.setId(MealEnum.values()[i].ordinal() + 1);
      meal.setName(value.getName());
      meal.setQuantity(value.getQuantity());
      meal.setPrice(value.getPrice());
      mealList.add(meal);
    }
    return mealList;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append(this.id).append(". ").append(this.name)
        .append(" - ").append(this.quantity).append(" бр. /общо ")
        .append(this.price.setScale(2, RoundingMode.HALF_UP)).append(" лв./");

    return builder.toString();

  }
}
