package conditionalchecks;

import java.util.Scanner;

public class Osigurovki
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    double salaryPerMonth = Double.parseDouble(scanner.nextLine());
    int age = Integer.parseInt(scanner.nextLine());

    double feeEmployer = 0;
    double feeManager = 0;

    if (salaryPerMonth <= 3000){
      if (age <= 55){
          feeEmployer = salaryPerMonth * 0.20;
          feeManager = salaryPerMonth * 0.17;
      }else if (age <= 60){
        feeEmployer = salaryPerMonth * 0.13;
        feeManager = salaryPerMonth * 0.13;
      }else if (age <= 65){
        feeEmployer = salaryPerMonth * 0.075;
        feeManager = salaryPerMonth * 0.09;
      }else if (age > 65){
        feeEmployer = salaryPerMonth * 0.05;
        feeManager = salaryPerMonth * 0.075;
      }
    }else {
      feeEmployer = 3000 * 0.20;
      feeManager = 3000 * 0.20;
    }

    System.out.printf("%.2f",feeEmployer);
    System.out.printf("%.2f",feeManager);



  }
}
