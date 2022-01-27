package tocastobjects1506.continuefactoryforcars;

public class TestExtras
{
  public static void main(String[] args)
  {
    System.out.println("======= Extras Catalogue =======");
    for (Extras9 value : Extras9.values()) {
      System.out.printf("%s %.2f\n",value.name(),value.getAddSum());
    }

  }
}
