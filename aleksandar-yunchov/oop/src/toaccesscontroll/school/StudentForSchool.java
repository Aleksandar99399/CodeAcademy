package toaccesscontroll.school;

public class StudentForSchool
{
  public static int    COUNT_STUDENTS = 0;
  private       String lastName;
  private       String firstName;
  private       int    age;
  private       int    schoolClass;

  public StudentForSchool(String lastName, String firstName, int age, int schoolClass)
  {
    this.lastName = lastName;
    this.firstName = firstName;
    this.age = age;
    this.schoolClass = schoolClass;
    COUNT_STUDENTS++;
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
    else {
      COUNT_STUDENTS--;
      System.out.println("Йееейй!Завърших!");
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
