package preparation.exercise2;

import preparation.exercise1.Employee;

import java.util.Comparator;

public class OwnComp implements Comparator<Employee>
{

  @Override
  public int compare(Employee o1, Employee o2)
  {
    return Double.compare(o1.getMeasure(),o2.getMeasure());

  }


}
