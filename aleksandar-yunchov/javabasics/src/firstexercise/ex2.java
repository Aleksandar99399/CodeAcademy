package firstexercise;

public class ex2
{

  public static void main(String[] args)
  {

    double x = 3.0, y = 4.0;

    double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

    System.out.println("r = " + r);


    double theta = Math.atan2(y, x);
    System.out.println("theta = " + theta);
  }
}
