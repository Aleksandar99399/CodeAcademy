package homework1007.phonebook;

import java.util.ArrayList;
import java.util.Scanner;

public class PhonebookTest
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    Phonebook phonebook = new Phonebook();
    System.out.println("Изберете опция:");

    while (true) {
      System.out.printf("I.Добави нов запис%n" +
          "II.Изтрий запис по номер%n" +
          "III.Покажи всички записи, които започват с низ%n" +
          "IV,Принтирай toString%n" +
          "V.Изход%n");
      int choice = Integer.parseInt(scanner.nextLine());

      if (choice == 5) {
        break;
      }
      if (choice == 0) {
        continue;
      }

      switch (choice) {
        case 1:
          System.out.print("Въведете име: ");
          String name = scanner.nextLine();
          System.out.print("Въведете телефонен номер: ");
          String number = scanner.nextLine();

          if (isValidNumber(number) && isValidName(name)) {
            number = numberFormat(number);
            /*for (String r : phonebook.getData()) {
              String[] splitR = r.split("-");
              if (splitR[1].equals(number)) {
                isPresent = true;
                System.out.println("Запис за този номер съществува вече. " +
                    "Изберете 1, за да бъде презаписан или 0, за да се върнете към началното меню.");
                int c = Integer.parseInt(scanner.nextLine());

                while (c != 0 && c != 1) {
                  System.out.println("Невалиден избор. Моля, опитайте отново:");
                  c = Integer.parseInt(scanner.nextLine());
                }

                if (c == 0) {
                  break;
                }
                phonebook.getData().remove(r);
                phonebook.insert(name, number);
                break;
              }
            }*/
           if(phonebook.insert(number, name) != null){
             System.out.println("Phone number already exists.");
             System.out.println("Enter 1 or 0");
             int num = Integer.parseInt(scanner.nextLine());
             if (num == 1){
               System.out.println("Записът се въвежда…");
               phonebook.getData().put(number,name);
             }else if(num == 0) {
               System.out.println("Изберете следваща опция");
               continue;
             }
           }

          } else {
            System.out.println("Невалидни данни, моля, изберете отново.");
            continue;
          }

          break;
        case 2:
          System.out.println("Въведете телефонния номер за който искате да бъде изтрит записът.");
          String numberToDelete = scanner.nextLine();


          if (isValidNumber(numberToDelete)) {
            /*numberToDelete = numberFormat(numberToDelete);
            for (String r : phonebook.getData()) {
              String[] splitR = r.split("-");
              if (splitR[1].equals(numberToDelete)) {
                phonebook.deleteByPhoneNumber(numberToDelete);
                exists = true;
                break;
              }
            }*/
            numberToDelete = numberFormat(numberToDelete);
            if (phonebook.getData().remove(numberToDelete,phonebook.getData().get(numberToDelete))){
              System.out.println("Запис за този номер не беше намерен.");
            }else {
              System.out.println("Записът беше успешно изтрит.");
            }
          } else {
            System.out.println("Невалидни данни, моля, изберете отново.");
            continue;
          }
          System.out.println("Изберете следваща опция");
          break;

        case 3:
          System.out.println("Въведете низ.");
          String checkString = scanner.nextLine();

          if (isValidName(checkString)) {
            System.out.printf("Всички записи, които започват с %s са следните: %n", checkString);
            ArrayList<String> checkNames = phonebook.getAllNamesStartingWith(checkString);
            for (String output : checkNames) {
              System.out.println(output);
            }
          } else {
            System.out.println("Невалидни данни, моля, изберете отново.");
            continue;
          }
          System.out.println("Изберете следваща опция");
          break;
        case 4:
          System.out.println(phonebook);
          System.out.println("Изберете следваща опция");
          break;
      }
    }
  }

  private static String numberFormat(String number)
  {
    if (number.matches("(^0)(87|88|89)([2-9])(\\d{6})")) {
      number = replacePhoneCode(number);
    }
    return number;
  }

  public static boolean isValidNumber(String number) {
    return number.matches("(^\\+|00)+(359)(87|88|89)([2-9])(\\d{6})") || number.matches("(^0)(87|88|89)([2-9])(\\d{6})");
  }

  public static boolean isValidName(String name) {
    return name.matches("[A-Za-z]+");
  }

  public static String replacePhoneCode(String number) {
    return number.replaceFirst("0", "+359");
  }
}
