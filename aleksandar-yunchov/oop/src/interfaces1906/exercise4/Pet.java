package interfaces1906.exercise4;

public interface Pet
{

  public String getName();

  public void setName(String name);

  public default void play(){
    System.out.println("I am playing on my own");
  }
}
