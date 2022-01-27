package conditionalchecks;

import java.util.Scanner;

public class TaxCalculator
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    double salary = Double.parseDouble(scanner.nextLine());
    double interest = 0;

    if (salary <= 20000){
      interest +=0;
    }else if (salary <= 40000){
      salary -=20000;
      double proba = salary * 0.10;
      interest+=proba;

    }else if (salary <= 60000){
      salary -= 40000;
      double first = 20000 * 0.10;
      double second = salary * 0.20;

      interest += first + second;

    }else {
      salary -= 60000;
      double first = 20000 * 0.10;
      double second = 20000 * 0.20;
      double third = salary * 0.30;

      interest += first + second + third;
    }
    System.out.printf("%.2f", interest);


  }
}