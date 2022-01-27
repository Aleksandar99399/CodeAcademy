package toaccesscontroll.school;

import java.util.ArrayList;
import java.util.List;

public class School
{
  private String         name;
  private List<Integer>  classes;
  private List<StudentForSchool> students;

  public School(String name)
  {
    this.name = name;
    this.classes = new ArrayList<>();
    this.students = new ArrayList<>();
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public List<Integer> getClasses()
  {
    return classes;
  }

  public void setClasses(List<Integer> classes)
  {
    this.classes = classes;
  }

  public List<StudentForSchool> getStudents()
  {
    return students;
  }

  public void setStudents(List<StudentForSchool> students)
  {
    this.students = students;
  }
}
