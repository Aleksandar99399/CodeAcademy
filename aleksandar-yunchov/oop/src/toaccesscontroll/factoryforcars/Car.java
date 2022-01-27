package toaccesscontroll.factoryforcars;

import java.util.ArrayList;
import java.util.List;

public class Car
{
  private String       brand;
  private String       model;
  private String       typeCoupe;
  private String       color;
  private double       length;
  private double       width;
  private double       weight;
  private String       engine;
  private double       price;
  private List<Extras> extras;

  public Car(String brand, String model, String typeCoupe, String color, double length, double width, double weight, String engine, double price)
  {
    this.brand = brand;
    this.model = model;
    this.typeCoupe = typeCoupe;
    this.color = color;
    this.length = length;
    this.width = width;
    this.weight = weight;
    this.engine = engine;
    this.price = price;
    this.extras = new ArrayList<>();
  }

  public String getBrand()
  {
    return brand;
  }

  public void setBrand(String brand)
  {
    this.brand = brand;
  }

  public String getModel()
  {
    return model;
  }

  public void setModel(String model)
  {
    this.model = model;
  }

  public String getTypeCoupe()
  {
    return typeCoupe;
  }

  public void setTypeCoupe(String typeCoupe)
  {
    this.typeCoupe = typeCoupe;
  }

  public String getColor()
  {
    return color;
  }

  public void setColor(String color)
  {
    this.color = color;
  }

  public double getLength()
  {
    return length;
  }

  public void setLength(double length)
  {
    this.length = length;
  }

  public double getWidth()
  {
    return width;
  }

  public void setWidth(double width)
  {
    this.width = width;
  }

  public double getWeight()
  {
    return weight;
  }

  public void setWeight(double weight)
  {
    this.weight = weight;
  }


  public double getPrice()
  {
    return price;
  }

  public void setPrice(double price)
  {
    this.price = price;
  }

  public String getEngine()
  {
    return engine;
  }

  public void setEngine(String engine)
  {
    this.engine = engine;
  }

  public List<Extras> getExtras()
  {
    return extras;
  }

  public void setExtras(List<Extras> extras)
  {
    this.extras = extras;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Car{" +
        "brand='" + brand + '\'' +
        ", model='" + model + '\'' +
        ", typeCoupe='" + typeCoupe + '\'' +
        ", color='" + color + '\'' +
        ", length=" + length +
        ", width=" + width +
        ", weight=" + weight +
        ", engine='" + engine + '\'' +
        ", price=" + price +
        '}' + " - ");
    if (this.getExtras().size() == 0) {
      builder.append("This car is not equipped with any extras");
    }
    else {
      for (int i = 0; i < this.getExtras().size(); i++) {
        Extras extras = Extras.valueOf(this.getExtras().get(i).toString());
        if (this.getExtras().size() - 1 > i) {
          builder.append(extras.toString()).append(", ");
        }
        builder.append(extras.toString());
      }
    }
    return builder.toString();
  }
}
