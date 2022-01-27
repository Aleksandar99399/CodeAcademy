package interfaces1906.exercise4;

public class Cat extends Animal implements Pet
{
  private String name;

  public Cat(String name)
  {
    super(4);
    this.name = name;
  }

  public Cat()
  {
    super(4);
  }

  @Override
  public void eat()
  {
    System.out.println("I am eating mice.");
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
    System.out.println("I like playing with wool balls");
  }
}
