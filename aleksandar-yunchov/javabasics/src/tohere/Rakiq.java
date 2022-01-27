package tohere;


public class Rakiq
{
  public static void main(String[] args)
  {

    double sugar = 100.00;
    double alcohol = 0.00;
    double last = 0.00;
    int i = 0;
    while (true) {
      alcohol += (10.0 / 100.0) * sugar;
      sugar -= (10.0 / 100.0) * sugar;
      alcohol -= (1.0 / 100.0) * alcohol;
      if (alcohol > last) {
        last = alcohol;
      }
      else {
        System.out.println(i);
        break;
      }
      i++;
    }

  }
}
