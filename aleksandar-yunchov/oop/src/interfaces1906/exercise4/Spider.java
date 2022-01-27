package interfaces1906.exercise4;

public class Spider extends Animal implements Pet
{
  private String name;

  public Spider( String name)
  {
    super(8);
    this.name = name;
  }

  public Spider()
  {
    super(8);
  }

  @Override
  public void eat()
  {
    System.out.println("I am eating flies");
  }

  @Override
  public String getName()
  {
    return this.name;
  }

  @Override
  public void setName(String name)
  {
    this.name = name;
  }

  @Override
  public void play()
  {
    System.out.println("I am playing with cobweb");
  }
}
