package toaccesscontroll.student2;

public class Student2
{
  private       String lastName;
  private       String firstName;
  private       int    age;
  private       int    schoolClass;

  public Student2(String lastName, String firstName, int age, int schoolClass)
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

  public void increaseAge()
  {
    this.age++;
  }

  public void increaseAge(int years)
  {
    this.age += years;
  }

  public void goToNextClass()
  {
    if (this.schoolClass <= 11) {
      this.schoolClass += 1;
    }
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    if (this.schoolClass <= 12) {
      builder.append(this.firstName + " "
          + this.lastName + " in "
          + this.schoolClass + " class is "
          + this.age + " years old.");
    }
    else {
      builder.append(this.firstName + " "
          + this.lastName + " has"
          + " graduated");
    }

    return builder.toString();
  }
}
