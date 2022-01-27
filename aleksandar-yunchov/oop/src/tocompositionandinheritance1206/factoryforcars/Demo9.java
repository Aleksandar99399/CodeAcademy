package tocompositionandinheritance1206.factoryforcars;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// в края на класа съм поставил примерни входни данни
// на вас остава само да изберете екстри

public class Demo9
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    Engine9 gasoline = new Engine9("ds477",154,245.56,TypeEngine.GASOLINE);
    Engine9 diesel = new Engine9("r848",115,151.43,TypeEngine.DIESEL);
    Engine9 electric = new Engine9("el88",230,422.15,TypeEngine.ELECTRIC);
    Engine9 hybrid = new Engine9("h464h",201,183.26,TypeEngine.HYBRID);
    Engine9[] engine9s = {gasoline,diesel,electric,hybrid};


    System.out.print("Enter count of cars per day: ");
    int countCarPerDay = Integer.parseInt(scanner.nextLine());
    List<Car9> carsPerDay = new ArrayList<>();

    for (int i = 1; i <= countCarPerDay; i++) {
      Car9 car = getCar(scanner,engine9s);

      if (i % 2 == 0) {
        System.out.print("Enter at least 2 and at most 5 extras: ");
        int countExtras = Integer.parseInt(scanner.nextLine());
        for (int j = 0; j < countExtras; j++) {
          System.out.println("Enter one of your favourite extras: 'AIR_CONDITIONING', 'AUTOMATIC', " +
              "'NAVIGATION', 'CRUISE_CONTROL', 'ELECTRIC_WINDOWS'");
          String extra = scanner.nextLine().toUpperCase();
          try {
            Extras9 currentExtra = Extras9.valueOf(extra);
            if (currentExtra.equals(Extras9.AIR_CONDITIONING)) {
              car.setPrice(car.getPrice() + 1500);
            }
            else if (currentExtra.equals(Extras9.NAVIGATION)) {
              car.setPrice(car.getPrice() + 500);
            }
            else if (currentExtra.equals(Extras9.CRUISE_CONTROL)) {
              car.setPrice(car.getPrice() + 350);
            }
            else if (currentExtra.equals(Extras9.AUTOMATIC)) {
              car.setPrice(car.getPrice() + 3000);
            }
            else if (currentExtra.equals(Extras9.ELECTRIC_WINDOWS)) {
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
  private static Engine9 getEngine(Engine9[] engine9s,String engine){

    for (Engine9 engine9 : engine9s) {
      if (engine9.getTypeEngine().equals(TypeEngine.valueOf(engine))){
        return engine9;
      }
    }
    return engine9s[0];
  }

  private static void printConditions(List<Car9> carsPerDay)
  {
    System.out.println(carsPerDay.size());
    System.out.println(carsPerDay.stream().mapToDouble(Car9::getPrice).sum());
    System.out.println(carsPerDay.stream().filter(car -> !car.getExtras().isEmpty()).count());
    System.out.print("Cars with ELECTRIC engine: ");
    System.out.println(carsPerDay.stream().filter(car -> car.getExtras().size()!=0).count());
    System.out.println(carsPerDay.stream()
        .filter(car9 -> car9.getEngine().getTypeEngine() == TypeEngine.ELECTRIC).count());
    System.out.println(carsPerDay.stream()
        .filter(car9 -> car9.getEngine().getTypeEngine() == TypeEngine.GASOLINE).count());
    System.out.println(carsPerDay.stream()
        .filter(car9 -> car9.getEngine().getTypeEngine() == TypeEngine.DIESEL).count());
    System.out.println(carsPerDay.stream()
        .filter(car9 -> car9.getEngine().getTypeEngine() == TypeEngine.HYBRID).count());
    carsPerDay.forEach(System.out::println);
  }

  private static Car9 getCar(Scanner scanner,Engine9[] engine9s)
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
    String engine = scanner.nextLine().toUpperCase();
    System.out.print("price:");
    double price = Double.parseDouble(scanner.nextLine());

    Car9 car = new Car9(brand, model, typeCoupe, color, length, width, weight, getEngine(engine9s,engine), price);
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
//GASOLINE
//51321

//BMW
//516
//Combi
//blue
//214.1
//123.2
//2530.12
//DIESEL
//23540