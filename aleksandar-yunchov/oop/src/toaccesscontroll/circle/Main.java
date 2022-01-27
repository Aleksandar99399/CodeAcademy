package toaccesscontroll.circle;

import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    Circle circle;

    System.out.print("Въведете дължина на радиус или 0 за радиус по подразбиране [1.0]: ");
    double radius = Double.parseDouble(scanner.nextLine());
    if (radius == 0) {
      circle = new Circle();
    }
    else {
      System.out.print("Изберете цвят (1 за жълт, 2 за червен, 3 за зелен, 4 за син)]: ");
      int inputColor = Integer.parseInt(scanner.nextLine());
      Color color = null;

      if (inputColor == 1) {
        color = Color.YELLOW;
      }
      else if (inputColor == 2) {
        color = Color.RED;
      }
      else if (inputColor == 3) {
        color = Color.GREEN;
      }
      else if (inputColor == 4) {
        color = Color.BLUE;
      }
      circle = new Circle(radius, color);
    }

    System.out.printf("Създаден е %s кръг с радиус %.2f, и диаметър %.2f%n"
        , circle.getColor(), circle.getRadius(), circle.getCircumference());
    System.out.println("Изберете опция: 1. Периметър 2. Лице 3. Луна ли е? 4. Направи кръга луна 5. Принтирай toString");
    int num = Integer.parseInt(scanner.nextLine());
    printCondition(num, circle);
  }

  private static void printCondition(int num, Circle circle)
  {
    if (num == 1) {
      System.out.println("Периметърът на кръга е " + circle.getCircumference());
    }
    else if (num == 2) {
      System.out.println("Лицето на кръга е " + circle.getArea());
    }
    else if (num == 3 || num == 4) {
      if (circle.getColor().equals(Color.YELLOW)) {
        if (num == 3) {
          System.out.println("Кръгът е луна");
        }
        else {
          System.out.println("Кръгът вече е луна. Няма да правя нищо.");
        }
      }
      else {
        if (num == 3) {
          System.out.println("Кръгът не е луна");
        }
        else {
          circle.makeMeMoon();
          System.out.println("Готов. Направих кръга на луна");
        }
      }
    }
    else if (num == 5) {
      System.out.println(circle.toString());
    }

  }

}
