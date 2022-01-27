package toaccesscontroll.student1;

public class Student1Test
{
  public static void main(String[] args){

    Student1 student1 = new Student1("Ivanov", "Dimityr",15,11);
    Student1 student2 = new Student1("Spasov", "Danail", 11,8);
    Student1 student3 = new Student1("Donkov", "Georgi", 12, 9);

    System.out.println(student1.toString());
    System.out.println(student2.toString());
    System.out.println(student3.toString());
  }
}
