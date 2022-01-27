package inputoutputdata;

import java.util.Scanner;

public class TableFormat
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    String firstName = "Иванчо";
    int firstGrade = 2;
    String secondName = "Марийка";
    int secondGrade = 64;
    String thirdName = "Пенчо";
    int thirdGrade = 4;
    String fourthName = "Петко";
    int fourthGrade = 5;

    System.out.printf("%-20s %2d %n",firstName, firstGrade);
    System.out.printf("%-20s %2d %n",secondName,secondGrade);
    System.out.printf("%-20s %2d %n",thirdName,thirdGrade);
    System.out.printf("%-20s %2d",fourthName,fourthGrade);

  }
}
