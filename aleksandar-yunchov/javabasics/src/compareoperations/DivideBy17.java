package compareoperations;

public class DivideBy17
{
  public static void main(String[] args)
  {
    long num = -8589935681L;

    String result = num % 17 == 0 ? "yes" : "no";
    System.out.println(result);
  }
}
