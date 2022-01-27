package conditionalchecks;

import java.util.Scanner;

public class MetricConverter
{

  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    double num = Double.parseDouble(scanner.nextLine());
    String own = scanner.nextLine();
    String convert = scanner.nextLine();



    double meter = 1;

    switch (own){
      case "mm":
        break;
      case "cm":
        break;
      case "mi":
        break;
      case "in":
        break;
      case "km":
        break;
      case "ft":
        break;
      case "yd":
        break;
    }


  }
  public static double convertFromMM(String word, double num){

    switch (word){
      case "mm":
        num *= 1;
        break;
      case "cm":
        num /=0.1;
        break;
      case "mi":
        num /= 0.00062137119;
        break;
      case "in":
        num /= 0.03937007874;
        break;
      case "km":
        num /= 1000000;
        break;
      case "ft":
        num /= 0.003280839895;
        break;
      case "yd":
        num /= 0.0010936133;
        break;
    }

    return num;
  }
}
