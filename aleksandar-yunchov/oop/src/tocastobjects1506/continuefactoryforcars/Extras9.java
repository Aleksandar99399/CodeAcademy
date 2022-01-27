package tocastobjects1506.continuefactoryforcars;

public enum Extras9
{
  AIR_CONDITIONING(1500),
  AUTOMATIC(3000),
  NAVIGATION(500),
  CRUISE_CONTROL(350),
  ELECTRIC_WINDOWS(250);

  private double addSum;

  Extras9(double addSum)
  {
    this.addSum = addSum;
  }

  public double getAddSum()
  {
    return addSum;
  }

}
