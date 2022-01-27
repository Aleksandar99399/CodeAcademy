package tohere;


public class Bottles99
{
  public static void main(String[] args)
  {


    for (int i = 99; i >= 0; i--) {

      if (i == 2) {
        System.out.println("2 bottles of beer on the wall, 2 bottles of beer.\n" +
            "Take one down and pass it around, 1 bottle of beer on the wall.");
      }
      else if (i == 1) {
        System.out.printf("%d bottle of beer on the wall, %d bottle of beer.\n" +
            "Take it down and pass it around, no more bottles of beer on the wall.\n", i, i);
      }
      else if (i == 0) {
        System.out.println("No more bottles of beer on the wall, no more bottles of beer.\n" +
            "Go to the store and buy some more, 99 bottles of beer on the wall.");
      }
      else {
        System.out.printf("%d bottles of beer on the wall, %d bottles of beer.\n" +
            "Take one down and pass it around, %d bottles of beer on the wall.\n", i, i, i - 1);
      }
      System.out.println();
    }
  }
}
