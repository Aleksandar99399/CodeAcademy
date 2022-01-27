package tocompositionandinheritance1206.random;

public class TestOwnRandom
{
  public static void main(String[] args)
  {

    OwnRandom ownRandom = new OwnRandom();
    double[] numbers = ownRandom.generatedDouble(3,23, 64);
    for (int i = 0; i < numbers.length; i++) {
      System.out.println(numbers[i] + " ");
    }

  }
}
