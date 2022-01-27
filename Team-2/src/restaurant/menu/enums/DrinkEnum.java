package restaurant.menu.enums;

import java.math.BigDecimal;

public enum DrinkEnum
{
  COCA_COLA("Кока Кола", new BigDecimal(2), 20),
  FANTA("Фанта", new BigDecimal(2), 15),
  TEA("Чай", new BigDecimal("1.5"), 40),
  VODKA("Водка", new BigDecimal(5), 35);

  private String     name;
  private BigDecimal price;
  private int        quantity;

  DrinkEnum(String name, BigDecimal price, int quantity)
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
