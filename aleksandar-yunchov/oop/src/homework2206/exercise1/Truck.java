package homework2206.exercise1;

public class Truck extends Car
{
  private double weight;

  public Truck(double speed, double regularPrice, String color, double weight)
  {
    super(speed, regularPrice, color);
    this.weight = weight;
  }

  public double getWeight()
  {
    return weight;
  }

  public void setWeight(double weight)
  {
    this.weight = weight;
  }

  public double getSalePrice(){
    if (this.weight > 2000){
      super.setRegularPrice(super.getRegularPrice() * 0.90);
    }else {
      super.setRegularPrice(super.getRegularPrice() * 0.80);
    }
    return super.getRegularPrice();
  }
}
