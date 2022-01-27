package compareoperations;

public class SpringSeason
{
  public static void main(String[] args)
  {
    int month = 4;
    int day = 31;


    if (day > 30 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
      System.out.println("false");
      return;

    }else if (day > 31 && (month == 1 || month == 3 || month == 5
              || month == 7 || month == 8 || month == 10 || month == 12)) {
      System.out.printf("The days in %d month are 31", month);
      return;

    }else if (month > 12){
      System.out.println("The months in year are 12");
      return;
    }

    if ((day >= 20 && month >= 3) && (day >= 20 && month <= 6)) {
      System.out.println("true");

    }else {
      System.out.println("false");
    }
  }
}
