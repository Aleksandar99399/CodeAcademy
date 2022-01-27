package interfaces1906.exercise4;

public abstract class Animal
{
  private int legs;

  public Animal(int legs)
  {
    this.legs = legs;
  }

  public abstract void eat();

  protected void walk(){
    System.out.printf("I am walking by moving my %d legs.%n",this.legs);
  }

  public int getLegs()
  {
    return legs;
  }

  public void setLegs(int legs)
  {
    this.legs = legs;
  }


}


//