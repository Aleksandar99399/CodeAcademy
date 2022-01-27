package tocastobjects1506.continueanimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Petshop
{
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    Petshop.printCatalogue();
    System.out.print("Choice your animal as enter number. Example - 2.1: ");
    String number = scanner.nextLine();

    if (number.startsWith("1.")){
      printCatalogue(new BirdCont());
      if (number.startsWith("1.1")){
        Pigeon pigeon = new Pigeon();
        pigeon.sing();
      }if (number.startsWith("1.2")){
        Parrot parrot = new Parrot();
        parrot.sing();
      }if (number.startsWith("1.3")){
        Seagull seagull = new Seagull();
        seagull.sing();
      }
    }else if (number.startsWith("2.")){
      printCatalogue(new Mammal());
      if (number.startsWith("2.1")){
        Dog dog = new Dog();
        dog.bark();
      }if (number.startsWith("2.2")){
        Cat cat = new Cat();
        cat.meow();
      }
    }

  }

  public static void printCatalogue(){
    StringBuilder builder = new StringBuilder();
    birdCatalogue(builder);
    mammalCatalogue(builder);
    System.out.println(builder.toString());
  }

  private static void birdCatalogue(StringBuilder builder){
    Class<? extends BirdCont> pigeon = Pigeon.class.asSubclass(BirdCont.class);
    Class<? extends BirdCont> parrot = Parrot.class.asSubclass(BirdCont.class);
    Class<? extends BirdCont> seagull = Seagull.class.asSubclass(BirdCont.class);
    List<?> birds = new ArrayList<>(Arrays.asList(pigeon,parrot,seagull));

    builder.append("1. Birds: \n");
    for (int i = 0; i < birds.size(); i++) {
      builder.append("1.").append(i+1+". ").append(birds.get(i).getClass().getSimpleName()).append("\n");
    }
  }

  private static void mammalCatalogue(StringBuilder builder)
  {
    Class<? extends Mammal> cat = Cat.class.asSubclass(Mammal.class);
    Class<? extends Mammal> dog = Dog.class.asSubclass(Mammal.class);
    List<?> mammals = new ArrayList<>(Arrays.asList(cat,dog));
    builder.append("2. Mammal: \n");
    for (int i = 0; i < mammals.size(); i++) {
      builder.append("1.").append(i+1+". ").append(mammals.get(i).getClass().getSimpleName()).append("\n");
    }
  }

  public static void printCatalogue(AnimalCont animalCont){
    StringBuilder builder = new StringBuilder();
    if (animalCont instanceof BirdCont){
      birdCatalogue(builder);
    }else if (animalCont instanceof Mammal){
      mammalCatalogue(builder);
    }
  }



}
