package firstexercise;

public class ex6
{
  public static void main(String[] args)
  {

    double t = 20;
    double toRadians  = Math.toRadians(t);


    double d = Math.sin(2*toRadians) + Math.sin(3*toRadians);
    System.out.println(d);
  }
}
