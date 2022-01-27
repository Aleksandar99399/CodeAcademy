package homework2206.exercise1;

public class Ford extends Car
{

  private String year;
  private double manufacturerDiscount;

  public Ford(double speed, double regularPrice, String color, String year, double manufacturerDiscount)
  {
    super(speed, regularPrice, color);
    this.year = year;
    this.manufacturerDiscount = manufacturerDiscount;
  }

  public String getYear()
  {
    return year;
  }

  public void setYear(String year)
  {
    this.year = year;
  }

  public double getManufacturerDiscount()
  {
    return manufacturerDiscount;
  }

  public void setManufacturerDiscount(double manufacturerDiscount)
  {
    this.manufacturerDiscount = manufacturerDiscount;
  }

  public double getSalePrice(){
    super.setRegularPrice(super.getRegularPrice() - this.manufacturerDiscount);
    return super.getRegularPrice();
  }
}
