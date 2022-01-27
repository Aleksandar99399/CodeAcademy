package interfaces1906.exercise1;

public class Calculator implements AdvancedArithmetic
{
  private double[] numbers;

  public Calculator(double[] numbers)
  {
    this.numbers = numbers;
  }

  public double[] getNumbers()
  {
    return numbers;
  }

  public void setNumbers(double[] numbers)
  {
    this.numbers = numbers;
  }

  @Override
  public double divisorSum(int n)
  {
    double sumOfNumbers = 0;
    for (int i = 0; i < this.numbers.length; i++) {
      if (numbers[i] % n == 0){
        sumOfNumbers+=numbers[i];
      }
    }
    return sumOfNumbers;
  }
}
