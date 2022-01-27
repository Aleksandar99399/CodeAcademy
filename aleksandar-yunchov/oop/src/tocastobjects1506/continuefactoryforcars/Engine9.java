package tocastobjects1506.continuefactoryforcars;

public class Engine9
{
  private String model;
  private double power;
  private double     volume;
  private TypeEngine typeEngine;

  public Engine9(String model, double power, double volume, TypeEngine typeEngine)
  {
    this.model = model;
    this.power = power;
    this.volume = volume;
    this.typeEngine = typeEngine;
  }

  public String getModel()
  {
    return model;
  }

  public void setModel(String model)
  {
    this.model = model;
  }

  public double getPower()
  {
    return power;
  }

  public void setPower(double power)
  {
    this.power = power;
  }

  public double getVolume()
  {
    return volume;
  }

  public void setVolume(double volume)
  {
    this.volume = volume;
  }

  public TypeEngine getTypeEngine()
  {
    return typeEngine;
  }

  public void setTypeEngine(TypeEngine typeEngine)
  {
    this.typeEngine = typeEngine;
  }
}
