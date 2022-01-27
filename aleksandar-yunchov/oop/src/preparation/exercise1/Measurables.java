package preparation.exercise1;

import java.util.Arrays;

public class Measurables
{

  public double average(Measurable[] objects){
    return Arrays.stream(objects).mapToDouble(Measurable::getMeasure).average().getAsDouble();
  }

  public Measurable largest(Measurable[] objects){

    double measure = Integer.MIN_VALUE;
    Employee employee = null;
    for (Measurable object : objects) {
      if (object.getMeasure() > measure){
        employee = (Employee) object;
      }
    }

    return employee;
  }
}
