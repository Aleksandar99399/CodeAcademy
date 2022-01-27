package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Score
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Моля въведете брой на играчите: ");
    int countOfPlayers = Integer.parseInt(scanner.nextLine());

    double[] scores = new double[countOfPlayers];

    for (int i = 0; i < countOfPlayers; i++) {
      System.out.printf("Въведете резултат №%d: ",i);
      double score = Double.parseDouble(scanner.nextLine());
      scores[i] = score;
    }



    double min = Arrays.stream(scores).min().orElse(-1);
    System.out.printf("Min      = %.3f%n",min);
    double max = Arrays.stream(scores).max().orElse(-1);
    System.out.printf("Max      = %.3f%n",max);
    double average = Arrays.stream(scores).average().orElse(-1);
    System.out.printf("Average  = %.3f%n",average);

    double sum = Arrays.stream(scores).sum();
    double cd1 = sum  * (Math.pow(scores[0] - average,2));
    double cd = Math.sqrt(cd1 / scores.length);
    System.out.printf("StdDev   = %.3f",cd);

  }
}
