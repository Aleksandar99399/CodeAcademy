package firstexercise;

public class ex4
{

  public static void main(String[] args)
  {
    double G = 6.6742E-11;
    double mass1 = 10;
    double mass2 = 10;
    double r = 2.3;
    double force =( G * (mass1 * mass2) )/ (r * r);
    System.out.println(force);
  }
}
