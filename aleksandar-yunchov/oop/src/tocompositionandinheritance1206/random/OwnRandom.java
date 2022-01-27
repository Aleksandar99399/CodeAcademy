package tocompositionandinheritance1206.random;

import java.util.Random;

public class OwnRandom extends Random
{

  public double[] generatedDouble(int countNumbers,double origin, double bound) {
    double[] arr = new double[countNumbers];
    for (int i = 0; i < countNumbers; i++) {
      double r = nextDouble();
      if (origin < bound) {
        r = r * (bound - origin) + origin;
        if (r >= bound)
          r = Double.longBitsToDouble(Double.doubleToLongBits(bound) - 1);
      }
      arr[i] = r;
    }
    return arr;
  }
}
