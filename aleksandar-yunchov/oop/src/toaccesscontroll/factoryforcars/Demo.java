package toaccesscontroll.factoryforcars;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// в края на класа съм поставил примерни входни данни
// на вас остава само да изберете екстри

public class Demo
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter count of cars per day: ");
    int countCarPerDay = Integer.parseInt(scanner.nextLine());
    List<Car> carsPerDay = new ArrayList<>();

    for (int i = 1; i <= countCarPerDay; i++) {
      Car car = getCar(scanner);

      if (i % 2 == 0) {
        System.out.print("Enter at least 2 and at most 5 extras: ");
        int countExtras = Integer.parseInt(scanner.nextLine());
        for (int j = 0; j < countExtras; j++) {
          System.out.println("Enter one of your favourite extras: 'AIR_CONDITIONING', 'AUTOMATIC', " +
              "'NAVIGATION', 'CRUISE_CONTROL', 'ELECTRIC_WINDOWS'");
          String extra = scanner.nextLine().toUpperCase();
          try {
            Extras currentExtra = Extras.valueOf(extra);
            if (currentExtra.equals(Extras.AIR_CONDITIONING)) {
              car.setPrice(car.getPrice() + 1500);
            }
            else if (currentExtra.equals(Extras.NAVIGATION)) {
              car.setPrice(car.getPrice() + 500);
            }
            else if (currentExtra.equals(Extras.CRUISE_CONTROL)) {
              car.setPrice(car.getPrice() + 350);
            }
            else if (currentExtra.equals(Extras.AUTOMATIC)) {
              car.setPrice(car.getPrice() + 3000);
            }
            else if (currentExtra.equals(Extras.ELECTRIC_WINDOWS)) {
              car.setPrice(car.getPrice() + 250);
            }

            car.getExtras().add(currentExtra);
          }
          catch (Exception e) {
            j--;
            System.out.println("Please enter correct extra!!!");
          }
        }
      }
      carsPerDay.add(car);
    }
    printConditions(carsPerDay);


  }

  private static void printConditions(List<Car> carsPerDay)
  {
    System.out.println(carsPerDay.size());
    System.out.println(carsPerDay.stream().mapToDouble(Car::getPrice).sum());
    System.out.println(carsPerDay.stream().filter(car -> !car.getExtras().isEmpty()).count());
    System.out.print("Cars with ELECTRIC engine: ");
    System.out.println(carsPerDay.stream()
        .filter(car -> Engine.valueOf(car.getEngine().toUpperCase()).equals(Engine.ELECTRIC)).count());
    System.out.print("Cars with GASOLINE engine: ");
    System.out.println(carsPerDay.stream()
        .filter(car -> Engine.valueOf(car.getEngine().toUpperCase()).equals(Engine.GASOLINE)).count());
    System.out.print("Cars with DIESEL engine: ");
    System.out.println(carsPerDay.stream()
        .filter(car -> Engine.valueOf(car.getEngine().toUpperCase()).equals(Engine.DIESEL)).count());
    System.out.print("Cars with HYBRID engine: ");
    System.out.println(carsPerDay.stream()
        .filter(car -> Engine.valueOf(car.getEngine().toUpperCase()).equals(Engine.HYBRID)).count());
    carsPerDay.forEach(System.out::println);
  }

  private static Car getCar(Scanner scanner)
  {
    System.out.print("brand:");
    String brand = scanner.nextLine();
    System.out.print("model:");
    String model = scanner.nextLine();
    System.out.print("typeCoupe:");
    String typeCoupe = scanner.nextLine();
    System.out.print("color:");
    String color = scanner.nextLine();
    System.out.print("length:");
    double length = Double.parseDouble(scanner.nextLine());
    System.out.print("width:");
    double width = Double.parseDouble(scanner.nextLine());
    System.out.print("weight:");
    double weight = Double.parseDouble(scanner.nextLine());
    System.out.print("Enter your preference engine: DIESEL', 'HYBRID', 'GASOLINE', 'ELECTRIC'");
    String engine = scanner.nextLine();
    System.out.print("price:");
    double price = Double.parseDouble(scanner.nextLine());

    Car car = new Car(brand, model, typeCoupe, color, length, width, weight, engine, price);
    return car;
  }
}
//2
//Toyota
//S500
//Sedan
//red
//213.1
//120.2
//2550.12
//gasoline
//51321

//BMW
//516
//Combi
//blue
//214.1
//123.2
//2530.12
//diesel
//23540