package toaccesscontroll.student2;

public class Student2Test
{
  public static void main(String[] args)
  {

    Student2 student1 = new Student2("Ivanov", "Dimityr",15,9);
    Student2 student2 = new Student2("Spasov", "Danail", 11,11);
    Student2 student3 = new Student2("Donkov", "Georgi", 12, 6);

    student1.increaseAge(12);
    System.out.println(student1);

    student2.increaseAge();
    student2.goToNextClass();
    System.out.println(student2);


  }
}
