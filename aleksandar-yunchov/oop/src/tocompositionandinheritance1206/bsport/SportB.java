package tocompositionandinheritance1206.bsport;

public class SportB
{

  private String name;
  private int countOfTeamMembers;

  public SportB()
  {
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getCountOfTeamMembers()
  {
    return countOfTeamMembers;
  }

  public void setCountOfTeamMembers(int countOfTeamMembers)
  {
    this.countOfTeamMembers = countOfTeamMembers;
  }

  public String getName(){
    return "General Sport";
  }

  public String getNumberOfTeamMembers(){

    return "Each team has n players in " + getName();
  }
}
