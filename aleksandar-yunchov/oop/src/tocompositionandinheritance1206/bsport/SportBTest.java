package tocompositionandinheritance1206.bsport;

public class SportBTest
{
  public static void main(String[] args)
  {
    SportB soccer = new SoccerB();
    SportB sport = new SportB();

    System.out.println(soccer.getName());
    System.out.println(soccer.getNumberOfTeamMembers());
    System.out.println(soccer.getClass());
    System.out.println(soccer.getClass().getSuperclass());

    System.out.println(sport.getName());
    System.out.println(sport.getNumberOfTeamMembers());
    System.out.println(sport.getClass());
    System.out.println(sport.getClass().getSuperclass());
  }
}
