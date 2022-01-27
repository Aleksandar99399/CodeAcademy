package toaccesscontroll.student1;

public class Student1
{
  private String lastName;
  private String firstName;
  private int age;
  private int schoolClass;

  public Student1(String lastName, String firstName, int age, int schoolClass)
  {
    this.lastName = lastName;
    this.firstName = firstName;
    this.age = age;
    this.schoolClass = schoolClass;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public int getSchoolClass()
  {
    return schoolClass;
  }

  public void setSchoolClass(int schoolClass)
  {
    this.schoolClass = schoolClass;
  }

  @Override
  public String toString()
  {
    return this.firstName + " "
        + this.lastName + " in "
        + this.schoolClass + " is "
        + this.age + " years old.";

  }
}
