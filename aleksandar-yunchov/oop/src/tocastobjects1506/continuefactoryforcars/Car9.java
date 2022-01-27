package tocastobjects1506.continuefactoryforcars;

import toaccesscontroll.factoryforcars.Extras;

import java.util.ArrayList;
import java.util.List;

public class Car9
{
  private String       brand;
  private String       model;
  private String       typeCoupe;
  private String       color;
  private double       length;
  private double       width;
  private double        weight;
  private Engine9       engine;
  private double        price;
  private List<Extras9> extras;

  public Car9(String brand, String model, String typeCoupe, String color, double length, double width, double weight, Engine9 engine, double price)
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

  public Engine9 getEngine()
  {
    return engine;
  }

  public void setEngine(Engine9 engine)
  {
    this.engine = engine;
  }

  public List<Extras9> getExtras()
  {
    return extras;
  }

  public void setExtras(List<Extras9> extras)
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
        ", price=" + price +
        ", Engine{'" + engine.getModel() + ", " +engine.getPower() +", " +engine.getVolume()+", " +engine.getTypeEngine() + '\'' +
        '}' + " - ");
    if (this.getExtras().size() == 0) {
      builder.append("This car is not equipped with any extras");
    }
    else {
      for (int i = 0; i < this.getExtras().size(); i++) {
        Extras extras = Extras.valueOf(this.getExtras().get(i).toString());
        if (this.getExtras().size() - 1 > i) {
          builder.append(extras.toString()).append(", ");
        }else {
          builder.append(extras.toString());
        }
      }
    }
    return builder.toString();
  }
}
