package restaurant.menu.enums;

import java.math.BigDecimal;

public enum MealEnum
{
  SALAD("Салата", new BigDecimal("5.5"), 30),
  STEAK("Пържола", new BigDecimal(8), 30),
  MEAT_BALLS("Кюфтенца", new BigDecimal(6), 20),
  PIZZA("Пица", new BigDecimal(7), 50),
  VEGETABLE_SOUP("Пилешка супа", new BigDecimal(4), 25);

  private String     name;
  private BigDecimal price;
  private int        quantity;

  MealEnum(String name, BigDecimal price, int quantity)
  {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public BigDecimal getPrice()
  {
    return price;
  }

  public void setPrice(BigDecimal price)
  {
    this.price = price;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }
}
