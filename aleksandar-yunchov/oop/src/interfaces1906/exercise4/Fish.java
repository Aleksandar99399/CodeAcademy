package interfaces1906.exercise4;

public class Fish extends Animal implements Pet
{
  private String name;

  public Fish(String name)
  {
    super(0);
    this.name = name;
  }

  public Fish()
  {
    super(0);
  }

  @Override
  public void walk()
  {
    System.out.println("I can't walk. I don't have legs");
  }

  @Override
  public void eat()
  {
//    if (name == null) {
      System.out.println("I am eating worms");
//    }else {
//      System.out.println("I am eating bread");
//    }
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
    System.out.println("I am playing in aquarium.");
  }
}
