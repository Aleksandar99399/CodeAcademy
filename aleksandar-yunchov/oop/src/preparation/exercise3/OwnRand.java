package preparation.exercise3;

import java.util.Random;
import java.util.stream.IntStream;

public class OwnRand
{
  public IntStream getRandomNumbers(){
    Random random = new Random();
    return random.ints();
  }
}
