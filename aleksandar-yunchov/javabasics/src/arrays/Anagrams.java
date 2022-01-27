package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Anagrams
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    String one = scanner.nextLine();
    String two = scanner.nextLine();
    boolean areAnagrams;

    if (one.length() != two.length()){
      areAnagrams = false;
    }else {
      char[] firstWord = one.toLowerCase().toCharArray();
      char[] secondWord = two.toLowerCase().toCharArray();
      Arrays.sort(firstWord);
      Arrays.sort(secondWord);
      areAnagrams = Arrays.equals(firstWord, secondWord);
    }

    System.out.println(areAnagrams);

  }
}
