package homework2206.exercise1;


import java.util.Scanner;

public class MyOwnAutoShop
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);

    Sedan sedan = new Sedan(132,213,"green", 6.7);
    Ford ford = new Ford(213,543,"red","1234",432);
    Ford ford2 = new Ford(432,12,"yellow","4322",542);
    Car car = new Car(231,432,"red");
    Truck truck = new Truck(213,543,"red",4321);
    Sedan sedan2 = new Sedan(132,213,"green", 6.7);

    System.out.println(sedan.getSalePrice());
    System.out.println(ford.getSalePrice());
    System.out.println(ford2.getSalePrice());
    System.out.println(car.getSalePrice());
    System.out.println(truck.getSalePrice());
    System.out.println(sedan2.getSalePrice());


  }
}
