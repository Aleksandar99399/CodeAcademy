package firstexercise;

import java.util.Scanner;

public class Hi
{
  public static void main(String[] args)
  {

    Scanner scanner = new Scanner(System.in);


    double a = Double.parseDouble(scanner.nextLine());
    double b = Double.parseDouble(scanner.nextLine());
    double c = 0;


    try {
      c = Double.parseDouble(scanner.nextLine());

    }catch (Exception e){
      System.out.println("Invalid");
    }


    if (a + b + c != 180 ){
      System.out.println("That is not triangle");

    }

  }
}
