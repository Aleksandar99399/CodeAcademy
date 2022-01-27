package toaccesscontroll.student3;

import java.util.ArrayList;
import java.util.List;

public class Student3Test
{
  public static void main(String[] args)
  {
    List<Student3> listOfStudents = new ArrayList<>();
    Student3 student1 = new Student3("Ivanov", "Dimityr", 15, 9, 4);
    Student3 student2 = new Student3("Spasov", "Danail", 11, 12, 5.50);
    Student3 student3 = new Student3("Donkov", "Georgi", 12, 6, 6);

    listOfStudents.add(student1);
    listOfStudents.add(student2);
    listOfStudents.add(student3);

    System.out.println("All with more 5.50");
    listOfStudents.stream().filter(Student3::isExcellent).forEach(System.out::println);

    System.out.println("All with 2");
    listOfStudents.stream().filter(Student3::isVeryBad).forEach(System.out::println);


  }
}
