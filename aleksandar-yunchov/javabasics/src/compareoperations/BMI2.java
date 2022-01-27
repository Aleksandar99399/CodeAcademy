package compareoperations;

public class BMI2
{
  public static void main(String[] args)
  {
    double height = Math.pow(1.71, 2);
    double BMI = 80 / height;

    if (BMI >= 25 && BMI <= 30){
      System.out.println("more training, less eating");
    }else {
      System.out.println("you rock!");
    }
  }
}
