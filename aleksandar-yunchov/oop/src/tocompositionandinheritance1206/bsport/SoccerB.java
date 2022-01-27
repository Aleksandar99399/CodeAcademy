package tocompositionandinheritance1206.bsport;

public class SoccerB extends SportB
{
  private String name = "Soccer";
  private int countOfTeamMembers = 11;

  public SoccerB()
  {
  }

  @Override
  public String getName()
  {
    return this.name;
  }

  @Override
  public String getNumberOfTeamMembers()
  {
    return "Each team has "+this.countOfTeamMembers+" players in " + this.name;
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
}
