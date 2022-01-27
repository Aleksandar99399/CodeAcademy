package preparation.exercise3;

import java.util.stream.IntStream;

public class Main
{
  public static void main(String[] args)
  {

    SquareSequence squareSequence = new SquareSequence();
    IntStream randomNumbers = squareSequence.randomInts().getRandomNumbers();
    randomNumbers.forEach(System.out::println);
  }
}
