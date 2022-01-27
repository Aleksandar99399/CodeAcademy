package toaccesscontroll.school;

import java.util.Scanner;

public class SchoolTest
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter 1 to create student or 2 to exit: ");
    int num = Integer.parseInt(scanner.nextLine());
    School school = new School("PMG");

    while (num != 2) {
      System.out.print("First name: ");
      String firstName = scanner.nextLine();
      System.out.print("Last name: ");
      String lastName = scanner.nextLine();
      System.out.print("Age: ");
      int age = Integer.parseInt(scanner.nextLine());
      System.out.print("School class: ");
      int schoolClass = Integer.parseInt(scanner.nextLine());
      StudentForSchool student = new StudentForSchool(lastName, firstName, age, schoolClass);

      school.getStudents().add(student);

      System.out.print("Enter 1 to create student or 2 to exit: ");
      num = Integer.parseInt(scanner.nextLine());
    }

    System.out.println(StudentForSchool.COUNT_STUDENTS);
    for (int i = 0; i < school.getStudents().size(); i++) {
      System.out.println(school.getStudents().get(i));
    }
    System.out.println("--------Graduates----------");
    for (int i = 0; i < school.getStudents().size(); i++) {

      if (school.getStudents().get(i).getSchoolClass() == 12) {
        System.out.println(school.getStudents().get(i));
        school.getStudents().get(i).goToNextClass();
      }
    }
    System.out.println(StudentForSchool.COUNT_STUDENTS);
  }

}
