package interfaces1906.exercise4;

public class TestAnimals
{
  public static void main(String[] args) throws NoSuchMethodException
  {
    Fish fish = new Fish();
    Cat cat = new Cat("Fluffy");
    Animal anFish = new Fish();
    Animal anSpider = new Spider();
    Pet petCat = new Cat();

//    System.out.println("6.6: ");
//    System.out.printf("Fish with name: %s",fish.getName());
//    fish.walk();
//    fish.eat();
//    fish.play();
//    System.out.println("6.7: ");
//    System.out.printf("Cat with name: %s",cat.getName());
//    cat.walk();
//    cat.eat();
//    cat.play();

    System.out.println("6.6: ");
    System.out.printf("Fish with name: %s",fish.getName());
    fish.walk();
    fish.eat();
    fish.play();
    System.out.println("6.7: ");
    System.out.printf("Cat with name: %s",cat.getName());
    cat.walk();
    cat.eat();
    cat.play();
    System.out.println("6.8: ");
    System.out.printf("The Animal is %s and its name is: %s",anFish.getClass().getSimpleName(), anFish.getClass().getMethod("getName"));
    anFish.walk();
    anFish.eat();
    System.out.println("6.9: ");
    System.out.printf("The Animal is %s and its name is: %s", anSpider.getClass().getSimpleName(),anSpider.getClass().getMethod("getName"));
    anSpider.walk();
    anSpider.eat();
    System.out.println("7.0: ");
    System.out.printf("The Pet is %s and its name is: %s", petCat.getClass().getSimpleName(),petCat.getClass().getMethod("getName"));
    Cat reserve = (Cat) petCat;
    reserve.walk();
    reserve.eat();
    reserve.play();

    System.out.println("7.1: ");
    cat.play();





  }
}
