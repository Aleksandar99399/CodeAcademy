package tocompositionandinheritance1206.primechecker;

import java.util.Scanner;

import static tocompositionandinheritance1206.primechecker.PrimeChecker.checkPrime;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter N");
        int numbers = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numbers; i++){
            int value = scanner.nextInt();
            checkPrime(value);
        }
    }
}
