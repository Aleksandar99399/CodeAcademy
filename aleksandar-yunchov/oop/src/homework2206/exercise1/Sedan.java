package homework2206.exercise1;

public class Sedan extends Car
{
  private double length;

  public Sedan(double speed, double regularPrice, String color, double length)
  {
    super(speed, regularPrice, color);
    this.length = length;
  }

  public double getLength()
  {
    return length;
  }

  public void setLength(double length)
  {
    this.length = length;
  }

  public double getSalePrice(){
    if (this.length > 6.5){
      super.setRegularPrice(super.getRegularPrice() * 0.95);
    }else {
      super.setRegularPrice(super.getRegularPrice() * 0.90);
    }
    return super.getRegularPrice();
  }
}
